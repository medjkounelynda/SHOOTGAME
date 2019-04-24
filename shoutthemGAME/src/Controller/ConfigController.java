/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author netbook
 */
public class ConfigController implements Initializable {
    @FXML
    TextField Droite;
    @FXML
    TextField Gauche;
    @FXML
    TextField Haut;
    @FXML
    TextField Bas;
    @FXML
    TextField Tirer;
    private SimpleBooleanProperty Save = new SimpleBooleanProperty(false);

	private KeyCode up;
	private KeyCode down;
	private KeyCode right;
	private KeyCode left;
	private KeyCode tire;

	// DoubleProperty vitesse = new SimpleDoubleProperty();

	public KeyCode getUp() {
		return up;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public KeyCode getDown() {
		return down;
	}

	public void setDown(KeyCode down) {
		this.down = down;
	}

	public KeyCode getRight() {
		return right;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}

	public KeyCode getLeft() {
		return left;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}

	public KeyCode getTire() {
		return tire;
	}

	public void setTire(KeyCode tire) {
		this.tire = tire;
	}

	public ReadOnlyBooleanProperty isSaveProperty() {
		return Save;
	}

	public boolean getSave() {
		return Save.get();
	}

	@FXML
	void sauvegardeTouche(KeyEvent e) {
		// System.out.println(e.getSource().equals(Droite));

		if (e.getSource().equals(Droite)) {
		
			Droite.setText(e.getCode().getName());
			right=e.getCode();
			e.consume();

			Gauche.requestFocus();

		}

		if (e.getSource().equals(Gauche)) {
			
			
			Gauche.setText(e.getCode().getName());
			left=e.getCode();
			e.consume();
			Haut.requestFocus();

		}

		if (e.getSource().equals(Haut)) {
			
			Haut.setText(e.getCode().getName());
			up=e.getCode();
			e.consume();
			Bas.requestFocus();

		}

		if (e.getSource().equals(Bas)) {
			
			Bas.setText(e.getCode().getName());
			down=e.getCode();
			e.consume();

			Tirer.requestFocus();

		}

		if (e.getSource().equals(Tirer)) {
			
			Tirer.setText(e.getCode().getName());
			tire=e.getCode();
		}

	}

	@FXML
	void sauvegarder(ActionEvent e) {
		Save.set(true);

	}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Droite.setEditable(false);
	Gauche.setEditable(false);
	Haut.setEditable(false);
	Bas.setEditable(false);
	Tirer.setEditable(false);
		
	up=KeyCode.UP;
	Haut.setText(up.getName());
	down=KeyCode.DOWN;
	Bas.setText(down.getName());
	left=KeyCode.LEFT;
	Gauche.setText(left.getName());
	right=KeyCode.RIGHT;
	Droite.setText(right.getName());
	tire=KeyCode.X;
	Tirer.setText(tire.getName());
    }    
    
}
