package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

/**
 * CLasse generatrice des classes issu de StockageMaster
 */
public class FactoryOfStockageVolatile{
  /**
   * Si le choix est chanson alors la methode renverra une liste de chanson, de meme pour les LivresAudios
   * @param  Choix Choix de la liste voulant être créer
   * @return       Envoi la liste générée
   * Si la liste voulu n'est pas connu, envoi null
   */
  final Logger logger = Logger.getLogger(FactoryOfStockageVolatile.class);
  public StockageVolatile Generate(String Choix){
    switch(Choix){
      case "Chanson":
        logger.info("Creation d'une liste de chanson");
        return new ChansonVolatile();
      case "LivreAudio":
        logger.info("Creation d'une liste de LivreAudio");
        return new LivreAudioVolatile();
      default:
        logger.error("Creation d'une liste inconnu, parametre invalide = "+Choix);
        return null;
    }
  }
}
