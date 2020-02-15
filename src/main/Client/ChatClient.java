package main.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChatClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Todo: Fix the below so that it isn't such a mess.
        // create url to hard coded line.
        URL url = new File("src/main/resources/Chat.fxml").toURI().toURL();
        final FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        primaryStage.setTitle("Java Chat");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
