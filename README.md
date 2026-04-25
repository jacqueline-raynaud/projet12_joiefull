# JoieFull - Projet Android Moderne avec BDD, Réseau et UI Adaptative
> **Formation OpenClassrooms** | AVRIL 2026

Ce projet est réalisé dans le cadre de la formation Android développeur d'OpenClassrooms

## Fonctionnalités Clés

*   **Clean Architecture/MVVM** : Séparation claire des responsabilités.
*   **Injection de dépendances** : Avec **Hilt** pour un code modulaire et testable.
*   **Persistance locale** : **Room** avec support complet des Coroutines.
*   **Réseau** : **Retrofit** + **Kotlinx Serialization** pour parler aux API.
*   **UI Moderne** : 100% **Jetpack Compose** (Material 3).
*   **Adaptatif** : Supporte mobiles, tablettes et pliables via `WindowSizeClass`.
*   **Images** : Chargement fluide avec **Coil 3**.

## Stack Technique

*   **Kotlin 2.3.20** & **Compose BOM 2026.03.01**
*   **AGP :** 9.1.0 / **KSP :** 2.3.5
*   **Hilt :** 2.59.2
*   **Room :** 2.8.4
*   **Retrofit :** 3.0.0

## Structure du Projet (Clean Architecture)

Les principaux packages sont :

```text
app/src/main/java/fr/quinquenaire/templatebddreseauadaptive/
├── data/           # LA SOURCE : Implémentation des Repositories, DAOs, API Services, DTOs.
├── domain/         # LE CERVEAU : Modèles métiers (Data Classes), Interfaces des Repositories, Use Cases.
├── di/             # LE CABLAGE : Modules Hilt pour l'injection.
└── presentation/   # LE VISAGE : Screens (Composables), ViewModels, Thème, Composants génériques.
```
## Installation & Utilisation
1. Clonez le dépôt.
2. Ouvrez le projet dans **Android Studio**
3. Synchronisez Gradle.
4. Lancez l'application (le peuplement initial se fait via le fichier JSON intégré si la base est vide).

## Licence

Libre d'utilisation. A vos risques et périls ! Je vous rappelle que c'est un projet d'apprentissage 


## 🎁 Bonus : Template réutilisable

Ce projet a été conçu pour servir de fondation solide à d'autres applications Android. J'en ai extrait un **template prêt à l'emploi** incluant toute la configuration (Hilt, Room, Retrofit, Adaptive UI) pour gagner du temps sur le démarrage de nouveaux projets.
**Retrouvez le template ici :** [Android Template Clean Architecture](https://github.com/jacqueline-raynaud/android-template-cleanarchi-room-retrofit-hilt-compose-adaptive.git)

--- 