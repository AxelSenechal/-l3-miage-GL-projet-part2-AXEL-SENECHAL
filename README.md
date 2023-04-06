# Rapport partie 2 Axel SENECHAL


# Sommaire:

- [Introduction](#introduction)
- [Les Modifications](#les-modifications)
    - [Petites modifications](#petites-modifications)
        - [P1 - Amélioration du README](#p1-amélioration-du-readme)
        - [P2 - Commentaire ajoutés](#p2--commentaires-ajoutés---fxassertjava)
        - [P3 -  Magic numbers en Variables](#p3--magic-numbers-en-variables)
        - [P4 - Nomenclature Méthode](#p4--keyandbuttonreleasetestjava---nomenclature-des-méthodes)

    - [Moyennes modification](#moyennes-modifications)
        - [M1 - Paramètre inutilisé / méthode non implémentée](#m1-glassrobotadapterjava---paramètre-inutilisé--méthode-non-implémentée)
        - [M2 - Redondance d'interface + méthode mal placée](#m2-pixelmatcherbasejava--pixelmatcherrgbjava---redondance-dinterface--méthode-mal-placée)
        - [M3 - Gestion d'exception explicitée + Logique inverse déportée](#m3-waitforasyncutilstestjava--waitforasyncutilsfxtestjava---gestion-dexception-explicitée--logique-inverse-déportée)
        - [M4 - Switch sans default case + encadrement par levé d'exception](#m4-moverobotimpljava---switch-sans-default-case--encadrement-par-levée-dexception)
 
    - [Grandes modification](#grandes-modifications)
        - [G1 - God class / Interface de 1000+ lignes](#g1-fxrobotinterface---god-class--interface-de-1000-lignes)
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

#### P3 -  Magic numbers en Variables


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


#### P5 : DragAndDropTest.java - Création de test unitaire

- **Localisation**: DragAndDropTest.java 

- **Explication**: 
Les tests de Drag&Drop Gauche vers Droite, Droite vers Gauche, et Gauche vers Gauche sont déjà réalisés, mais pas Droit vers Droite


- **Solution**: 
        
    - Implémentation d'un test pour le Drag&Drop Droite vers Droite. (Par soucis d'homgénéité, j'utilise la convention d'écriture de ces méthodes-test soeurs, malgré P4)

            @Test
                public void should_drag_and_drop_from_right_to_right() {
                    // when:
                    drag("R3");
                    dropTo("R2");

                    // then:
                    assertThat(waitForAsyncFx(5000, () -> leftListView.getItems()))
                            .containsExactly("L1", "L2", "L3");
                    assertThat(waitForAsyncFx(5000, () -> rightListView.getItems()))
                            .containsExactly("R1", "R2", "R3");
                }



        

- **Lien commit**: x





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


## M1 GlassRobotAdapter.java - Paramètre inutilisé / méthode non implémentée

- **Localisation**: GlassRobotAdapter.java 

- **Explication**: Mauvaises pratiques détectée à la partie 1. 

        
        Le paramètre glassRobot est inchangé et cette la méthode est un "getter" (getRobot). Cependant, la methode getRobot appelle une autre méthode lorsque le glassRobot est null. 
        Cela n'a pas d'impact direct sur le paramètre en question car ceci est une méthode d'interface et GlassRobotAdaptater ne l'implémente pas lui-même. Il serait bon de soit encadré par de la documentation 
        la nécessité de cet appel méthode, où de rendre celui-ci plus efficace en le supprimant si non-nécessaire.
        


- **Solution**: 
        
    - Implémentation de createRobot dans la classe pour permettre un encadrement local et spécifique propre au GlassRobot, lors de l'appel à la méthode getRobot() via cette classe:

            public static GlassRobotAdapter createGlassRobot() {
                if (publicRobot) {
                    return new PublicGlassRobotAdapter();
                }
                else {
                    return new PrivateGlassRobotAdapter();
                }
            }

            //Implementation of robotCreate from the interface RobotAdapter. Needed for getrobot()
            public void robotCreate() {
                glassRobot = createGlassRobot();
            }

    La méthode createRobot reste donc inchangée, ne changeant pas le comportement du code.
        

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/afb3665da6d1537b603bb18833d8d34995a454a7



## M2 PixelMatcherBase.java  & PixelMatcherRGB.java - Redondance d'interface + méthode mal placée

- **Localisation**: PixelMatcherBase.java  & PixelMatcherRGB.java

- **Explication**: Mauvaises pratiques détectée à la partie 1. 

        
        Ici, la classe PixelMatcherRGB hérite de la classe PixelMatcherBase. Elle implémente toute les deux la même interface, "PixelMatcher". 
        Faire implémenter PixelMatcherRGB l'interface Pixer Matcher est inutile car elle l'implémente déjà par défaut grâce à son héritage.

        Le plus vraisemblable serait que cela soit une trace d'un version précédente où l'implementation était nécessaire. Peut-etre que la classe mère était inexistante à un moment.
        En tout cas, peu importe la raison réelle de la présence de cette redondance, elle reste très simple à corriger.
        
    De plus, après analyse plus profonde, la méthode "createEmptyMatchImage" ne dépendant en rien de la nature du caclul de couleur du RGB (ou autre systeme), la méthode n'a pas sa place dans une classe fille.

- **Solution**: 
        
    - Suppression de "implements PixelMatcher" de la classe PixelMatcherRGB, le lien avec l'interface étant déjà hérité par sa classe mère abstraite.
    - Déplacement de la méthode "createEmptyMatchImage" vers la classe mère abstraite car ce comportement standard est à hériter. Il n'utilise aucun argument/attribut de la classe, il n'y a que du traitement fait via deux "Image" données pour créer une image vierge.

            @Override
            public WritableImage createEmptyMatchImage(Image image0,
                                                    Image image1) {
                return new WritableImage((int) image0.getWidth(), (int) image1.getHeight());
            }
        

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/b12ff27d2ea13f9712b61c9fbce15a0be121f62f


## M3 WaitForAsyncUtilsTest.java  & WaitForAsyncUtilsFxTest.java - Gestion d'exception explicitée + Logique inverse déportée

- **Localisation**: WaitForAsyncUtilsTest.java  & WaitForAsyncUtilsFxTest.java

- **Explication**: 

Plusieurs fois dans ces deux classes, des levées de Throwable sont encadrées. Si ce Throwable est non géré, une exception est levée et s'il l'est, il est simplement "throw". 


        // then:
        try {
            WaitForAsyncUtils.checkException();
            fail("checkException didn't detect Exception");
        }
        catch (Throwable e) {
            if (!(e instanceof UnsupportedOperationException)) {
                throw e;
            }
        }

Néanmoins, l'exception UnsupportedOperationException n'entraine aucun traitement si levée, ni même de la documentation sur le comportement ici appliqué.

- **Solution**: 
        
    - Catch préalable de ce type d'exception pour traitement/documentation comportementale + allégé la comparaison d'un comparatif

            try {
                    future = WaitForAsyncUtils.async(callable);
                    fail("No exception thrown by autoCheck");
                }
                catch (UnsupportedOperationException e){
                    //You can here managed how you deal with UnsupportedOperationException, with a standard error log/system printing/etc
                }
                catch (Throwable e) {
                        throw e;
                }
        

- **Liens commits**: 
    - https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/feccadd12b32a49928d524085c8f1d55d91d7944
    - https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/954055536cc1ff5861d8692814943db5f03c8292



## M4 MoveRobotImpl.java - Switch sans default case + encadrement par levée d'exception

- **Localisation**: MoveRobotImpl.java

- **Explication**: 

Dans la méthode ``public void moveBy(double x, double y, Motion motion)``, MoveRobotImpl.java utilise un switch mais sans aucune valeur default:



    [...Début de la méthode...]
    switch (motion) {
                case DIRECT:
                    path = interpolatePointsBetween(sourcePoint, targetPoint, totalStepsCount);
                    break;
                case HORIZONTAL_FIRST: {
                    Point2D intermediate = new Point2D(targetPoint.getX(), sourcePoint.getY());
                    path = Stream.concat(
                            interpolatePointsBetween(
                                    sourcePoint, intermediate, horizontalStepsCount).stream(),
                            interpolatePointsBetween(
                                    intermediate, targetPoint, verticalStepsCount).stream())
                            .collect(Collectors.toList());
                    break;
                }
                case VERTICAL_FIRST: {
                    Point2D intermediate = new Point2D(sourcePoint.getX(), targetPoint.getY());
                    path = Stream.concat(
                            interpolatePointsBetween(
                                    sourcePoint, intermediate, verticalStepsCount).stream(),
                            interpolatePointsBetween(
                                    intermediate, targetPoint, horizontalStepsCount).stream())
                            .collect(Collectors.toList());
                    break;
                }
            }




- **Solution**: 
        
    - Création d'un default case, envoyant une exception de type ``UnsupportedOperationException`` car un comportement non encadré par ces 3 cases seraient un cas d'opération non supportée par le Robot.

       
            switch (motion) {
                        case DIRECT:
                            path = interpolatePointsBetween(sourcePoint, targetPoint, totalStepsCount);
                            break;
                        case HORIZONTAL_FIRST: {
                            Point2D intermediate = new Point2D(targetPoint.getX(), sourcePoint.getY());
                            path = Stream.concat(
                                    interpolatePointsBetween(
                                            sourcePoint, intermediate, horizontalStepsCount).stream(),
                                    interpolatePointsBetween(
                                            intermediate, targetPoint, verticalStepsCount).stream())
                                    .collect(Collectors.toList());
                            break;
                        }
                        case VERTICAL_FIRST: {
                            Point2D intermediate = new Point2D(sourcePoint.getX(), targetPoint.getY());
                            path = Stream.concat(
                                    interpolatePointsBetween(
                                            sourcePoint, intermediate, verticalStepsCount).stream(),
                                    interpolatePointsBetween(
                                            intermediate, targetPoint, horizontalStepsCount).stream())
                                    .collect(Collectors.toList());
                            break;
                        }
                        default: {
                            throw new UnsupportedOperationException();
                            break;
                        }
                    }

        

- **Liens commits**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/8255283974a473a07382c411a0435d1f88f4d60c









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

## G1 FxRobotInterface - God class / Interface de 1000+ lignes

- **Localisation**: FxRobotInterface.java

- **Explication**: Mauvaises pratiques détectée à la partie 1. 

        
        Une classe a particulièrement retenue notre attention. FxRobotInterface.java est une interface de plus de 1300 lignes. 
        Pour respecter les conventions de bonnes pratiques, il faudrait séparer cette classe en plusieurs sous-classes, ce qui rendrait le code plus lisible et compréhensible.

- **Solution**: 
        
    - Découpage de l'interface en 10 interfaces plus courtes aux noms explicites, permettant un meilleur ciblage des fonctionnalités et une maintenance plus claire:

            -FxRobotInterfaceWindow
            -FxRobotInterfaceNode
            -FxRobotInterfaceBounds
            -FxRobotInterfacePointAndPointQuery
            -FxRobotInterfaceCapture
            -FxRobotInterfaceInteractAndSleep
            -FxRobotInterfaceClick
            -FxRobotInterfaceDragAndDropAndMove
            -FxRobotInterfaceScroll
            -FxRobotInterfacePushAndTypeAndWrite

    - Changement de la signature des méthodes de l'interface. Beaucoup d'entre elle renvoyait FxRobotInterface, mais était uniquement utilisé par FxRobot en réalité. Remplacement donc par FxRobot à chaque appel de l'interface supprimée.

        - Exemple (Dans FxRobotInterfaceClick):

                /**
                * Calls {@link org.testfx.robot.ClickRobot#clickOn(PointQuery, MouseButton...)} and returns itself for more method
                * chaining.
                */
                FxRobotInterface clickOn(PointQuery pointQuery, Motion motion, MouseButton... buttons);

                En 

                                    /**
                * Calls {@link org.testfx.robot.ClickRobot#clickOn(PointQuery, MouseButton...)} and returns itself for more method
                * chaining.
                */
                FxRobot clickOn(PointQuery pointQuery, Motion motion, MouseButton... buttons);



    - Modification de la classe FxRobot qui implémentait cette interface pour quelle implémente les sous-interfaces créées. Ajout d'un commentaire incitant à hériter de FxRobot plutôt que d'implémenter toutes les interfaces, permettant aussi l'interversion des paramètres de retour des méthodes comme indiqué ci-dessus.


            
            public class FxRobot implements FxRobotInterfaceBounds,FxRobotInterfaceCapture,
            FxRobotInterfaceClick,FxRobotInterfaceDragAndDropAndMove,FxRobotInterfaceInteractAndSleep,
            FxRobotInterfaceNode, FxRobotInterfacePointAndPointQuery,
            FxRobotInterfacePushAndTypeAndWrite,FxRobotInterfaceScroll,
            FxRobotInterfaceWindow
            {

                private final FxRobotContext context;

                //.........Reste de la classe

       
        

- **Lien commit**: https://github.com/AxelSenechal/-l3-miage-GL-projet-part2-AXEL-SENECHAL/commit/03750e80c59e81563e9eb8122617697dc2bb5f09



## Comparatif Partie 1 / Partie 2

//Traitée : P4, G1, M1, M2
//Pas traité : Tests JUnit4 et JUnit5 - Duplication de code (Code Smells - Don't Repeat Yourself);WaitForAsyncUtils.java - Exception non gérée 




### Tests JUnit4 et JUnit5 - Duplication de code (Code Smells - Don't Repeat Yourself)

Comme annoncé dans la partie 1, il y a beaucoup de duplication de classe/de code entre les subprojects JUnit4 et Junit5. L'intérêt aurait été de les faire s'importer ou de créer un classe mère commune, mais étant non pas des packages différents d'un même projet mais de véritable projet à pat entière rangé dans le même dossier git, le packaging n'arrive pas à remonter plus loin que "org.[...]" , les projets ne se voient pas.

Cela serait laborieux, mais une solution possible serait peut-être de développer une bibliothèque pour les classes communes (tel que ApplicationAdaptater.java ou ApplicationFixture.java) et de créer une dépendance. Néanmoins, les dépendances déjà préexistantes étant déjà complexe, cette duplication permet, d'une certaine manière, une meilleure compréhension au prix d'une répétition; à contrario d'une économie de code mineure pour une complexité rendue plus nébuleuse.


### WaitForAsyncUtils.java - Exception non gérée 

Après analyse, le fait que l'exception ne soit pas gérée ici est tout à fait normal à cause de l'utilisation de `Futur<T>`. Ainsi même si une exeception devait être levée, le code de futur n'a a être vérfié qu'une fois produit, via les méthode get() et checkException(). Cela permet ici une meilleure adaptation à l'usage dans les divers tests utilisant TestFx dans ce cadre.


## Retour

//Brouillon
- git public pour inciter aux bonnes pratiques publiquement + Git co pilot
- Documenter mon code java car un enfer de relire derrière quelqu'un sans doc
