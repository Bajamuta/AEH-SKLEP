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
        
        Osoba Admin = new Osoba("Kowalski", "Kazimierz", Pozycja.administrator)
        {
             public void utworzProdukt(String nazwa, String kategoria, float cena, int ilosc){
                Przedmiot(nazwa, kategoria, cena, ilosc);
            };
        }
        Osoba Klient = new Osoba("Jawroska", "Milena", Pozycja.klient){
            Koszyk koszyk = new Koszyk(1234);
        };
    }
    
    
}
