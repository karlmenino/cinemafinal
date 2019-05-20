package io.menino.demo.service;

import io.menino.demo.dao.GroupDao;
import io.menino.demo.dao.UserDao;
import io.menino.demo.model.Groups;
import io.menino.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  21/03/2017.
 *
 * @author fred
 */
@Service
public class JpaUserService {
    private UserDao userDao;
    private GroupDao groupDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Autowired
    public void setGroupDao(GroupDao groupDao){
        this.groupDao = groupDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HashSet<Groups> groupes = new HashSet ();
        groupes.add (groupDao.getOne (Long.parseLong ("2")));
        user.setGroups(groupes);
        userDao.save(user);
    }

    public User findByUserName(String userName){ return userDao.findByLogin (userName); }

    public Optional<User> findById(Long id){ return userDao.findById (id); }

    public List<User> listeUser(){
       List<User> liste= userDao.findAll();
        return liste;
    }
}
