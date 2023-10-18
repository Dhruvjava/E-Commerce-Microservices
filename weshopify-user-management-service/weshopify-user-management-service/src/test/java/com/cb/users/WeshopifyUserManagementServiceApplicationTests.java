package com.cb.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@MockitoSettings
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeshopifyUserManagementServiceApplicationTests {

    @Test
    @DisplayName("CONTEXT_LOAD")
    void contextLoads() {
    }

}
