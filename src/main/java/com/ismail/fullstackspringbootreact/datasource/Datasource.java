package com.ismail.fullstackspringbootreact.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Datasource
{

    // inject app jdbc configs
    @ConfigurationProperties("app.datasource")
    @Bean  // instantiate this data source
    public HikariDataSource hikariDatasource()
    {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
