package com.example.dailytest.demos.Service;

import com.example.dailytest.demos.Service.Impl.DesensitizationIdentityCardServiceImpl;
import com.example.dailytest.demos.Service.Impl.DesensitizationNameServiceImpl;
import com.example.dailytest.demos.Service.Impl.DesensitizationPhoneNumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/19 14:50
 */
@Component
public class DesensitizationFactory {
    private final Map<String, DesensitizationService> orderHandlerMap = new HashMap<>();

    @Autowired
    public DesensitizationFactory(List<DesensitizationService> orderHandlers) {
        for (DesensitizationService orderHandler : orderHandlers) {
            // 获取处理类的类型
            String type = orderHandler.getClass().getAnnotation(Component.class).value();
            orderHandlerMap.put(type, orderHandler);
        }
    }

    public DesensitizationService getOrderHandler(String type) {
        return orderHandlerMap.get(type);
    }
//private static final Map<String, DesensitizationService> map = new HashMap<>();
//
//    @Qualifier("desensitizationIdentityCardServiceImpl" )
//    @Autowired
//    private static DesensitizationService desensitizationIdentityCardService;
//
//    @Qualifier("desensitizationNameServiceImpl" )
//    @Autowired
//    private static DesensitizationService desensitizationNameService;
//
//    @Qualifier("desensitizationPhoneNumServiceImpl" )
//    @Autowired
//    private static DesensitizationService desensitizationPhoneNumService;
//    static {
//        map.put("IdentityCard", desensitizationIdentityCardService);
//        map.put("Name", desensitizationNameService);
//        map.put("PhoneNum", desensitizationPhoneNumService);
//    }
//    public static DesensitizationService getStrategy(String type){
//        return map.get(type);
//    }
}
