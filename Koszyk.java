/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

import java.util.ArrayList;
enum Stan{nieoplacone, oplacone, wycofany}; //STAN KOSZYKA

/**
 *
 * @author jpraj
 */
public class Koszyk {
    private int nr_zam;
    private Stan stan = Stan.nieoplacone;
    private double Wartosc = 0;
    private ArrayList<Zakup> lista = new ArrayList<Zakup>(); //LISTA PRZEDMIOTOW W KOSZYKU
    Sklep sklep;
    
    //KONSTRUKTOR
    Koszyk(int nr_zam, Sklep sklep){
        this.nr_zam=nr_zam;
        this.sklep = sklep;
        //System.out.println("Koszyk utworzony \n");
    }; 
    
    public int getNrZamKoszyk(){return nr_zam;};
    public double getWartoscKoszyka(){return Wartosc;};

    public Stan getStanKoszyka(){return stan;};
    
    public void wycofajKoszyk(){
        stan = Stan.wycofany; 
        Wartosc = 0;
        for(Zakup i: lista)
        {
            sklep.zwrocWycofane(i.getNazwaPrzedmiotu(), i.getIloscPrzedmiotu());
        }
        lista.clear(); 
    }; 
    
    public void pokazKoszyk(){
        if(lista != null)
        {
            System.out.println(lista);
        }
        else //throw new RuntimeException("Koszyk jest pusty.");
        {
            System.out.println("Koszyk jest pusty");
        }
    };
       
    //sprawdz czy przedmiot jest w koszyku
    private boolean czyWkoszyku(Przedmiot zakup)
    {
        boolean test = false;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).nazwa == null ? zakup.nazwa == null : lista.get(i).nazwa.equals(zakup.nazwa))
            {
                test = true;
            }
        }
        return test;
    };
    
    //znajdz zakup w koszyku
    private int znajdzWkoszyku(Zakup zakup)
    {
        int pozycja=0;
        //System.out.println("Szukam: "+zakup);
        for(int i = 0; i < lista.size(); i++)
        {
            //System.out.println(i + ": "+lista.get(i));
            //System.out.println(i + ": "+lista.get(i).equals(zakup)+"");
            //NIE MOŻNA BEZPOŚREDNIO POROWNAC BO ILOSC INNA!!!
            //System.out.println(i + ": " + lista.get(i).nazwa == null ? zakup.nazwa == null : lista.get(i).nazwa.equals(zakup.nazwa));
            if(lista.get(i).nazwa == null ? zakup.nazwa == null : lista.get(i).nazwa.equals(zakup.nazwa))
            {
                pozycja = i;
                //System.out.println("Jestem tutaj");
            }
            else if(i == lista.size())
            {
                pozycja = -1;
                //System.out.println("Jestem tam");
            }
        }
        return pozycja;
    }
    
    public void dodajDoKoszyka(String nazwa, String kategoria)
    {
        //System.out.println("poczatek\n");
        int pozycja_w_sklepie = sklep.wyszukajTowar(nazwa, kategoria, 0);
        //System.out.println(pozycja_w_sklepie);
        Towar towar = sklep.getTowar(pozycja_w_sklepie);
        System.out.println("Dodaję: "+towar);
        Zakup zakup = new Zakup(towar.nazwa, towar.kategoria, towar.cena);
        //sprawdz ilosc dostepnych sztuk towaru
        int ilosc_towaru = towar.getIloscPrzedmiotu();
        System.out.println("Dostępna ilość:"+ilosc_towaru);
        
        if(ilosc_towaru>0)
        {
            //System.out.println("dalej...\n");
           //sprawdz czy zakup jest juz w koszyku
            if(czyWkoszyku(zakup))
            {
                //System.out.println("czy w koszyku?\n");
                int pozycja_w_koszyku = znajdzWkoszyku(zakup);
                int ilosc_zakupu = lista.get(pozycja_w_koszyku).getIloscPrzedmiotu();
                //sprawdz czy mozna dodac wiecej sztuk zakupu
                if(ilosc_zakupu < ilosc_towaru)
                {
                    //System.out.println("tutaj");
                    int ilosc = ilosc_zakupu + 1;
                    lista.get(znajdzWkoszyku(zakup)).zmienIloscPrzedmiotu(ilosc);
                    Wartosc = Wartosc + zakup.getCenaPrzedmiotu();
                    sklep.zmniejszWsklepie(nazwa);
                }
                else
                {
                    System.out.println("Nie można dodać więcej");
                }
            }
            else // przedmiotu nie ma jeszcze w koszyku
            {
                //System.out.println("koniec...\n");
                lista.add(zakup); Wartosc = Wartosc + zakup.getCenaPrzedmiotu();
            }
        }
        else
        {
            System.out.println("Nie wystarczająca ilość towaru!");
        }
    }; //dodaj przedmiot do koszyka
    
    // usun przedmiot z koszyka
    public void usunKoszyka(String nazwa)
    {
        int pozycja_w_sklepie = sklep.wyszukajTowar(nazwa, "", 0);
        if(pozycja_w_sklepie >=0)
        {
            Towar towar = sklep.getTowar(pozycja_w_sklepie);
            System.out.println("Czy w sklepie jest "+nazwa+": "+towar);
            Zakup zakup = new Zakup(towar.nazwa, towar.kategoria, towar.cena);
            System.out.println("chce usunac: "+zakup+"");
            int pozycja = znajdzWkoszyku(zakup);
            //System.out.println("Nr pozycji: " + pozycja + "\n");
            if(czyWkoszyku(lista.get(pozycja)))
            {
                //System.out.println(lista.get(pozycja)+"\n");
                    lista.remove(pozycja);
                    //Wartosc = Wartosc - lista.get(pozycja).cena;
                    sklep.zwrocWycofane(nazwa, 1);
                    Wartosc = Wartosc - zakup.getCenaPrzedmiotu();
            }
            else //throw new RuntimeException("Brak podanej pozycji w koszyku!\n");  
            {
                System.out.println("Nie ma takiej pozycji w koszyku.");
            }
        }
        else// throw new RuntimeException("Nie ma takiego przedmiotu w sklepie!\n");
        {
            System.out.println("Nie ma takiego towaru!");
        }
    };     
    
    public void oplacKoszyk(){
        stan = Stan.oplacone;
        //zmien ilosc dostepnego towaru w sklepie, poniewaz zostal kupiony!
        for(int i = 0; i < lista.size(); i++)
        {
            for(int j = 0; j < sklep.wszystkie_towary.size(); j++)
            {
                Towar towar = sklep.wszystkie_towary.get(j);
                Zakup zakup = lista.get(i);
                if(zakup.nazwa == null ? towar.nazwa == null : zakup.nazwa.equals(towar.nazwa))
                {
                    int ilosc = towar.getIloscPrzedmiotu() - zakup.getIloscPrzedmiotu();
                    towar.zmienIloscPrzedmiotu(ilosc);                   
                }
            }
        }
    };
    
     @Override public String toString(){ 
        String string="Nr zamowienia: " + getNrZamKoszyk() + ". Stan: " + getStanKoszyka() + ", wartosc: "+getWartoscKoszyka() + "."; 
        return string;
    }
}
