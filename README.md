# JoieFull - Template Android Moderne avec BDD, Réseau et UI Adaptative
## AVRIL 2026

Ce projet est réalisé dans le cadre de la formation Android développeur d'OpenClassrooms

## Fonctionnalités Clés

*   **Architecture Clean/MVVM** : Séparation claire des responsabilités.
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

## Licence

Libre d'utilisation. A vos risques et périls ! 
