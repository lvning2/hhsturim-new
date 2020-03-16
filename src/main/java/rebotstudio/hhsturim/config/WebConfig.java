package rebotstudio.hhsturim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rebotstudio.hhsturim.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${hhsturim.uploadfilepath}")
    String UPLOADFILEPATH;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

       registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
               .excludePathPatterns("/login","/logout","/register","/login.html","/register.html")
               .excludePathPatterns("/main.html","/info.html","/place/**")
               .addPathPatterns("/myList.html")
               .excludePathPatterns("/css/**","/js/**","/img/**","/layui/**","/imgs/**","/img/**")
               .excludePathPatterns("/swagger-resources/**","/v2/**","/webjars/**","/swagger-ui.html/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {                 //注册一个虚拟路径，用于存放上传的图片
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:"+UPLOADFILEPATH);
    }


}

