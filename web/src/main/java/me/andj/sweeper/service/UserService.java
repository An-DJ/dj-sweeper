package me.andj.sweeper.service;

import me.andj.sweeper.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String username, String password) {
        jdbcTemplate.update("insert into user(username,password) values(?, ?)", username,password);
    }

    public boolean checkUser(String username, String password) {
        int res=
                jdbcTemplate.queryForObject("select count(*) from user where username=? and password=?", Integer.class,username,password);
        return res != 0;
    }
    public boolean hasUserName(String username){
        return jdbcTemplate.queryForObject("select count(*) from user where username=?", Integer.class,username)!=0;
    }
}
