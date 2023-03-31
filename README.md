# Rapport partie 2 Axel SENECHAL

## Introduction

Lors de cette seconde partie du projet de Génie Logiciel, je vais personnellement m'intéresser à la résolution de problème relevé dans la partie 1, bien que je ne me ferme pas à trouver d'autre bug durant ce second parcours. 

Globalement, le schéma de correction sera le suivant:

    - Petites modifications 
    - Moyennes modifications
    - Grandes modifications

Comme convenu dans la description consigne du sujet partie 2.

Chaque modification suivra cette structure:

    - ID de modification (première lettre de la catégorie "P | M | G" + le numéro dans la catégorie)
    - Nom du problème
    - Localisation du problème
    - Explication
    - Description de la solution
    - Lien commit git
<br>

Puis, je présenterai le comparatif entre les bugs décelés à la partie 1 et les résolutions (ou explications si pas résolues) de cette partie 2.

Et pour finir, le cloturerait sur un retour de mon apprentissage de Génie Logiciel, sur ma prise de recul sur mes habitudes erronées et mes "bonnes résolutions" que ce cours m'a permis de prendre.


## Les Modifications
### Petites modifications

#### P1: Amélioration du README

- **Localisation**: README de TestFX

- **Explication**: Le README est assez daté et n'est plus ni à jour ni entièrement bien organisé. Un README d'un projet Git est un document essentiel car il sert de première porte d'entrée pour les utilisateurs qui souhaitent en savoir plus sur le projet. Il doit être à jour et bien expliqué pour permettre une découverte et compréhension préliminaire du projet. Si des imprécisions résident, des erreurs de compréhension suivront.

- **Solution**: Relecture et amélioration
     - Explicitation de la dépendance avec JavaFX et la nature de bibliothèque externe de celui ci depuis Java 11 dès le début plutot que dans les features.
     - Création du sommaire dynamique dans le README pour améliorer le parcours utilisateur. (Uniquement pour les grands titre type "##")


- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/a2f11be09cc1dad42c88899058a61f3d540e3946

#### P2 : Commentaires ajoutés - FxAssert.java

- **Localisation**: FxAssert.java

- **Explication**: Classe assez grande déclinant l'utilisation polymorphe d'une methode. Nécessite plus de clarté

- **Solution**: 
        
        - Commentaire rajouté au dessus des méthodes de recherche à la fin de la classe pour la compréhesion de la suite de méthodes, de plus, ces méthodes ont étés déplacées en amont des méthodes les appelant.
        - Commentaire présentant la suite de methode qui wrap verifyThat sous divers signatures, pour clarifier la lecture de code, ainsi que les methodes privées déclarées plus tard par formalisme (mais qui du coup rendait la compréhension fragmentée)
        - Mise à jour des copyrights des contributeurs Git à 2023.
        



- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/0ea0e032a1e85b44fe3d0bc7c5489be6e694facd
### Moyennes modifications


### Grandes modifications

