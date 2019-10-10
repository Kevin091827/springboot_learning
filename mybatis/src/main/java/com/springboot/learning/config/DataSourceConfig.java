package com.springboot.learning.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:DataSourceConfig
 * @Description: TODO
 */
@Configuration
@Slf4j
public class DataSourceConfig {


    /**
     * 配置数据源
     * @return
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        log.info("HikariDataSource数据源配置");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
