package com.ordermanagementservice.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SleuthConfig {

    //Collects data from sleuth and gives to zipkin server
    @Bean
    public Sampler samplerObj(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
