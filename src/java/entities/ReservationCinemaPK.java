package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ReservationCinemaPK implements Serializable {

    @Column(name = "clientId")
    private Long client;

    @Column(name = "seanceId")
    private Long seance;

    @Column(name = "dateReservation")
    private Date dateReservation;

    public ReservationCinemaPK() {
    }

    public ReservationCinemaPK(Long client, Long seance, Date dateReservation) {
        this.client = client;
        this.seance = seance;
        this.dateReservation = dateReservation;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getSeance() {
        return seance;
    }

    public void setSeance(Long seance) {
        this.seance = seance;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationCinemaPK that = (ReservationCinemaPK) o;
        return client.equals(that.client) &&
               seance.equals(that.seance) &&
               dateReservation.equals(that.dateReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, seance, dateReservation);
    }
}
