package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;
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
    private ComboBox<Creator> boxAutore1;

    @FXML
    private ComboBox<Creator> boxAutore2;

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
    	
    	txtRisultato.clear();
    	
    	Creator autore1 = boxAutore1.getSelectionModel().getSelectedItem();
    	Creator autore2 = boxAutore2.getSelectionModel().getSelectedItem();
    	
    	if(autore1.equals(autore2)) {
    		txtRisultato.setText("Errore: selezionare due autori diversi.");
    		return;
    	}
    	
    	if(model.getVicini(autore1).contains(autore2)) {
    		txtRisultato.appendText("I due autori selezioni sono coautori.\n");
    		txtRisultato.appendText("\nLo/Gli articolo/i di connessione sono:\n");
    		for(Article a: model.getArticle(autore1, autore2)) 
    		{
    			txtRisultato.appendText("-" +a+"\n");
    		}
    		
    		
    	}
    	

    }

    @FXML
    void doCluster(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	model.popolaCluster();
    	
    	txtRisultato.appendText("I cluster sono " + (model.getNumCluster()-1));
    	
    	for (int i = 1; i < model.getNumCluster(); i++) {
			
			List<Creator> l = model.getCluster()[i];
			if(l!= null) {
				
				txtRisultato.appendText("\n");
				txtRisultato.appendText("--------------------------------------");
				txtRisultato.appendText("\n");
				
				txtRisultato.appendText("\nCLUSTER " +i+ "\n");
				for(Creator c: l) {
					txtRisultato.appendText(" " +c +"\n");
				}
				
				
				
			    
			}
		}
    	
    	

    }

    @FXML
    void doCoautori(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
	
    	
    	Creator autore1 = boxAutore1.getSelectionModel().getSelectedItem();
    	
    	List<Creator> coautori = model.getVicini(autore1);
    	
    	if(coautori.size()==0) {
    		txtRisultato.appendText(autore1 + " non ha coautori.");
    		return;
    	}
    	
    	txtRisultato.appendText("I coautori di " +autore1.toString() + " sono:\n");
    	txtRisultato.appendText("\n");
    	
    	for(Creator c: coautori) {
    		txtRisultato.appendText(c.toString() +"\n");
    		
    	}

    }
    
    
    @FXML
    void doReset(ActionEvent event) {
    	
    	boxAutore1.getSelectionModel().clearSelection();
    	boxAutore2.getSelectionModel().clearSelection();
    	
    	boxAutore2.setDisable(true);
    	
    	btnCoautori.setDisable(true);
    	btnCluster.setDisable(false);
    	btnArticoli.setDisable(true);
    	
    	txtRisultato.clear();
    	
    	

    }
    
    
    public void setModel(PortoModel model) {
    	this.model=model;
    	model.createGraph();
    	 
    	 
        boxAutore1.getItems().addAll(model.getAutori());
         
         
        boxAutore2.getItems().addAll(model.getAutori());
         
        boxAutore1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Creator>() {

 			@Override
 			public void changed(ObservableValue<? extends Creator> observable, Creator oldValue, Creator newValue) {

 				if(newValue != null) {
 					btnCluster.setDisable(true);
 					btnCoautori.setDisable(false);
 					boxAutore2.setDisable(false);
 					btnArticoli.setDisable(true);
 				}
 				
 				if(newValue== null) {
 					btnCluster.setDisable(false);
 					btnCoautori.setDisable(true);
 					boxAutore2.setDisable(true);
 					btnArticoli.setDisable(true);
 					
 				}

 			}

 		});
         
         
         boxAutore2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Creator>() {

 			@Override
 			public void changed(ObservableValue<? extends Creator> observable, Creator oldValue, Creator newValue) {

 				if(newValue != null) {
 					btnCluster.setDisable(true);
 					btnCoautori.setDisable(true);
 					btnArticoli.setDisable(false);
 				}
 				
 				if(newValue==null) {
 					btnCluster.setDisable(true);
 					btnCoautori.setDisable(false);
 					btnArticoli.setDisable(true);
 					
 				}

 			}

 		});
         
         
    }

    @FXML
    void initialize() {
        assert boxAutore1 != null : "fx:id=\"boxAutore1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxAutore2 != null : "fx:id=\"boxAutore2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticoli != null : "fx:id=\"btnArticoli\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Porto.fxml'.";
        
        
       
        
        
      

    }
}
