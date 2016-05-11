package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.PortoModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private PortoModel model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxAutore1;

    @FXML
    private ComboBox<String> boxAutore2;

    @FXML
    private Button btnCoautori;

    @FXML
    private Button btnCluster;

    @FXML
    private Button btnArticoli;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doArticoli(ActionEvent event) {

    }

    @FXML
    void doCluster(ActionEvent event) {

    }

    @FXML
    void doCoautori(ActionEvent event) {

    }
    
    
    public void setModel(PortoModel model) {
    	this.model=model;
    }

    @FXML
    void initialize() {
        assert boxAutore1 != null : "fx:id=\"boxAutore1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxAutore2 != null : "fx:id=\"boxAutore2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticoli != null : "fx:id=\"btnArticoli\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Porto.fxml'.";
        
        model = new PortoModel();
        
        boxAutore1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if(newValue != "" && newValue != null) {
					btnCluster.setDisable(true);
					btnCoautori.setDisable(false);
					boxAutore2.setDisable(false);
					btnArticoli.setDisable(true);
				}
				
				if(newValue == "" || newValue== null) {
					btnCluster.setDisable(false);
					btnCoautori.setDisable(true);
					boxAutore2.setDisable(true);
					btnArticoli.setDisable(true);
					
				}

			}

		});
        
        
        boxAutore2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if(newValue != "" && newValue != null) {
					btnCluster.setDisable(true);
					btnCoautori.setDisable(true);
					btnArticoli.setDisable(false);
				}
				
				if(newValue == "" || newValue==null) {
					btnCluster.setDisable(true);
					btnCoautori.setDisable(false);
					btnArticoli.setDisable(true);
					
				}

			}

		});
        
        

    }
}
