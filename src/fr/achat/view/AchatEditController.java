package fr.achat.view;
import fr.achat.model.Achat;
import fr.achat.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AchatEditController 
{
	@FXML
	private TextField magasinField;
	
	@FXML
	private TextField PriceField;
	
	@FXML
	private TextField dateField;
	
	@FXML
	private TextArea descriptionArea;
	
	@FXML
	private ChoiceBox<String> categorieChoice = new ChoiceBox<>();
	
	@FXML
	private Labeled assuranceItem;
	


	private Stage dialogStage;
	private Achat achat;
	private boolean okClique = false;
	//private String choixCat;
	

	
	
	@FXML
	private void initialize()
	{
		categorieChoice.getItems().addAll("ca1", "ca2, ca3, ca4",
				"ca5", "ca6", "ca7","Autre");
		
		categorieChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)
				-> achat.setCategorie(newValue));
	}
	
	
	public void setStageAjout(Stage dialogStage)
	{
		this.dialogStage = dialogStage;
		
	}
	
	
	/*
	 * On relie les champs du GridPane de AchatEditController.fxml � nos getters des objets Achat
	 * pour �dition ou New.
	 */	
	public void setAchat(Achat achat)
	{
		this.achat = achat;
		
		magasinField.setText(achat.getMagasin());
		PriceField.setText(Float.toString(achat.getPrice()));
		dateField.setText(DateUtil.format(achat.getDateAchat()));
		dateField.setPromptText("jj/mm/aaaa");
		descriptionArea.setText(achat.getDescription());
		categorieChoice.setValue(achat.getCategorie());
		
		
	}
	
	//M�thode quand l'utilisateur va appuyer sur OK 
	@FXML
	public void appuiOK()
	{
		if(isInputValid())
		{
			achat.setMagasin(magasinField.getText());
			achat.setPrice(Float.parseFloat(PriceField.getText()));
			achat.setDateAchat(DateUtil.parse(dateField.getText()));
			achat.setDescription(descriptionArea.getText());
			achat.setCategorie(categorieChoice.getValue());
			
			if(categorieChoice.getValue().equals("Ca1"))
			{
				System.out.println("TOTO");
				achat.setNcategorie(0);
			}
			
			else if(categorieChoice.getValue().equals("jalaba, Ca6, Ca6"))
			{
				//System.out.println("TATA");
				achat.setNcategorie(1);
			}
			
			else if(categorieChoice.getValue().equals("Ca3"))
			{
				//System.out.println("TBTB");
				achat.setNcategorie(2);
			}
			
			else if(categorieChoice.getValue().equals("Ca4"))
			{
				//System.out.println("TETE");
				achat.setNcategorie(3);
			}
			
			else if(categorieChoice.getValue().equals("Ca5"))
			{
				//System.out.println("TYTY");
				achat.setNcategorie(4);
			}
					
			else if(categorieChoice.getValue().equals("Autre"))
			{
				//System.out.println("TITI");
				achat.setNcategorie(5);
			}

			okClique = true;
			dialogStage.close();
		}		
	}
	
	//M�thode pour confimer si l'utilisateur appui sur OK
	public boolean cliqueOk()
	{
		return okClique;
	}
	
	@FXML
	private void appuiAnnuler()
	{
		dialogStage.close();
	}
	
	//Validation de l'utilisateur
	private boolean isInputValid()
	{
		String messageErreur = "";
		
		if(magasinField.getText() == null || magasinField.getText().length() == 0)
		{
			messageErreur += "Nom du magasin invalide\n";
		}
		
		if(dateField.getText() == null || dateField.getText().length() == 0)
		{
			messageErreur += "Date invalide\n";
		}
		else if (!DateUtil.valideDate(dateField.getText()))
		{
			messageErreur += "Format de la date invalide. Utiliser le format jj.mm.aaaa\n";
			
		}
		
		if(PriceField.getText() == null || PriceField.getText().length() == 0)
		{
			messageErreur += "Price invalide\n";
		}
		else 
		{
			try
			{
				Float.parseFloat(PriceField.getText()); 
			}catch(NumberFormatException e)
			{
				messageErreur += "Le Price doit �tre en chiffres\n" ;
			}
		}
		
		if(descriptionArea.getText() == null || descriptionArea.getText().length() == 0)
		{
			messageErreur += "Veuillez entrer un br�ve description de votre achat sinon vous allez oublier ;)\n";
		}
		
		if (categorieChoice.getValue() == "" )
		{
			messageErreur += "Veuillez choisir une cat�gorie\n";
		}
		
		if(messageErreur.length() == 0)
		{
			return true;
		}
		
		
		
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Erreur");
            alert.setHeaderText("Champ(s) invalide(s)");
            alert.setContentText(messageErreur);

            alert.showAndWait();

            return false;
		}
			
	}
	
	
	
}
