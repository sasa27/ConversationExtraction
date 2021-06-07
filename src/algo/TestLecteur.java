package algo;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import objetsconversations.*;
public class TestLecteur
{
  public static ArrayList<Event> Read(String log)
  {
    ArrayList<Event> ListeEvenements=new ArrayList<Event>();
    try
    {
      // Le fichier d'entrée
      FileInputStream file = new FileInputStream(log);   
      Scanner scanner = new Scanner(file);  
      while(scanner.hasNextLine())
      {
        ListeEvenements.add(new Event(scanner.nextLine()));
      }
      scanner.close();
      return ListeEvenements;
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    return ListeEvenements;
  }
}