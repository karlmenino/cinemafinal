package io.menino.demo.api;

import io.menino.demo.dao.FilmDao;
import io.menino.demo.dao.GenreDao;
import io.menino.demo.dao.RoleDao;
import io.menino.demo.model.*;
import io.menino.demo.service.FilmManager;
import io.menino.demo.service.ImageManager;
import io.menino.demo.service.PersonManager;
import io.menino.demo.service.TmdbManager;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/film")
public class FilmTmbdRestController {
    private FilmManager filmManager;
    private PersonManager personManager;
    private GenreDao genredao;
    private RoleDao roledao;
    private FilmDao filmDao;
    private TmdbManager tmdbManager;
    private ImageManager imm;

    public FilmTmbdRestController(TmdbManager tmdbManager,FilmManager filmManager, PersonManager personManager, GenreDao genredao, RoleDao roledao, FilmDao filmDao, ImageManager imm){
        assert(filmManager != null);
        this.filmManager = filmManager;
        this.personManager = personManager;
        this.genredao = genredao;
        this.roledao = roledao;
        this.filmDao = filmDao;
        this.tmdbManager = tmdbManager;
        this.imm = imm;
    }
    @PostMapping("/{title}/{page}")
    public Page<FilmTmbd> remove(@PathVariable("title") String title,@PathVariable("page") String page){
        Page<FilmTmbd> resultat = tmdbManager.findAllByTitle(title,Integer.parseInt(page)-1);
        return resultat;
    }
}
