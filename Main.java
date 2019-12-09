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
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sklep sklep = new Sklep();
        
        Administrator admin = new Administrator("Jan", "Kowalski", sklep);
        Klient klient = new Klient("Ewa", "Kowalska", sklep);
        admin.utworzTowar("chleb", "pieczywo", 2.5, 3);
        admin.utworzTowar("szynka", "nabiał", 12.3, 2);
        admin.utworzTowar("dżem", "przetwory", 1.5, 5);
        admin.utworzTowar("woda", "napoje", 1.2, 1);
        admin.utworzTowar("mleko", "nabiał", 2.1, 10);
        admin.utworzTowar("marmolada", "przetwory", 0.9, 0);
        admin.utworzTowar("powidła", "przetwory", 1.8, 2);
        admin.utworzTowar("sok", "napoje", 2.3, 2);
        //sklep.pokazTowary();
        admin.usunTowar("sok");
        //sklep.pokazTowary();
        admin.usunTowar("pomarancze");
        
        //sklep.wyszukajTowar("chleb", "", 0);
        System.out.println("Szukam po nazwie:");
        klient.wyszukajTowarNazwa("mleko");
        klient.wyszukajTowarNazwa("lizak");
        System.out.println("Klient szuka kategorii:");
        klient.wyszukajTowarKategoria("nabiał");
        admin.wyszukajTowarKategoria("słodycze");
        System.out.println("Admin szuka po cenie:");
        admin.wyszukajTowarCena(2.5);
        admin.wyszukajTowarCena(2.7);
        
        System.out.println("Klient dodaje do koszyka chleb i szynke:");
        klient.dodajDoKoszyka("chleb");
        klient.dodajDoKoszyka("szynka");
        System.out.println("Zawartosc koszyka:");
        klient.pokazKoszyk();
        System.out.println("Usuwam z koszyka szynke:");
        klient.usunKoszyka("szynka");
        klient.pokazKoszyk();
        System.out.println("Usuwam z koszyka dzem, ktorego nie ma:");
        klient.usunKoszyka("dzem");
        
        System.out.println("Admin sprawdza stan koszyka:");
        admin.sprawdzStanKoszyka(klient.getKoszyk());
        klient.sprawdzStanKoszyka(klient.getKoszyk());
        
        System.out.println("zmieniam ilość towaru:");
        Towar towar = sklep.getTowar(sklep.wyszukajTowar("woda", null, 0));
        admin.zmienIloscTowaru(towar, 3);
        sklep.pokazTowary();
    }    
}
