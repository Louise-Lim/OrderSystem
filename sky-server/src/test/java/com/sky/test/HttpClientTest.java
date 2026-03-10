package com.sky.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HttpClientTest {

    /**
     * HttpClient send GET request
     */
    @Test
    public void testGet() throws Exception {
        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // create request object
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        // send request
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // Get status codde from server
        int statusCode = response.getStatusLine().getStatusCode();
        log.info("Server response status code: " + statusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        log.info("Server response body: " + body);

        // close resources
        response.close();
        httpClient.close();
    }

    /**
     * HttpClient send POST request
     */
    @Test
    public void testPost() throws Exception {
        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // create request object
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");

        StringEntity stringEntity = new StringEntity(jsonObject.toString());

        stringEntity.setContentEncoding("utf-8");
        stringEntity.setContentType("application/json");

        httpPost.setEntity(stringEntity);

        // send request
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // Get status codde from server
        int statusCode = response.getStatusLine().getStatusCode();
        log.debug("Server response status code: " + statusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        log.info("Server response body: " + body);

        // close resources
        response.close();
        httpClient.close();
    }
}
