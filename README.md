# demo-decryption-service
A Springboot Microservice to expose REST endpoints to Encrypt and Decrypt given Payload using Self-Signed Crypto Certificates

### Generate Private Key and Public Key in PEM format

```jsx
openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
```

### Convert PEM file into PKCS12

```jsx
openssl pkcs12 -export -inkey key.pem -in certificate.pem -out keystore.pkcs12
```

### Convert PEM file into PKCS12 with Certificate Alias

```jsx
openssl pkcs12 -export -inkey key.pem -in certificate.pem -out keystore.pkcs12 -name my-alias
```
