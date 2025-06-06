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

public abstract class BaseGameView {
    protected final AnchorPane root;
    protected Button antiTitanShellCard;
    protected Button longRangeSpearCard;
    protected Button wallSpreadCannonCard;
    protected Button proximityTrapCard;
    protected GridPane gameGrid;
    protected Label score;
    protected Label turn;
    protected Label phase;
    protected Label resources;
    protected Label lanes;
    protected ArrayList<ProgressBar> wallHealthBars;
    protected ArrayList<Label> wallDangerLevels;

    public BaseGameView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);
        wallHealthBars = new ArrayList<>();
        wallDangerLevels = new ArrayList<>();

        initializeView();
    }

    private void initializeView() {
        setupBackgroundVideo();
        setupLaneBackground();
        setupGameGrid();
        setupInfoCircles();
        setupWallHealthBars();
        createWeaponCards();
    }

    private void setupBackgroundVideo() {
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
    }

    private void setupLaneBackground() {
        String imagePath = getClass().getResource(getLaneBackgroundPath()).toExternalForm();
        Image background = new Image(imagePath);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(620);
        backgroundView.setFitWidth(1200);
        backgroundView.setLayoutY(80);
        Rectangle clip = new Rectangle(backgroundView.getFitWidth(), backgroundView.getFitHeight());
        clip.setFill(new LinearGradient(
                0, 35 / backgroundView.getFitHeight(), 0, 0,
                true, null,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, Color.TRANSPARENT)));
        backgroundView.setClip(clip);
        root.getChildren().add(backgroundView);
        backgroundView.toFront();
    }

    private void setupGameGrid() {
        gameGrid = new GridPane();
        gameGrid.setStyle("-fx-background-color: transparent;");
        gameGrid.setHgap(0);

        GridConfig config = getGridConfig();
        gameGrid.setPadding(new Insets(config.padding));
        gameGrid.setPrefSize(config.width, config.height);
        gameGrid.setVgap(config.vgap);

        for (int i = 0; i < config.rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(50);
            row.setPercentHeight(-1);
            gameGrid.getRowConstraints().add(row);
        }
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            gameGrid.getColumnConstraints().add(column);
        }

        AnchorPane.setLeftAnchor(gameGrid, config.leftAnchor);
        AnchorPane.setRightAnchor(gameGrid, config.rightAnchor);
        AnchorPane.setTopAnchor(gameGrid, config.topAnchor);
        AnchorPane.setBottomAnchor(gameGrid, config.bottomAnchor);

        root.getChildren().add(gameGrid);
        gameGrid.toFront();
        gameGrid.setVisible(true);
    }

    private void setupInfoCircles() {
        double[] xOffsets = { 50, 150, 250, 350, 450 };
        score = createInfoCircle("Score: 0", xOffsets[0], Color.SADDLEBROWN);
        turn = createInfoCircle("Turn: 1", xOffsets[1], Color.SIENNA);
        phase = createInfoCircle("Phase: Initial", xOffsets[2], Color.PERU);
        resources = createInfoCircle("Resources: 0", xOffsets[3], Color.BURLYWOOD);
        lanes = createInfoCircle("Lanes: " + getNumberOfLanes(), xOffsets[4], Color.TAN);
        root.getChildren().addAll(score, turn, phase, resources, lanes);
        score.toFront();
        turn.toFront();
        phase.toFront();
        resources.toFront();
        lanes.toFront();
    }

    private void setupWallHealthBars() {
        WallConfig wallConfig = getWallConfig();

        for (int i = 0; i < getNumberOfLanes(); i++) {
            ProgressBar wallHealth = new ProgressBar(1.0);
            wallHealth.setPrefWidth(wallConfig.progressBarWidth);
            wallHealth.setStyle("-fx-accent: green;");
            wallHealthBars.add(wallHealth);

            Label dangerLevel = new Label("Danger: 0");
            dangerLevel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dangerLevel.setTextFill(Color.ORANGE);
            wallDangerLevels.add(dangerLevel);

            double topOffset = wallConfig.baseTopOffset + i * wallConfig.spacing;
            root.getChildren().addAll(wallHealth, dangerLevel);
            AnchorPane.setLeftAnchor(wallHealth, wallConfig.progressBarLeftAnchor);
            AnchorPane.setTopAnchor(wallHealth, topOffset - wallConfig.progressBarTopOffset);
            AnchorPane.setLeftAnchor(dangerLevel, wallConfig.labelLeftAnchor);
            AnchorPane.setTopAnchor(dangerLevel, topOffset);
            wallHealth.toFront();
            dangerLevel.toFront();
        }
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

    protected Button createWeaponCard(String imagePath, double x, double y) {
        Button card = new Button();
        Image cardImage = new Image(imagePath);
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(130);
        cardImageView.setFitHeight(156);
        cardImageView.setPreserveRatio(true);

        card.setGraphic(cardImageView);
        card.setPrefSize(130, 156);
        card.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

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

    protected Button createLargeWeaponCard(String imagePath, double x, double y) {
        Button card = new Button();
        Image cardImage = new Image(imagePath);
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(150);
        cardImageView.setFitHeight(180);
        cardImageView.setPreserveRatio(true);

        card.setGraphic(cardImageView);
        card.setPrefSize(150, 180);
        card.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

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

    protected Label createInfoCircle(String text, double xOffset, Color fillColor) {
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

    // Abstract methods that subclasses must implement
    protected abstract String getLaneBackgroundPath();

    protected abstract int getNumberOfLanes();

    protected abstract GridConfig getGridConfig();

    protected abstract WallConfig getWallConfig();

    // Configuration classes
    protected static class GridConfig {
        public final int padding;
        public final int width;
        public final int height;
        public final int vgap;
        public final int rows;
        public final double leftAnchor;
        public final double rightAnchor;
        public final double topAnchor;
        public final double bottomAnchor;

        public GridConfig(int padding, int width, int height, int vgap, int rows,
                double leftAnchor, double rightAnchor, double topAnchor, double bottomAnchor) {
            this.padding = padding;
            this.width = width;
            this.height = height;
            this.vgap = vgap;
            this.rows = rows;
            this.leftAnchor = leftAnchor;
            this.rightAnchor = rightAnchor;
            this.topAnchor = topAnchor;
            this.bottomAnchor = bottomAnchor;
        }
    }

    protected static class WallConfig {
        public final int progressBarWidth;
        public final double baseTopOffset;
        public final double spacing;
        public final double progressBarLeftAnchor;
        public final double progressBarTopOffset;
        public final double labelLeftAnchor;

        public WallConfig(int progressBarWidth, double baseTopOffset, double spacing,
                double progressBarLeftAnchor, double progressBarTopOffset, double labelLeftAnchor) {
            this.progressBarWidth = progressBarWidth;
            this.baseTopOffset = baseTopOffset;
            this.spacing = spacing;
            this.progressBarLeftAnchor = progressBarLeftAnchor;
            this.progressBarTopOffset = progressBarTopOffset;
            this.labelLeftAnchor = labelLeftAnchor;
        }    }

    // Getters
    public AnchorPane getRoot() {
        return root;
    }

    public GridPane getGameGrid() {
        return gameGrid;
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

    public ArrayList<ProgressBar> getWallHealthBars() {
        return wallHealthBars;
    }

    public ArrayList<Label> getWallDangerLevels() {
        return wallDangerLevels;
    }
}
