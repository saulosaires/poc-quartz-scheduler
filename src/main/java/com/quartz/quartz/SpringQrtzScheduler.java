package com.quartz.quartz;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class SpringQrtzScheduler {

    /**
    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create().build();
    }
    **/
}