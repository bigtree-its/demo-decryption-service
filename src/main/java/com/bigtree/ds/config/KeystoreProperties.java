package com.bigtree.ds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class KeystoreProperties {
    
    @Value("${keystore.type}")
    private String keystoreType;

    @Value("${keystore.path}")
    private String keystorePath;

    @Value("${keystore.password}")
    private String keystorePassword;

    @Value("${keystore.alias}")
    private String keystoreAlias;
}
