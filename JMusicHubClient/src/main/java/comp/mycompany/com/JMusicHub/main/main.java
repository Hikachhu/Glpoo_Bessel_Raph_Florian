package comp.mycompany.com.JMusicHub.main;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.*;
import org.apache.log4j.Logger;

import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import java.util.Scanner;

class main{
  final static Logger logger = Logger.getLogger(main.class);
  public static void main(String[] args) {

    if(System.getProperty("os.name").startsWith("Windows"))
    {
      logger.info("Initialisation des couleurs");
        // Set output mode to handle virtual terminal sequences
        Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
        DWORD STD_OUTPUT_HANDLE = new DWORD(-11);
        HANDLE hOut = (HANDLE)GetStdHandleFunc.invoke(HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

        DWORDByReference p_dwMode = new DWORDByReference(new DWORD(0));
        Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
        GetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, p_dwMode});

        int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
        DWORD dwMode = p_dwMode.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
        SetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, dwMode});
    }

    logger.info("Debut du programme");

    //Couleurs
    final String RESET = "\u001B[0m";
    final String BLACK = "\u001B[30m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String PURPLE = "\u001B[35m";
    final String CYAN = "\u001B[36m";
    final String WHITE = "\u001B[37m";

    char c='h';
    Scanner clavier = new Scanner(System.in);
    int ChansonNumber;

    //Declaration des listes pour stocker les informations

    PlaySound                 Player                  = new PlaySound();
    FacadeMenu                Facade                  =new FacadeMenu();
    Adapter                   adapter                 =new Adapter();

    //Lecture des informations
    MutableInt ChoixClient = MutableInt.getInstance();
    String tempDir=System.getProperty("java.io.tmpdir");
    Client client = new Client();


    //Boucle principale pour le menu
    do{
      System.out.println(c);
      switch (c) {
        case 'j':
          Facade.LectureAudio(tempDir);
          break;
        case 'd':
          Facade.Display();
          break;
        case 'c':
          Facade.AjoutChanson();
          break;
        case 'a':
          Facade.AjoutAlbum();
          break;
        case '+':
          Facade.AjoutMusiqueDansAlbum();
          break;
        case 'l':
          Facade.AjoutLivreAudio();
          break;
        case 'p':
          Facade.CreationPlaylist();
          break;
        case '-':
          Facade.SuppressionPlaylist();
          break;
        case 'h':
          Facade.Help();
          break;
        case 'm':
          Facade.ReceptionClient();
          break;
      }
      System.out.println("___________________________MENU PRINCIPALE___________________________");
      System.out.println("Entrez une commande");
      System.out.println("h pour obtenir la liste des commandes disponibles");
      try{
        c=clavier.next().charAt(0);
        //                                         1   2   3   4   5   6   7   8   9  10  11  12
        ChoixClient.setValue(adapter.Conversion(c,'d','c','a','+','l','p','-','s','h','j','q','m'));
        client.EnvoiChoix(ChoixClient);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }while (c!='q');

  }
}
