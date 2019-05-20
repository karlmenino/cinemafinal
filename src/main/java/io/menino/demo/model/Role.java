package io.menino.demo.model;

import javax.persistence.*;

@Entity
@Table(name="play")
public class Role {
        private Long id;
        private Personne person;
        private Film film;
        private Integer rank;
        private String name;

//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name ="person_id")
    public Personne getPerson() {return person;}

    public void setPerson(Personne person) {this.person = person;}

    @ManyToOne
    @JoinColumn(name ="film_id")
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Basic
    @Column(name = "rank", nullable = true)
    public Integer getRank() {return rank;}
    public void setRank(Integer rank) {this.rank = rank; }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}

