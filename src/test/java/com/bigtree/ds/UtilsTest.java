package com.bigtree.ds;

import java.security.KeyStore;

import com.bigtree.ds.config.KeystoreProperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    
    @Test
    public void testLoadCertificate(){
        KeystoreProperties keystoreProperties = new KeystoreProperties();
        keystoreProperties.setKeystoreType("pkcs12");
        keystoreProperties.setKeystorePassword("NOT-EASY-TO-GET");
        keystoreProperties.setKeystorePath("./keystores/keystore.pkcs12");
        KeyStore keyStore = Utils.getKeyStore(keystoreProperties);
        Assertions.assertNotNull(keyStore);
    }
}
