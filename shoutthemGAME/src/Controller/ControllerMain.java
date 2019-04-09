package Controller;

import java.io.IOException;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ControllerMain {
	@FXML
	private AnchorPane AnchormainGame;

	private Parent gamePane;
	private Parent configPane;
	private Parent AcceuilPane;

	Controller gameSceneController;
	configController configController;
	AcceuilController AcceuilController;
	
	
	FXMLLoader loaderprincipale;
	FXMLLoader loaderJeu;
	FXMLLoader loaderconfig;

	@FXML
	public void initialize() {
		
		loaderprincipale=new FXMLLoader();
		loaderprincipale.setLocation(getClass().getResource("/application/Acceuil.fxml"));
		
		
		
		
		try {
			AcceuilPane = loaderprincipale.load();
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(0);
		}

		if (AcceuilPane == null) {
		
			System.exit(0);
		}
		
	lancerConfigTouche(null);
		
		AnchormainGame.getChildren().add(AcceuilPane);
		AcceuilController=loaderprincipale.getController();
		
		
		AcceuilController.isJouerProperty().addListener((Observable, oldVal, newVal) -> {
			if(newVal) {
			lancerPartie();
			finParti();
			//AcceuilController
			}else {
				
			}
			
		});
		
		AcceuilController.isconfigProperty().addListener((Observable, oldVal, newVal) -> {
			if (newVal) {
			lancerConfigTouche(AcceuilPane);	
			}
		});
		
		
		


     /* oublier login */
/*  configPane = null;
    configController = null;
	
});
*/
		

	}
	
	void lancerPartie() {
		
		
		if (loaderJeu==null) {
			loaderJeu = new FXMLLoader();
		     loaderJeu.setLocation(getClass()
		             .getResource("/application/scene.fxml"));
		     
		     try {
		    	 gamePane = loaderJeu.load();
		     } catch (IOException e) {
		         System.err.println("Another load failed IOException");
		         e.printStackTrace();
		         System.exit(0);
		     }
		     if (gamePane == null) {
		         System.err.println("Another load failed null");
		         System.exit(0);
		     }
		     
		     
		     gameSceneController = loaderJeu.getController();	
		}
	   
	     //System.out.println("getdown"+configController.getDown());
	     gameSceneController.setDown(configController.getDown());
	     gameSceneController.setLeft(configController.getLeft());
	     gameSceneController.setRight(configController.getRight());
	     gameSceneController.setUp(configController.getUp());
	     gameSceneController.setTire(configController.getTire());
	     gameSceneController.setNbBalle(AcceuilController.getNbBalle());
	     
	     System.out.println("dans la recup"+AcceuilController.getNbBalle());
	     
	     AnchormainGame.getChildren().add(gamePane);
	     AnchormainGame.getChildren().remove(AcceuilPane);
	}
	
	
	void lancerConfigTouche(Parent Activepane) {
		if (loaderconfig==null) {
			loaderconfig = new FXMLLoader();	
			loaderconfig.setLocation(getClass().getResource("/application/config.fxml"));

			
			try {
				configPane = loaderconfig.load();
			} catch (IOException e) {
				
				e.printStackTrace();
				System.exit(0);
			}

			if (configPane == null) {
			
				System.exit(0);
			}
			configController=loaderconfig.getController();
			
		}
		
		if (Activepane!=null) {
			AnchormainGame.getChildren().add(configPane);
		     AnchormainGame.getChildren().remove(Activepane);
		     
		     configController.isSaveProperty().addListener((Observable, oldVal, newVal) -> {
					
		    	 AnchormainGame.getChildren().add(Activepane);
			     AnchormainGame.getChildren().remove(configPane);	
					
				});
			
		}
		 
		
		
		
		
		
	}

	void finParti() {
		gameSceneController.getNbballe_restante().addListener((Observable, oldVal, newVal) -> {
			if (newVal.intValue()==0) {
				 System.out.println("fin du jeu");	
			}
			
	    		
				
			});
		
	}
}