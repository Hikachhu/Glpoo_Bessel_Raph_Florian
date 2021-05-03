# Projet Glpoo_Bessel_Raph_Florian

Projet JmusicHub réalisé par:
BESSEL KOBEISSI
FLORIAN MORIN
RAPHAËL PTIT HADDAD
dans le cadre d'un projet dirigé par IONASCU Félicia au cours du 2ème Semestre de la 3ème année à l'ESIEA

## Arborescence

Dans ce projet vous trouverez 2 dossier, le dossier Client et Serveur sont parfaitement indépendant.
Vous pouvez séparer les dossiers mais vous devez les laisser dans sur le meme ordinateur.

## Utilisation

Le fonctionnement de l'application est UNIQUEMENT LOCAL. Le serveur et le client doivent fonctionner sur la même machine.
L'application est destiné à un environnement Windows. Chaque dossier contient globalement la meme arborescence.
Dedans vous trouverez un fichier de compilation utilisant MAVEN. Si ce fichier ne fonctionne pas, veillez modifier le chemin où se trouve votre MAVEN dans le fichier Compilation.bat
Vous trouverez également un fichier d'execution permettant de lancer le client ou le serveur selon le dossier où vous vous trouvez.

## Fonctionnement

L'application est destiné à un fonctionnement local et le code source ne permet pas de modifier l'IP à laquel le client se connectera sans modifier le code source.
Pour ajouter des fichiers audios, veillez les ajouter dans le dossier DATA, puis dans un des deux dossier selon la catégrie du fichier que vous souhaitez ajouter.
Les informations relative au fichier seront directment lu dans les METADA, si vous souhaitez les modifier nous vous recommandons : 
```
https://tagscanner.fr.uptodown.com/windows
```
De plus tout fichier audio doit être obligatoirement de type WAV, aucun autre fichier n'est pris en compte dans la lecture audio

## Exemple d'utilisation

Aller dans un dossier (Client ou Serveur) exécutez "Compilation.bat" afin de générer le .jar nécessaire au fonctionnement de l'application
Puis exécutez "Execution.bat" afin de lancer l'application
Une fois que les deux applications sont lancées (Client et Serveur) sur la même machine, vous pouvez utiliser une fonctionnalité du menu du client.
Vous pouvez commencer par synchronisez vos données avec le serveur afin de récupérer les différentes listes de données. Puis jouer une chanson.
Pour cela faite j afin d'ouvrir le menu de lecture audio, puis le type de fichier que vous souhaitez lire, son numéro et enfin valider votre choix.
Ainsi vous pourrez écouter un fichier audio depuis le client.

