package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

public class PlaylistVolatile implements Serializable{
  public ArrayList<Playlist> Ensemble = new ArrayList<Playlist>();

  public void addUser(){

    Scanner clavier = new Scanner(System.in);
    System.out.println("Entrez le Nom:");
    String Nom=clavier.nextLine();

    Playlist nouveau= new Playlist(Nom,Ensemble.size()+1);
    Ensemble.add(nouveau);
  }


  public void addContenu(LivreAudioVolatile livreaudiovolatile,ChansonVolatile chansonvolatile){
    Client client = new Client();
    MutableInt ChoixClient = new MutableInt(0,0);

    int number;
    char c;
    Scanner clavier = new Scanner(System.in);
    System.out.println("___________________________________________________");
    System.out.println("Titre de la playlist:");
    String Titre =clavier.nextLine();
    try{
      client.EnvoiString(Titre);
    }catch (Exception e) {

    }
    System.out.println("Contenu que vous pouvez ajouter:");
    System.out.println("Chanson:");
    System.out.println(chansonvolatile);

    System.out.println("Livre audio:");
    System.out.println(livreaudiovolatile);

    do {
      System.out.println("c ajouter chanson\nl ajouter livreaudio\nq terminer\nEntrez une commande:");
      c = clavier.next().charAt(0);
      try{
        switch(c){
          case 'c':
            ChoixClient.setValue(1);
            break;
          case 'l':
            ChoixClient.setValue(2);
            break;
          case 'q':
            ChoixClient.setValue(3);
            break;
          }
          client.EnvoiChoix(ChoixClient);
        }catch (Exception e) {

        }
      System.out.println("selectionnez l'id à ajouter:");
      switch (c) {
        case 'c':
          number = clavier.nextInt();
          ChoixClient.setValue(number);
          try{
            client.EnvoiChoix(ChoixClient);
          }catch (Exception e) {

          }
          break;

        case 'l':
          number = clavier.nextInt();
          ChoixClient.setValue(number);
          try{
            client.EnvoiChoix(ChoixClient);
          }catch (Exception e) {

          }
          break;
      }
    } while (c!='q');
  }

  public String toString(){
    String s="";
    for (Playlist Courant : Ensemble ) {
      s+=(Courant+"\n");
    }
    return s;
  }

  public ArrayList<Playlist> getEnsemble(){
    return Ensemble;
  }

  public Playlist get(int number){
    return Ensemble.get(number);
  }

  public void add(Playlist Aajouter){
    Ensemble.add(Aajouter);
  }

  public void suppression(){

      Client client = new Client();
      MutableInt ChoixClient = new MutableInt(0,0);
    int Number;
    Scanner clavier = new Scanner(System.in);
    do{
      System.out.println("Choisissez la playlist à supprimer");
      Number=clavier.nextInt();
    }while (Number>Ensemble.size()&&Number<1);
    try{
      ChoixClient.setValue(Number);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {

    }
  }

  public String Trie(){
    ArrayList<Playlist> Trier= new ArrayList<Playlist>(Ensemble);
    Trier.sort((p1, p2) -> p1.getTitre().compareTo(p2.getTitre()));
    String s="";
    for (Playlist playlist : Trier) {
      s+="\t"+playlist+"\n";
    }
    return s;
  }

}
