package com.example.demoot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
        io.opentelemetry.instrumentation.spring.autoconfigure.internal.instrumentation.jdbc.JdbcInstrumentationAutoConfiguration.class
})
public class DemoOt2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOt2Application.class, args);
    }

}
