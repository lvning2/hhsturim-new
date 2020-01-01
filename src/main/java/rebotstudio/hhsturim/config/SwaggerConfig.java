package rebotstudio.hhsturim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("rebotstudio.hhsturim.controller")).build();
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("zzsoft","www.www","zzsoft@163.com");

        return new ApiInfo("接口文档","系统文档","1.0","aaa",
                contact,"Apache 2.0","http://www.apache.org/license/LICENSE-2.0", new ArrayList());
    }


}
