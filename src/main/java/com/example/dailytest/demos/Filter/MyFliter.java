package com.example.dailytest.demos.Filter;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/13 10:36
 */
public class MyFliter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------进入过滤器--------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8"); // 设置响应的字符编码为UTF-8
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        System.out.println(request.getRequestURI());
        filterChain.doFilter(request,responseWrapper);
//        ContentCachingResponseWrapper wrapper =  new ContentCachingResponseWrapper(response);
//        String responseBody = new String(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
//        System.out.println(responseBody);
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseBody = new String(responseArray, responseWrapper.getCharacterEncoding());

        // 在这里，你可以获取到接口返回的数据
        System.out.println(responseBody);
        // 在这里，你可以获取到接口返回的数据，并进行修改
//        String modifiedResponseBody = modify(responseBody);
//
//        // 将修改后的数据写回到响应中
//        responseWrapper.reset();
//        responseWrapper.setCharacterEncoding("UTF-8");
//        responseWrapper.getOutputStream().write(modifiedResponseBody.getBytes(responseWrapper.getCharacterEncoding()));

        responseWrapper.copyBodyToResponse();
    }

    // 这是一个示例方法，你可以根据你的需求来实现它
    private String modify(String originalResponseBody) {
//        String jsonStr = "{\"name\":\"Alice\",\"id\":\"123\"}";

        // 解析 JSON 字符串为 JSONObject 对象
        JSONObject jsonObject = JSONObject.parseObject(originalResponseBody);
        // 检查 "name" 和 "id" 字段是否存在
        if (jsonObject.containsKey("name")) {
            jsonObject.put("name", desensitizeName(jsonObject.getString("name")));  // 进行脱敏操作
        }
        if (jsonObject.containsKey("idCard")) {
            jsonObject.put("idCard", desensitizeIdCard(jsonObject.getString("idCard")));    // 进行脱敏操作
        }

        // 将 JSONObject 对象转回为 JSON 字符串
        String desensitizedJsonStr = jsonObject.toJSONString();

        System.out.println(desensitizedJsonStr);  // 输出: {"name":"***","id":"***"}
        if(StringUtils.isNotBlank(desensitizedJsonStr))return desensitizedJsonStr;
        // 在这里实现你的修改逻辑
        //手机号码脱敏
        // 使用 Hutool 的 StrUtil 类
        String phone = StrUtil.hide("18330303030", 3, 7);
        //身份证脱敏
        String idCardNumber = "123456789012345678";
        String newIdCardNumber = StringUtils.overlay(idCardNumber, "************", 6, idCardNumber.length() - 2);
        System.out.println(newIdCardNumber); // 输出结果: "123456************78"
        //姓名脱敏
        String name = "张三";
        String newName = StringUtils.overlay(name, "*", 1, name.length());
        System.out.println(newName); // 输出结果: "张*"
        return originalResponseBody;
    }

    private  String desensitizePhoneNumber(String phoneNo) {
        if (!Strings.isNullOrEmpty(phoneNo)) {
            if (phoneNo.length() == 11){
                phoneNo = phoneNo.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
            }
        }
        return phoneNo;
    }

    private String desensitizeIdCard(String idCardNumber){
        String front = idCardNumber.substring(0, 6); // 获取前6位
        String middle = idCardNumber.substring(6, idCardNumber.length() - 2); // 获取中间的数字
        String end = idCardNumber.substring(idCardNumber.length() - 2); // 获取后2位
        String replacedMiddle = middle.replaceAll("\\d", "*"); // 将中间的数字替换为星号
        String newIdCardNumber = front + replacedMiddle + end; // 将三部分拼接起来
        System.out.println(newIdCardNumber); // 输出结果
        return newIdCardNumber;
    }

    private String desensitizeName(String name){
        String firstName = name.substring(0, 1); // 获取姓氏
        String lastName = name.substring(1); // 获取名字
        String replacedLastName = lastName.replaceAll(".", "*"); // 将名字替换为星号
        String newName = firstName + replacedLastName; // 将姓氏和替换后的名字拼接起来
        System.out.println(newName); // 输出结果
        return newName;
    }


}
