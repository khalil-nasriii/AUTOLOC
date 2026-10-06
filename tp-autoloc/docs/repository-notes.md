# Notes Atelier 3 - Couche Repository

## Choix d'interface

| Interface | Étend | Justification |
|---|---|---|
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IEmployeRepository | JpaRepository<Employe, Long> | Idem : CRUD, listes, tri, pagination, flush. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | Idem. |
| IEquipementRepository | JpaRepository<Equipement, Long> | Idem. |
| IClientRepository | JpaRepository<Client, Long> | Idem. |
| IReservationRepository | JpaRepository<Reservation, Long> | Idem. |
| IContratRepository | JpaRepository<Contrat, Long> | Idem. Les suppressions en lot ignorent cascade et orphanRemoval. |
| IPaiementRepository | JpaRepository<Paiement, Long> | Lecture des paiements ; création et retrait passent par le Contrat (composition). |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | Idem. |

JpaRepository est retenue pour tous les repositories : elle cumule CRUD, List, tri, pagination et méthodes JPA (flush, saveAndFlush, getReferenceById).

## Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| Classe RepositoryTests vide | java:S2094 : une classe vide doit être complétée ou supprimée. | Classe complétée avec les tests des repositories. |
| Chemin du fichier RepositoryTests ne correspond pas au package | java:S1598 : le chemin du fichier doit correspondre au nom du package. | Fichier déplacé dans src/test/java/tn/esprit/autoloc. |
| Imports inutilisés dans Equipement | java:S1128 : les imports inutilisés doivent être supprimés. | Imports supprimés. |