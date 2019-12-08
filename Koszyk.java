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
    private ArrayList<Zakup> lista = new ArrayList<Zakup>(); //LISTA PRZEDMIOTOW W KOSZYKU
    Sklep sklep;
    
    Koszyk(int nr_zam){this.nr_zam=nr_zam;}; //KONSTRUKTOR
    
    public int getNrZamKoszyk(){return nr_zam;};
    public float getWartoscKoszyka(){return Wartosc;};
    public Stan getStanKoszyka(){return stan;};
    public void wycofajKoszyk(){stan = Stan.wycofany; lista.clear(); Wartosc = 0;};  
    public void pokazKoszyk(){System.out.println(lista);};
   
    // usun przedmiot z koszyka
    public void usunKoszyka(Przedmiot przedmiot)
    {
        int pozycja=0;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getNazwaPrzedmiotu() == null ? przedmiot.getNazwaPrzedmiotu() == null : lista.get(i).getNazwaPrzedmiotu().equals(przedmiot.getNazwaPrzedmiotu()))
            {pozycja = i;};
        }
        lista.remove(pozycja); 
        Wartosc = Wartosc - przedmiot.getCenaPrzedmiotu();
    }; 
   
    //sprawdz czy przedmiot jest w koszyku
    private boolean czyWkoszyku(Przedmiot zakup)
    {
        boolean test = false;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i) == zakup)
            {
                test = true;
            }
        }
        return test;
    };
    
    //znajdz zakup w koszyku
    private Zakup znajdzWkoszyku(Przedmiot zakup)
    {
        int pozycja=0;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).equals(zakup))
            {
                pozycja = i;
            }
        }
        return lista.get(pozycja);
    }
    
    public void dodajDoKoszyka(Towar towar)
    {
        //sprawdz ilosc dostepnych sztuk towaru
        int ilosc_towaru = towar.getIloscPrzedmiotu();
        if(ilosc_towaru>0)
        {
           //sprawdz czy zakup jest juz w koszyku
            if(czyWkoszyku(towar))
            {
                Zakup zakup = znajdzWkoszyku(towar);
                int ilosc_zakupu = zakup.getIloscPrzedmiotu();
                //sprawdz czy mozna dodac wiecej sztuk zakupu
                if(ilosc_zakupu < ilosc_towaru)
                {
                    int ilosc = ilosc_zakupu + 1;
                    zakup.zmienIloscPrzedmiotu(ilosc);
                }
                else
                {
                    System.out.println("Nie można dodać więcej");
                }
            }
            else // przedmiotu nie ma jeszcze w koszyku
            {
                Zakup zakup = new Zakup(towar.getNazwaPrzedmiotu(), towar.getKategoriaPrzedmiotu(), towar.getCenaPrzedmiotu());
                lista.add(zakup); Wartosc = Wartosc + zakup.getCenaPrzedmiotu();
            }
        }
    }; //dodaj przedmiot do koszyka
    
    public void oplacKoszyk(){
        stan = Stan.oplacone;
        //zmien ilosc dostepnego towaru w sklepie, poniewaz zostal kupiony!
        for(int i = 0; i < lista.size(); i++)
        {
            for(int j = 0; j < sklep.wszystkie_towary.size(); j++)
            {
                Towar towar = sklep.wszystkie_towary.get(j);
                Zakup zakup = lista.get(i);
                if(zakup.equals(towar))
                {
                    int ilosc = towar.getIloscPrzedmiotu() - zakup.getIloscPrzedmiotu();
                    sklep.zmienIloscTowaru(towar, ilosc);                   
                }
            }
        }
    };
    
     @Override public String toString(){ 
        String string="Nr zamowienia: " + getNrZamKoszyk() + ". Stan: " + getStanKoszyka() + ", wartosc: "+getWartoscKoszyka() + "."; 
        return string;
    }
}
