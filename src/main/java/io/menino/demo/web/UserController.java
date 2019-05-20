package io.menino.demo.web;

import io.menino.demo.dao.UserDao;
import io.menino.demo.model.User;
import io.menino.demo.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserDao userDao;
    @Autowired
    JpaUserService jpaUserService;

    @GetMapping("/connection")
    public String connect(){
        return "login";
    }
    @PostMapping("/connection")
    public String validation(@RequestParam("login") String login, @RequestParam("pass") String pass, HttpSession session){
        for (User user:userDao.findAll ())
        {
            if (user.getLogin ().equals (login)) {
                if (user.getPassword ().equals (pass)) {
                    session.setAttribute ("user", user);
                }
            }
        }
        return "redirect:/";
    }
    @GetMapping("/form")
    public String inscrit(Model model){
        model.addAttribute ("user", new User ());
        return "user/form";
    }
    @PostMapping("/form")
    public String form (@ModelAttribute User newuser) {
        jpaUserService.save(newuser);
        return "redirect:/";
    }
}
