package com.example.dailytest.demos.Controller;

import com.alibaba.fastjson2.JSON;
import com.example.dailytest.demos.Entity.User;
import com.example.dailytest.demos.Service.DesensitizationFactory;
import com.example.dailytest.demos.Utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/13 10:24
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final DesensitizationFactory desensitizationFactory;

    @Autowired
    public HelloController(DesensitizationFactory desensitizationFactory) {
        this.desensitizationFactory = desensitizationFactory;
    }
    @GetMapping("/say")
    private void hello(){
        String json ="{\n" +
                "                     \"timeoutMarkerName\": \"否\",\n" +
                "                     \"orderId\": \"2312210018291075\",\n" +
                "                     \"rightClass\": \"2\",\n" +
                "                     \"postAddress\": null,\n" +
                "                     \"orderFee\": \"0\",\n" +
                "                     \"remark\": \"\",\n" +
                "                     \"groupPsptTypeCode\": \"\",\n" +
                "                     \"custPhone\": \"\",\n" +
                "                     \"mainTagCode\": \"0\",\n" +
                "                     \"superManagerId\": \"\",\n" +
                "                     \"orderState\": \"失败\",\n" +
                "                     \"grabSuppermanId\": \"\",\n" +
                "                     \"workFlow\": \"low_consumer_product_change\",\n" +
                "                     \"payMoney\": \"\",\n" +
                "                     \"custCertId\": \"412727198002255748\",\n" +
                "                     \"orderTime\": \"0天0小时30分\",\n" +
                "                     \"appointmentTime\": null,\n" +
                "                     \"processId\": \"2023122109383597770462552900101641621939531581141511778457477047\",\n" +
                "                     \"grabStaffId\": \"\",\n" +
                "                     \"inModeCode\": \"9030\",\n" +
                "                     \"provOrderId\": \"\",\n" +
                "                     \"createDate\": \"2023-12-21 09:38:34\",\n" +
                "                     \"custCertType\": \"身份证\",\n" +
                "                     \"offerName\": \"5G加油包29元10GB首月优惠版-集团互联网渠道\",\n" +
                "                     \"serialNumber\": \"15649981051\",\n" +
                "                     \"currentStepName\": \"处理中\",\n" +
                "                     \"postType\": \"\",\n" +
                "                     \"count\": null,\n" +
                "                     \"mainTag\": \"主\",\n" +
                "                     \"sceneTypeCode\": \"91\",\n" +
                "                     \"custName\": \"杨玉灵\",\n" +
                "                     \"userTpye\": \"新用户\",\n" +
                "                     \"currentStep\": \"lowCostChange\",\n" +
                "                     \"isrefuse\": \"无\",\n" +
                "                     \"extCreateDate\": \"2023-12-21 09:38:34\",\n" +
                "                     \"groupPsptId\": null,\n" +
                "                     \"sceneType\": \"无\",\n" +
                "                     \"groupCustName\": null,\n" +
                "                     \"inMode\": \"无\",\n" +
                "                     \"refuseRemark\": \"\",\n" +
                "                     \"orderLineId\": \"2312210018014539\",\n" +
                "                     \"payTag\": \"\"\n" +
                "              }";
//        JSONObject jsonObject=JSONObject.parseObject(json);
        Map<String,Object> mapType = JSON.parseObject(json,Map.class);
        System.out.println(mapType.toString());
//循环数据判断是否存在需要脱敏的字段 存在则进行脱敏然后重新赋值
        for (String paramKey : mapType.keySet()) {
            if("serialNumber".contains(paramKey)){
                mapType.put(paramKey, desensitizationFactory.getOrderHandler("PhoneNum").desensitizationField((String) mapType.get(paramKey)));  // 进行脱敏操作
            }
            if("custPhone".contains(paramKey)){
                mapType.put(paramKey, desensitizationFactory.getOrderHandler("PhoneNum").desensitizationField((String) mapType.get(paramKey)));  // 进行脱敏操作
            }
            if("custName".contains(paramKey)){
                mapType.put(paramKey, desensitizationFactory.getOrderHandler("Name").desensitizationField((String) mapType.get(paramKey)));  // 进行脱敏操作
            }
            if("custCertId".contains(paramKey)){
                mapType.put(paramKey, desensitizationFactory.getOrderHandler("IdentityCard").desensitizationField((String) mapType.get(paramKey)));  // 进行脱敏操作
            }
        }
        System.out.println(mapType.toString());
    }


    private void modify(Map<String,Object>  dataList, List<ParamConfig> paramConfigList) {
        Map<String,String> paramConfigMap = paramConfigList.stream().collect(Collectors.toMap(ParamConfig::getValue1,ParamConfig::getValue2));

        Map<String,Object> rspMap = (Map<String, Object>) dataList.get("response");
        String rspCode = (String) rspMap.get("rspCode");
        if(rspCode.equals("0")){
            //取出返回的数据
            List<Map<String,Object>> rspData = (List<Map<String, Object>>) rspMap.get("rspData");
            //循环数据判断是否存在需要脱敏的字段 存在则进行脱敏然后重新赋值
            for (Map<String, Object> dateMap : rspData) {
                for (String paramKey : paramConfigMap.keySet()) {
                    if(dateMap.keySet().contains(paramKey)){
                        //证件号码添加身份证的条件
                        if("custCertId".equals(paramKey) && "身份证".equals(MapUtil.getString(dateMap,"custCertType"))){
                            dateMap.put(paramKey, desensitizationFactory.getOrderHandler(paramConfigMap.get(paramKey))
                                    .desensitizationField(MapUtil.getString(dateMap,paramKey)));
                        }else {
                            // 进行脱敏操作
                            dateMap.put(paramKey, desensitizationFactory.getOrderHandler(paramConfigMap.get(paramKey))
                                    .desensitizationField(MapUtil.getString(dateMap,paramKey)));
                        }
                    }
                }
            }
        }
    }
}
