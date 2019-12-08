/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

/**
 *
 * @author jpraj
 * PRZEDMIOTY W SKLEPIE
 */
public class Towar extends Przedmiot{
    private int ilosc;
    
    //konstruktor
    Towar(String nazwa, String kategoria, float cena, int ilosc)
    {
        //konstruktor klasy nadrzędnej
        super(nazwa, kategoria, cena);
        this.ilosc = ilosc;
    }
    
    /**
     *
     * @param ilosc
     */
    @Override public void zmienIloscPrzedmiotu(int ilosc){this.ilosc = ilosc;};
    @Override public int getIloscPrzedmiotu(){return ilosc;};
    
    //??jak zmienic cene tylko towaru? w klasie nadrzędnej cena musi być public
            public void zmienCenaPrzedmiotu(float cena){this.cena = cena;};
}
