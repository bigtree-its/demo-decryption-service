package com.bigtree.ds.service;

import java.security.PrivateKey;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.flogger.Flogger;

@Service
@Flogger
public class DecryptionService {

    @Autowired(required = false)
    PrivateKey rsaPrivateKey;

    public String decrypt(String encryptedPayload) {
        log.atInfo().log("Decrypting::START");
        String decryptedContent = null;
        try {
            JWEObject decryptedObject = JWEObject.parse(encryptedPayload);
            decryptedObject.decrypt(new RSADecrypter(rsaPrivateKey));
            decryptedContent = decryptedObject.getPayload().toString();
        } catch (ParseException e) {
            log.atSevere().withCause(e).log(e.getLocalizedMessage());
        } catch (JOSEException e) {
            log.atSevere().withCause(e).log(e.getLocalizedMessage());
        }
        log.atInfo().log("Decrypting::END");
        return decryptedContent;
    }
}
