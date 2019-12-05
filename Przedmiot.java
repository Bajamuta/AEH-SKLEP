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
public class Przedmiot {
    private String nazwa;
    private String kategoria;
    private float cena;
    private int ilosc;
   
    Przedmiot(String nazwa, String kategoria, float cena, int ilosc){this.nazwa = nazwa; this.kategoria = kategoria; this.cena = cena; this.ilosc = ilosc;};

    public void zmienCenaPrzedmiotu(float cena){this.cena = cena;};
    public void zmienIloscPrzedmiotu(int ilosc){this.ilosc = ilosc;};
    
    public String getNazwaPrzedmiotu(){return nazwa;};
    public String getKategoriaPrzedmiotu(){return kategoria;};
    public int getIloscPrzedmiotu(){return ilosc;};
    public float getCenaPrzedmiotu(){return cena;};
    
    @Override 
    public String toString(){ 
        String string=getNazwaPrzedmiotu() +" w kategorii " + getKategoriaPrzedmiotu() + ". Ilość: " + getCenaPrzedmiotu() + ", cena: "+getCenaPrzedmiotu() + "."; 
        return string;
    }
}
