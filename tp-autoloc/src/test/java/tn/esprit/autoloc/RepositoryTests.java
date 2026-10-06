package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IEmployeRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class RepositoryTests {

    @Autowired private IAgenceRepository agenceRepo;
    @Autowired private IClientRepository clientRepo;
    @Autowired private IVehiculeRepository vehiculeRepo;
    @Autowired private IEmployeRepository employeRepo;
    @Autowired private IEquipementRepository equipementRepo;
    @Autowired private IMaintenanceRepository maintenanceRepo;

    @Test
    void testAgenceRepository() {
        Agence a = new Agence();
        a.setNom("Agence Tunis");
        a.setVille("Tunis");
        a.setAdresse("Avenue Habib Bourguiba");
        a.setTelephone("71000000");

        Agence saved = agenceRepo.save(a);
        System.out.println("Saved: id=" + saved.getId() + " nom=" + saved.getNom());

        agenceRepo.findAll().forEach(x ->
                System.out.println("findAll -> id=" + x.getId() + " nom=" + x.getNom()));
        System.out.println("findById: " + agenceRepo.findById(saved.getId()).map(Agence::getNom).orElse("not found"));
        System.out.println("existsById: " + agenceRepo.existsById(saved.getId()));
        System.out.println("count: " + agenceRepo.count());

        agenceRepo.deleteById(saved.getId());
        System.out.println("existsById after delete: " + agenceRepo.existsById(saved.getId()));
    }

    @Test
    void testClientRepository() {
        Client c1 = new Client();
        c1.setNom("Ben Ali");
        c1.setPrenom("Ali");
        c1.setEmail("ali@mail.com");
        c1.setTelephone("20111111");
        c1.setNumPermis("P-1001");
        c1.setDateInscription(LocalDate.now());

        Client c2 = new Client();
        c2.setNom("Trabelsi");
        c2.setPrenom("Sara");
        c2.setEmail("sara@mail.com");
        c2.setTelephone("20222222");
        c2.setNumPermis("P-1002");
        c2.setDateInscription(LocalDate.now());

        Client saved1 = clientRepo.save(c1);
        Client saved2 = clientRepo.save(c2);

        clientRepo.findAll().forEach(x ->
                System.out.println("findAll -> id=" + x.getId() + " " + x.getPrenom() + " " + x.getNom()));
        System.out.println("findById: " + clientRepo.findById(saved1.getId()).map(Client::getEmail).orElse("not found"));
        System.out.println("existsById (real id): " + clientRepo.existsById(saved1.getId()));
        System.out.println("existsById (fake id): " + clientRepo.existsById(99999L));
        System.out.println("count: " + clientRepo.count());

        clientRepo.deleteById(saved1.getId());
        clientRepo.deleteById(saved2.getId());
    }

    @Test
    void testVehiculeRepository() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("123 TUN 4567");
        v.setMarque("Peugeot");
        v.setModele("208");
        v.setTarifJournalier(new BigDecimal("80.00"));

        Vehicule saved = vehiculeRepo.save(v);
        System.out.println("Saved: id=" + saved.getIdVehicule() + " marque=" + saved.getMarque());

        vehiculeRepo.findAll().forEach(x ->
                System.out.println("findAll -> id=" + x.getIdVehicule() + " " + x.getMarque() + " " + x.getModele()));
        System.out.println("count: " + vehiculeRepo.count());

        saved.setMarque("Renault");
        Vehicule updated = vehiculeRepo.save(saved);
        System.out.println("Updated: id=" + updated.getIdVehicule() + " marque=" + updated.getMarque());

        vehiculeRepo.deleteById(updated.getIdVehicule());
        System.out.println("existsById after delete: " + vehiculeRepo.existsById(updated.getIdVehicule()));
    }

    // ---------- Exercice autonome : Employe, Equipement, Maintenance ----------

    @Test
    void testEmployeRepository() {
        Employe e = new Employe();
        e.setNom("Gharbi");
        e.setPrenom("Mehdi");

        Employe saved = employeRepo.save(e);
        System.out.println("Saved: id=" + saved.getId() + " " + saved.getPrenom() + " " + saved.getNom());
        System.out.println("findById: " + employeRepo.findById(saved.getId()).map(Employe::getNom).orElse("not found"));
        System.out.println("existsById: " + employeRepo.existsById(saved.getId()));

        employeRepo.deleteById(saved.getId());
        System.out.println("existsById after delete: " + employeRepo.existsById(saved.getId()));
    }

    @Test
    void testEquipementRepository() {
        Equipement eq = new Equipement();
        eq.setLibelle("GPS");

        Equipement saved = equipementRepo.save(eq);
        System.out.println("Saved: id=" + saved.getId() + " libelle=" + saved.getLibelle());
        equipementRepo.findAll().forEach(x ->
                System.out.println("findAll -> id=" + x.getId() + " " + x.getLibelle()));
        System.out.println("existsById: " + equipementRepo.existsById(saved.getId()));

        equipementRepo.deleteById(saved.getId());
        System.out.println("existsById after delete: " + equipementRepo.existsById(saved.getId()));
    }

    @Test
    void testMaintenanceRepository() {
        Maintenance m = new Maintenance();
        m.setDateDebut(LocalDate.now());
        m.setDateFin(LocalDate.now().plusDays(2));
        m.setDescription("Vidange");

        Maintenance saved = maintenanceRepo.save(m);
        System.out.println("Saved: id=" + saved.getId() + " description=" + saved.getDescription());
        System.out.println("findById: " + maintenanceRepo.findById(saved.getId()).map(Maintenance::getDescription).orElse("not found"));
        System.out.println("existsById: " + maintenanceRepo.existsById(saved.getId()));

        maintenanceRepo.deleteById(saved.getId());
        System.out.println("existsById after delete: " + maintenanceRepo.existsById(saved.getId()));
    }
}