package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = (Parent)loader.load();
        primaryStage.setTitle("XLS в XML");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 244, 178));
        Controller myController = (Controller) loader.getController();
        myController.setStage(primaryStage);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
