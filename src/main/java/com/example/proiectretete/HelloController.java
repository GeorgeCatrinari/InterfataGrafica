package com.example.proiectretete;

import Domain.Recipe;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Service service;

    public HelloController(Service service) {
        this.service = service;
    }

    @FXML
    private TextField ingredientsTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ListView<String> recipesListView;

    @FXML
    private TextField timeTextField;
    @FXML
    private TextField filtrareTextField;
    @FXML
    private TextField cumparaturiTextField;


    protected void printRecipesFromSql() {
        ObservableList<String> recipeNames = FXCollections.observableArrayList();

        List<Recipe> recipeList = service.getAll().stream()
                .sorted(Comparator.comparing(Recipe::getIngredients)).toList();


        for (Recipe s : recipeList) {

            recipeNames.add("Recipes{" +
                    "name=" + s.getName() +
                    ", time='" + s.getTime() + '\'' +
                    ", ingredients='" + s.getIngredients() + '\'' +
                    '}');
        }
        recipesListView.setItems(recipeNames);

    }

    @FXML
    private void AddRecipeButton() throws Exception {
        try {
            System.out.println("Add recipe button clicked!");

            String name = nameTextField.getText();
            int time = Integer.parseInt(timeTextField.getText());
            String ingredients = ingredientsTextField.getText();
            service.add(name, time, ingredients);

        } catch (Exception e) {
            Alert alertPopUp = new Alert(Alert.AlertType.ERROR);
            alertPopUp.setTitle("ERROR");
            alertPopUp.setContentText(e.getMessage());
            alertPopUp.show();
        }
        printRecipesFromSql();
    }

    @FXML
    private void filtrareButton() throws Exception {
        try {
            System.out.println("Filter recipes button clicked!");
            String string = filtrareTextField.getText();
            ArrayList<Recipe> filtrate = service.filterByString(string);

            ObservableList<String> recipeNames = FXCollections.observableArrayList();
            List<Recipe> recipeList = filtrate.stream().toList();
            for (Recipe s : recipeList) {

                recipeNames.add("Recipe{" +
                        "name=" + s.getName() +
                        ", time='" + s.getTime() + '\'' +
                        ", ingredients='" + s.getIngredients() + '\'' +
                        '}');
            }
            recipesListView.setItems(recipeNames);

        } catch (Exception e) {
            Alert alertPopUp = new Alert(Alert.AlertType.ERROR);
            alertPopUp.setTitle("ERROR");
            alertPopUp.setContentText(e.getMessage());
            alertPopUp.show();
        }

    }

    @FXML
    private ListView<String> cumparaturiListView;
    @FXML
    private void cumparaturiButton() throws Exception {
        try {
            System.out.println("Filter cumparaturi button clicked!");
            String string = cumparaturiTextField.getText();
            ArrayList<Recipe> cumparate = service.filterByString(string);

            ObservableList<String> recipeNames = FXCollections.observableArrayList();
            List<Recipe> recipeList = cumparate.stream().toList();
            for (Recipe s : recipeList) {

                recipeNames.add("Recipe{" +
                        "name=" + s.getName() +
                        ", time='" + s.getTime() + '\'' +
                        ", ingredients='" + s.getIngredients() + '\'' +
                        '}');
            }
            cumparaturiListView.setItems(recipeNames);

        } catch (Exception e) {
            Alert alertPopUp = new Alert(Alert.AlertType.ERROR);
            alertPopUp.setTitle("ERROR");
            alertPopUp.setContentText(e.getMessage());
            alertPopUp.show();
        }
    }
}