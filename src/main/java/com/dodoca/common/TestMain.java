package com.dodoca.common;

import com.dodoca.jedis.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class TestMain {

    private  static final Logger logger = LoggerFactory.getLogger(TimeTask.class);

    public static void main(String[] args) {
        String  aa = "http://mch.weiba896.com/user/system_message.json?offset=0";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", "uuid=ef18beae-9fc9-47df-a92b-d2df242e9fc7;shop_id=13299275;wxrrd_mch_session=eyJpdiI6InhYRFdaczliZ2VBbFhBZEZ4bThBSXc9PSIsInZhbHVlIjoicmdhUG1nZlZWeDNCSFhDUVA4MHdIenQrUXY4MXhDS1hrUjF4Mk5TZlJlRG9iQ1hHczBzSjVpaGNIZjFLWWJxUkdwWFUrTnpDMW1Fa0VzSFBTV3RLbFE9PSIsIm1hYyI6ImIwYmRjNTVjOTJmMWM2YzhkM2EzYWIzNTcwMTcwMjJmNTg2MDRmOTc4N2UzYTU2NjEzYzZmNDI0YzI3NDgyNTcifQ%3D%3D");
        HttpEntity<String> httpEntity = new HttpEntity<String>("Client", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(aa,HttpMethod.GET ,httpEntity,String.class);
        logger.info("result: " + responseEntity.getBody() );


//        String url = "http://mch.weiba896.com/design/feature.json";
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        headers.add("Cookie", "uuid=ef18beae-9fc9-47df-a92b-d2df242e9fc7;shop_id=13299275;wxrrd_mch_session=eyJpdiI6IjNUN2FnZnVXMFNLSmp6SWh5cEI4WXc9PSIsInZhbHVlIjoiSmdQaEJQRmxGcllaZCtUXC9oSkVYMU1Uc0I2V1dPQnBJZTZ1NlhkZGEwdk5aTG9nV1p2dFRBOGRiTEs0QjVIbUFJZEdtaUZsazV1Rlk3OWQ3REgxMExnPT0iLCJtYWMiOiIyYmI0NTA0OGJmNDIwMjA5Zjk5MmNjN2VkMTJkZjNlMWJjZDMzYWRhNzFkMjlhODBkMDU5MjdkM2ViYzdiOWMwIn0%3D");
//        HttpEntity<String> httpEntity = new HttpEntity<String>("Client", headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
//        String body  = responseEntity.getBody();
//        System.out.println("body :" + body);
//        logger.info("body : " + body);
    }

}
