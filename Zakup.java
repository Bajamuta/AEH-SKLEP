/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

/**
 *
 * @author jpraj
 * PREDMIOTY W KOSZYKU
 */
public class Zakup extends Przedmiot {
    private int ilosc;
        
    //konstruktory
    Zakup(String nazwa, String kategoria, float cena, int ilosc)
    {
        //konstruktor klasy nadrzędnej
        super(nazwa, kategoria, cena);
        this.ilosc = ilosc;
    }
    Zakup(String nazwa, String kategoria, float cena)
    {
        //konstruktor klasy nadrzędnej
        super(nazwa, kategoria, cena);
        this.ilosc = 1;
    }
    
    /**
     *
     * @param ilosc
     */
    @Override public void zmienIloscPrzedmiotu(int ilosc){this.ilosc = ilosc;};
    @Override public int getIloscPrzedmiotu(){return ilosc;};
}