package typingtester.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import typingtester.filehandling.MinimalFileHandler;
import typingtester.model.Progress;
import typingtester.model.TypingTestStats;

public class ResultsController extends PageController {
    private TypingTestStats stats;

    @FXML
    Label wpmAndAccuracy, otherStats;

    public void setStats(TypingTestStats stats) {
        this.stats = stats;
    }

    @FXML
    public void displayStats() {
        wpmAndAccuracy.setText(String.format(
            "%.2f WPM"+maybePB(stats.getWPM())+"\n"
           +"%.2f%% accuracy",

            stats.getWPM(),
            stats.getAccuracy()
        ));

        otherStats.setText(String.format(
            "%.2f raw WPM\n"
           +"%d correct word"+maybePlural(stats.getCorrectWords())+"\n"
           +"%d incorrect word"+maybePlural(stats.getIncorrectWords()),

            stats.getRawWPM(),
            stats.getCorrectWords(),
            stats.getIncorrectWords()
        ));
    }

    private static String maybePB(double WPM) {

        MinimalFileHandler fileHandler = new MinimalFileHandler();
        Progress progress = fileHandler.loadTests();
        return progress.WPMRecordIs(WPM) ? " (PB!!)" : "";
    }

    private static String maybePlural(int n) {
        return n == 1 ? "" : "s";
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB)
            newTypingTest();
    }

    @FXML
    private void newTypingTest() {
        try {
            sceneController.setTypingTest();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
