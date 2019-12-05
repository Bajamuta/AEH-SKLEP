/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jpraj
 */
public class Sklep {
    public ArrayList<Przedmiot> wszystkie_towary = new ArrayList<Przedmiot>(); //LISTA WSZYSTKICH PRZEDMIOTOW W SKLEPIE
    //private int[] numery_zam = new 
    
    public int getSizeSklep(){return wszystkie_towary.size();};
    public void dodajProdukt(Przedmiot produkt){wszystkie_towary.add(produkt);};
    public void usunProdukt(Przedmiot produkt)
    {
        int pozycja=0;
        for(int i = 0; i < wszystkie_towary.size(); i++)
        {
            if(wszystkie_towary.get(i).getNazwaPrzedmiotu() == produkt.getNazwaPrzedmiotu())
            {pozycja = i;};
        }
        wszystkie_towary.remove(pozycja); 
    }; // usun przedmiot z koszyka;  
    public Przedmiot wyszukajProdukt(String nazwa, String kategoria, float cena)
    {
        int pozycja=0;
        for(int i = 0; i < wszystkie_towary.size(); i++)
        {
            if(nazwa != null) 
            {
                if(wszystkie_towary.get(i).getNazwaPrzedmiotu() == nazwa)
                {pozycja = i;};
            }
            else if (kategoria != null)
            {
                if(wszystkie_towary.get(i).getKategoriaPrzedmiotu() == kategoria)
                {pozycja = i;};
            }
            else if (cena >0)
            {
                if(wszystkie_towary.get(i).getCenaPrzedmiotu() == cena)
                {pozycja = i;};
            }
        }
        return wszystkie_towary.get(pozycja);
    };
}
