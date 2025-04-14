package game.gui;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Random;

public class GameOverView {
    private AnchorPane root;
    private Button returnToStart;
    private Label gameOverLabel;
    private static Label scoreLabel;
    private Canvas particleCanvas;
    private Timeline particleTimeline;

    public GameOverView(String score) {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        ImageView imageView = setupBackgroundImage();
        if (imageView != null) {
            root.getChildren().add(0, imageView);
        }

        particleCanvas = new Canvas(1200, 700);
        setupParticleEffects();
        root.getChildren().add(particleCanvas);

        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Chiller", FontWeight.BOLD, 100));
        gameOverLabel.setTextFill(Color.WHITE);
        gameOverLabel.setAlignment(Pos.CENTER);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setSpread(0.6);
        gameOverLabel.setEffect(shadow);
        AnchorPane.setLeftAnchor(gameOverLabel, 600.0);
        AnchorPane.setRightAnchor(gameOverLabel, 200.0);
        AnchorPane.setTopAnchor(gameOverLabel, 110.0);
        applyPulseAnimation(gameOverLabel);

        // Score Label
        scoreLabel = new Label("Your Score is " + score);
        scoreLabel.setFont(Font.font("Chiller", FontWeight.NORMAL, 70));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setEffect(shadow);
        AnchorPane.setLeftAnchor(scoreLabel, 600.0);
        AnchorPane.setRightAnchor(scoreLabel, 200.0);
        AnchorPane.setTopAnchor(scoreLabel, 320.0);

        // Return to Start Button
        returnToStart = new Button("Back to Main Menu");
        returnToStart.setPrefSize(200, 50);
        returnToStart.setFont(Font.font("Chiller", FontWeight.BOLD, 24));
        returnToStart.setTextFill(Color.WHITE);
        returnToStart.setBackground(new Background(
            new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(10), null)
        ));
        returnToStart.setAlignment(Pos.CENTER);
        applyButtonHoverEffect(returnToStart);
        AnchorPane.setLeftAnchor(returnToStart, 50.0);
        AnchorPane.setRightAnchor(returnToStart, 850.0);
        AnchorPane.setTopAnchor(returnToStart, 600.0);

        root.getChildren().addAll(gameOverLabel, scoreLabel, returnToStart);
        applyFadeIn(root.getChildren());
    }

    private ImageView setupBackgroundImage() {
        try {
            // Placeholder: Replace "gameOverImage.png" with your image name
            java.net.URL imageUrl = getClass().getResource("/GameOver.jpg");
            if (imageUrl == null) {
                System.err.println("Image not found: /GameOver.jpg");
                return null;
            }

            Image image = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1200);
            imageView.setFitHeight(700);
            imageView.setPreserveRatio(false);
            imageView.setOpacity(1.0);

            AnchorPane.setTopAnchor(imageView, 0.0);
            AnchorPane.setBottomAnchor(imageView, 0.0);
            AnchorPane.setLeftAnchor(imageView, 0.0);
            AnchorPane.setRightAnchor(imageView, 0.0);

            return imageView;
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    }

    private void setupParticleEffects() {
        GraphicsContext gc = particleCanvas.getGraphicsContext2D();
        Random rand = new Random();
        particleTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            gc.clearRect(0, 0, 1200, 700);
            for (int i = 0; i < 20; i++) {
                double x = rand.nextDouble() * 1200;
                double y = rand.nextDouble() * 700;
                double size = rand.nextDouble() * 3 + 1;
                gc.setFill(rand.nextBoolean() ? Color.DARKGRAY : Color.ORANGERED.darker());
                gc.fillOval(x, y, size, size);
            }
        }));
        particleTimeline.setCycleCount(Animation.INDEFINITE);
        particleTimeline.play();

        FadeTransition fade = new FadeTransition(Duration.seconds(1), particleCanvas);
        fade.setFromValue(0);
        fade.setToValue(0.5);
        fade.play();
    }

    private void applyButtonHoverEffect(Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setSpread(0.6);

        Glow glow = new Glow();
        glow.setLevel(0.7);

        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
            button.setTextFill(Color.rgb(255, 255, 150));
            button.setBackground(new Background(
                new BackgroundFill(Color.rgb(80, 80, 80), new CornerRadii(10), null)
            ));
            button.setEffect(glow);
        });

        button.setOnMouseExited(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setTextFill(Color.WHITE);
            button.setBackground(new Background(
                new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(10), null)
            ));
            button.setEffect(shadow);
        });
    }

    private void applyPulseAnimation(Label label) {
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1.5), label);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.play();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    private void applyFadeIn(javafx.collections.ObservableList<javafx.scene.Node> nodes) {
        for (javafx.scene.Node node : nodes) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), node);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setDelay(Duration.seconds(0.5));
            fade.play();
        }
    }

    public AnchorPane getRoot() {
        return root;
    }

    public Button getReturnToStartButton() {
        return returnToStart;
    }

    public void setScore(String score) {
        scoreLabel.setText("Your Score is " + score);
    }

    public static Label getScoreLabel() {
        return scoreLabel;
    }

    public void stopAnimations() {
        if (particleTimeline != null) {
            particleTimeline.stop();
        }
    }
}