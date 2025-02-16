package rocket.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rocket.Rocket;

import java.io.IOException;

public class Main extends Application {

    private final Rocket rocket = new Rocket("./data/storage.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.setFill(Color.MEDIUMBLUE);
            stage.setScene(scene);
            stage.setTitle("Rocket");
            stage.getIcons().add(new Image("/images/DaRocketIcon.png"));

            fxmlLoader.<MainWindow>getController().setRocket(rocket);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().introMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
