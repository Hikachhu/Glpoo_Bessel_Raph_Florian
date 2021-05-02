package comp.mycompany.com.JMusicHub.util;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.*;
import java.net.*;

public interface InterfaceAudio{
  public void Lecture(String strFilename);
  public void LectureListe(StockageVolatile PlaylistJouer,String tempDir);
}
