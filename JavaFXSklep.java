/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

import static java.lang.Double.parseDouble;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 *
 * @author jpraj
 */
public class JavaFXSklep extends Application {
    
    Stage window;
    Scene logowanie, menu_klient, menu_admin, zarzadzanie, zarzadzaj_koszyk, wyszukaj_klient;
    Sklep sklep;
    Administrator admin;
    Klient klient;
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        sklep = new Sklep();
        admin = new Administrator("Jan", "Kowalski", sklep);
        klient = new Klient("Adam", "Nowak", sklep);
        
        //logowanie
        Button zaloguj = new Button("Zaloguj");
        TextArea uzytkownik = new TextArea();
        StackPane layout_logowanie = new StackPane();
        Label info = new Label("Prosze podac uzytkownika: klient lub admin");
        VBox log = new VBox(20);
        VBox uzytk = new VBox();
        uzytk.setPrefHeight(15);
        uzytk.getChildren().add(uzytkownik);
        log.getChildren().addAll(uzytk, zaloguj, info);
        layout_logowanie.getChildren().add(log);
        logowanie = new Scene(layout_logowanie, 600, 600);
        
        //admin zarzadzanie
        sklep.dodajTowar(new Towar("jabłko", "owoce", 1.2, 1));
        sklep.dodajTowar(new Towar("masło", "nabiał", 2.3, 1));
        TableView<Towar> table = new TableView<>();
        ObservableList<Towar> towary = FXCollections.observableArrayList();
        towary.setAll(sklep.wszystkie_towary);
        table.setItems(towary);
        table.refresh();
        System.out.println(towary);
        TableColumn nazwaColumn = new TableColumn("Nazwa");
        nazwaColumn.setMinWidth(200);
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        //nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaPrzedmiotu().asObject() );
        TableColumn kategoriaColumn = new TableColumn("Kategoria");
        kategoriaColumn.setMinWidth(200);
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn cenaColumn = new TableColumn("Cena");
        cenaColumn.setMinWidth(50);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn iloscColumn = new TableColumn("Dostępna ilość");
        iloscColumn.setMinWidth(200);
        iloscColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table.getColumns().addAll(nazwaColumn, kategoriaColumn, cenaColumn, iloscColumn);
        //table.setEditable(true);
        
        Label wszystkie_towary = new Label();
        wszystkie_towary.setText(pokazSklep(towary));
       
        Label nazwa_label = new Label("nazwa");
        TextArea nazwa = new TextArea();
        nazwa.setPrefWidth(100);
        Label kategoria_label = new Label("kategoria");
        //checkbox!!
        TextArea kategoria = new TextArea();
        kategoria.setPrefWidth(100);
        Label cena_label = new Label("cena");
        TextArea cena = new TextArea();
        cena.setPrefWidth(30);
        //!!sprawdzenie czy double
        Label znaleziony_towar = new Label();
        Button powrot_menu_admin = new Button("Powrót");
        powrot_menu_admin.setOnAction(e->
            {   
                window.setScene(menu_admin);
                nazwa.clear();
                kategoria.clear();
                cena.clear();
                znaleziony_towar.setText("");
        });
        Button dodaj_towar = new Button("Dodaj");
        Button znajdz_towar = new Button("Wyszukaj");
        Button wyczysc = new Button("Wyczyść");
        Button zwieksz_ilosc = new Button("+");
        zwieksz_ilosc.setVisible(false);
        Button zmniejsz_ilosc = new Button(" - ");
        zmniejsz_ilosc.setVisible(false);
        Button usun_towar = new Button("Usuń");
        usun_towar.setVisible(false);
        
        VBox tabela_towarow = new VBox();
        HBox przyciski_towar = new HBox(20);
        przyciski_towar.setPrefHeight(15);
        HBox wyszukiwanie_towar = new HBox(20);
        wyszukiwanie_towar.getChildren().addAll(znaleziony_towar,zwieksz_ilosc,zmniejsz_ilosc, usun_towar);
        //przyciski_towar.setPrefHeight(20);
        przyciski_towar.getChildren().addAll(nazwa_label, nazwa, kategoria_label, kategoria, cena_label, cena, dodaj_towar, znajdz_towar, wyczysc);
        tabela_towarow.getChildren().addAll(table);
        VBox layout_zarzadzanie = new VBox(30);
        layout_zarzadzanie.getChildren().addAll(tabela_towarow, przyciski_towar, wyszukiwanie_towar,powrot_menu_admin);
        zarzadzanie = new Scene(layout_zarzadzanie, 1200, 800);
        
