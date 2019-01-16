package me.andj.sweeper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class demo {
    @RequestMapping("/demo")
    public String index(HttpSession session){
        if (session.isNew()) {
            System.out.println(session.getId());
        }
        else {
            System.out.println("delete session");
            session.invalidate();
        }
        return "hhh";
    }
}
