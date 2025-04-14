package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "films")
@NamedQueries({
    @NamedQuery(name = "findByTitre", query = "from Film where titre =:titre"),
    @NamedQuery(name  ="findByGenre", query = "from Film where genre =:genre"),
    @NamedQuery(name = "findByRealisateur", query = "from Film where realisateur =:realisateur")
})
@NamedNativeQuery(name="findSeances", query = "SELECT s.* FROM seances s inner JOIN films f on s.film_id = f.id where f.id=:id", resultClass = Seance.class)

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String realisateur;
    private double duree;
    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genre;
    @OneToMany(mappedBy = "film")
    private List<Seance> seances;

    public Film() {
    }

    public Film(String titre, String realisateur, double duree, Genre genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.duree = duree;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    

}
