package io.menino.demo.dao;


import io.menino.demo.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des genres des films
 */
public interface GenreDao extends CrudRepository<Genre, Long> {
    /**
     * La liste des genres par ordre alphabétique croissant
     * @return la liste de genres
     */
    public List<Genre> findAllByOrderByNameAsc();
    public Genre findByName(String name);
    public Genre findByIdOrName(long id,String name);
}
