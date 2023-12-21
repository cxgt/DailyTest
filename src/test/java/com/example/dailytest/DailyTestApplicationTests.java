package com.example.dailytest;

import com.example.dailytest.demos.Service.DesensitizationFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DailyTestApplicationTests {
//    @Autowired
//    DesensitizationFactory desensitizationFactory;
    @Test
    void contextLoads() {
//        String desensitizationField = desensitizationFactory.getStrategy("IdentityCard").desensitizationField("321190033201234887");
//        System.out.println(desensitizationField);
    }

}
