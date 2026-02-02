package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    /**
     * API documentation generated using Knife4j
     * @return
     */
    @Bean
    public Docket docketUser() {
        log.info("Preparing to generate API documentation...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Project API Documentation")
                .version("2.0")
                .description("Project API Documentation")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Amin API")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * API documentation generated using Knife4j
     * @return
     */
    @Bean
    public Docket docketAdmin() {
        log.info("Preparing to generate API documentation...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Project API Documentation")
                .version("2.0")
                .description("Project API Documentation")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("User API")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * Configure static resource mapping
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Starting static resource mapping...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:C:/Users/ASUS/Downloads/Ordering/sky-take-out/sky-server/src/main/resources/upload/");
    }

    /**
     * Extend the Spring MVC framework's message converters to uniformly format date types
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("Extend the Spring MVC framework's message converters...");
        // Create object message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Configure an object converter for object message converter
        // It is responsible for serializing Java objects into JSON data.
        converter.setObjectMapper(new JacksonObjectMapper());

        converters.add(0, converter);
    }
}
