package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
    private static Button weaponShopButtonHard;
    private GridPane hardGrid;
    private Label score;
    private Label turn;
    private Label phase;
    private Label resources;
    private Label lanes;
    private ArrayList<ProgressBar> wallHealthHard;
    private ArrayList<Label> wallDangerLevelHard; // Changed to Label

    public HardView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Media Background (Video)
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

        // Lane Image (lane5.png)
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
            new Stop(1.0, Color.TRANSPARENT)
        ));
        backGround.setClip(clip);
        root.getChildren().add(backGround);
        backGround.toFront();

        // GridPane
        hardGrid = new GridPane();
        hardGrid.setPadding(new Insets(50));
        hardGrid.setStyle("-fx-background-color: transparent;");
        hardGrid.setPrefSize(100, 700);
        hardGrid.setVgap(100);
        hardGrid.setHgap(0);
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(20);
            hardGrid.getRowConstraints().add(row);
        }
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            hardGrid.getColumnConstraints().add(column);
        }
        AnchorPane.setLeftAnchor(hardGrid, 0.0);
        AnchorPane.setRightAnchor(hardGrid, 1050.0);
        AnchorPane.setBottomAnchor(hardGrid, 100.0);
        AnchorPane.setTopAnchor(hardGrid, 100.0);
        root.getChildren().add(hardGrid);
        hardGrid.toFront();

        // Info Circles (Closer Together)
        double[] xOffsets = {50, 150, 250, 350, 450}; // Reduced spacing
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

        // Wall Health (Vertical ProgressBar) and Danger Level (Label)
        wallHealthHard = new ArrayList<>();
        wallDangerLevelHard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // Vertical Health ProgressBar
            ProgressBar wallHealth = new ProgressBar(1.0); // Full health
            wallHealth.setPrefSize(20, 100); // Width 20, Height 100 for vertical
            wallHealth.setRotate(90); // Rotate 90 degrees for vertical orientation
            wallHealth.setStyle("-fx-accent: green; -fx-control-inner-background: #333333;");
            wallHealthHard.add(wallHealth);

            // Danger Level Label
            Label dangerLevel = new Label("Danger: 0");
            dangerLevel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dangerLevel.setTextFill(Color.ORANGE);
            wallDangerLevelHard.add(dangerLevel);

            double topOffset = 100 + i * 110; // Align with lane rows
            root.getChildren().addAll(wallHealth, dangerLevel);
            AnchorPane.setLeftAnchor(wallHealth, 110.0); // Right of grid
            AnchorPane.setTopAnchor(wallHealth, topOffset - 40); // Adjusted for vertical height
            AnchorPane.setLeftAnchor(dangerLevel, 150.0); // Next to progress bar
            AnchorPane.setTopAnchor(dangerLevel, topOffset);
            wallHealth.toFront();
            dangerLevel.toFront();
        }

        // Weapon Shop Button
        weaponShopButtonHard = new Button("Weapon Shop");
        weaponShopButtonHard.setPrefSize(300, 25);
        weaponShopButtonHard.setFont(Font.font("Chiller", FontWeight.BOLD, 13));
        weaponShopButtonHard.setTextFill(Color.WHITE);
        weaponShopButtonHard.setAlignment(Pos.CENTER);
        weaponShopButtonHard.setStyle("-fx-background-color: rgb(50, 50, 50); -fx-background-radius: 5;");
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setSpread(0.6);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        weaponShopButtonHard.setOnMouseEntered(event -> {
            weaponShopButtonHard.setScaleX(1.1);
            weaponShopButtonHard.setScaleY(1.1);
            weaponShopButtonHard.setTextFill(Color.rgb(255, 255, 150));
            weaponShopButtonHard.setStyle("-fx-background-color: rgb(80, 80, 80); -fx-background-radius: 5;");
            weaponShopButtonHard.setEffect(glow);
        });
        weaponShopButtonHard.setOnMouseExited(event -> {
            weaponShopButtonHard.setScaleX(1.0);
            weaponShopButtonHard.setScaleY(1.0);
            weaponShopButtonHard.setTextFill(Color.WHITE);
            weaponShopButtonHard.setStyle("-fx-background-color: rgb(50, 50, 50); -fx-background-radius: 5;");
            weaponShopButtonHard.setEffect(shadow);
        });
        AnchorPane.setTopAnchor(weaponShopButtonHard, 10.0);
        AnchorPane.setLeftAnchor(weaponShopButtonHard, 900.0);
        root.getChildren().add(weaponShopButtonHard);
        weaponShopButtonHard.toFront();
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

    public static Button getWeaponShopButton() {
        return weaponShopButtonHard;
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