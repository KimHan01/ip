package rocket.ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rocket.Rocket;

/**
 * Represents a class to start up the stage and scene for the application
 */
public class Main extends Application {

    private final Rocket rocket = new Rocket("./data/storage.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(400);
            stage.setMinWidth(420);
            stage.setScene(scene);
            stage.setTitle("Rocket");
            stage.getIcons().add(new Image("/images/DaRocketIcon.png"));

            fxmlLoader.<MainWindow>getController().setRocket(rocket); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().introMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
