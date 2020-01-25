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
    
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
