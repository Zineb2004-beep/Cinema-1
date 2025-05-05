/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class ReservationCinemaPK  implements Serializable {

    @Column(name = "clientId")
    private int client;

    @Column(name = "seanceId")
    private int seance;

    @Column(name = "dateReservation")
    private Date dateReservation;

    public ReservationCinemaPK() {
    }

    public ReservationCinemaPK(int client, int seance, Date dateReservation) {
        this.client = client;
        this.seance = seance;
        this.dateReservation = dateReservation;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getSeance() {
        return seance;
    }

    public void setSeance(int seance) {
        this.seance = seance;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    
    
}
