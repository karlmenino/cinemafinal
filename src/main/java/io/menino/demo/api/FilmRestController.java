//package io.menino.demo.api;
//
//import io.menino.demo.dao.FilmDao;
//import io.menino.demo.dao.GenreDao;
//import io.menino.demo.dao.RoleDao;
//import io.menino.demo.model.*;
//import io.menino.demo.service.FilmManager;
//import io.menino.demo.service.ImageManager;
//import io.menino.demo.service.PersonManager;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api/film")
//public class FilmRestController {
//    private FilmManager filmManager;
//    private PersonManager personManager;
//    private GenreDao genredao;
//    private RoleDao roledao;
//    private FilmDao filmDao;
//    ImageManager imm;
//
//    public FilmRestController(FilmManager filmManager,PersonManager personManager,GenreDao genredao,RoleDao roledao,FilmDao filmDao,ImageManager imm){
//        assert(filmManager != null);
//        this.filmManager = filmManager;
//        this.personManager = personManager;
//        this.genredao = genredao;
//        this.roledao = roledao;
//        this.filmDao = filmDao;
//        this.imm = imm;
//    }
//
//
//    @GetMapping("")
//    public List<Film> getAll() {
//        return filmManager.getAll();
//    }
//    @GetMapping("/{id}")
//    public Film getById(@PathVariable("id")long id){
//        return filmManager.getById(id);
//    }
//
//    @PostMapping("")
//    public Film addfilm(@RequestBody ContainerJsonForFilm container){
//        Film film = container.film;
//        Person director = personManager.findById(container.director);
//        film.setDirector(director);
//        Set<Genre> lesgenres = new HashSet<>();
//        for (long genre_id:container.genres
//             ) {
//            Genre genre = genredao.findById(genre_id).get();
//          lesgenres.add(genre);
//        }
//        film.setGenres(lesgenres);
//        film = filmManager.save2(film);
//        return film;
//    }
//    @PostMapping("/mod")
//    public Play modRole(@RequestBody Container2 container2){
//        Long id = container2.id;
//        Play play = roledao.findById(id).get();
//        play.setName(container2.name);
//        play.setRank(container2.rank);
//        Person acteur = personManager.findById(container2.acteur);
//        play.setActor(acteur);
//        Film film = filmManager.getById(container2.film);
//        play.setFilm(film);
//        play = roledao.save(play);
//        return play;
//    }
//    @PostMapping("/add")
//    public Play addRole(@RequestBody Container2 container2){
//        Play play = new Play();
//        play.setName(container2.name);
//        play.setRank(container2.rank);
//        Person acteur = personManager.findById(container2.acteur);
//        play.setActor(acteur);
//        Film film = filmManager.getById(container2.film);
//        play.setFilm(film);
//        play = roledao.save(play);
//        return play;
//    }
//    @PostMapping("/addimage/{id}")
//    public Film addimage(@PathVariable("id")long id,@RequestBody MultipartFile file){
//        Film film = filmManager.getById(id);
//        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try {
//                imm.savePoster(film, file.getInputStream());
//            } catch (IOException ioe){
//                System.out.println("Erreur lecture : "+ioe.getMessage());
//            }
//        }
//        return filmManager.save2(film);
//    }
//    @DeleteMapping("/{id}")
//    public Film remove(@PathVariable("id") long id){
//        Film film = filmDao.findById(id).get();
//        filmDao.delete(film);
//        return film;
//    }
//}
