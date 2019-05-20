package io.menino.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
    private long id;
    private String name;
    private Set<Film> listFilm ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {return name;}
    public void setName(String name) { this.name = name;}

    @ManyToMany(mappedBy = "genreFilm")
    public Set<Film> getListFilm() {
        return listFilm;
    }
    public void setListFilm(Set<Film> listFilm) {
        this.listFilm = listFilm;
    }
}
