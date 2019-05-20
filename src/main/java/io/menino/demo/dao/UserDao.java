package io.menino.demo.dao;


import io.menino.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  21/03/2017.
 *
 * @author fred
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String name);
    User findById(long id);

}
