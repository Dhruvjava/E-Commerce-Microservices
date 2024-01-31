package org.cb;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cb.authn.clients.Wso2IamClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeshopifyAuthnServiceApplicationTests {

    @Autowired
    private Wso2IamClient client;

    @Test
    void contextLoads() {
    }

    @Test
    public void testWso2() throws JsonProcessingException {
        client.getAuthToekn("admin", "admin");
    }

}
