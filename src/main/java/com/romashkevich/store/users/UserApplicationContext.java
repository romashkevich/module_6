package com.romashkevich.store.users;

import com.romashkevich.store.users.dao.UserDaoJdbcImpl;
import com.romashkevich.store.users.dbServiceUsers.DbConnectUser;
import com.romashkevich.store.users.service.ServiceUserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class UserApplicationContext {
    @Bean
    public DbConnectUser dbConnectUser(){
        return new DbConnectUser();
    }
    @Bean
    public UserDaoJdbcImpl userDaoJdbc(){
        UserDaoJdbcImpl bean = new UserDaoJdbcImpl();
        bean.setDbConnectUser(dbConnectUser());
        return bean;
    }
    @Bean
    public ServiceUserImpl serviceUser(){
        ServiceUserImpl bean = new ServiceUserImpl();
        bean.setUserDao(userDaoJdbc());
        return bean;
    }
}
