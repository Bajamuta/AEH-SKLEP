/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jpraj
 */
public class Sklep {
    public ArrayList<Towar> wszystkie_towary = new ArrayList<Towar>(); //LISTA WSZYSTKICH PRZEDMIOTOW W SKLEPIE
    //private int[] numery_zam = new 
    
    public int getSizeSklep(){return wszystkie_towary.size();};
    public void dodajTowar(Towar towar){wszystkie_towary.add(towar);};
       
    //wyszukaj towar w sklepie
    public int wyszukajTowar(String nazwa, String kategoria, double cena)
    {
        //System.out.println("Szukam...\n");
        int pozycja=0;
        for(int i = 0; i < wszystkie_towary.size(); i++)
        {
            if(!nazwa.isEmpty()) 
            {
                //System.out.println("tutaj:");
                if(wszystkie_towary.get(i).getNazwaPrzedmiotu() == null ? nazwa == null : wszystkie_towary.get(i).getNazwaPrzedmiotu().equals(nazwa))
                {
                    pozycja = i;
                    //System.out.println("I tu.");
                    break;                    
                }
                else
                {
                    pozycja = -1;
                    //System.out.println("A może tu.");
                }
            }
            else if (!kategoria.isEmpty())
            {
                if(wszystkie_towary.get(i).getKategoriaPrzedmiotu() == null ? kategoria == null : wszystkie_towary.get(i).getKategoriaPrzedmiotu().equals(kategoria))
                {
                    pozycja = i;
                    break;
                }
                else
                {
                    pozycja = -1;
                }
            }
            else if (cena >0)
            {
                if(wszystkie_towary.get(i).getCenaPrzedmiotu() == cena)
                {
                    pozycja = i;
                    break;
                }
            }
            else
            {
                pozycja = -1;
            }
        }
        return pozycja;        
    };
    
    public Towar getTowar(int pozycja)
    {
        return wszystkie_towary.get(pozycja);
    }
    
    public void przegladajTowary(String nazwa, String kategoria, double cena)
    {
        //System.out.println("Przeglądam...");
        for(int i = 0; i < wszystkie_towary.size(); i++)
        {
            if(nazwa != null) 
            {
                //System.out.println("Po nazwie...");
                if(wszystkie_towary.get(i).getNazwaPrzedmiotu() == null ? nazwa == null : wszystkie_towary.get(i).getNazwaPrzedmiotu().equals(nazwa))
                {
                    System.out.println(wszystkie_towary.get(i));
                }
                else if(i == wszystkie_towary.size())
                {
                    System.out.println("Brak produktów o nazwie "+nazwa+".");
                }
            }
            else if (kategoria != null)
            {
                //System.out.println("Po kategorii...");
                if(wszystkie_towary.get(i).getKategoriaPrzedmiotu() == null ? kategoria == null : wszystkie_towary.get(i).getKategoriaPrzedmiotu().equals(kategoria))
                {
                    System.out.println(wszystkie_towary.get(i));
                }
                else if(i == wszystkie_towary.size())
                {
                    System.out.println("Brak produktów w kategorii "+kategoria+".");
                }
            }
            else if (cena >0)
            {
                //System.out.println("Po cenie...");
                //System.out.println(wszystkie_towary.get(i).getCenaPrzedmiotu());
                if(wszystkie_towary.get(i).getCenaPrzedmiotu() == cena)
                {
                    System.out.println(wszystkie_towary.get(i));
                }
                else if(i == wszystkie_towary.size())
                {
                    System.out.println("Brak produktów z ceną "+cena+".");
                }
            }
        }
    }
    
    public void pokazTowary()
    {
        System.out.println(wszystkie_towary);
    }
    
    public ArrayList<Towar> getWszystkieTowary()
    {
        return wszystkie_towary;
    }
    
    // usun przedmiot z koszyka;
    public void usunTowar(String nazwa)
    {
        int pozycja=wyszukajTowar(nazwa, "", 0);
        if(pozycja >= 0){wszystkie_towary.remove(pozycja);}
        else{System.out.println("Nie ma takiego towaru w sklepie!");}        
    }; 
    
    public void zmniejszWsklepie(String nazwa, String kategoria, Double cena)
    {
        int pozycja = wyszukajTowar(nazwa,kategoria,cena);
        int ilosc = wszystkie_towary.get(pozycja).getIloscPrzedmiotu()-1;
        wszystkie_towary.get(pozycja).zmienIloscPrzedmiotu(ilosc);
    }
    
    public void zwrocWycofane(String nazwa, int ilosc_zwrotu)
    {
        //System.out.println("Zwracam...");
        int pozycja = wyszukajTowar(nazwa,"",0);
        int ilosc = wszystkie_towary.get(pozycja).getIloscPrzedmiotu()+ilosc_zwrotu;
        wszystkie_towary.get(pozycja).zmienIloscPrzedmiotu(ilosc);
    }
    
    //?? JAK DODAć to?
    @Override 
    public String toString(){ 
        String string = "Znaleziono w sklepie:";
        return string;
    }
    
}
