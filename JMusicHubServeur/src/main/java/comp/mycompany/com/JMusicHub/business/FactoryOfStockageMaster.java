package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

/**
 * CLasse generatrice des classes issu de StockageMaster
 */
public class FactoryOfStockageMaster{
  /**
   * Si le choix est album alors la methode renverra une liste d'album, de meme pour les playlists
   * @param  Choix Choix de la liste voulant être créer
   * @return       Envoi la liste générée
   * Si la liste voulu n'est pas connu, envoi null
   */
  public StockageMaster Generate(String Choix){
    final Logger logger = Logger.getLogger(FactoryOfStockageMaster.class);
    switch(Choix){
      case "Album":
        logger.info("Creation d'une liste d'album");
        return new AlbumVolatile();
      case "Playlist":
        logger.info("Creation d'une liste de Playlist");
        return new PlaylistVolatile();
      default:
        logger.error("Creation d'une liste inconnu, parametre invalide = "+Choix);
        return null;
    }
  }
}
