package test;

import entities.Client;
import entities.ReservationCinema;
import services.ClientService;
import services.ReservationCinemaService;
import services.SeanceService;

import java.util.List;

public class ReservationTest {

    private ReservationCinemaService reservationCinemaService;
    private ClientService clientService;
    private SeanceService seanceService;

    // Constructeur pour initialiser les services
    public ReservationTest() {
        this.reservationCinemaService = new ReservationCinemaService();
        this.clientService = new ClientService();
        this.seanceService = new SeanceService();
    }

    // Méthode pour tester les réservations d'un client
    public void testReservations() {
        try {
            // Récupérer un client avec ID = 1
            Client client = clientService.findById(1);
            
            if (client == null) {
                System.out.println("Client non trouvé avec l'ID 1");
                return;
            }

            // Récupérer les réservations pour ce client
            List<ReservationCinema> reservations = reservationCinemaService.findByClient(client);

            // Afficher les réservations
            for (ReservationCinema r : reservations) {
                System.out.println(r); // Assurez-vous que la méthode `toString()` est définie dans `ReservationCinema`
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des réservations.");
        }
    }

    // Méthode principale pour exécuter le test
    public static void main(String[] args) {
        ReservationTest test = new ReservationTest();
        test.testReservations();
    }
}
