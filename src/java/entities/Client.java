/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author zineb
 */

@Entity

@NamedQueries({
    @NamedQuery(name = "findByName", query = "FROM Client c WHERE c.nom LIKE :name"),
    @NamedQuery(name = "Client.findByEmail", query = "FROM Client c WHERE c.email = :email")
})

public class Client extends User {

    private String cin;

    @OneToMany(mappedBy = "client")
    private List<ReservationCinema> reservationsCinema;

    public Client() {
    }

    public Client(String cin, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.cin = cin;
    }

    

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public List<ReservationCinema> getReservationsCinema() {
        return reservationsCinema;
    }

    public void setReservationsCinema(List<ReservationCinema> reservationsCinema) {
        this.reservationsCinema = reservationsCinema;
    }

    
}
