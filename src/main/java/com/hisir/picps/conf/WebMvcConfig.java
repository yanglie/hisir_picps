package com.hisir.picps.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.hisir.picps.common.ApiVersionRequestMappingHandlerMapping;



@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	@Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }
}
