# Implémentation d'une Architecture Micro services avec Spring Cloud

**Nom :** Hafssa Miftah Idrissi
**Master :** SDIA2
**Module :** Systèmes Distribués et DevOps

---

## Partie 1 —  Créer le micro-service customer-service qui permet de gérer les client

- Création d'entite Customer avec les annotations lombok et jpa, Repository avec l'annotation @RepositoryRestResource
- Ajout de quelques clients et Test


---

## Partie 2 —  Créer le micro-service inventory-service qui permet de gérer les client

- Création d'entite Product avec les annotations lombok et jpa, Repository avec l'annotation @RepositoryRestResource
- Ajout de quelques produits et Test


---

## Partie 3 —  Configurer la gateway

- Ajouter la gateway et properties.yaml


---

## Partie 4 —  Configurer la config

- Ajouter la config et configurer d'une manière statique
- Configurer la route dynamique


---

## Partie 5 —  Créer le service de facturation Billing-Service en utilisant Open Feig

- Creer le service de facturation et les models product et customer et leurs repository
- Creer en utilisant fiegn CustomerServiceRestClient et ProductServiceRestClient
- Ajouter CillRestController dans package Web
- Ajouter resiliance4j pour les tolerances en pannes dans billing service en utilisant le circuit breaker


---

## Partie 6 —  Créer le service service de Configuration

- Creer le service de configuration config-service et activer la configuration avec l'annotation @EnableConfigServer
- Creer le repo git via le dossier config-repo et initialiser le git et commit des fichier des properties { application.properties , customer-serivce/-dev/-prod.properties
- Tester la configuration et la connexion avec DiscoveryService
- Activer le config dans le Customer service et creer un controlleur pour le test; ConfigTestRestController avec l'annotation @RestController
- Essayer une autre approche avec CustomerConfigParams comme record class
- refresh des changement dans le fichier properties du config service repo en changeant puis faisant commit puis la requete refresh via http client post method et ajouter l'annotation éRefreshScope dans ConfigTestRestController dans le customer service
