package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Classe stockant les Element de l'etage inférieur à savoir le StockageVolatile
 */
public interface StockageMaster extends Serializable{
  public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();

 public void add(int ListeNumber,Stockage Aajouter);

 public void addUser(StockageVolatile ...Utile);

 public void Suppression();

 public void add(StockageVolatile nouveau);

 public String toString();

 public ArrayList<StockageVolatile> getEnsemble();

 public StockageVolatile get(int number);

 public String Tri(int Choix);

}
