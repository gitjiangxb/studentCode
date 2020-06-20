package batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("nxcloudDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.nxcloud")
    public DataSource nxcloudDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("financeDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.finance")
    public DataSource financeDatasource() {
        return DataSourceBuilder.create().build();
    }
}
