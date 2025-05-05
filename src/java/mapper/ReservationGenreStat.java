/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

/**
 *
 * @author User
 */
public class ReservationGenreStat {

    private String genre;
    private long total;

    public ReservationGenreStat(String genre, long total) {
        this.genre = genre;
        this.total = total;
    }

    public String getGenre() {
        return genre;
    }

    public long getTotal() {
        return total;
    }
}
