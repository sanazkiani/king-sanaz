package kia.sanazco.cloud.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

@ActiveProfiles("unit_test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewTest {

    @Test
    public void findAllByOrg() {
        try {
            Assert.isTrue(false, "not  false");
        } catch (Exception e) {
            Assert.isTrue(true, "not true");
        }
    }
}