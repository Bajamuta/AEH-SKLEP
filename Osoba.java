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

public abstract class Osoba {
    protected String nazwisko;
    protected String imie;
    public Sklep sklep;
    
    Osoba(String imie, String nazwisko, Sklep sklep){ this.imie = imie; this.nazwisko = nazwisko; this.sklep = sklep;};
    
    public void wyszukajTowarNazwa(String nazwa)
    {
        sklep.przegladajTowary(nazwa,null,0);
    };
    public void wyszukajTowarKategoria(String kategoria)
    {
        sklep.przegladajTowary(null,kategoria,0);
    };
    public void wyszukajTowarCena(double cena)
    {
        sklep.przegladajTowary(null,null,cena);
    };
    
    //DO ROZWINIĘCIA GDY KLIENT MA WIELE KOSZYKOW
    /*private Stan sprawdzStanKoszyka(int nr_zam){
        Koszyk koszyk = Klient.koszyk;
        return koszyk.getStanKoszyka();
    };*/
    
    public void sprawdzStanKoszyka(Koszyk koszyk)
    {
        System.out.println(koszyk.getStanKoszyka());
    }
}
