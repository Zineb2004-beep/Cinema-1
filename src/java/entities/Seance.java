package entities;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "seances")
@NamedQueries({
    @NamedQuery(name = "Seance.findByDate", query = "FROM Seance s WHERE s.date = :date"),
    @NamedQuery(name = "Seance.findByHeure", query = "FROM Seance s WHERE s.heure = :heure"),
    @NamedQuery(name = "Seance.findBySalle", query = "FROM Seance s WHERE s.salle = :salle"),
    @NamedQuery(name = "Seance.findByFilm", query = "FROM Seance s WHERE s.film.id = :filmId")
})

@NamedNativeQuery(
        name = "Seance.findByFilmAndDate",
        query = "SELECT * FROM seances WHERE film_id = :filmId AND date = :date",
        resultClass = Seance.class
)
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Time heure;
    private String salle;
    private int places;
    @ManyToOne
    private Film film;

    public Seance() {
    }

    public Seance(Date date, Time heure, String salle, int places) {
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.places = places;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

}
