package io.menino.demo.web;

import io.menino.demo.dao.FilmDao;
import io.menino.demo.dao.PersonsDao;
import io.menino.demo.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController {
    //on peut utiliser cette méthode avec autowired et component dans le servlet DataModel
    @Autowired
    PersonsDao personsDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    FilmDao filmDao;


    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M){
        M.addAttribute ("films",filmDao.findAll ());
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }

}
