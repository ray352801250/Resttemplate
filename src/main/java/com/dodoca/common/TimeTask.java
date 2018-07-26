package com.dodoca.common;

import com.alibaba.fastjson.JSONObject;
import com.dodoca.jedis.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Set;


@Component
@RestController
public class TimeTask {
    private  static final Logger logger = LoggerFactory.getLogger(TimeTask.class);
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${shop_key}")
    private String shoopKey;

    /**
     * 字段　　允许值　　允许的特殊字符
     * 秒     　 0-59 　　　　, - * /
     * 分     　 0-59　　　　 , - * /
     * 小时      0-23 　　　　, - * /
     * 日期      1-31 　　　　, - * ? / L W C
     * 月份      1-12 　　　　, - * /
     * 星期      1-7 　　　　  , - * ? / L C #
     * 年     1970-2099 　　, - * /
     *
     *每天半夜12点30分执行一次：0 30 0 * * ? （注意日期域为0不是24）
     *          每天凌晨1点执行一次：0 0 1 * * ?
     *          每天上午10：15执行一次： 0 15 10 ? * * 或 0 15 10 * * ? 或 0 15 10 * * ? *
     *          每天中午十二点执行一次：0 0 12 * * ?
     *          每天14点到14：59分，每1分钟执行一次：0 * 14 * * ?
     *          每天14点到14：05分，每1分钟执行一次：0 0-5 14 * * ?
     *          每天14点到14：55分，每5分钟执行一次：0 0/5 14 * * ?
     *          每天14点到14：55分，和18点到18点55分，每5分钟执行一次：0 0/5 14,18 * * ?
     *          每天18点执行一次：0 0 18 * * ?
     *          每天18点、22点执行一次：0 0 18,22 * * ?
     *          每天7点到23点，每整点执行一次：0 0 7-23 * * ?
     *          每个整点执行一次：0 0 0/1 * * ?
     *          每30 秒执行一次"0/30 * * * * ?
     */
    @Scheduled(cron = "${time_interval}")
    public void updateRedis(){
        logger.info("开始查询redis");
//        String  shopid = "shoop10001";
//        String  aa = "https://shop13323333.wxrrd.com/design/feature.json?t=1532582476836";
//        String bb = "https://shop13323333.wxrrd.com/goods.json?limit=18&offset=0&goods_tag_id=762020&column=created_at&t=1532582478212";
//        jedisClient.hset(shopid, aa,"{test:123456}");
//        jedisClient.hset(shopid, bb,"{test2:6789}");
        Set<String> set = jedisClient.hKeys(shoopKey);
        logger.info(set.toString());
//        String  aa = "https://shop13323333.wxrrd.com/design/feature.json?t=1532582476836";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        headers.setContentType(MediaType.APPLICATION_JSON);
////        headers.add("Cookie", "uuid=ef18beae-9fc9-47df-a92b-d2df242e9fc7;shop_id=13299275;wxrrd_mch_session=eyJpdiI6InhYRFdaczliZ2VBbFhBZEZ4bThBSXc9PSIsInZhbHVlIjoicmdhUG1nZlZWeDNCSFhDUVA4MHdIenQrUXY4MXhDS1hrUjF4Mk5TZlJlRG9iQ1hHczBzSjVpaGNIZjFLWWJxUkdwWFUrTnpDMW1Fa0VzSFBTV3RLbFE9PSIsIm1hYyI6ImIwYmRjNTVjOTJmMWM2YzhkM2EzYWIzNTcwMTcwMjJmNTg2MDRmOTc4N2UzYTU2NjEzYzZmNDI0YzI3NDgyNTcifQ%3D%3D");
//        HttpEntity<String> httpEntity = new HttpEntity<String>("Client", headers);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(aa,HttpMethod.GET ,httpEntity,String.class);
//        String result = responseEntity.getBody();
//        logger.info("result: " + result);
        if(set != null && set.size() > 0){
            logger.info("查询到" + set.size() + "条记录,开始更新");
            for (String key : set) {
                String  rest1 = jedisClient.hget(shoopKey, key);
                logger.info("oldValue : " + rest1);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> httpEntity = new HttpEntity<String>("Client", headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(key, HttpMethod.GET , httpEntity, String.class);
                String result = responseEntity.getBody();
                logger.info("result: " + result);
                jedisClient.hset( shoopKey , key, result);
            }
        }
        logger.info("记录更新完毕");
    }
}
