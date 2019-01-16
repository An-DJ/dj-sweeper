package me.andj.sweeper.controller;

import me.andj.sweeper.domain.ReturnInformation;
import me.andj.sweeper.domain.User;
import me.andj.sweeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnInformation login(HttpSession session, User user){
        if (userService.checkUser(user.getUsername(),user.getPassword())) {
            return ReturnInformation.ReturnInformationBuilder.get104();
        }
        else {
            System.out.println("log in failed");
            return ReturnInformation.ReturnInformationBuilder.get103();
        }
    }
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public ReturnInformation logup(User user){
        if(userService.hasUserName(user.getUsername()))
            return ReturnInformation.ReturnInformationBuilder.get101();
        else {
            userService.create(user.getUsername(),user.getPassword());
            return ReturnInformation.ReturnInformationBuilder.get102();
        }
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public ReturnInformation logout(HttpSession session){
        if(session.isNew()) {session.invalidate();return ReturnInformation.ReturnInformationBuilder.get105();}
        else {session.invalidate();return ReturnInformation.ReturnInformationBuilder.get106();}

    }
}
