package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class StockageMaster implements Serializable{
  public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();

  public void add(int AlbumNumber,Stockage Aajouter){
   StockageVolatile nouveau=Ensemble.get(AlbumNumber);
   nouveau.add(Aajouter);
  }

  public void addUser(){}
  public void suppression(){}

 public void add(StockageVolatile nouveau){
   Ensemble.add(nouveau);
 }

 /**
  * Creer un String contenant l'ensemble des chansons avec les informations sur l'album au début
  * @return Renvoi la chaine de caractere
  */
 public String toString(){
   String s="";
   for (StockageVolatile Courant : Ensemble ) {
     s+=(Courant+"\n");
   }
   return s;
 }

 /**
  * Accesseur de la l'ArrayList
  * @return ArrayList d'Album
  */
 public ArrayList<StockageVolatile> getEnsemble(){
   return Ensemble;
 }

 /**
  * Accesseur d'un album parmis l'ArrayList d'albums
  * @param  number Numero de l'Album à récupérer
  * @return        Renvoi l'album selectionné
  */
 public StockageVolatile get(int number){
   return Ensemble.get(number);
 }


 public String Tri(int Choix){
   if(Choix==0){
     String s="";
     ArrayList<StockageVolatile> init= new ArrayList<StockageVolatile>(Ensemble);
     for (StockageVolatile Actuel :init ) {
       s+="\t"+Actuel+"\n";
     }
     return s;
   }else{
     return "ERROR";
   }
 }

}
