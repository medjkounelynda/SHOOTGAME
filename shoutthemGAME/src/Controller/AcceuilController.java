/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author netbook
 */
public class AcceuilController implements Initializable {
    @FXML
	Button buttonJouer;
	
	@FXML
	CheckBox balleInfini;
	
	@FXML
	Spinner<Integer> spinnerNbBalle;
	
	private int nbBalle=0;
	

	public int getNbBalle() {
		return nbBalle;
	}

	
	public void setNbBalle(int nbBalle) {
		this.nbBalle = nbBalle;
	}

	private SimpleBooleanProperty Jouer = new SimpleBooleanProperty(false);

	public ReadOnlyBooleanProperty isJouerProperty() {
		return Jouer;
	}

	private SimpleBooleanProperty config = new SimpleBooleanProperty(false);

	public ReadOnlyBooleanProperty isconfigProperty() {
		return config;
	}

	@FXML
	void jouer(ActionEvent e) {
		if (!spinnerNbBalle.isDisable()) {
			if(spinnerNbBalle.getValue()!=0) {
				
				nbBalle=spinnerNbBalle.getValue();
				System.out.println("mise a jour"+nbBalle);
			}
			
			
		}
		
		

		Jouer.set(true);
	}

	@FXML
	void option(ActionEvent e) {

		config.set(true);

	}

	public boolean getJouer() {
		return Jouer.get();
	}
	

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        balleInfini.selectedProperty().addListener(new ChangeListener<Boolean>() {

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		
		if (newValue) {
			spinnerNbBalle.setDisable(true);
		}else {
			spinnerNbBalle.setDisable(false);
		}
		
	}
	
	
	
});
    }    
    
}
