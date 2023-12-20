package com.example.dailytest.demos.Service;

import com.example.dailytest.demos.Service.Impl.DesensitizationIdentityCardServiceImpl;
import com.example.dailytest.demos.Service.Impl.DesensitizationNameServiceImpl;
import com.example.dailytest.demos.Service.Impl.DesensitizationPhoneNumServiceImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/19 14:50
 */
@Component
public class DesensitizationFactory {
    private static final Map<String, DesensitizationService> map = new HashMap<>();
    static {
        map.put("IdentityCard", new DesensitizationIdentityCardServiceImpl());
        map.put("Name", new DesensitizationNameServiceImpl());
        map.put("PhoneNum", new DesensitizationPhoneNumServiceImpl());
    }
    public static DesensitizationService getStrategy(String type){
        return map.get(type);
    }
}
