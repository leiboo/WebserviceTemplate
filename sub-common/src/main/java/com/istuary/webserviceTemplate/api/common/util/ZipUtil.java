package com.istuary.webserviceTemplate.api.common.util;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ZipUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);


    public static void decompressFile(String path, String fileName) {
        File inputFile = new File(path + File.separator + fileName);

        try {
            final InputStream is = new FileInputStream(inputFile);
            final TarArchiveInputStream debInputStream = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", is);
            TarArchiveEntry tarentry = null;
            while ((tarentry = (TarArchiveEntry) debInputStream.getNextEntry()) != null) {
                final File outputFile = new File(new File(path), tarentry.getName());

                if (tarentry.isDirectory()) {
                    File tarfile = new File(path + "/" + tarentry.getName());
                    tarfile.mkdir();

                } else {
                    final OutputStream outputFileStream = new FileOutputStream(outputFile);
                    IOUtils.copy(debInputStream, outputFileStream);
                    outputFileStream.close();
                }
            }
            debInputStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
