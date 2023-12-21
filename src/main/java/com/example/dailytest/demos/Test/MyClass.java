package com.example.dailytest.demos.Test;

import com.example.dailytest.demos.Service.DesensitizationService;
import com.example.dailytest.demos.Service.Impl.DesensitizationNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/21 14:35
 */
@Component
public class MyClass {
    static {
        System.out.println("Static block is executed when class is loaded");
    }

    @Autowired
    @Qualifier("Name")
    private DesensitizationService myDependency;

    public MyClass() {
        System.out.println("Constructor is executed when new instance is created");
        System.out.println("MyDependency is injected: " + (myDependency != null));
    }

    @PostConstruct
    public void init() {
        System.out.println("MyDependency is injected: " + (myDependency != null));
        System.out.println("@PostConstruct method is executed after constructor");
    }

    public static void staticMethod() {
        System.out.println("Static method is executed after static block");
    }
}
