# Film affiche

## Présentation du projet et de la démarche

De manière hebdomadaire, tous les mercredis, de nouvelles oeuvres cinématographiques font leur apparition au grand écran. Afin de pouvoir suivre la cadence et de ne pas louper les plus belles pépites, nous souhaitons pouvoir consulter les films à l'affiche et voir si ils valent le coup. C'est autour de cette idée que s'articule ce projet.
L'idée générale est de récupérer des données venant d'une api opensource (TMDB) que l'on va ensuite intégrer grâce à des jobs dans Talend, pour ensuite les manipuler et les intégrer en tant qu'information dans notre application python conçue avec Flask.

Sources de données (TMDB) : https://developer.themoviedb.org

# Installation

## Prérequis

Avoir :

- Python
- une variable d'environnement permettant l'éxécution d'un script python (ex : *python script.py*)
- Talend Open Studio

## Installer les dépendances Python (flask et pandas)

```bash
pip install flask pandas
```
## Importer le projet

importer le projet dans le dossier de votre choix
```bash
git clone https://github.com/rabailh/film_affiche_.git
```

Comment démarrer l'application avec Talend :
- Ouvrir Talend
- Importer le projet depuis le dossier **talend** du projet
- Changer la variable de contexte du projet talend **filme_affiche_context.importedDirectory** en y insérant le chemin vers le dossier où à été importé le projet git.
  La variable doit se finir par :
  ```bash
  /film_affiche_/
  ```
  
- Autoriser talend à propager la modification dans tous les jobs
- Exécuter le job **call_api**
- Exécuter le job **call_script**
- L'application devrait s'ouvrir sur le navigateur par défaut. L'application reste visitable même une fois close : *http://127.0.0.1:5000/*
