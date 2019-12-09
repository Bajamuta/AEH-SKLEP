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
public abstract class Przedmiot {
    public String nazwa;
    public String kategoria;
    public double cena;
   
    Przedmiot(String nazwa, String kategoria, double cena){this.nazwa = nazwa; this.kategoria = kategoria; this.cena = cena;};

    public abstract void zmienIloscPrzedmiotu(int ilosc);
    
    public String getNazwaPrzedmiotu(){return nazwa;};
    public String getKategoriaPrzedmiotu(){return kategoria;};
    public abstract int getIloscPrzedmiotu();
    public double getCenaPrzedmiotu(){return cena;};
    
}
