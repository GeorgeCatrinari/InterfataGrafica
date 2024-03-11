package com.example.proiectretete;

import Repository.SqlRepository;
import Service.Service;
import com.example.proiectretete.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private SqlRepository repository = new SqlRepository();
    private Service service = new Service(repository);

    public HelloApplication() throws Exception {
    }


    @Override
    public void start(Stage stage) throws IOException {
        HelloController hc = new HelloController(service);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        fxmlLoader.setController(hc);


        Scene scene = new Scene(fxmlLoader.load(), 640, 320);

        hc.printRecipesFromSql();

        stage.setTitle("Recipes!");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}