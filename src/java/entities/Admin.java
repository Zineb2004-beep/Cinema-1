
package entities;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
    private String matricule;

    public Admin() {
    }

    public Admin(String matricule, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
}
