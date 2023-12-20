package com.example.dailytest.demos.Filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/15 10:15
 */
@Component
public class DesensitizationFilter implements Filter {

    static Logger logger = LoggerFactory.getLogger(DesensitizationFilter.class);
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8"); // 设置响应的字符编码为UTF-8

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        String url=request.getRequestURI();
        logger.info("DesensitizationFilter-----url:{}",url);

           //接口处理
        filterChain.doFilter(request,responseWrapper);
        //返回处理
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseBody = new String(responseArray, responseWrapper.getCharacterEncoding());
        logger.info("DesensitizationFilter Return Body-----Value:{}",responseBody);

        //业务判断
//        if(keyCodeSet.contains(url)){
            // 在这里，你可以获取到接口返回的数据，并进行修改
//            String modifiedResponseBody = modify(responseBody,paramConfigMap.get(url));
        String modifiedResponseBody="";
            // 将修改后的数据写回到响应中
            responseWrapper.reset();
            responseWrapper.setCharacterEncoding("UTF-8");
            responseWrapper.getOutputStream().write(modifiedResponseBody.getBytes(responseWrapper.getCharacterEncoding()));
            responseWrapper.copyBodyToResponse();
//        }
    }

//    private String modify(String originalResponseBody,List<ParamConfig> paramConfigList) {
////        String jsonStr = "{\"name\":\"Alice\",\"id\":\"123\"}";
////        List<String> fieldName = Arrays.asList(paramConfig.getValue1().split(";"));
////        String[] split = paramConfig.getValue1().split(";");
////         解析 JSON 字符串为 JSONObject 对象
//        JSONObject jsonObject = JSONObject.parseObject(originalResponseBody);
//        // 检查 "name" 和 "id" 字段是否存在
//        for (ParamConfig paramConfig : paramConfigList) {
//            if (jsonObject.containsKey(paramConfig.getValue1())) {
//                jsonObject.put(paramConfig.getValue1(), DesensitizationFactory.getStrategy(paramConfig.getValue2()).desensitizationField(jsonObject.getString("name")));  // 进行脱敏操作
//            }
//        }
//
//
//        // 将 JSONObject 对象转回为 JSON 字符串
//        String desensitizedJsonStr = jsonObject.toJSONString();
//
//        System.out.println(desensitizedJsonStr);  // 输出: {"name":"***","id":"***"}
//        if(StringUtils.isNotBlank(desensitizedJsonStr))return desensitizedJsonStr;
//        return originalResponseBody;
//    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
