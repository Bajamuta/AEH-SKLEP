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

enum Pozycja{administrator, klient};

abstract class Osoba {
    protected String nazwisko;
    protected String imie;
    protected Pozycja pozycja;
    private Koszyk koszyk;
    
    private static int nr_zam = 0;
    private ArrayList<Koszyk> wszystkie_koszyki = new ArrayList<Koszyk>();
    
    Osoba(String imie, String nazwisko, Pozycja pozycja){ this.imie = imie; this.nazwisko = nazwisko; this.pozycja = pozycja;};
    
    public Przedmiot wyszukajProduktNazwa(String nazwa, Sklep sklep)
    {
        return sklep.wyszukajProdukt(nazwa,"",0);
    };
    public Przedmiot wyszukajProduktKategoria(String kategoria, Sklep sklep)
    {
        return sklep.wyszukajProdukt("",kategoria,0);
    };
    public Przedmiot wyszukajProduktCena(float cena, Sklep sklep)
    {
        return sklep.wyszukajProdukt("","",cena);
    };
    
    
    private Stan sprawdzStanKoszyka(Koszyk koszyk){return koszyk.getStanKoszyka();}; //SPRAWDZ STAN KOSZYKA

    /**
     *
     * @param przedmiot
     */
    public void dodajDoKoszyka(Przedmiot przedmiot)
    {
        /*Stan st_ost = wszystkie_koszyki.get(wszystkie_koszyki.size() - 1).getStanKoszyka();
        if(koszyk == null ||st_ost == Stan.wycofany || st_ost == Stan.oplacone){
            nr_zam ++;
            Koszyk koszyk = new Koszyk(nr_zam);
            wszystkie_koszyki.add(koszyk);
        }
        koszyk.dodajDoKoszyka(przedmiot);*/
        Zakup zakup = new sklep.wyszukajProdukt(przedmiot.getNazwaPrzedmiotu(), przedmiot.getKategoriaPrzedmiotu(), przedmiot.getCenaPrzedmiotu());
    };
    
    
    public void usunKoszyka(Przedmiot przedmiot){koszyk.usunKoszyka(przedmiot);};
}
