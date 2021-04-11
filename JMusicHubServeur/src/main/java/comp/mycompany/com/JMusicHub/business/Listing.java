package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;

public interface Listing{
  public Element getElement(Document document);
}
