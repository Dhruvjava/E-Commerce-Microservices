package org.cb.authn.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.authn.rq.Wso2TokenRq;
import org.cb.authn.rs.Wso2TokenRs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class Wso2IamClient {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Value("${iam.wso2.client-id}")
    private String clientId;
    @Value("${iam.wso2.client-secrete}")
    private String clientSecrete;
    @Value("${iam.wso2.scope}")
    private String scope;
    @Value("${iam.wso2.grant-type}")
    private String grantType;
    @Value("${iam.wso2.token_uri}")
    private String tokenUri;

    public Wso2TokenRs getAuthToekn(String username, String password) {
        Wso2TokenRq wso2TokenRq =
                        Wso2TokenRq.builder().grant_type(grantType).scope(scope).username(username)
                                        .password(password).build();
        String encodedCreds = Base64.getEncoder()
                        .encodeToString((clientId + ":" + clientSecrete).getBytes());
        log.debug("encodedCreds value : Basic " + encodedCreds);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedCreds);
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(wso2TokenRq);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Authentication data : " + payload);
        HttpEntity<String> entity = new HttpEntity<>(payload, headers);
        log.debug("Ignoring ssll certificates : ->");
        ignoreCertificates();
        log.debug("token uri: " + tokenUri);
        ResponseEntity<Wso2TokenRs> wso2TokenRs =
                        restTemplate.exchange(tokenUri, HttpMethod.POST, entity, Wso2TokenRs.class);
        Wso2TokenRs respone = null;
        if (wso2TokenRs.getStatusCode().value() == HttpStatus.OK.value()) {
            respone = wso2TokenRs.getBody();
            log.info("Response data is :\t" + wso2TokenRs.getBody());
        }
        return respone;
    }

    private void ignoreCertificates() {
        TrustManager[] trustAllCert = new TrustManager[] {new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                            throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                            throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCert, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {

        }
    }

}
