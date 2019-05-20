package io.menino.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//attention cette classe est un bean persistant(nom de classe = nom de table)
@Entity
@Table(name="persons")

public class Personne {
    private long id;
    private String nom;
    private String prenom;
    private LocalDate naissance;
    private String photoPath;
    private List<Film> listeFilms;
    private List<Role> posts = new ArrayList<> ();

    private BigInteger idtmbd;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "person")
    public List<Role> getPosts() {
        return posts;
    }

    public void setPosts(List<Role> posts) {
        this.posts = posts;
    }
    @Basic
    @Column(name = "surname", length = 60)
    public String getNom() {
        return nom;
    }

    public void setNom(String surname) {
        this.nom = surname;
    }

    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String givenname) {
        this.prenom = givenname;
    }

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = true)
    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate birthYear) {
        this.naissance = birthYear;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String imagePath) {
        this.photoPath = imagePath;
    }


    @OneToMany(mappedBy = "film_director")
    public List<Film> getListeFilms() { return listeFilms; }
    public void setListeFilms(List<Film> listeFilms) { this.listeFilms = listeFilms; }

    @Basic
    @Column(name = "idtmbd", nullable = false, length = 60)
    public BigInteger getIdtmbd() { return idtmbd; }
    public void setIdtmbd(BigInteger idtmbd) { this.idtmbd = idtmbd; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (id != personne.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }


}
