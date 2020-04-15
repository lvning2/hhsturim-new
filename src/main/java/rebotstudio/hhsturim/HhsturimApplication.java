package rebotstudio.hhsturim;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import rebotstudio.hhsturim.listener.CreateMapListener;


@SpringBootApplication
@EntityScan("rebotstudio.hhsturim.entity")
@ServletComponentScan
public class HhsturimApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(HhsturimApplication.class, args);
        //context.addApplicationListener(new CreateMapListener());


    }

}








