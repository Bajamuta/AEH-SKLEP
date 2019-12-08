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
    
    private Koszyk koszyk;
    private static int nr_zam = 1234;
    //private ArrayList<Zakup> wszystkie_zakupy = new ArrayList<Zakup>();
    
    Klient(String imie, String nazwisko, Sklep sklep)
    {
        super(imie, nazwisko, sklep);
        Koszyk koszyk = new Koszyk(nr_zam);
    }
    
        //znajdź przedmiot w sklepie
        //Towar towar = sklep.wyszukajProdukt(przedmiot.getNazwaPrzedmiotu(), przedmiot.getKategoriaPrzedmiotu(), przedmiot.getCenaPrzedmiotu());
         //Zakup zakup = new Zakup(towar.getKategoriaPrzedmiotu(), towar.getKategoriaPrzedmiotu(), towar.getCenaPrzedmiotu());
       
    public void dodajDoKoszyka(Towar towar) //co chcemy dodac do koszyka
    {
        koszyk.dodajDoKoszyka(towar);
    };
    
    public void usunKoszyka(Zakup zakup){
        koszyk.usunKoszyka(zakup);
    };
    
    public void wycofajKoszyk()
    {
        koszyk.wycofajKoszyk();
    };
    
    public void oplacKoszyk()
    {
        koszyk.oplacKoszyk();
        System.out.println("Opłaciłeś koszyk o wartości: " + koszyk.getWartoscKoszyka());
    }
}
