package pl.urban.houseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HouseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseServiceApplication.class, args);
    }

}
