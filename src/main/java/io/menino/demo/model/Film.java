package io.menino.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="films")
public class Film {
    private long id;
    private String title;
    private Double rating;
    private String image_path;
    private String summary;
    private Personne film_director;
    private LocalDate release;
    private List<Role> posts;
    private Set<Genre> genreFilm;
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

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Role> getPosts() {
        return posts;
    }

    public void setPosts(List<Role> posts) {
        this.posts = posts;
    }

    @Basic
    @Column(name = "title", length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 120)
    public String getImage_path() {
        return image_path;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }


    @Basic
    @Column(name = "summary", nullable = true, length = 80)
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date", nullable = true, length = 80)
    public LocalDate getRelease() {return release;}
    public void setRelease(LocalDate release) {this.release = release; }

    @ManyToOne
    @JoinColumn(name = "film_director")
    public Personne getFilm_director() {
        return film_director;
    }

    public void setFilm_director(Personne film_director) {
        this.film_director = film_director;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="film_genre", joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    public Set<Genre> getGenreFilm() {return genreFilm;}
    public void setGenreFilm(Set<Genre> genreFilm) {this.genreFilm = genreFilm;}

    @Basic
    @Column(name = "idtmbd")
    public BigInteger getIdtmbd() {return idtmbd;}
    public void setIdtmbd(BigInteger idtmbd) {this.idtmbd = idtmbd;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash (getId ());
    }

}
