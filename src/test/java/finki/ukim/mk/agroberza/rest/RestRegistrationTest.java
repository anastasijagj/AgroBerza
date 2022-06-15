package finki.ukim.mk.agroberza.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RestRegistrationTest {
    public static String registrationUrl;
    private String responseBody;
    private RestTemplate restTemplate;
    public HttpHeaders headers;
    public static String jsonBody;
    public static HttpEntity<String> entity;

    @BeforeTest
    public void beforeClass() {
        restTemplate = new RestTemplate();

    }

    @Test
    public void registerUserTest() throws IOException {
        registrationUrl = "http://localhost:9000/api/registration";

        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"username\":\"ATtestUser\",\n" +
                "  \"password\":\"user\",\n" +
                "  \"name\":\"ATUser\",\n" +
                "  \"surname\":\"test\",\n" +
                "  \"userCategory\":\"MERCHANT\"\n" +
                "}";
        entity = new HttpEntity<String>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(registrationUrl, entity, String.class);
        responseBody = response.getBody();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);


    }
}
