package typingtester;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import typingtester.pages.PageSwitcher;

import java.io.IOException;

public class TypingTesterApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("blank.png")));
        stage.setResizable(false);

        PageSwitcher sceneController = new PageSwitcher(stage);
        sceneController.setTypingTest();
    }
}
