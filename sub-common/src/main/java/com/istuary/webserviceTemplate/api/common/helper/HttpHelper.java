package com.istuary.webserviceTemplate.api.common.helper;

import com.google.common.base.Strings;
import com.istuary.webserviceTemplate.api.common.entity.HttpResponseResult;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhenhua.li on 16/11/2.
 */
@Component
public class HttpHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    public String buildUrl(String url, Map<String, String> params) {

        if (null == params) {
            return url;
        }

        if (url.contains("?")) {
            url += "&";
        } else {
            url += "?";
        }
        for (Map.Entry entry : params.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        return url.substring(0, url.length() - 1);
    }

    public HttpResponseResult request(String method, String url, HashMap<String, String> params) {
        return request(method, url, null, params, null);
    }

    public HttpResponseResult request(String method, String url, HashMap<String, String> params, HashMap<String, String> headers) {
        return request(method, url, headers, params, null);
    }

    public HttpResponseResult request(String method, String url, String requestBody) {
        return request(method, url, null, null, requestBody);
    }

    public HttpResponseResult request(String method, String url) {
        return request(method, url, null, null, null);
    }

    public HttpResponseResult request(String method, String url, HashMap<String, String> headers, HashMap<String, String> params, String requestBody) {

        HttpResponseResult httpResponseResult = new HttpResponseResult();
        method = method.toUpperCase();

        // build url when GET, DELETE, etc
        if (!(method.equals("POST") || method.equals("PUT") || method.equals("PATCH"))) {
            url = this.buildUrl(url, params);
            params = null;
        }

        CloseableHttpClient client = null;
        HttpRequestBase httpRequestBase = null;
        CloseableHttpResponse response = null;
        String responseBody = "";
        try {
            if (url.toLowerCase().startsWith("https")) {
                // SSL
                client = this.createSSLClientDefault();
            } else {
                client = HttpClients.createDefault();
            }

            // get request class from method
            switch (method) {
                case "GET":
                    httpRequestBase = new HttpGet(url);
                    break;
                case "POST":
                    httpRequestBase = new HttpPost(url);
                    break;
                case "PUT":
                    httpRequestBase = new HttpPut(url);
                    break;
                case "DELETE":
                    httpRequestBase = new HttpDelete(url);
                    break;
                case "HEAD":
                    httpRequestBase = new HttpHead(url);
                    break;
                case "OPTIONS":
                    httpRequestBase = new HttpOptions(url);
                    break;
                case "PATCH":
                    httpRequestBase = new HttpPatch(url);
                    break;
                case "TRACE":
                    httpRequestBase = new HttpTrace(url);
                    break;
                default:
                    httpRequestBase = new HttpGet(url);
            }

            // add header
            if (headers != null && headers.size() > 0) {
                for (String key: headers.keySet()) {
                    httpRequestBase.setHeader(key, headers.get(key));
                }
            }

            // POST, PUT, PATCH, set request Body
            if (Objects.equals(method, "POST") || Objects.equals(method, "PUT") || Objects.equals(method, "PATCH")) {
                StringEntity entity = null;
                if(!Strings.isNullOrEmpty(requestBody)) {
                    entity = new StringEntity(requestBody, Consts.UTF_8);
                    entity.setContentType("application/json; charset=" + Consts.UTF_8);
                } else if ( params != null && params.size()>0) {
                    // set params
                    // TODO not tested
                    List<NameValuePair> list = params.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString())).collect(Collectors.toList());
                    entity = new UrlEncodedFormEntity(list, Consts.UTF_8);
                }
                ((HttpEntityEnclosingRequestBase)httpRequestBase).setEntity(entity);
            }

            response = client.execute(httpRequestBase);
            if (response.getEntity() != null) {
                responseBody = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }

            httpResponseResult.setSuccess(true);
            httpResponseResult.setHeaders(response.getAllHeaders());
            httpResponseResult.setResponseBody(responseBody);
            httpResponseResult.setStatusCode(response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            LOGGER.error("Http {} request failed with url [{}].", method, url, e);
            httpResponseResult.setSuccess(false);
            httpResponseResult.setError(e.getMessage());
            httpResponseResult.setResponseBody(e.getMessage());
            httpResponseResult.setStatusCode(500);
        } finally {
            closeHttp(client, httpRequestBase);
        }

        return httpResponseResult;
    }

    private void closeHttp(CloseableHttpClient client, HttpRequestBase httpRequestBase) {
        if (client != null) {
            try {
                if (httpRequestBase != null) {
                    httpRequestBase.releaseConnection();
                    client.close();
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void downloadPackageFile(String url, String localPath, String fileName) {
        CloseableHttpClient client = null;
        HttpGet getMethod = null;
        int BUFFER = 1024;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            getMethod = new HttpGet(url);
            response = client.execute(getMethod);
            InputStream in = response.getEntity().getContent();
            FileOutputStream out = new FileOutputStream(new File(localPath + File.separator + fileName));
            byte[] b = new byte[BUFFER];
            int len = 0;
            while((len=in.read(b))!= -1){
                out.write(b,0,len);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            LOGGER.error("HttpPost request failed with url [{}].", url, e);
        } finally {
            closeHttp(client, getMethod);
        }
    }

    public HttpResponseResult getResponseInfo(String requestMethod, String url, String requestBody) {

        HttpResponseResult responseInfo = null;

        if (requestMethod.equals("POST") || requestMethod.equals("PUT") || requestMethod.equals("PATCH")) {
            responseInfo = this.request(requestMethod, url, requestBody);
        } else {
            responseInfo = this.request(requestMethod, url);
        }

        return responseInfo;
    }

    public CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true; // NOT CHECK
                }
            }).build();

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2"},
                    null, new HostnameVerifier(){
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true; // NOT CHECK
                }
            });

            return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return  HttpClients.createDefault();
    }
}

