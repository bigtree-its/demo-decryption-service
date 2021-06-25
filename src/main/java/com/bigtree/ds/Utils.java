package com.bigtree.ds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import com.bigtree.ds.config.KeystoreProperties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import lombok.extern.flogger.Flogger;

@Flogger
public class Utils {

    public static KeyStore getKeyStore(KeystoreProperties keystoreProperties) {
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(keystoreProperties.getKeystoreType());
            File resource = ResourceUtils.getFile("classpath:"+ keystoreProperties.getKeystorePath());
            FileInputStream fis = new FileInputStream(resource);
            // InputStream fis = resource.getInputStream();
            log.atInfo().log("certificate loaded from path " + keystoreProperties.getKeystorePath());
            keyStore.load(fis, keystoreProperties.getKeystorePassword().toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
            e.printStackTrace();
        }
        return keyStore;
    }
}
