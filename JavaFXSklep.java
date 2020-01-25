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
    Scene logowanie, menu_klient, menu_admin, zarzadzanie;
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
        uzytk.setPrefHeight(20);
        uzytk.getChildren().add(uzytkownik);
        log.getChildren().addAll(uzytk, zaloguj, info);
        layout_logowanie.getChildren().add(log);
        logowanie = new Scene(layout_logowanie, 600, 600);
        
        //klient menu
        Button wyszukaj = new Button("Wyszukaj produkt");
        Button zamowienia = new Button("Moje zamowienia");
        Button wyloguj_klient = new Button("Wyloguj");
        wyloguj_klient.setOnAction(e -> window.setScene(logowanie));
        VBox layout_menu_klient = new VBox(20);
        layout_menu_klient.getChildren().addAll(wyszukaj, zamowienia, wyloguj_klient);
        menu_klient = new Scene(layout_menu_klient, 600, 600);
        
        //admin zarzadzanie
        sklep.dodajTowar(new Towar("jabłko", "owoce", 1.2, 1));
        sklep.dodajTowar(new Towar("masło", "nabiał", 2.3, 1));
        //TableView<Towar> table = new TableView<>();
        ObservableList<Towar> towary = FXCollections.observableArrayList();
        towary.setAll(sklep.wszystkie_towary);
        /*table.setItems(towary);
        table.refresh();
        System.out.println(towary);
        TableColumn nazwaColumn = new TableColumn<Towar, String>("Nazwa");
        nazwaColumn.setMinWidth(200);
        //nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaPrzedmiotu().asObject() );
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
        table.setEditable(true);*/
        
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
        Button dodaj_towar = new Button("Dodaj");
        Button znajdz_towar = new Button("Wyszukaj");
        Button wyczysc = new Button("Wyczyść");
        Label znaleziony_towar = new Label();
        Button zwieksz_ilosc = new Button("+");
        zwieksz_ilosc.setVisible(false);
        Button zmniejsz_ilosc = new Button(" - ");
        zmniejsz_ilosc.setVisible(false);
        Button usun_towar = new Button("Usuń");
        usun_towar.setVisible(false);
        
        //VBox tabela_towarow = new VBox();
        HBox przyciski_towar = new HBox(20);
        HBox wyszukiwanie_towar = new HBox(20);
        wyszukiwanie_towar.getChildren().addAll(znaleziony_towar,zwieksz_ilosc,zmniejsz_ilosc, usun_towar);
        przyciski_towar.setPrefHeight(20);
        przyciski_towar.getChildren().addAll(nazwa_label, nazwa, kategoria_label, kategoria, cena_label, cena, dodaj_towar, znajdz_towar, wyczysc);
        //tabela_towarow.getChildren().addAll(table);
        VBox layout_zarzadzanie = new VBox(30);
        layout_zarzadzanie.getChildren().addAll(wszystkie_towary, przyciski_towar, wyszukiwanie_towar);
        zarzadzanie = new Scene(layout_zarzadzanie, 900, 600);
        
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
                /*table.setItems(towary);
                table.refresh();
                System.out.println(towary + "\n VS \n");*/
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
                    Towar towar = admin.wyszukajTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()));
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
            /*table.setItems(towary);
            table.refresh();*/
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
                /*table.setItems(towary);
                table.refresh();*/
                wszystkie_towary.setText(pokazSklep(towary));
            }
            else
            {
                znaleziony_towar.setText("Nie można więcej odjąć produktu!\n" + towar.nazwa + " w kategorii " + towar.kategoria + " w cenie " + towar.cena + ", dostępna ilość: " + towar.getIloscPrzedmiotu());
                
            }
        });       
        
        usun_towar.setOnAction((ActionEvent event) ->{
            admin.usunTowar(nazwa.getText());
            znaleziony_towar.setText("Towar usunięty ze sklpeu.");
            nazwa.clear();
            kategoria.clear();
            cena.clear();
            zwieksz_ilosc.setVisible(false);
            zmniejsz_ilosc.setVisible(false);
            usun_towar.setVisible(false);
            towary.setAll(sklep.wszystkie_towary);
            /*table.setItems(towary);
            table.refresh();
            System.out.println(towary + "\n VS \n");*/
            sklep.pokazTowary();
            wszystkie_towary.setText(pokazSklep(towary));
        });
        
       
        
        window.setScene(zarzadzanie);
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
    
}
