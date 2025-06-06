package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class HardView {
    private final AnchorPane root;
    private static Button antiTitanShellCard;
    private static Button longRangeSpearCard;
    private static Button wallSpreadCannonCard;
    private static Button proximityTrapCard;
    private GridPane hardGrid;
    private Label score;
    private Label turn;
    private Label phase;
    private Label resources;
    private Label lanes;
    private ArrayList<ProgressBar> wallHealthHard;
    private ArrayList<Label> wallDangerLevelHard;

    public HardView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background video setup
        String videoPath = getClass().getResource("/InGameClouds.mp4").toExternalForm();
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
        root.getChildren().add(mediaView);

        // Lane background image
        String imagePath = getClass().getResource("/lane5.png").toExternalForm();
        Image background = new Image(imagePath);
        ImageView backGround = new ImageView(background);
        backGround.setFitHeight(620);
        backGround.setFitWidth(1200);
        backGround.setLayoutY(80);
        Rectangle clip = new Rectangle(backGround.getFitWidth(), backGround.getFitHeight());
        clip.setFill(new LinearGradient(
                0, 35 / backGround.getFitHeight(), 0, 0,
                true, null,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, Color.TRANSPARENT)));
        backGround.setClip(clip);
        root.getChildren().add(backGround);
        backGround.toFront();

        // Game grid setup
        hardGrid = new GridPane();
        hardGrid.setPadding(new Insets(10));
        hardGrid.setStyle("-fx-background-color: transparent;");
        hardGrid.setPrefSize(100, 550);
        hardGrid.setVgap(40);
        hardGrid.setHgap(0);
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(50);
            row.setPercentHeight(-1);
            hardGrid.getRowConstraints().add(row);
        }
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            hardGrid.getColumnConstraints().add(column);
        }
        AnchorPane.setLeftAnchor(hardGrid, 0.0);
        AnchorPane.setRightAnchor(hardGrid, 1050.0);
        AnchorPane.setTopAnchor(hardGrid, 130.0);
        AnchorPane.setBottomAnchor(hardGrid, 85.0);
        root.getChildren().add(hardGrid);
        hardGrid.toFront();
        hardGrid.setVisible(true);

        // Info circles setup
        double[] xOffsets = { 50, 150, 250, 350, 450 };
        score = createInfoCircle("Score: 0", xOffsets[0], Color.SADDLEBROWN);
        turn = createInfoCircle("Turn: 1", xOffsets[1], Color.SIENNA);
        phase = createInfoCircle("Phase: Initial", xOffsets[2], Color.PERU);
        resources = createInfoCircle("Resources: 0", xOffsets[3], Color.BURLYWOOD);
        lanes = createInfoCircle("Lanes: 5", xOffsets[4], Color.TAN);
        root.getChildren().addAll(score, turn, phase, resources, lanes);
        score.toFront();
        turn.toFront();
        phase.toFront();
        resources.toFront();
        lanes.toFront();

        // Wall health bars and danger level labels
        wallHealthHard = new ArrayList<>();
        wallDangerLevelHard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProgressBar wallHealth = new ProgressBar(1.0);
            wallHealth.setPrefWidth(150);
            wallHealth.setStyle("-fx-accent: green;");
            wallHealthHard.add(wallHealth);

            Label dangerLevel = new Label("Danger: 0");
            dangerLevel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dangerLevel.setTextFill(Color.ORANGE);
            wallDangerLevelHard.add(dangerLevel);

            double topOffset = 165 + i * 100;
            root.getChildren().addAll(wallHealth, dangerLevel);
            AnchorPane.setLeftAnchor(wallHealth, 90.0);
            AnchorPane.setTopAnchor(wallHealth, topOffset - 30);
            AnchorPane.setLeftAnchor(dangerLevel, 150.0);
            AnchorPane.setTopAnchor(dangerLevel, topOffset);
            wallHealth.toFront();
            dangerLevel.toFront();
        }

        createWeaponCards();
    }

    private void createWeaponCards() {
        antiTitanShellCard = createWeaponCard("AntiTitanShellCard.png", 600, 5);

        longRangeSpearCard = createWeaponCard("LongRangeSpearCard.png", 740, 5);
                                                                               
        wallSpreadCannonCard = createLargeWeaponCard("WallSpreadCannonCard.png", 855, 0);

        proximityTrapCard = createWeaponCard("ProximityTrapCard.png", 1025, 5);

        root.getChildren().addAll(antiTitanShellCard, longRangeSpearCard, wallSpreadCannonCard, proximityTrapCard);
        antiTitanShellCard.toFront();
        longRangeSpearCard.toFront();
        wallSpreadCannonCard.toFront();
        proximityTrapCard.toFront();
    }

    private Button createWeaponCard(String imagePath, double x, double y) {
        Button card = new Button();
        Image cardImage = new Image(imagePath);
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(130);
        cardImageView.setFitHeight(156);
        cardImageView.setPreserveRatio(true);

        card.setGraphic(cardImageView);
        card.setPrefSize(130, 156);
        card.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // Hover effects
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GOLD);
        shadow.setRadius(10);
        shadow.setSpread(0.5);

        card.setOnMouseEntered(event -> {
            card.setScaleX(1.1);
            card.setScaleY(1.1);
            card.setEffect(shadow);
        });

        card.setOnMouseExited(event -> {
            card.setScaleX(1.0);
            card.setScaleY(1.0);
            card.setEffect(null);
        });

        AnchorPane.setLeftAnchor(card, x);
        AnchorPane.setTopAnchor(card, y);

        return card;
    } 
    
    //method to create a larger weapon card for Wall Spread Cannon
    private Button createLargeWeaponCard(String imagePath, double x, double y) {
        Button card = new Button();
        Image cardImage = new Image(imagePath);
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(150);
        cardImageView.setFitHeight(180);
        cardImageView.setPreserveRatio(true);

        card.setGraphic(cardImageView);
        card.setPrefSize(150, 180);
        card.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // Enhanced hover effects for the special card
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GOLD);
        shadow.setRadius(15);
        shadow.setSpread(0.7);

        card.setOnMouseEntered(event -> {
            card.setScaleX(1.15);
            card.setScaleY(1.15);
            card.setEffect(shadow);
        });

        card.setOnMouseExited(event -> {
            card.setScaleX(1.0);
            card.setScaleY(1.0);
            card.setEffect(null);
        });

        AnchorPane.setLeftAnchor(card, x);
        AnchorPane.setTopAnchor(card, y);

        return card;
    }

    // Helper method to create circular info badges
    private Label createInfoCircle(String text, double xOffset, Color fillColor) {
        Circle circle = new Circle(40, fillColor);
        circle.setStroke(Color.BROWN);
        circle.setStrokeWidth(2);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        circle.setEffect(shadow);

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setPrefSize(80, 80);
        label.setWrapText(true);

        AnchorPane.setLeftAnchor(circle, xOffset);
        AnchorPane.setTopAnchor(circle, 10.0);
        AnchorPane.setLeftAnchor(label, xOffset);
        AnchorPane.setTopAnchor(label, 10.0);

        root.getChildren().add(circle);
        return label;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public static Button getAntiTitanShellCard() {
        return antiTitanShellCard;
    }

    public static Button getLongRangeSpearCard() {
        return longRangeSpearCard;
    }

    public static Button getWallSpreadCannonCard() {
        return wallSpreadCannonCard;
    }

    public static Button getProximityTrapCard() {
        return proximityTrapCard;
    }

    public GridPane getHardGrid() {
        return hardGrid;
    }

    public Label getScore() {
        return score;
    }

    public Label getTurn() {
        return turn;
    }

    public Label getPhase() {
        return phase;
    }

    public Label getResources() {
        return resources;
    }

    public Label getLanes() {
        return lanes;
    }

    public ArrayList<ProgressBar> getWallHealthHard() {
        return wallHealthHard;
    }

    public ArrayList<Label> getWallDangerLevelHard() {
        return wallDangerLevelHard;
    }
}