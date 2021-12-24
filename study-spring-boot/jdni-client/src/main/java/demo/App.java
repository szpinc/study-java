package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author 孙志鹏
 * @since 2021/12/23 4:14 PM
 */
@SpringBootApplication
@Slf4j
public class App {

    public static void main(String[] args) throws NamingException {
        SpringApplication.run(App.class, args);
        log.info("");
    }
}
