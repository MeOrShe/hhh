package com.liqian.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/*
* 验证码配置类
* */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer(){
        Properties properties = new Properties();
        //边框
        properties.put("kaptcha.border","no");
        //颜色
        properties.put("kaptcha.textproducer.font.color","black");
        //space
        properties.put("kaptcha.textproducer.char.space","5");
        //把设置的具体内容放到Config中
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
