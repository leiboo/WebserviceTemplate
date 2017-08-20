package com.istuary.webserviceTemplate.api.common.entity;

import org.apache.http.Header;

/**
 * Created by zhenhua.li on 16/11/2.
 */
public class HttpResponseResult {

    private boolean isSuccess;
    private String responseBody;
    private String error;
    private Integer statusCode;
    private Header[] headers;

    public HttpResponseResult() {
        this.isSuccess = false;
    }

    public HttpResponseResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }
}
