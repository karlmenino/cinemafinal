package io.menino.demo.web;

import io.menino.demo.model.User;
import io.menino.demo.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminControler {

    @Autowired
    JpaUserService jpaUserService;

    @GetMapping("/")
    @PreAuthorize ("hasAuthority('ADMIN')")
    public String index() {
        return "admin/index";
    }
    @GetMapping("/list")
    public String listuser(Model model) {
        model.addAttribute("listuser", jpaUserService.listeUser());
        return "admin/list";
    }
    @GetMapping("/pass/{id}")
    public String changePass(@PathVariable("id") String login, Model model) {
        User user = jpaUserService.findByUserName (login);
        model.addAttribute("util",user);
        return "admin/pass";
    }
    @PostMapping("/pass")
    public String changePass(@RequestParam("nmdp") String nmdp, @RequestParam("vmdp") String vmdp, @RequestParam("nom") String nom) {
        User user =jpaUserService.findByUserName (nom) ;
        if (nmdp.equals (vmdp)) {
            user.setPassword (nmdp);
            jpaUserService.save (user);
        }
        return "redirect:/admin/list";
    }
    }
