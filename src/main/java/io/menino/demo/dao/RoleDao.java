package io.menino.demo.dao;


import io.menino.demo.model.Film;
import io.menino.demo.model.Personne;
import io.menino.demo.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    public boolean existsByNameAndFilmAndPerson(String name, Personne person, Film film);
}
