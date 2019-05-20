package io.menino.demo.service;

import io.menino.demo.dao.FilmDao;
import io.menino.demo.dao.RoleDao;
import io.menino.demo.model.Film;
import io.menino.demo.model.Role;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class FilmManager {


    private FilmDao filmDao;
    private RoleDao roleDao;

    /**
     * Constructeur utilisé par Spring pour la construction du bean
     * @ param  genre Dao le DAO qui gère le genre dans le système de persistance, ne peut être null
     */
    public FilmManager(FilmDao filmDao, RoleDao roleDao){

        this.filmDao = filmDao;
        this.roleDao = roleDao;
        assert(filmDao != null);
        assert(roleDao != null);


    }



    public Film getById(long id){
        return filmDao.findById(id).get();
    }
    public Film getByIdtmbd(BigInteger id){
        return filmDao.findByIdtmbd(id);
    }
    public boolean existsByIdtmbd(BigInteger id){return filmDao.existsByIdtmbd(id);}

    public List<Film> getAll(){
        return filmDao.findAllByOrderByTitle();
    }

    /**
     * Sauvegarder le film
     * @param film le film à créer ou modifier
     * @return l'id du film créé ou modifié
     */
    public Long save(Film film){
        filmDao.save(film);
        return film.getId();
    }
    public Film save2(Film film){
        filmDao.save(film);
        return film;
    }

    /**
     * Supprime un rôle dans un film
     * @param roleId l'identifiant du rôle à deleteByUser
     * @return l'id du film auquel appartenait le rôle
     */

    public long removeRole(long roleId){
        Role role = roleDao.findById(roleId).get();
        long filmId = role.getFilm().getId();
        Film film = filmDao.findById(filmId).get();
        film.getPosts ().remove(role);
        filmDao.save(film);
        roleDao.delete(role);
        return filmId;
    }


    /**
     * Crée un role associé à un film
     * @param filmId l'identifiant du film
     * @param Role le role à créer
     * @return l'id du film associé au rôle
     */
    public long addRole(long filmId, Role Role){
        Film film = filmDao.findById(filmId).get();
        Role.setFilm(film);
        roleDao.save(Role);
        return Role.getFilm().getId();
    }

    public long saveRole(Role Role){
        roleDao.save(Role);
        return Role.getId();
    }
    public long deleteFilm(Film film){
        filmDao.deleteById(film.getId());
        return film.getId();
    }
}
