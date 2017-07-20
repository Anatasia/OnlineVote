package com.xj.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by xujuan1 on 2017/7/19.
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestRedis {
    private Logger logger = Logger.getLogger(TestRedis.class);
    @Test
    public void testRedisConnect(){
        Jedis jedis = new Jedis("localhost");
        logger.info("连接成功");
        logger.info("服务正在运行："+jedis.ping());
    }
}
