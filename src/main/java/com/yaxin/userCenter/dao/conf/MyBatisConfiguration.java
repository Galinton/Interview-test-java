package com.yaxin.userCenter.dao.conf;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * 获取数据库的连接信息，在application.properties中配置，时间有限，用的比较原始的实现方法，其实应该用pool方式
 */
@Configuration
public class MyBatisConfiguration {

    @Autowired
    CustomDataSourceProperties properties;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    SqlSessionTemplate sessionTemplate;


    @Bean
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            DataSourceBuilder factory = DataSourceBuilder
                    .create(this.properties.getClassLoader())
                    .driverClassName(this.properties.getDriverClassName())
                    .url(this.properties.getUrl())
                    .username(this.properties.getUsername())
                    .password(this.properties.getPassword());
            bean.setDataSource(factory.build());
            PathMatchingResourcePatternResolver p = new PathMatchingResourcePatternResolver();
            Resource[] resources = p.getResources("classpath*:/mapper/*.xml");
            bean.setMapperLocations(resources);
            sqlSessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return sqlSessionFactory;
    }
}
