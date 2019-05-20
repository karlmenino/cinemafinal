package io.menino.demo.dao;

import io.menino.demo.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  21/03/2017.
 *
 * @author fred
 */
public interface GroupDao extends JpaRepository<Groups, Long> {
    @Override
    Groups getOne(Long aLong);
}
