package rebotstudio.hhsturim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("rebotstudio.hhsturim.entity")
public class HhsturimApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhsturimApplication.class, args);
    }

}
