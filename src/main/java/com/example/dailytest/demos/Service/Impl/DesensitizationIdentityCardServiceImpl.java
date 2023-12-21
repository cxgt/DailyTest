package com.example.dailytest.demos.Service.Impl;

import com.example.dailytest.demos.Service.DesensitizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/19 10:02
 */
@Component("IdentityCard")
public class DesensitizationIdentityCardServiceImpl implements DesensitizationService {
    @Override
    public String desensitizationField(String originalField) {
        return StringUtils.overlay(originalField, "************", 6, originalField.length() - 2);
    }
}
