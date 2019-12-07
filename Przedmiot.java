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
    private String nazwa;
    private String kategoria;
    private float cena;
   
    Przedmiot(String nazwa, String kategoria, float cena){this.nazwa = nazwa; this.kategoria = kategoria; this.cena = cena;};

    public abstract void zmienIloscPrzedmiotu(int ilosc);
    
    public String getNazwaPrzedmiotu(){return nazwa;};
    public String getKategoriaPrzedmiotu(){return kategoria;};
    public abstract int getIloscPrzedmiotu();
    public float getCenaPrzedmiotu(){return cena;};
    
    @Override 
    public String toString(){ 
        String string=getNazwaPrzedmiotu() +" w kategorii " + getKategoriaPrzedmiotu() + ". Ilość: " + getCenaPrzedmiotu() + ", cena: "+getCenaPrzedmiotu() + "."; 
        return string;
    }
}
