package com.amal.amalproject.controllers;



import com.amal.amalproject.MainApplication3;
import com.amal.amalproject.entities.Dons;
import com.amal.amalproject.models.DonsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.stage.StageStyle;

public class AccueilController implements Initializable {



    private Parent root;

    DonsModel donsModel = new DonsModel();
    ObservableList<Dons> don = FXCollections.observableArrayList();

    @FXML
    protected Button BAjouter,BAccueil,BModifier,BSupprimer,btnretour;

    @FXML
    private ImageView x;

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView addIcon;

    @FXML
    private ImageView modifyIcon;

    @FXML
    private ImageView removeIcon;

    @FXML
    private Stage stage;

    @FXML
    private TableColumn<Dons,Integer> id_col;

    @FXML
    private TableColumn <Dons,String> lib_col;

    @FXML
    private TableColumn <Dons,String> desc_col;

    @FXML
    private TableColumn <Dons,String> photo_col;

    @FXML
    private TableColumn <Dons,String> type_col;

    @FXML
    protected TableView TableDons;

    @FXML
    private TextField RechercheID;





    @FXML
    public void goToAccueil(ActionEvent event) {

        try {

            if (event.getSource() == BAccueil) {
                stage = (Stage) BAccueil.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(
                        MainApplication3.class.getResource("Accueil.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void goToAjouter (ActionEvent event) {

        try {

            if (event.getSource() == BAjouter) {
                stage = (Stage) BAjouter.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(
                        MainApplication3.class.getResource("Ajouter.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void goToModifier (ActionEvent event){

        try {

            if (event.getSource() == BModifier) {
                stage = (Stage) BAccueil.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(
                        MainApplication3.class.getResource("Modifier.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void goToSupprimer (ActionEvent event)  {

        try {

            if (event.getSource() == BSupprimer) {
                stage = (Stage) BAccueil.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(
                        MainApplication3.class.getResource("Supprimer.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void exit(){
        System.exit(0);
    }

    public void btnRetourClicked (ActionEvent event) {

        try {

            if (event.getSource() == btnretour) {
                stage = (Stage) btnretour.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(
                        MainApplication3.class.getResource("Home.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id_col.setCellValueFactory(new PropertyValueFactory<Dons,Integer>("id_dons"));
        lib_col.setCellValueFactory(new PropertyValueFactory<Dons,String>("libele_dons"));
        desc_col.setCellValueFactory(new PropertyValueFactory<Dons,String>("description_dons"));
        photo_col.setCellValueFactory(new PropertyValueFactory<Dons,String>("photo_produit_dons"));
        type_col.setCellValueFactory(new PropertyValueFactory<Dons,String>("type_dons"));


        TableDons.setItems(donsModel.getAllDons());


        donsModel.getAllDons().forEach(System.out::println);
        FilteredList<Dons> filtredData = new FilteredList<> (don, b -> true);

        RechercheID.textProperty().addListener((observable, oldValue, newValue) -> {
            filtredData.setPredicate(don -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (don.getLibele_dons().toLowerCase().indexOf(lowerCaseFilter)  != -1) {
                    return true;
                } else if (don.getType_dons().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;

            });
        });

        SortedList<Dons> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(TableDons.comparatorProperty());
        TableDons.setItems(sortedData);


    }
}
