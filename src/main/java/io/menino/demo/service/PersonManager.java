package io.menino.demo.service;

import io.menino.demo.dao.PersonsDao;
import io.menino.demo.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class PersonManager {
    @Autowired
    PersonsDao personDao;

    public List<Personne> getAll(){
        return personDao.findAllByOrderByNom ();
    }
    public Personne findById(long id){
        return personDao.findById(id).get();
    }
    public Personne findByIdtmbd(BigInteger id){
        return personDao.findByIdtmbd(id);
    }
    public Personne save(Personne person){
        return personDao.save(person);
    }
    public boolean existsByIdtmbd(BigInteger id){return personDao.existsByIdtmbd(id);}
}
