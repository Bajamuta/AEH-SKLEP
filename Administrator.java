/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

/**
 *
 * @author jpraj
 */
public class Administrator extends Osoba {
    Administrator(String imie, String nazwisko, Sklep sklep)
    {
        super(imie,nazwisko, sklep);
    }
    
    public void utworzTowar(String nazwa, String kategoria, float cena, int ilosc){
        Towar produkt = new Towar(nazwa, kategoria, cena, ilosc);
        sklep.dodajTowar(produkt);
    };
    
    public void usunTowar(Towar towar)
    {
        sklep.usunTowar(towar);
    }
    
    public void zmienCenaTowaru(Towar towar, float cena)
    {
        towar.zmienCenaPrzedmiotu(cena);
    };
    
    public void zmienIloscTowaru(Towar towar, int ilosc)
    {
        towar.zmienIloscPrzedmiotu(ilosc);
    }
}
