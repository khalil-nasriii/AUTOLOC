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
| Imports inutilisés dans Equipement (enums.*, BigDecimal, LocalDate) | Les imports inutilisés doivent être supprimés (code mort). | Imports supprimés. |
| Imports avec * (jakarta.persistence.*) dans les entités | Les imports génériques (wildcard) ne doivent pas être utilisés. | Remplacés par les imports explicites. |
| (3e anomalie à compléter) | (règle affichée par Sonar) | (correction) |