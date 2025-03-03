package game.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainMenuView {
    private static AnchorPane root;
    private Button NewGame;
    private Button GameRules;
    private Button exitButton;
    private Button soundButton;
    private AudioClip backgroundMusic;
    private double volumeLevel = 1.0;
    
    public MainMenuView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Image
        ImageView background2 = new ImageView(new Image(getClass().getResource("/Snk.jpeg").toExternalForm()));
        background2.setFitWidth(1200);
        background2.setFitHeight(700);
        background2.setPreserveRatio(false);
        AnchorPane.setTopAnchor(background2, 0.0);
        AnchorPane.setBottomAnchor(background2, 0.0);
        AnchorPane.setLeftAnchor(background2, 0.0);
        AnchorPane.setRightAnchor(background2, 0.0);

        // Label Image (Title)
        ImageView labelImage = new ImageView(new Image(getClass().getResource("/label.png").toExternalForm()));
        labelImage.setFitWidth(600);
        labelImage.setPreserveRatio(true);
        AnchorPane.setTopAnchor(labelImage, -160.0);
        AnchorPane.setLeftAnchor(labelImage, (1200 - labelImage.getFitWidth()) / 2);

        // Sound Button
        soundButton = new Button("🔊");
        soundButton.setFont(new Font(50));
        soundButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        AnchorPane.setTopAnchor(soundButton, 20.0);
        AnchorPane.setRightAnchor(soundButton, 20.0);
        
        // Background Music
        backgroundMusic = new AudioClip(getClass().getResource("/backSound.mp3").toExternalForm());
        backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
        backgroundMusic.play(volumeLevel);
        
        // Sound Button Action
        soundButton.setOnAction(event -> {
            if (volumeLevel == 0.0) {
                volumeLevel = 1.0;
                soundButton.setText("🔊");
                backgroundMusic.play(volumeLevel);
            } else if (volumeLevel == 1.0) {
                volumeLevel = 0.0;
                soundButton.setText("🔇"); // Mute
                backgroundMusic.stop();
            } 
            backgroundMusic.setVolume(volumeLevel);
        });
        
        // Buttons (Invisible Clickable Area)
        NewGame = new Button();
        NewGame.setOpacity(0);
        NewGame.setPrefSize(200, 50);
        AnchorPane.setTopAnchor(NewGame, 400.0);
        AnchorPane.setLeftAnchor(NewGame, 500.0);

        GameRules = new Button();
        GameRules.setOpacity(0);
        GameRules.setPrefSize(200, 50);
        AnchorPane.setTopAnchor(GameRules, 470.0);
        AnchorPane.setLeftAnchor(GameRules, 500.0);

        exitButton = new Button();
        exitButton.setOpacity(0);
        exitButton.setPrefSize(200, 50);
        AnchorPane.setTopAnchor(exitButton, 540.0);
        AnchorPane.setLeftAnchor(exitButton, 500.0);

        // Labels
        Label newGameLabel = createEnhancedLabel("New Game", 405.0, 540.0);
        Label gameRulesLabel = createEnhancedLabel("Game Rules", 475.0, 530.0);
        Label exitLabel = createEnhancedLabel("Exit", 545.0, 580.0);

        // Add elements to root
        root.getChildren().addAll(
            background2, labelImage,
            NewGame, GameRules, exitButton,
            newGameLabel, gameRulesLabel, exitLabel, soundButton
        );
    }

    // Helper method to create enhanced labels
    private Label createEnhancedLabel(String text, double topAnchor, double leftAnchor) {
        Label label = new Label(text);
        label.setFont(new Font("Chiller", 40));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);

        // Semi-transparent background
        Rectangle bgRect = new Rectangle(200, 50);
        bgRect.setFill(Color.rgb(0, 0, 0, 0.5));
        bgRect.setArcWidth(10);
        bgRect.setArcHeight(10);
        AnchorPane.setTopAnchor(bgRect, topAnchor - 5);
        AnchorPane.setLeftAnchor(bgRect, leftAnchor - 20);
        root.getChildren().add(bgRect);

        // Position label
        AnchorPane.setTopAnchor(label, topAnchor);
        AnchorPane.setLeftAnchor(label, leftAnchor);

        // Shadow effect
        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setRadius(10);
        textShadow.setSpread(0.6);
        textShadow.setOffsetX(2);
        textShadow.setOffsetY(2);
        label.setEffect(textShadow);

        // Hover effect
        Glow glow = new Glow(1.0);
        label.setOnMouseEntered(event -> {
            label.setScaleX(1.2);
            label.setScaleY(1.2);
            label.setTextFill(Color.rgb(255, 255, 150));
            label.setEffect(glow);
            bgRect.setFill(Color.rgb(50, 50, 50, 0.7));
        });
        label.setOnMouseExited(event -> {
            label.setScaleX(1.0);
            label.setScaleY(1.0);
            label.setTextFill(Color.WHITE);
            label.setEffect(textShadow);
            bgRect.setFill(Color.rgb(0, 0, 0, 0.5));
        });
        return label;
    }

    public Button getNewGameButton() { return NewGame; }
    public Button getGameRulesButton() { return GameRules; }
    public Button getExitButton() { return exitButton; }
    public AnchorPane getRoot() { return root; }
}
