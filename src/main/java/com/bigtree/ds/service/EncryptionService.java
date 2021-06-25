package com.bigtree.ds.service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.flogger.Flogger;

@Service
@Flogger
public class EncryptionService {

    @Value("${encryption.key.id}")
    private String encryptionKeyId;

    @Autowired(required = false)
    RSAPublicKey rsaPublicKey;

    public String encrypt(String plainText) {
        log.atInfo().log("Encrypting::START");
        String encryptedContent = null;
        try {
            JWEHeader.Builder header = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);
            header.keyID(encryptionKeyId);
            header.customParam("iat", System.currentTimeMillis());
            JWEObject encryptedObject = new JWEObject(header.build(), new Payload(plainText));
            encryptedObject.encrypt(new RSAEncrypter(rsaPublicKey));
            encryptedContent = encryptedObject.serialize();
        } catch (JOSEException e) {
            log.atSevere().withCause(e).log(e.getLocalizedMessage());
        }
        log.atInfo().log("Encrypting::END");
        return encryptedContent;
    }
}
