package io.menino.demo.web;

import io.menino.demo.dao.FilmDao;
import io.menino.demo.dao.GenreDao;
import io.menino.demo.dao.PersonsDao;
import io.menino.demo.dao.RoleDao;
import io.menino.demo.model.Film;
import io.menino.demo.model.Role;
import io.menino.demo.service.ImageManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "/film")
public class FilmController {
    @Autowired
    ImageManager imm;
    @Autowired
    PersonsDao personDao;
    @Autowired
    FilmDao filmDao;
    @Autowired
    GenreDao genreDao;
    @Autowired
    RoleDao roleDao;

    @GetMapping("/list")
    public String main(Model M){
        M.addAttribute ("films",filmDao.findAll ());
        return "film/list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model m, @PathVariable("id") Long id){
        m.addAttribute ("film", filmDao.findById (id).get ());
        m.addAttribute ("role", filmDao.findById (id).get ().getPosts ());
        return"film/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmDao.findById(id).get());
        model.addAttribute ("persons", personDao.findAll ());
        model.addAttribute ("genres", genreDao.findAll ());
        model.addAttribute ("roles", filmDao.findById(id).get().getPosts ());
        model.addAttribute ("role", new Role ());
        return "film/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("film", new Film ());
        model.addAttribute ("persons", personDao.findAll ());
        model.addAttribute ("genres", genreDao.findAll ());
        return "film/form";
    }

    @PostMapping("/add")
    public String submit(@RequestParam("poster") MultipartFile file, @ModelAttribute Film film){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePoster(film, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        filmDao.save(film);

        return "redirect:/film/list";
    }
    @GetMapping("/delete/{id}")
    public String supFilm(@PathVariable("id")Long id) {
        filmDao.deleteById (id);
        return "redirect:/film/list/";
    }

    @Value( "${url}" )
    private String url;
    @GetMapping("/affiche/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity (HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url+id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache ().getHeaderValue ());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }

}
