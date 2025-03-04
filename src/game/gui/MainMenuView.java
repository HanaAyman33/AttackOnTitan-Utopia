package game.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainMenuView {
    private static AnchorPane root;
    private static Button NewGame;
    private static Button GameRules;
    private static Button exitButton;
    private Button soundButton;
    private Slider volumeSlider;
    private MediaPlayer backgroundMusic;
    private double volumeLevel = 0.8;
    
    public MainMenuView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Video
        String videoPath = getClass().getResource("/MainMenuVideo.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(700);
        mediaView.setPreserveRatio(false);
        AnchorPane.setTopAnchor(mediaView, 0.0);
        AnchorPane.setBottomAnchor(mediaView, 0.0);
        AnchorPane.setLeftAnchor(mediaView, 0.0);
        AnchorPane.setRightAnchor(mediaView, 0.0);

        // Label Image (Title)
        ImageView labelImage = new ImageView(new Image(getClass().getResource("/label.png").toExternalForm()));
        labelImage.setFitWidth(800);
        labelImage.setPreserveRatio(true);
        AnchorPane.setTopAnchor(labelImage, -260.0);
        AnchorPane.setLeftAnchor(labelImage, (1200 - labelImage.getFitWidth()) / 2);
        
        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setRadius(5);
        textShadow.setSpread(0.4);
        textShadow.setOffsetX(2);
        textShadow.setOffsetY(2);
        labelImage.setEffect(textShadow);
        // Sound Button
        soundButton = new Button("🔊");
        soundButton.setFont(new Font(50));
        soundButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 0;");
        soundButton.setMinSize(60, 60);
        soundButton.setMaxSize(60, 60);
        soundButton.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(soundButton, 20.0);
        AnchorPane.setRightAnchor(soundButton, 40.0);

        // Volume Slider
        volumeSlider = new Slider(0, 1, 1.0);
        volumeSlider.setPrefWidth(120);
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setShowTickLabels(false);
        volumeSlider.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-background-insets: 0 0 -1 0, 0, 1; " +
            "-fx-background-radius: 10; " +
            "-fx-padding: 5 0 5 0; " +
            "-fx-accent: #FFFFFF; " +
            "-fx-focus-color: rgba(255, 255, 255, 0.3); " +
            "-fx-faint-focus-color: transparent; " +
            "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 5, 0.2, 1, 1); " +
            "-fx-opacity: 1.0;"
        );
        AnchorPane.setTopAnchor(volumeSlider, 80.0);
        AnchorPane.setRightAnchor(volumeSlider, 10.0);

        // Apply track and thumb styling after scene is rendered
        Platform.runLater(() -> {
            // Track styling with LinearGradient
            LinearGradient trackGradient = new LinearGradient(
                0, 0, 1, 0, true,
                null,
                new Stop(0.0, Color.web("#4A4A4A")),
                new Stop(1.0, Color.web("#6A6A6A"))
            );
            volumeSlider.lookup(".track").setStyle(
                "-fx-background-color: " + toCssString(trackGradient) + "; " +
                "-fx-background-radius: 10;"
            );

            // Thumb styling
            volumeSlider.lookup(".thumb").setStyle(
                "-fx-background-color: #FFFFFF; " +
                "-fx-background-radius: 12; " +
                "-fx-pref-width: 16; " +
                "-fx-pref-height: 16;"
            );
            volumeSlider.lookup(".thumb").hoverProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    volumeSlider.lookup(".thumb").setStyle(
                        "-fx-background-color: #FFD700; " +
                        "-fx-background-radius: 12; " +
                        "-fx-pref-width: 18; " +
                        "-fx-pref-height: 18;"
                    );
                } else {
                    volumeSlider.lookup(".thumb").setStyle(
                        "-fx-background-color: #FFFFFF; " +
                        "-fx-background-radius: 12; " +
                        "-fx-pref-width: 16; " +
                        "-fx-pref-height: 16;"
                    );
                }
            });
        });

        // Background Music (Using MediaPlayer)
        String musicPath = getClass().getResource("/backSound.mp3").toExternalForm();
        Media musicMedia = new Media(musicPath);
        backgroundMusic = new MediaPlayer(musicMedia);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(volumeLevel);
        backgroundMusic.play();

        // Sound Button Action (Toggle Mute)
        soundButton.setOnAction(event -> {
            if (volumeLevel > 0.0) {
                volumeLevel = 0.0;
                volumeSlider.setValue(0.0);
                backgroundMusic.setVolume(0.0);
            } else {
                volumeLevel = 1.0;
                volumeSlider.setValue(1.0);
                backgroundMusic.setVolume(1.0);
            }
            updateSoundButton(volumeLevel);
        });

        // Slider Volume Control
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            volumeLevel = newVal.doubleValue();
            backgroundMusic.setVolume(volumeLevel);
            updateSoundButton(volumeLevel);
        });

        // Buttons (Invisible Clickable Area)
        NewGame = new Button();
        NewGame.setOpacity(0);
        NewGame.setPrefSize(450, 50);
        AnchorPane.setTopAnchor(NewGame, 405.0);
        AnchorPane.setLeftAnchor(NewGame, -10.0);

        GameRules = new Button();
        GameRules.setOpacity(0);
        GameRules.setPrefSize(450, 50);
        AnchorPane.setTopAnchor(GameRules, 475.0);
        AnchorPane.setLeftAnchor(GameRules, -10.0);

        exitButton = new Button();
        exitButton.setOpacity(0);
        exitButton.setPrefSize(450, 50);
        AnchorPane.setTopAnchor(exitButton, 545.0);
        AnchorPane.setLeftAnchor(exitButton, -10.0);

        // LinearGradient for right-edge fade
        Stop[] normalStops = new Stop[] {
            new Stop(0.0, Color.rgb(30, 30, 30, 0.2)),
            new Stop(0.8, Color.rgb(30, 30, 30, 0.2)),
            new Stop(1.0, Color.rgb(30, 30, 30, 0.0))
        };
        LinearGradient normalGradient = new LinearGradient(
            0, 0, 1, 0, true,
            null, normalStops
        );

        Stop[] hoverStops = new Stop[] {
            new Stop(0.0, Color.rgb(10, 10, 10, 0.3)),
            new Stop(0.8, Color.rgb(10, 10, 10, 0.3)),
            new Stop(1.0, Color.rgb(10, 10, 10, 0.0))
        };
        LinearGradient hoverGradient = new LinearGradient(
            0, 0, 1, 0, true,
            null, hoverStops
        );

        // Rectangles with gradient
        Rectangle bgRect1 = new Rectangle(500, 50);
        bgRect1.setFill(normalGradient);
        bgRect1.setArcWidth(20);
        bgRect1.setArcHeight(20);
        AnchorPane.setTopAnchor(bgRect1, 405.0);
        AnchorPane.setLeftAnchor(bgRect1, -10.0);
        
        Rectangle bgRect2 = new Rectangle(500, 50);
        bgRect2.setFill(normalGradient);
        bgRect2.setArcWidth(20);
        bgRect2.setArcHeight(20);
        AnchorPane.setTopAnchor(bgRect2, 475.0);
        AnchorPane.setLeftAnchor(bgRect2, -10.0);
        
        Rectangle bgRect3 = new Rectangle(500, 50);
        bgRect3.setFill(normalGradient);
        bgRect3.setArcWidth(20);
        bgRect3.setArcHeight(20);
        AnchorPane.setTopAnchor(bgRect3, 545.0);
        AnchorPane.setLeftAnchor(bgRect3, -10.0);

        // Labels
        Label newGameLabel = createEnhancedLabel("New Game", 400.0, 90.0, bgRect1, hoverGradient, normalGradient,NewGame);
        Label gameRulesLabel = createEnhancedLabel("Game Rules", 470.0, 90.0, bgRect2, hoverGradient, normalGradient,GameRules);
        Label exitLabel = createEnhancedLabel("Exit", 540.0, 130.0, bgRect3, hoverGradient, normalGradient,exitButton);

        // Add elements to root
        root.getChildren().addAll(
            mediaView, labelImage,
            bgRect1, bgRect2, bgRect3,
            newGameLabel, gameRulesLabel, exitLabel, soundButton, volumeSlider,NewGame, GameRules, exitButton
        );
    }

    // Helper method to convert LinearGradient to CSS string
    private String toCssString(LinearGradient gradient) {
        StringBuilder sb = new StringBuilder("linear-gradient(to right, ");
        for (Stop stop : gradient.getStops()) {
            sb.append(String.format("rgba(%d, %d, %d, %.2f) %.2f%%, ",
                (int)(stop.getColor().getRed() * 255),
                (int)(stop.getColor().getGreen() * 255),
                (int)(stop.getColor().getBlue() * 255),
                stop.getColor().getOpacity(),
                stop.getOffset() * 100));
        }
        sb.setLength(sb.length() - 2); // Remove last comma and space
        sb.append(")");
        return sb.toString();
    }

    // Helper method to update sound button shape based on volume
    private void updateSoundButton(double volume) {
        if (volume == 0.0) {
            soundButton.setText("🔇");
        } else if (volume <= 0.33) {
            soundButton.setText("🔈");
        } else if (volume <= 0.66) {
            soundButton.setText("🔉");
        } else {
            soundButton.setText("🔊");
        }
        soundButton.setFont(new Font(50));
    }

    // Helper method to create enhanced labels with background rectangle
    private Label createEnhancedLabel(String text, double topAnchor, double leftAnchor, Rectangle bgRect, 
                                      LinearGradient hoverGradient, LinearGradient normalGradient,Button button) {
        Label label = new Label(text);
        label.setFont(new Font("Chiller", 40));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(label, topAnchor);
        AnchorPane.setLeftAnchor(label, leftAnchor);

        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setRadius(10);
        textShadow.setSpread(0.6);
        textShadow.setOffsetX(2);
        textShadow.setOffsetY(2);
        label.setEffect(textShadow);

        Glow glow = new Glow();
        glow.setLevel(0.7);

        Runnable enterHandler = () -> {
            label.setScaleX(1.2);
            label.setScaleY(1.2);
            label.setTextFill(Color.rgb(255, 255, 150));
            label.setEffect(glow);
            bgRect.setFill(hoverGradient);
        };

        Runnable exitHandler = () -> {
            label.setScaleX(1.0);
            label.setScaleY(1.0);
            label.setTextFill(Color.WHITE);
            label.setEffect(textShadow);
            bgRect.setFill(normalGradient);
        };

        button.setOnMouseEntered(event -> enterHandler.run());
        button.setOnMouseExited(event -> exitHandler.run());

        return label;
    }

    public static Button getNewGameButton() { return NewGame; }
    public static Button getGameRulesButton() { return GameRules; }
    public static Button getExitButton() { return exitButton; }
    public AnchorPane getRoot() { return root; }
}