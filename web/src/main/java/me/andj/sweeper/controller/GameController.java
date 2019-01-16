package me.andj.sweeper.controller;

import me.andj.sweeper.domain.ReturnInformation;
import me.andj.sweeper.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class GameController {

    @RequestMapping(value = "/create_room",method = RequestMethod.POST)
    public ReturnInformation createRoom(HttpSession session, User user){

    }
    @RequestMapping(value = "/delete_room",method = RequestMethod.POST)
    public ReturnInformation deleteRoom(HttpSession session, User user){

    }
    @RequestMapping(value = "/room_add_in",method = RequestMethod.POST)
    public ReturnInformation login(HttpSession session, User user){

    }
    @RequestMapping(value = "/room_leave",method = RequestMethod.POST)
    public ReturnInformation login(HttpSession session, User user){

    }
    @RequestMapping(value = "/get_state",method = RequestMethod.POST)
    public ReturnInformation login(HttpSession session, User user){

    }

}
