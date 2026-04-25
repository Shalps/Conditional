package ru.netology.conditional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;


    private static final GenericContainer<?> dev = new GenericContainer<>("devapp:latest").withExposedPorts(8080);

    private static final GenericContainer<?> prod = new GenericContainer<>("sha256:462d07f6a73377e3feb9331d91d0dadbadcb0a856ca9be7179f0f3130af610a2").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        dev.start();
        prod.start();
    }

    @Test
    void contextLoads() {

    }

    @Test
    void testDevResponse() {
        Integer devMappedPort = dev.getMappedPort(8080);
        String url = "http://localhost:" + devMappedPort + "/profile";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals(200,responseEntity.getStatusCode().value());
    }
    @Test
    void testProdResponse() {
        Integer prodMappedPort = prod.getMappedPort(8081);
        String url = "http://localhost:" + prodMappedPort + "/profile";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals(200,responseEntity.getStatusCode().value());
    }
}
