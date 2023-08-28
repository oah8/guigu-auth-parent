package oah.project.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ServiceAuthApplication
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.28 18:50
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("oah.project.system.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
