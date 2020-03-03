package rebotstudio.hhsturim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${hhsturim.uploadfilepath}")
    String UPLOADFILEPATH;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//       registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/register","/login.html","/register.html")
//       .excludePathPatterns("/css/**","/js/**","/img/**","/layui/**","/imgs/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {                 //注册一个虚拟路径，用于存放上传的图片
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:"+UPLOADFILEPATH);
    }


}

