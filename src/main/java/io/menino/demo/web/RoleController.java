package io.menino.demo.web;

import io.menino.demo.dao.RoleDao;
import io.menino.demo.model.Film;
import io.menino.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    RoleDao roleDao;

    @PostMapping("/add")
    public String submit( @ModelAttribute Role role){
        roleDao.save(role);
        return "redirect:/film/mod/"+role.getFilm ().getId ();
    }
    @GetMapping("/delete/{id}")
    public String delete( @PathVariable Long id){
        Film film = roleDao.findById (id).get ().getFilm ();
        roleDao.deleteById (id);
        return "redirect:/film/mod/"+film.getId ();
    }
}
