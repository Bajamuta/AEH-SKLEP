/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

/**
 *
 * @author jpraj
 * PREDMIOTY W KOSZYKU
 */
public class Zakup extends Przedmiot {
    private int ilosc;
        
    //konstruktory
    Zakup(String nazwa, String kategoria, double cena, int ilosc)
    {
        //konstruktor klasy nadrzędnej
        super(nazwa, kategoria, cena);
        this.ilosc = ilosc;
    }
    Zakup(String nazwa, String kategoria, double cena)
    {
        //konstruktor klasy nadrzędnej
        super(nazwa, kategoria, cena);
        this.ilosc = 1;
    }
    
    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
    
    /**
     *
     * @param ilosc
     */
    @Override public void zmienIloscPrzedmiotu(int ilosc){
        //System.out.println("dalej...");
        this.ilosc = ilosc;
    };
    @Override public int getIloscPrzedmiotu(){return ilosc;};
    
    @Override 
    public String toString(){ 
        String string="Koszyk zawiera: " + getNazwaPrzedmiotu() +" w kategorii " + getKategoriaPrzedmiotu() + ". Ilość: " + getIloscPrzedmiotu() + ", cena: "+getCenaPrzedmiotu() + "zł."; 
        return string;
    }
}
