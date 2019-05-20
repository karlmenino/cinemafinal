package io.menino.demo.dao;

import io.menino.demo.model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface FilmDao extends CrudRepository<Film, Long> {
    public List<Film> findAllByOrderByTitle();
    public Film findByIdtmbd(BigInteger id);
    public boolean existsByIdtmbd(BigInteger id);
}
