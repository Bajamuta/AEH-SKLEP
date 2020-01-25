/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

/**
 *
 * @author jpraj
 */
public class Administrator extends Osoba {
    Administrator(String imie, String nazwisko, Sklep sklep)
    {
        super(imie,nazwisko,sklep);
    }
    
    public void utworzTowar(String nazwa, String kategoria, double cena, int ilosc){
        Towar produkt = new Towar(nazwa, kategoria, cena, ilosc);
        sklep.dodajTowar(produkt);
    };
    
    public void usunTowar(String nazwa)
    {
        sklep.usunTowar(nazwa);
    }
    
    public void zmienCenaTowaru(Towar towar, double cena)
    {
        towar.zmienCenaPrzedmiotu(cena);
    };
    
    public void zmienIloscTowaru(Towar towar, int ilosc)
    {
        towar.zmienIloscPrzedmiotu(ilosc);
    }
}
