package com.bigtree.ds.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.bigtree.ds.service.DecryptionService;
import com.bigtree.ds.service.EncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.flogger.Flogger;

@RestController
@Validated
@Flogger
public class DecryptController {

    @Autowired
    DecryptionService decryptionService;

    @Autowired
    EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<String> decrypt(
            @Parameter(description = "Authorization", required = true) @RequestHeader(value = "Authorization") @NotBlank(message = "Authorization header cannot be blank") String authorization,
            @RequestBody @Valid String plainText) {
        log.atInfo().log("Received a encryption request");
        String encryptedContent = encryptionService.encrypt(plainText);
        return ResponseEntity.ok(encryptedContent);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> encrypt(@RequestBody @Valid String encrypted) {
        log.atInfo().log("Received a decryption request");
        String plainText = decryptionService.decrypt(encrypted);
        return ResponseEntity.ok(plainText);
    }
}
