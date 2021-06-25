package com.bigtree.ds;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.bigtree.ds.config.KeystoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.flogger.Flogger;

@SpringBootApplication
@Flogger
@Configuration
public class DemoDecryptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDecryptionServiceApplication.class, args);
	}

	@Autowired
	private KeystoreProperties keystoreProperties;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info().title("Decrypt Application API")
				.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
	}

	@Bean
	public RSAPublicKey rsaPublicKey() {
		log.atInfo().log("Getting RSA Public Key");
		RSAPublicKey key = null;
		try {
			key = (RSAPublicKey) Utils.getKeyStore(keystoreProperties)
					.getCertificate(keystoreProperties.getKeystoreAlias()).getPublicKey();
			log.atInfo().log("PublicKey loaded");
		} catch (Exception e) {
			log.atSevere().withCause(e).log("Could not load public key");
		}
		return key;
	}

	@Bean
	public PrivateKey rsaPrivateKey() {
		log.atInfo().log("Getting RSA Private Key");
		PrivateKey key = null;
		try {
			key = (PrivateKey) Utils.getKeyStore(keystoreProperties).getKey(keystoreProperties.getKeystoreAlias(),
					keystoreProperties.getKeystorePassword().toCharArray());
			log.atInfo().log("PrivateKey loaded");
		} catch (Exception e) {
			log.atSevere().withCause(e).log("Could not load private key");
		}
		return key;
	}

}
