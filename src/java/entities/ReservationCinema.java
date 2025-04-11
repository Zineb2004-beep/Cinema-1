
package entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "reservationsCinema")
public class ReservationCinema {

    @EmbeddedId
    private ReservationCinemaPK pK;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable = false)
    private Date dateReservation;

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "seanceId", referencedColumnName = "id", insertable = false, updatable = false)
    private Seance seance;

    public ReservationCinema() {
    }

    public ReservationCinema(ReservationCinemaPK pK, Date dateReservation, Client client, Seance seance) {
        this.pK = pK;
        this.dateReservation = dateReservation;
        this.client = client;
        this.seance = seance;
    }

    public ReservationCinemaPK getpK() {
        return pK;
    }

    public void setpK(ReservationCinemaPK pK) {
        this.pK = pK;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

   

}
