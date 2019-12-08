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

public abstract class Osoba {
    protected String nazwisko;
    protected String imie;
    public Sklep sklep;
    
    Osoba(String imie, String nazwisko, Sklep sklep){ this.imie = imie; this.nazwisko = nazwisko; this.sklep = sklep;};
    
    public Przedmiot wyszukajTowarNazwa(String nazwa, Sklep sklep)
    {
        return sklep.wyszukajTowar(nazwa,"",0);
    };
    public Przedmiot wyszukajTowarKategoria(String kategoria, Sklep sklep)
    {
        return sklep.wyszukajTowar("",kategoria,0);
    };
    public Przedmiot wyszukajTowarCena(float cena, Sklep sklep)
    {
        return sklep.wyszukajTowar("","",cena);
    };
    
    //DO ROZWINIĘCIA GDY KLIENT MA WIELE KOSZYKOW
    /*private Stan sprawdzStanKoszyka(int nr_zam){
        Koszyk koszyk = Klient.koszyk;
        return koszyk.getStanKoszyka();
    };*/
    
    private Stan sprawdzStanKoszyka(Koszyk koszyk)
    {
        return koszyk.getStanKoszyka();
    }
    
}
