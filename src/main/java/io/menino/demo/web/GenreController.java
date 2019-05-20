package io.menino.demo.web;

import io.menino.demo.dao.GenreDao;
import io.menino.demo.model.Film;
import io.menino.demo.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/genre")
public class GenreController {
    @Autowired
    GenreDao genreDao;

    @GetMapping("/list")
    public String main(Model M){
        M.addAttribute ("genres",genreDao.findAll ());
        return "genre/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("genre", new Genre ());
        return "genre/form";
    }

    @PostMapping("/add")
    public String submit( @ModelAttribute Genre genre){
        genreDao.save (genre);
        return "redirect:/genre/list";
    }
    @GetMapping("/delete/{id}")
    public String supFilm(@PathVariable("id")Long id) {
        Genre test = genreDao.findById (id).get ();
        for (Film film:test.getListFilm ()){
            film.getGenreFilm ().remove(test);
        }
        genreDao.delete (test);
        return "redirect:/genre/list/";
    }


}
