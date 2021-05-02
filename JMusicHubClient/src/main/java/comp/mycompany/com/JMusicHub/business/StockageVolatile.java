package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.*;

/**
 * Intefarce Stockant une liste de Stockage avec differents moyen d'accès standart
 */
public interface StockageVolatile extends Serializable {
  public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();

   public void add(Stockage nouveau);

  public String toString();

   public ArrayList<Stockage> getEnsemble();

  public Stockage get(int number);

  public String Tri(int Choix);

  public Element getElement(Document document);

}
