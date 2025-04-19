/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.SeanceDao;
import entities.Seance;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class SeanceService implements IService<Seance> {

    private final SeanceDao sd;

    public SeanceService() {
        this.sd = new SeanceDao();
    }

    @Override
    public boolean create(Seance o) {
        return sd.create(o);
    }

    @Override
    public boolean delete(Seance o) {
        return sd.delete(o);
    }

    @Override
    public boolean update(Seance o) {
        return sd.update(o);
    }

    @Override
    public Seance findById(int id) {
        return sd.findById(id);
    }

    @Override
    public List<Seance> findAll() {
        return sd.findAll();
    }

    public List<Seance> findByDate(Date date) {
        return sd.findByDate(date);
    }

    public List<Seance> findByHeure(Time heure) {
        return sd.findByHeure(heure);
    }

    public List<Seance> findBySalle(String salle) {
        return sd.findBySalle(salle);
    }

    public List<Seance> findByFilm(int filmId) {
        return sd.findByFilm(filmId);
    }

    public List<Seance> findByFilmAndDate(int filmId, Date date) {
        return sd.findByFilmAndDate(filmId, date);
    }
}
