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

public class AutomaticUpdateXML{
  public static StockageVolatile GetListFileChanson(){
    System.out.println("=================\n");
    FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
    StockageVolatile          ListeChanson            = FactoryStockageVolatile.Generate("Chanson");
    Chanson lu;
    String Contenu;
    File ListeFichier = new File("Data/Chansons");
    int Duree,ID=1;
    for (String pathname : ListeFichier.list()){
      Contenu = "Data/Chansons/"+pathname;
      System.out.println("\n"+Contenu);
      try{
        AudioFile f = AudioFileIO.read(new File(Contenu));
        lu=LectureFichierChanson(f,ID,pathname);
        System.out.println(lu);
        ListeChanson.add(lu);
      }catch (Exception e) {

      }
      ID++;
    }
    System.out.println(ListeChanson);
    System.out.println("=================\n");
    return ListeChanson;
  }
  public static Chanson LectureFichierChanson(AudioFile f,int ID,String Contenu) {
    WavTag  tag = (WavTag) f.getTag();
    String Artiste=tag.getFirst(FieldKey.ARTIST);
    String Album=tag.getFirst(FieldKey.ALBUM);
    String Titre=tag.getFirst(FieldKey.TITLE);
    String Commentaire=tag.getFirst(FieldKey.COMMENT);
    String Annee=tag.getFirst(FieldKey.YEAR);
    String Track=tag.getFirst(FieldKey.TRACK);
    String genre = tag.getFirst(FieldKey.GENRE);
    int Duree=f.getAudioHeader().getTrackLength();
    int genreEnregistrer=Genre.valueOf(genre).ordinal();
    Chanson lu = new Chanson(Titre,Duree,ID,Artiste,Contenu,genreEnregistrer);
    return lu;
  }


  public static StockageVolatile GetListFileLivresAudios(){
    System.out.println("=================\n");
    FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
    StockageVolatile          ListeLivreAudio            = FactoryStockageVolatile.Generate("LivreAudio");
    LivreAudio lu;
    String Contenu;
    File ListeFichier = new File("Data/LivresAudios");
    int Duree,ID=1;
    for (String pathname : ListeFichier.list()){
      Contenu = "Data/LivresAudios/"+pathname;
      System.out.println("\n"+Contenu);
      try{
        AudioFile f = AudioFileIO.read(new File(Contenu));
        lu=LectureFichierLivreAudio(f,ID,pathname);
        System.out.println("lu ="+lu);
        ListeLivreAudio.add(lu);
      }catch (Exception e) {
        System.out.println("JEHRBGAHEBLZEHBZELHGZELJGZELJZEL"+e.getMessage());
      }
      ID++;
    }
    System.out.println(ListeLivreAudio);
    System.out.println("=================\n");
    return ListeLivreAudio;
  }

  public static LivreAudio LectureFichierLivreAudio(AudioFile f,int ID,String Contenu) {
    WavTag  tag = (WavTag) f.getTag();
    String Artiste=tag.getFirst(FieldKey.ARTIST);
    String Album=tag.getFirst(FieldKey.ALBUM);
    String Titre=tag.getFirst(FieldKey.TITLE);
    String Commentaire=tag.getFirst(FieldKey.COMMENT);
    String Annee=tag.getFirst(FieldKey.YEAR);
    String Track=tag.getFirst(FieldKey.TRACK);
    String langue = tag.getFirst(FieldKey.LANGUAGE);
    System.out.println(langue);
    String categorie = tag.getFirst(FieldKey.GENRE);
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

    LivreAudio lu = new LivreAudio(Titre,Duree,ID,Artiste,Contenu,langueEnregistrer,categorieEnregistrer);
    return lu;
  }
}
