# Rapport partie 2 Axel SENECHAL


# Sommaire:

- [Introduction](#introduction)
- [Les Modifications](#les-modifications)
    - [Petites modifications](#petites-modifications)
        - [P1 - Amélioration du README](#p1-amélioration-du-readme)
        - [P2 - Commentaire ajoutés](#p2--commentaires-ajoutés---fxassertjava)
        - [P3 :  Magic numbers en Variables](#p3--magic-numbers-en-variables)
        - [P4 - Nomenclature Méthode](#p4--keyandbuttonreleasetestjava---nomenclature-des-méthodese)

    - [Moyennes modification](#moyennes-modifications)
    - [Grandes modification](#grandes-modifications)
- [Comparatif Partie 1 / Partie 2](#comparatif-partie-1--partie-2)
- [Retour](#retour)
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

Exemples: 

• renommer une classe, une méthode, une variable
• changer le type ou le nombre de paramètres d’une méthode
• créer des variables pour supprimer des nombres magiques.
• supprimer du code mort
• réorganiser une classe pour le code soit bien structuré, les variables d’instance en
début de classe, puis méthodes publiques et enfin méthodes privées

#### P1: Amélioration du README

- **Localisation**: README de TestFX

- **Explication**: Le README est assez daté et n'est plus ni à jour ni entièrement bien organisé. Un README d'un projet Git est un document essentiel car il sert de première porte d'entrée pour les utilisateurs qui souhaitent en savoir plus sur le projet. Il doit être à jour et bien expliqué pour permettre une découverte et compréhension préliminaire du projet. Si des imprécisions résident, des erreurs de compréhension suivront.

- **Solution**: Relecture et amélioration
     - Explicitation de la dépendance avec JavaFX et la nature de bibliothèque externe de celui ci depuis Java 11 dès le début plutot que dans les features.

```
TestFX requires a minimum Java version of 8 (1.8) and JavaFX (no longer included in Java SE since Java 11).
```


     - Création du sommaire dynamique dans le README pour améliorer le parcours utilisateur. (Uniquement pour les grands titre type "##")

```
ad

[Documentation](#documentation)

[Features](#features)

[...]

## <span id="documentation">Documentation </span>

## <span id="features">Features </span>
```

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/a2f11be09cc1dad42c88899058a61f3d540e3946

(Correctif => Documentation passe plus tard de type # à type ##, léger oublie au moment de la solution)
#### P2 : Commentaires ajoutés - FxAssert.java

- **Localisation**: FxAssert.java

- **Explication**: Classe assez grande déclinant l'utilisation polymorphe d'une methode. Nécessite plus de clarté

- **Solution**: 
    - Commentaire présentant la suite de methode qui wrap verifyThat sous divers signatures, pour clarifier la lecture de code, ainsi que les methodes privées déclarées plus tard par formalisme (mais qui du coup rendait la compréhension fragmentée)
        
            
            /* Wrappings of the verifyThat methods with multiple signatures, that may use the following private methods:
            - verifyThatImpl: initial method wrapped by verifyThat which test to make the assertion and manage the potential exception
            - toNode/toNodeSet/toNodeMatcher: finding methods for Node elements
            */
            

    - Commentaire rajouté au dessus des méthodes de recherche à la fin de la classe pour la compréhesion de la suite de méthodes, de plus, ces méthodes ont étés déplacées en amont des méthodes les appelant.
                
                /*
                Here are several methods to get to Node elements (Node / NodeSet / NodeMatcher)
                */
                
    - Mise à jour des copyrights des contributeurs Git à 2023.
        



- **Lien commit**: 


https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/ab8c62cb90260442f83714197f035367d4a665a7

puis: 

https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/0ea0e032a1e85b44fe3d0bc7c5489be6e694facd

En effet, à la première lecture j'avais décider de déplacer directement les méthodes verifyThat et les méthodes de recherches *avant* les méthodes verifyThis, pour clarifier l'utilisation et le sens de lecture du code. Mais étant privées, ces méthodes doivent rester en fin de classe. J'ai donc remis en plus les méthodes et ai commenté comme annoncé dans la solution

#### P3 :  Magic numbers en Variables


- **Localisation**: ApplicationStartTest.java

- **Explication**: Des magics numbers étaient utilisés lors de l'initialisation de l'objeet Scene lors du test (méthode "start").

- **Solution**: 
        
    - Mise en paramètre publique des attributs nécessaires à la création et l'utilisation de l'objet Scene.
                
            //Scene parameter
            public double width,height;

            double seconds_wait;

    - Initialisation de ces paramètres avec les valeurs choisies initialement en magic numbers dans la méthode "init".

          @Override
            public void init() throws Exception {
            FxToolkit.registerStage(Stage::new);

            //Setting scene parameters
            width=100;
            height=100;
            seconds_wait= 10;
            }
        

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/f3cf93c97720791156e9492d356a8a79def6511e
d

#### P4 : KeyAndButtonReleaseTest.java - Nomenclature des méthodes

- **Localisation**: KeyAndButtonReleaseTest.java 

- **Explication**: Mauvaises pratiques détectée à la partie 1. 

    `Les méthodes de test commencent par une lettre (à l'image de magic numbers, ces lettres sont arbitrairement choisies). De plus, un underscore est employé pour séparer les termes (Snake Case).`


- **Solution**: 
        
    - Modification (refactoring) des méthodes concernées: changement du nom de méthode en nom plus clair et respectant la convention de camelCase.
            
            b_When_a_test_forgets_to_release_keys => testForgetingKeysRelease
            c_Then_keys_are_not_pressed testKeysNotPressed
            e_When_a_test_forgets_to_release_buttons  testForgetingButtonsRelease
            f_Then_buttons_are_not_pressed testButtonsNotPressed


        

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/05b9c2fc860b751f31763ddb18b0c078b29c9d53


### Moyennes modifications

Exemples: 
• réduire la complexité cyclomatique ou le nombre de lignes d’une méthode
• décomposer une méthode qui à la fois retourne des informations et modifie l’état
d’un objet
• remplacer le fait qu’une méthode retourne un code d’erreur par le fait qu’elle lève
une exception
• supprimer de la duplication de codes entre méthodes
• ajouter un test pertinent
• corriger un test rouge ou orange


### Grandes modifications

Exemples: 
• décomposer une god classe
• ajouter une super classe pour supprimer des méthodes dupliquées
• supprimer des classes static
• fusionner des classes
• supprimer des packages contenant peu de classes (en fonction du nombre de classes
et de ce qui est fait, cette modification peut être consiérée comme moyenne)
• utiliser un design pattern (MVC, Strategy, Composite, Decorator)
• supprimer des cycles dans les dépendances entre packages.





## Comparatif Partie 1 / Partie 2

//Traitée : P4
//Pas traité :

## Retour

//Brouillon
- git public pour inciter aux bonnes pratiques publiquement + Git co pilot
- Documenter mon code java car un enfer de relire derrière quelqu'un sans doc
