package com.jxb.demo.multiple.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.jxb.demo.multiple.config.MybatisConfig;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jxb.demo.multiple.vosDao", sqlSessionFactoryRef = "vosFactory")
public class VosDataSourceConfig {

    @Autowired
    private MybatisConfig mybatisConfig;

    /**
     * 封装数据源对象创建, 该方法就已经将数据源的各个数据封装到该对象中
     * @return
     */
    @Bean(name = "vosDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vos") //读取的数据源前缀, 跟yml文件对应
    public DataSource vosDataSource(){
        return new DruidDataSource();
    }

    /**
     * SqlSession对象创建
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "vosFactory")
    public SqlSessionFactory vosFactory(@Qualifier("vosDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        bean.setConfiguration(configuration);
        bean.setPlugins(new Interceptor[]{mybatisConfig.paginationInterceptor()});
        return bean.getObject();
    }
}
