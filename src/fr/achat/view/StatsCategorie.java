package fr.achat.view;


import java.util.List;

import fr.achat.model.Achat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class StatsCategorie
{
	
	@FXML
	private BarChart<String, Float> barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis = new NumberAxis();
	
	private ObservableList<String> categorieAbs =  FXCollections.observableArrayList();
	
	
	@FXML
	private void initialize()
	{
		categorieAbs.addAll("ca1", "ca2", "ca3", "ca4","ca5", "ca6", "ca7","Autre");
		
	}
	
	
	public void setAchatDonnee(List<Achat> listAchat)
	{
		try{
			
			float somme[]= new float [6];

			/*
			for(Achat achat : listAchat)
			{
				System.out.println("Magasin : " +achat.getMagasin());
				System.out.println("Price : " +achat.getPrice());
				System.out.println("Catégorie : " +achat.getCategorie());
				System.out.println("Num catégorie : " +achat.getNcategorie());		
				System.out.println("******************************************");
			}
			*/
			
			for(int i=0; i<listAchat.size(); i++)
			{
				int categorie = listAchat.get(i).getNcategorie();
				somme[categorie] = somme[categorie] + listAchat.get(i).getPrice();
			}
			

			XYChart.Series<String, Float> series2 = new XYChart.Series<>(); 
			
			for(int i = 0; i < somme.length ; i++)
			{
				series2.getData().add(new XYChart.Data<>( categorieAbs.get(i), somme[i] ));
				
				//System.out.println("Tour : " +i);
			}
			
			
			barChart.getData().add(series2);	
			
			
			
		}catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
			System.out.println("Pb pour stats par catégorie ");
		}
		
		
	}
	


}
