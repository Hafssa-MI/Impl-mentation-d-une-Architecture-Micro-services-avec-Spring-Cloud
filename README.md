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
- Laisser juste les configurations specifique à customer dans son ficier properties et passer le reste vers config-service-repo/customer-service.properties
- Appliquer le meme pour billing service et inventory service
- Passer à un repo git remote pour le config service


---


---

## Partie 7 —  Créer le client Angular

- Création du module Angular ecom-client-angular via IntelliJ (NgModule classique, pas standalone) avec Node 22 et Angular CLI 19
- Installation de bootstrap via npm et ajout dans angular.json pour le style
- Création des models (interfaces TS) : Customer, Product, ProductItem, Bill dans le dossier models
- Création des services Angular (CustomerService, InventoryService, BillingService) qui appellent la Gateway sur le port 8888, avec gestion du format HAL (_embedded) renvoyé par @RepositoryRestResource
- Configuration du environment.ts avec l'url de la gateway
- Ajout de provideHttpClient() dans app.module.ts (à la place de HttpClientModule dépréciée)
- Ajout du CORS dans gateway-service avec un CorsWebFilter reactif pour autoriser localhost:4200
- Création des composants CustomersListComponent et ProductsListComponent avec formulaire d'ajout, tableau, suppression, et spinner de chargement
- Création de BillsListComponent (liste des factures) et BillDetailsComponent (détail d'une facture avec client et produits résolus)
- Problème rencontré : les id n'apparaissaient pas dans les réponses JSON de Spring Data REST (uniquement dans les liens _links.self) → résolu avec exposeIdsFor() dans une classe RepositoryRestConfigurer pour chaque service
- Ajout d'un endpoint personnalisé POST /bills/full dans BillRestController pour créer une facture avec plusieurs produits en une seule requête, avec récupération du prix courant via Feign au moment de la création
- Ajout du formulaire de création de facture dans BillsListComponent (sélection client + liste dynamique de produits/quantités)
- Ajout d'une confirmation avant suppression (customers et products) avec confirm()
- Navigation simple entre les 3 pages (Customers, Products, Bills) via app-routing.module.ts