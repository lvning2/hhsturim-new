package rebotstudio.hhsturim.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class Test {



    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);

    }


}
