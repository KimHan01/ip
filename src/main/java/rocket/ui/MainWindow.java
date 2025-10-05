package rocket.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rocket.Rocket;
import rocket.command.InputCommandType;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Rocket rocket;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image rocketImage = new Image(this.getClass().getResourceAsStream("/images/DaRocket.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRocket(Rocket r) {
        rocket = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rocket.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRocketDialog(response, rocketImage)
        );
        userInput.clear();

        // Used GitHub Copilot to learn how to delay the application for 2 seconds before exiting
        if (input.equalsIgnoreCase(InputCommandType.BYE.name())) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
    // testing123
    /**
     * Creates one dialog box for rocket containing his introduction message.
     */
    @FXML
    public void introMessage() {
        dialogContainer.getChildren().add(DialogBox.getRocketDialog(Rocket.rocketIntro(), rocketImage));
    }
}

