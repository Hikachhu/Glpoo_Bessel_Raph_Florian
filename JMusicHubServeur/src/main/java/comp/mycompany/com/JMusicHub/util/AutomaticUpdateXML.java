package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.wav.WavCleaner;
import org.jaudiotagger.audio.wav.WavOptions;
import org.jaudiotagger.audio.wav.WavSaveOptions;
import org.jaudiotagger.audio.wav.WavSaveOrder;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.wav.*;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;

/**
 * Classe permettant de mettre à jour des fichiers à partir de leurs METADONNEES
 */
public class AutomaticUpdateXML{
  final static Logger logger = Logger.getLogger(AutomaticUpdateXML.class);

  /**
   * Mise à jour de la liste des Chansons
   * @return Liste des Chansons crées
   */
  public static StockageVolatile GetListFileChanson(){
    System.out.println("=================\n");
    FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
    StockageVolatile          ListeChanson            = FactoryStockageVolatile.Generate("Chanson");
    Chanson lu;
    String Contenu;
    File ListeFichier = new File("Data/Chansons");
    int Duree;
    //Pour tout les fichiers présent dans un dossier
    for (String pathname : ListeFichier.list()){
      //Creation du chemin vers ce fichier
      Contenu = "Data/Chansons/"+pathname;
      System.out.println("\n"+Contenu);
      try{
        //Ouverture du fichier dans un objet donné
        AudioFile f = AudioFileIO.read(new File(Contenu));
        lu=LectureFichierChanson(f,pathname);
        //Ajout de la chanson créer dans la liste des chansons
        ListeChanson.add(lu);
      }catch (Exception e) {
        logger.error("Erreur dans la lecture des metadatas d'un fichier:"+e.getMessage());
      }
    }
    System.out.println(ListeChanson);
    System.out.println("=================\n");
    return ListeChanson;
  }

  /**
   * Lecture de METADATA pour un fichier donnée
   * @param  f       Fichier dont nous cherchons à avoir les métadonnées
   * @param  Contenu Nom du fichier
   * @return         Chanson crée à partir des METADATA
   */
  public static Chanson LectureFichierChanson(AudioFile f,String Contenu) {
    WavTag  tag = (WavTag) f.getTag();

    //Recuperation de la METADATA concernant l'artiste
    String Artiste=tag.getFirst(FieldKey.ARTIST);
    if(Artiste=="")Artiste="INCONNU";

    //Recuperation de la METADATA concernant l'album
    String Album=tag.getFirst(FieldKey.ALBUM);
    if(Album=="")Album="INCONNU";

    //Recuperation de la METADATA concernant le titre
    String Titre=tag.getFirst(FieldKey.TITLE);
    if(Titre=="")Titre="INCONNU";

    //Recuperation de la METADATA concernant un commentaire
    String Commentaire=tag.getFirst(FieldKey.COMMENT);
    if(Commentaire=="")Commentaire="INCONNU";

    //Recuperation de la METADATA concernant l'année
    String Annee=tag.getFirst(FieldKey.YEAR);
    if(Annee=="")Annee="INCONNU";

    //Recuperation de la METADATA concernant l'id du fichier
    int ID=Integer.parseInt(tag.getFirst(FieldKey.TRACK));
    String genre = tag.getFirst(FieldKey.GENRE);

    //Recuperation de la METADATA concernant la durée
    int Duree=f.getAudioHeader().getTrackLength();
    int genreEnregistrer=Genre.valueOf("INCONNU").ordinal();
    try{
      genreEnregistrer=Genre.valueOf(genre).ordinal();
    }catch (Exception e) {
      logger.error("Echec dans la modification du genre");
    }

    //Creation dune chanson selon les parametre lu
    Chanson lu = new Chanson(Titre,Duree,ID,Artiste,Contenu,genreEnregistrer);
    return lu;
  }

  /**
   * Mise à jour de la liste des LivresAudios
   * @return Liste des LivresAudios crées
   */
  public static StockageVolatile GetListFileLivresAudios(){
    System.out.println("=================\n");
    FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
    StockageVolatile          ListeLivreAudio            = FactoryStockageVolatile.Generate("LivreAudio");
    LivreAudio lu;
    String Contenu;
    File ListeFichier = new File("Data/LivresAudios");
    int Duree;
    //Pour tout les fichiers présent dans un dossier
    for (String pathname : ListeFichier.list()){
      //Creation du chemin vers ce fichier
      Contenu = "Data/LivresAudios/"+pathname;
      System.out.println("\n"+Contenu);
      try{
        //Ouverture du fichier dans un objet donné
        AudioFile f = AudioFileIO.read(new File(Contenu));
        lu=LectureFichierLivreAudio(f,pathname);
        //Ajout de la chanson créer dans la liste des chansons
        ListeLivreAudio.add(lu);
      }catch (Exception e) {
        logger.error("Erreur dans la lecture des metadatas d'un fichier:"+e.getMessage());
      }
    }
    System.out.println(ListeLivreAudio);
    System.out.println("=================\n");
    return ListeLivreAudio;
  }

  /**
   * Lecture de METADATA pour un fichier donnée
   * @param  f       Fichier dont nous cherchons à avoir les métadonnées
   * @param  Contenu Nom du fichier
   * @return         LivreAudio crée à partir des METADATA
   */
  public static LivreAudio LectureFichierLivreAudio(AudioFile f,String Contenu) {
    WavTag  tag = (WavTag) f.getTag();

    //Recuperation de la METADATA concernant l'artiste
    String Artiste=tag.getFirst(FieldKey.ARTIST);
    if(Artiste=="")Artiste="INCONNU";

    //Recuperation de la METADATA concernant un album
    String Album=tag.getFirst(FieldKey.ALBUM);
    if(Album=="")Album="INCONNU";

    //Recuperation de la METADATA concernant un titre
    String Titre=tag.getFirst(FieldKey.TITLE);
    if(Titre=="")Titre="INCONNU";

    //Recuperation de la METADATA concernant le commentaire
    String Commentaire=tag.getFirst(FieldKey.COMMENT);
    if(Commentaire=="")Commentaire="INCONNU";

    //Recuperation de la METADATA concernant l'année
    String Annee=tag.getFirst(FieldKey.YEAR);
    if(Annee=="")Annee="INCONNU";

    //Recuperation de la METADATA concernant son ID
    int ID=0;
    try{
      ID=Integer.parseInt(tag.getFirst(FieldKey.TRACK));
    }catch (Exception e) {
    }
    String langue = tag.getFirst(FieldKey.LANGUAGE);
    if(langue=="")langue="INCONNU";
    String categorie = tag.getFirst(FieldKey.GENRE);
    if(categorie=="")categorie="INCONNU";
    int Duree=f.getAudioHeader().getTrackLength();
    int langueEnregistrer;
    int categorieEnregistrer;
    try{
      langueEnregistrer=Langues.valueOf(langue).ordinal();
    }catch (Exception e) {
       langueEnregistrer=Langues.valueOf("INCONNU").ordinal();
    }
    try {
      categorieEnregistrer=Categorie.valueOf(categorie).ordinal();
    }catch (Exception e) {
       categorieEnregistrer=Categorie.valueOf("INCONNU").ordinal();
    }

    //Creation d'un livre audio selon les parametre lu
    LivreAudio lu = new LivreAudio(Titre,Duree,ID,Artiste,Contenu,langueEnregistrer,categorieEnregistrer);
    return lu;
  }
}
