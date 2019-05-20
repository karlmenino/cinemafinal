package io.menino.demo.service;

import io.menino.demo.dao.RoleDao;
import io.menino.demo.model.Film;
import io.menino.demo.model.Personne;
import io.menino.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleManager {
    @Autowired
    RoleDao roleDao;

//    public Film findById(long id){
//        return filmDao.findById(id).get();
//    }
//    public Film findByIdtmbd(BigInteger id){
//        return filmDao.findByIdtmbd(id);
//    }
    public Role save(Role role){
        return roleDao.save(role);
    }
    public boolean existsByNameAndFilmAndPerson(String name, Personne actor, Film film){
        return roleDao.existsByNameAndFilmAndPerson(name,actor,film);
    }
}
