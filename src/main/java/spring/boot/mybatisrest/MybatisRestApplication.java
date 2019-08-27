package spring.boot.mybatisrest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("spring.boot.mybatisrest.dao")
public class MybatisRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRestApplication.class, args);
    }

}
