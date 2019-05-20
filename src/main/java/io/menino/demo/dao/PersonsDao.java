package io.menino.demo.dao;

import io.menino.demo.model.Film;
import io.menino.demo.model.Personne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PersonsDao extends CrudRepository<Personne, Long> {
    public Personne findByPhotoPath(String img);
    public List<Personne> findAllByOrderByNom();
    public Personne findByIdtmbd(BigInteger id);
    public boolean existsByIdtmbd(BigInteger id);
}
