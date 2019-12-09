/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.util.ArrayList;

/**
 *
 * @author jpraj
 */
public class Klient extends Osoba {
    
    private Koszyk koszyk_klienta;
    private static int nr_zam = 1234;
    //private ArrayList<Zakup> wszystkie_zakupy = new ArrayList<Zakup>();
    
    Klient(String imie, String nazwisko, Sklep sklep)
    {
        super(imie, nazwisko, sklep);
        Koszyk koszyk = new Koszyk(nr_zam, sklep);
        //System.out.println("Klient utworzony.\n");
    }
    
        //znajdź przedmiot w sklepie
        //Towar towar = sklep.wyszukajProdukt(przedmiot.getNazwaPrzedmiotu(), przedmiot.getKategoriaPrzedmiotu(), przedmiot.getCenaPrzedmiotu());
         //Zakup zakup = new Zakup(towar.getKategoriaPrzedmiotu(), towar.getKategoriaPrzedmiotu(), towar.getCenaPrzedmiotu());
       
    public void dodajDoKoszyka(String nazwa)
    {
        //System.out.println("test...\n");
        //Zakup zakup = new Zakup(nazwa, kategoria, 0);
        if(koszyk_klienta != null)
        {
            koszyk_klienta.dodajDoKoszyka(nazwa, "");
        }
        else 
        {
            koszyk_klienta = new Koszyk(nr_zam, sklep);
            koszyk_klienta.dodajDoKoszyka(nazwa, "");
        }
         
    };
    
    public void usunKoszyka(String nazwa){
        Koszyk koszyk = koszyk_klienta;
        koszyk.usunKoszyka(nazwa);
    };
    
    public void wycofajKoszyk()
    {
        Koszyk koszyk = koszyk_klienta;
        koszyk.wycofajKoszyk();
    };
    
    public void oplacKoszyk()
    {
        Koszyk koszyk = koszyk_klienta;
        koszyk.oplacKoszyk();
        System.out.println("Opłaciłeś koszyk o wartości: " + koszyk.getWartoscKoszyka());
    }
    
    public void pokazKoszyk()
    {
        //System.out.println("Test...\n");
        Koszyk koszyk = koszyk_klienta;
        //System.out.println(koszyk);
        if(koszyk != null)
        {
            koszyk.pokazKoszyk();
        }
        else throw new RuntimeException("Koszyk jest przecież pusty.");
    }
    
    public Koszyk getKoszyk()
    {
        return koszyk_klienta;
    }
}
