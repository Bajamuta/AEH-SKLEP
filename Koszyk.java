/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.util.ArrayList;
import java.util.Iterator;

enum Stan{nieoplacone, oplacone, wycofany}; //STAN KOSZYKA

/**
 *
 * @author jpraj
 */
public class Koszyk {
    private int nr_zam;
    private Stan stan = Stan.nieoplacone;
    private float Wartosc = 0;
    private ArrayList<Przedmiot> lista = new ArrayList<Przedmiot>(); //LISTA PRZEDMIOTOW W KOSZYKU
    
    Koszyk(int nr_zam){this.nr_zam=nr_zam;}; //KONSTRUKTOR
    
    public int getNrZamKoszyk(){return nr_zam;};
    
    public void wycofajKoszyk(){stan = Stan.wycofany; lista.clear(); Wartosc = 0;};
    public void oplacKoszyk(){stan = Stan.oplacone;};
    public void dodajDoKoszyka(Przedmiot przedmiot){lista.add(przedmiot); Wartosc = Wartosc + przedmiot.getCenaPrzedmiotu();}; //dodaj przedmiot do koszyka
    public void pokazKoszyk(){System.out.println(lista);};
    public float getWartoscKoszyka(){return Wartosc;};
    public Stan getStanKoszyka(){return stan;};
   
    public void usunKoszyka(Przedmiot przedmiot)
    {
        int pozycja=0;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getNazwaPrzedmiotu() == przedmiot.getNazwaPrzedmiotu())
            {pozycja = i;};
        }
        lista.remove(pozycja); 
        Wartosc = Wartosc - przedmiot.getCenaPrzedmiotu();
    }; // usun przedmiot z koszyka
    
     @Override public String toString(){ 
        String string="Nr zamowienia: " + getNrZamKoszyk() + ". Stan: " + getStanKoszyka() + ", wartosc: "+getWartoscKoszyka() + "."; 
        return string;
    }
}
