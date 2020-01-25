/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxsklep;

import static java.lang.Double.parseDouble;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
        TableView table = new TableView();
        //ObservableList<Towar> towary = FXCollections.observableArrayList();
        //towary.add(new Towar("jablko", "owoce", 12.3,1));
        //towary.add(new Towar("masło", "nabiał", 3.2, 1));
        TableColumn<Towar,String> nazwaColumn = new TableColumn<>("Nazwa");
        nazwaColumn.setMinWidth(200);
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Towar,String> kategoriaColumn = new TableColumn<>("Kategoria");
        kategoriaColumn.setMinWidth(200);
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<Towar,Double> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setMinWidth(50);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn<Towar,String> iloscColumn = new TableColumn<>("Dostępna ilość");
        iloscColumn.setMinWidth(200);
        iloscColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
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
        
        ObservableList<Towar> towary = FXCollections.observableArrayList(sklep.getWszystkieTowary());
        table.getColumns().addAll(nazwaColumn, kategoriaColumn, cenaColumn, iloscColumn);
        VBox tabela_towarow = new VBox();
        HBox przyciski_towar = new HBox(20);
        przyciski_towar.setPrefHeight(20);
        przyciski_towar.getChildren().addAll(nazwa_label, nazwa, kategoria_label, kategoria, cena_label, cena, dodaj_towar);
        tabela_towarow.getChildren().addAll(table);
        VBox layout_zarzadzanie = new VBox(30);
        layout_zarzadzanie.getChildren().addAll(tabela_towarow, przyciski_towar);
        zarzadzanie = new Scene(layout_zarzadzanie, 900, 600);
        
        //admin menu
        Button zarzadzaj = new Button("Zarzadzaj produktami");
        Button wyloguj_admin = new Button("Wyloguj");
        zarzadzaj.setOnAction(e -> window.setScene(zarzadzanie));
        wyloguj_admin.setOnAction(e -> window.setScene(logowanie));
        VBox layout_menu_admin = new VBox(20);
        layout_menu_admin.getChildren().addAll(zarzadzaj, wyloguj_admin);
        menu_admin = new Scene(layout_menu_admin, 600, 600);
        
        

        
        
        zaloguj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
            }
        });
        
        //dodaj_towar.setOnAction(e -> admin.utworzTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()), 1));
        //dodaj_towar.setOnAction(e -> sklep.dodajTowar(new Towar(nazwa.getText(),kategoria.getText(),parseDouble(cena.getText()),1)));
        
        dodaj_towar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                admin.utworzTowar(nazwa.getText(), kategoria.getText(), parseDouble(cena.getText()),1);
                //WYCZYŚCIC TEXTAREA I ODSWIEZ WIDOK!!!
                nazwa.clear();
                kategoria.clear();
                cena.clear();
                System.out.println("Dodano: "+nazwa.getText());
                System.out.println("Sklep:");
                System.out.println(sklep.getWszystkieTowary());
                table.setItems(towary);
                table.refresh();
            }
        });
        
        
        
        window.setScene(logowanie);
        window.setTitle("Sklep");
        window.show();
    }    
    
}