        //admin menu
        Button zarzadzaj = new Button("Zarzadzaj produktami");
        Button wyloguj_admin = new Button("Wyloguj");
        zarzadzaj.setOnAction(e -> window.setScene(zarzadzanie));
        wyloguj_admin.setOnAction(e -> window.setScene(logowanie));
        VBox layout_menu_admin = new VBox(20);
        layout_menu_admin.getChildren().addAll(zarzadzaj, wyloguj_admin);
        menu_admin = new Scene(layout_menu_admin, 600, 600);
        
        
        zaloguj.setOnAction((ActionEvent event) -> {
            System.out.println(uzytkownik.getText());
            if(uzytkownik.getText().equals("admin"))
            {
                window.setScene(menu_admin);
            }
            else if(uzytkownik.getText().equals("klient"))
            {
                window.setScene(menu_klient);
            }
            else
            {
                info.setText("Bledny uzytkownik");
            }
        });
        
        dodaj_towar.setOnAction((ActionEvent event) -> {
            if(!nazwa.getText().isEmpty() && !kategoria.getText().isEmpty() && !cena.getText().isEmpty())
            {
                admin.utworzTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()),1);
                //ODSWIEZ WIDOK!!!
                nazwa.clear();
                kategoria.clear();
                cena.clear();
                znaleziony_towar.setText("Dodano do sklepu.");
                towary.setAll(sklep.wszystkie_towary);
                table.setItems(towary);
                table.refresh();
                System.out.println(towary + "\n VS \n");
                sklep.pokazTowary();
                wszystkie_towary.setText(pokazSklep(towary));
            }
            else
            {
                znaleziony_towar.setText("Podaj wszystkie dane");
            }
        });
        
        znajdz_towar.setOnAction((ActionEvent event) -> {
            if(!nazwa.getText().isEmpty() && !kategoria.getText().isEmpty() && !cena.getText().isEmpty())
            {
                try
                {
                    Towar towar = sklep.getTowar(sklep.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText())));
                    znaleziony_towar.setText(towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                    zwieksz_ilosc.setVisible(true);
                    zmniejsz_ilosc.setVisible(true);
                    usun_towar.setVisible(true);
                }
                catch(ArrayIndexOutOfBoundsException exception)
                {
                    znaleziony_towar.setText("Brak podanego przedmiotu w sklepie.");
                }
            }
            else
            {
                znaleziony_towar.setText("Podaj wszystkie dane");
            }
        });
        
        wyczysc.setOnAction((ActionEvent event) ->{
            nazwa.clear();
            kategoria.clear();
            cena.clear();
            zwieksz_ilosc.setVisible(false);
            zmniejsz_ilosc.setVisible(false);
            usun_towar.setVisible(false);
            znaleziony_towar.setText("");
        });
        
        zwieksz_ilosc.setOnAction((ActionEvent event) ->{
            int ilosc = sklep.getTowar(sklep.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()))).getIloscPrzedmiotu()+1;
            sklep.getTowar(sklep.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()))).zmienIloscPrzedmiotu(ilosc);
            Towar towar = admin.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()));
            znaleziony_towar.setText(towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
            towary.setAll(sklep.wszystkie_towary);
            table.setItems(towary);
            table.refresh();
            wszystkie_towary.setText(pokazSklep(towary));
        });
        
        zmniejsz_ilosc.setOnAction((ActionEvent event) ->{
            int ilosc = sklep.getTowar(sklep.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()))).getIloscPrzedmiotu()-1;
            Towar towar = admin.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()));
            if(ilosc >=0)
            {
                sklep.getTowar(sklep.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()))).zmienIloscPrzedmiotu(ilosc);
                znaleziony_towar.setText(towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                towary.setAll(sklep.wszystkie_towary);
                table.setItems(towary);
                table.refresh();
                wszystkie_towary.setText(pokazSklep(towary));
            }
            else
            {
                znaleziony_towar.setText("Nie można więcej odjąć produktu!\n" + towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                
            }
        });       
        
        usun_towar.setOnAction((ActionEvent event) ->{
            admin.usunTowar(nazwa.getText());
            znaleziony_towar.setText("Towar usunięty ze sklepu.");
            nazwa.clear();
            kategoria.clear();
            cena.clear();
            zwieksz_ilosc.setVisible(false);
            zmniejsz_ilosc.setVisible(false);
            usun_towar.setVisible(false);
            towary.setAll(sklep.wszystkie_towary);
            table.setItems(towary);
            table.refresh();
            System.out.println(towary + "\n VS \n");
            sklep.pokazTowary();
            wszystkie_towary.setText(pokazSklep(towary));
        });
        
        
        //klient zamowienia
        klient.dodajDoKoszyka("jabłko", "owoce", 1.2);
        ObservableList<Zakup> zamowienie = FXCollections.observableArrayList();
        zamowienie.setAll(klient.getKoszyk().getListaZakupow());
        
        TableView<Zakup> table_zamowienie = new TableView<>();
        table_zamowienie.setItems(zamowienie);
        table_zamowienie.refresh();
        TableColumn nazwaColumn_z = new TableColumn("Nazwa");
        nazwaColumn_z.setMinWidth(200);
        nazwaColumn_z.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn kategoriaColumn_z = new TableColumn("Kategoria");
        kategoriaColumn_z.setMinWidth(200);
        kategoriaColumn_z.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn cenaColumn_z = new TableColumn("Cena");
        cenaColumn_z.setMinWidth(50);
        cenaColumn_z.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn iloscColumn_z = new TableColumn("Zamówiona ilość");
        iloscColumn_z.setMinWidth(200);
        iloscColumn_z.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table_zamowienie.getColumns().addAll(nazwaColumn_z, kategoriaColumn_z, cenaColumn_z, iloscColumn_z);
        VBox tabela_zamowienie = new VBox(30);
        tabela_zamowienie.getChildren().addAll(table_zamowienie);
        
        Label nazwa_label_z = new Label("nazwa");
        TextArea nazwa_z = new TextArea();
        nazwa_z.setPrefWidth(100);
        Label kategoria_label_z = new Label("kategoria");
        Label znalezione_z = new Label();
        Label wartosc_koszyka = new Label("WARTOŚĆ KOSZYKA WYNOSI: "+String.valueOf(klient.getKoszyk().getWartoscKoszyka()));
        //checkbox!!
        TextArea kategoria_z = new TextArea();
        kategoria_z.setPrefWidth(100);
        Label cena_label_z = new Label("cena");
        TextArea cena_z = new TextArea();
        cena_z.setPrefWidth(30);
        Button znajdz_zakup_z = new Button("Znajdż");
        Button wyczysc_z = new Button("Wyczyść");
        Button zwieksz_ilosc_z = new Button("+");
        zwieksz_ilosc_z.setVisible(false);
        Button zmniejsz_ilosc_z = new Button(" - ");
        zmniejsz_ilosc_z.setVisible(false);
        HBox wyszukaj_przedmiot_z = new HBox(20);
        wyszukaj_przedmiot_z.setPrefHeight(20);
        wyszukaj_przedmiot_z.getChildren().addAll(nazwa_label_z, nazwa_z, kategoria_label_z, kategoria_z, cena_label_z, cena_z, znajdz_zakup_z, wyczysc_z);
        
        Label zamowienie_klienta = new Label();
        zamowienie_klienta.setText(pokazZakupy(zamowienie));
        Button powrot = new Button("Powrót");
        Button usun_z_koszyka = new Button("Usuń");
        usun_z_koszyka.setVisible(false);
        powrot.setOnAction(e -> window.setScene(menu_klient));
        
        Button oplac = new Button("OPŁAĆ");
        
        HBox akcja_zamowienie = new HBox(30);
        akcja_zamowienie.getChildren().addAll(znalezione_z,zwieksz_ilosc_z, zmniejsz_ilosc_z, usun_z_koszyka);
        
        VBox layout_koszyk = new VBox(30);
        layout_koszyk.getChildren().addAll(tabela_zamowienie, wyszukaj_przedmiot_z, akcja_zamowienie, powrot, wartosc_koszyka, oplac);
        zarzadzaj_koszyk = new Scene(layout_koszyk, 900, 600);
        
        //klient wyszukaj produkt  
        Label nazwa_label2 = new Label("nazwa");
        TextArea nazwa2 = new TextArea();
        nazwa2.setPrefWidth(100);
        Label kategoria_label2 = new Label("kategoria");
        Label znaleziony_zakup = new Label();
        //checkbox!!
        TextArea kategoria2 = new TextArea();
        kategoria2.setPrefWidth(100);
        Label cena_label2 = new Label("cena");
        TextArea cena2 = new TextArea();
        cena2.setPrefWidth(30);
        Button znajdz_zakup = new Button("Znajdż");
        HBox wyszukaj_przedmiot = new HBox(20);
        wyszukaj_przedmiot.setPrefHeight(20);
        wyszukaj_przedmiot.getChildren().addAll(nazwa_label2, nazwa2, kategoria_label2, kategoria2, cena_label2, cena2, znajdz_zakup);
        VBox layout_wyszukaj = new VBox(30);
        
        TableView<Towar> table_klient_sklep = new TableView<>();
        ObservableList<Towar> towary2 = FXCollections.observableArrayList();
        towary2.setAll(sklep.wszystkie_towary);
        table_klient_sklep.setItems(towary);
        table_klient_sklep.refresh();
        TableColumn nazwaColumn2 = new TableColumn("Nazwa");
        nazwaColumn2.setMinWidth(200);
        nazwaColumn2.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn kategoriaColumn2 = new TableColumn("Kategoria");
        kategoriaColumn2.setMinWidth(200);
        kategoriaColumn2.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn cenaColumn2 = new TableColumn("Cena");
        cenaColumn2.setMinWidth(50);
        cenaColumn2.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn iloscColumn2 = new TableColumn("Dostępna ilość");
        iloscColumn2.setMinWidth(200);
        iloscColumn2.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table_klient_sklep.getColumns().addAll(nazwaColumn2, kategoriaColumn2, cenaColumn2, iloscColumn2);
        VBox tabela_sklep = new VBox(30);
        tabela_sklep.getChildren().addAll(table_klient_sklep);
        
        Button powrot_menu = new Button("Powrót");
        powrot_menu.setOnAction(e -> 
        {
            window.setScene(menu_klient);
        }); 
        Button kup = new Button("Kup");
        kup.setVisible(false);
        HBox akcja_zakup = new HBox(30);
        akcja_zakup.getChildren().addAll(znaleziony_zakup, kup);
        layout_wyszukaj.getChildren().addAll(tabela_sklep, wyszukaj_przedmiot, akcja_zakup, powrot_menu);
        wyszukaj_klient = new Scene(layout_wyszukaj, 900, 600);
        
        //klient menu
        Button wyszukaj = new Button("Wyszukaj produkt");
        wyszukaj.setOnAction(e -> window.setScene(wyszukaj_klient));
        Button zamowienia = new Button("Moje zamowienia");
        zamowienia.setOnAction(e -> 
        {
            zamowienie_klienta.setText(pokazZakupy(zamowienie));
            znaleziony_zakup.setText("");
            zwieksz_ilosc_z.setVisible(false);
            zmniejsz_ilosc_z.setVisible(false);
            usun_z_koszyka.setVisible(false);
            window.setScene(zarzadzaj_koszyk); 
        });
        Button wyloguj_klient = new Button("Wyloguj");
        wyloguj_klient.setOnAction(e -> window.setScene(logowanie));
        VBox layout_menu_klient = new VBox(20);
        layout_menu_klient.getChildren().addAll(wyszukaj, zamowienia, wyloguj_klient);
        menu_klient = new Scene(layout_menu_klient, 600, 600);
        
        znajdz_zakup.setOnAction((ActionEvent event) -> {
            if(!nazwa2.getText().isEmpty() && !kategoria2.getText().isEmpty() && !cena2.getText().isEmpty())
            {
                try
                {
                    Towar towar = sklep.getTowar(sklep.wyszukajTowar(nazwa2.getText(), kategoria2.getText(), parseDouble(cena2.getText())));
                    znaleziony_zakup.setText(towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                    kup.setVisible(true);
                }
                catch(ArrayIndexOutOfBoundsException exception)
                {
                    znaleziony_zakup.setText("Brak podanego przedmiotu w sklepie.");
                }
            }
            else
            {
                znaleziony_zakup.setText("Podaj wszystkie dane");
            }
        });
        
        kup.setOnAction(e -> 
        {
            Towar towar = sklep.getTowar(sklep.wyszukajTowar(nazwa2.getText(), kategoria2.getText(), parseDouble(cena2.getText())));
            int ilosc = sklep.getTowar(sklep.wyszukajTowar(nazwa2.getText(), kategoria2.getText(), parseDouble(cena2.getText()))).getIloscPrzedmiotu()-1;
            if(ilosc >=0)
            {
                klient.dodajDoKoszyka(nazwa2.getText(), kategoria2.getText(), parseDouble(cena2.getText()));
                sklep.zmniejszWsklepie(nazwa2.getText(), kategoria2.getText(), parseDouble(cena2.getText()));
                zamowienie.setAll(klient.getKoszyk().getListaZakupow());
                table_zamowienie.setItems(zamowienie);
                table_zamowienie.refresh();
                towary.setAll(sklep.wszystkie_towary);
                table_klient_sklep.setItems(towary);
                table_klient_sklep.refresh();
                nazwa2.clear();
                kategoria2.clear();
                cena2.clear();
                znaleziony_zakup.setText("Dodano do koszyka");   
            }
            else
            {
                znaleziony_zakup.setText("Brak dostępnych produktów");
            }
            kup.setVisible(false);
        });
        
        znajdz_zakup_z.setOnAction((ActionEvent event) -> {
            if(!nazwa_z.getText().isEmpty() && !kategoria_z.getText().isEmpty() && !cena_z.getText().isEmpty())
            {
                try
                {
                    int pozycja = klient.getKoszyk().znajdzWkoszyku(new Zakup(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText())));
                    Zakup zakup = klient.getKoszyk().getZakup(pozycja);
                    znalezione_z.setText(zakup.nazwa + " w kategorii " + zakup.kategoria + " w cenie " + zakup.cena + ", zamówione: " + zakup.getIloscPrzedmiotu());
                    zwieksz_ilosc_z.setVisible(true);
                    zmniejsz_ilosc_z.setVisible(true);
                    usun_z_koszyka.setVisible(true);
                }
                catch(ArrayIndexOutOfBoundsException exception)
                {
                    znalezione_z.setText("Brak podanego przedmiotu w zamówieniu.");
                }
            }
            else
            {
                znalezione_z.setText("Podaj wszystkie dane");
            }
        });
        
        wyczysc_z.setOnAction((ActionEvent event) ->{
            nazwa_z.clear();
            kategoria_z.clear();
            cena_z.clear();
            zwieksz_ilosc_z.setVisible(false);
            zmniejsz_ilosc_z.setVisible(false);
            usun_z_koszyka.setVisible(false);
            znalezione_z.setText("");
        });
        
        zwieksz_ilosc_z.setOnAction((ActionEvent event) ->{
            Towar towar = sklep.getTowar(sklep.wyszukajTowar(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText())));
            int dostepne = towar.getIloscPrzedmiotu();
            int pozycja = klient.getKoszyk().znajdzWkoszyku(new Zakup(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText())));
            Zakup zakup = klient.getKoszyk().getZakup(pozycja);
            if(dostepne >0)
            {
                sklep.zmniejszWsklepie(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText()));
                zakup.zmienIloscPrzedmiotu(zakup.getIloscPrzedmiotu()+1);
                znalezione_z.setText(zakup.nazwa + " w kategorii " + zakup.kategoria + " w cenie " + zakup.cena + ", zamówione: " + zakup.getIloscPrzedmiotu());
                zamowienie.setAll(klient.getKoszyk().getListaZakupow());
                towary.setAll(sklep.wszystkie_towary);
                table_klient_sklep.setItems(towary);
                table_klient_sklep.refresh();
                table_zamowienie.setItems(zamowienie);
                table_zamowienie.refresh();
            }
            else
            {
                znalezione_z.setText("Brak dostępnych produktów w sklepie! "+zakup.nazwa + " w kategorii " + zakup.kategoria + " w cenie " + zakup.cena + ", zamówione: " + zakup.getIloscPrzedmiotu());
            }
        });
        
        zmniejsz_ilosc_z.setOnAction((ActionEvent event) ->{
            int pozycja = klient.getKoszyk().znajdzWkoszyku(new Zakup(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText())));
            Zakup zakup = klient.getKoszyk().getZakup(pozycja);
            int ilosc_zam = zakup.getIloscPrzedmiotu();
            int ilosc_sklep = sklep.getTowar(sklep.wyszukajTowar(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText()))).getIloscPrzedmiotu()+1;
            Towar towar = admin.wyszukajTowar(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText()));
            if(ilosc_zam >=2)
            {
                sklep.getTowar(sklep.wyszukajTowar(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText()))).zmienIloscPrzedmiotu(ilosc_sklep);
                zakup.zmienIloscPrzedmiotu(ilosc_zam-1);
                znalezione_z.setText(towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                towary.setAll(sklep.wszystkie_towary);
                table.setItems(towary);
                table.refresh();
                zamowienie.setAll(klient.getKoszyk().getListaZakupow());
                table_zamowienie.setItems(zamowienie);
                table_zamowienie.refresh();
            }
            else
            {
                znalezione_z.setText("Nie można więcej odjąć produktu, usun produkt z koszyka.");
                zmniejsz_ilosc_z.setVisible(false);
                zwieksz_ilosc_z.setVisible(false);
                usun_z_koszyka.setVisible(false);
                nazwa_z.clear();
                kategoria_z.clear();
                cena_z.clear();
                
            }
        });       
        
        usun_z_koszyka.setOnAction((ActionEvent event) -> {
            if(!nazwa_z.getText().isEmpty() && !kategoria_z.getText().isEmpty() && !cena_z.getText().isEmpty())
            {
                try
                {
                    int pozycja = sklep.wyszukajTowar(nazwa_z.getText(), kategoria_z.getText(), parseDouble(cena_z.getText()));
                    klient.usunKoszyka(nazwa_z.getText());
                    sklep.zwrocWycofane(nazwa_z.getText(), pozycja);
                    zamowienie.setAll(klient.getKoszyk().getListaZakupow());
                    towary.setAll(sklep.wszystkie_towary);
                    table.setItems(towary);
                    table.refresh();
                    table_klient_sklep.setItems(towary);
                    table_klient_sklep.refresh();
                    znalezione_z.setText("Usunięto z koszyka.");
                }
                catch(ArrayIndexOutOfBoundsException exception)
                {
                    znalezione_z.setText("Brak podanego przedmiotu w zamówieniu.");
                }
                zmniejsz_ilosc_z.setVisible(false);
                zwieksz_ilosc_z.setVisible(false);
                usun_z_koszyka.setVisible(false);
            }
            else
            {
                znalezione_z.setText("Podaj wszystkie dane");
            }
        });
        
        oplac.setOnAction((ActionEvent event) ->{
            try
                {
                    klient.getKoszyk().oplacKoszyk();
                    zamowienie.setAll(klient.getKoszyk().getListaZakupow());
                    towary.setAll(sklep.wszystkie_towary);
                    table_klient_sklep.setItems(towary);
                    table_klient_sklep.refresh();
                    znalezione_z.setText("Opłacono zamówienie.");
                 }
                catch(Exception e)
                {
                    
                }
            
        });
       
        
        window.setScene(logowanie);
        window.setTitle("Sklep");
        window.show();
    }    
    
     public String pokazSklep(ObservableList<Towar> towary)
        {
            int r = towary.size();
            String sklep = "W sklepie znajdują się: \n";
            for(int i=0; i< r; i++)
            {
                sklep = sklep + towary.get(i).nazwa + " w: " + towary.get(i).kategoria + ", cena: " + towary.get(i).cena + ", dostępne: " + towary.get(i).getIloscPrzedmiotu() + "\n"  ;
            }
            return sklep;
        };
     
     public String pokazZakupy(ObservableList<Zakup> zakupy)
        {
            int r = zakupy.size();
            String koszyk = "W koszyku znajdują się: \n";
            for(int i=0; i< r; i++)
            {
                koszyk = koszyk + zakupy.get(i).nazwa + " w: " + zakupy.get(i).kategoria + ", cena: " + zakupy.get(i).cena + ", zamówione: " + zakupy.get(i).getIloscPrzedmiotu() + "\n"  ;
            }
            return koszyk;
        };
    
}
