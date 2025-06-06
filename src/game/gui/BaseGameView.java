package game.gui;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

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
    protected ArrayList<VBox> wallHealthContainers;
    protected ArrayList<Label> wallDangerLevels;
    protected ArrayList<ProgressBar> wallHealthBars;
    protected HBox infoContainer;

    // Phase change notification components
    protected Label phaseChangeLabel;
    protected StackPane phaseChangeContainer;
    protected String currentPhase = "Initial";
    protected MediaPlayer phaseChangeSound;

    public BaseGameView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);
        wallHealthContainers = new ArrayList<>();
        wallDangerLevels = new ArrayList<>();
        wallHealthBars = new ArrayList<>();

        initializeView();
    }

    private void initializeView() {
        setupBackgroundVideo();
        setupLaneBackground();
        setupGameGrid();
        setupInfoDisplays();
        setupWallHealthDisplays();
        createWeaponCards();
        initializePhaseChangeNotification();
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
        gameGrid.setHgap(2); // Small gap between columns for better visual separation

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
        for (int i = 0; i < 5; i++) { // 5 columns for 5 weapons per row
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(40); // Fixed width for tighter spacing
            column.setMinWidth(35);
            column.setMaxWidth(45);
            gameGrid.getColumnConstraints().add(column);
        }

        AnchorPane.setLeftAnchor(gameGrid, config.leftAnchor);
        AnchorPane.setRightAnchor(gameGrid, config.rightAnchor);
        AnchorPane.setTopAnchor(gameGrid, config.topAnchor);
        AnchorPane.setBottomAnchor(gameGrid, config.bottomAnchor);
        root.getChildren().add(gameGrid);
        gameGrid.toFront();
        gameGrid.setVisible(true);

        setupGridDropHandling();
    }

    private void setupInfoDisplays() {
        infoContainer = new HBox(15);
        infoContainer.setAlignment(Pos.TOP_CENTER);
        infoContainer.setLayoutX(50);
        infoContainer.setLayoutY(10);
        infoContainer.setPrefHeight(80); // Create info displays
        score = createInfoDisplay("SCORE", "0", Color.GOLD);
        phase = createInfoDisplay("PHASE", "Initial", Color.GOLD);
        resources = createResourcesDisplay("RESOURCES", "0", Color.GOLD);
        infoContainer.getChildren().addAll(score, phase, resources);
        root.getChildren().add(infoContainer);
        infoContainer.toFront();
    }

    private Label createInfoDisplay(String title, String value, Color accentColor) {
        StackPane container = new StackPane();
        container.setPrefSize(110, 60);
        container.setMaxSize(110, 60);
        container.setMinSize(110, 60);

        // Create background with radial gradient
        Rectangle bg = new Rectangle(110, 60);
        bg.setArcHeight(15);
        bg.setArcWidth(15);
        bg.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 0.8, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(60, 60, 60, 0.9)),
                new Stop(1, Color.rgb(30, 30, 30, 0.9))));
        bg.setStroke(Color.rgb(100, 100, 100, 0.5));
        bg.setStrokeWidth(1);

        // Create accent bar with glow effect
        Rectangle accentBar = new Rectangle(5, 50);
        accentBar.setFill(accentColor);
        accentBar.setEffect(new DropShadow(5, accentColor));
        StackPane.setAlignment(accentBar, Pos.CENTER_LEFT);
        StackPane.setMargin(accentBar, new Insets(0, 0, 0, 5));

        // Create text content with better typography
        Label label = new Label(title + "\n" + value);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial Narrow", FontWeight.BOLD, 14));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setEffect(new InnerShadow(2, Color.BLACK));
        label.setPrefWidth(160);
        StackPane.setAlignment(label, Pos.CENTER);
        StackPane.setMargin(label, new Insets(0, 10, 0, 20));
        container.getChildren().addAll(bg, accentBar, label);
        Label wrapper = new Label();
        wrapper.setGraphic(container);
        wrapper.setPrefSize(50, 60);
        return wrapper;
    }

    private Label createResourcesDisplay(String title, String value, Color accentColor) {
        StackPane container = new StackPane();
        container.setPrefSize(110, 60);
        container.setMaxSize(110, 60);
        container.setMinSize(110, 60);

        // Create background with radial gradient
        Rectangle bg = new Rectangle(110, 60);
        bg.setArcHeight(15);
        bg.setArcWidth(15);
        bg.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 0.8, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(60, 60, 60, 0.9)),
                new Stop(1, Color.rgb(30, 30, 30, 0.9))));
        bg.setStroke(Color.rgb(100, 100, 100, 0.5));
        bg.setStrokeWidth(1);

        // Create accent bar with glow effect
        Rectangle accentBar = new Rectangle(5, 50);
        accentBar.setFill(accentColor);
        accentBar.setEffect(new DropShadow(5, accentColor));
        StackPane.setAlignment(accentBar, Pos.CENTER_LEFT);
        StackPane.setMargin(accentBar, new Insets(0, 0, 0, 5));

        // Create coin icon
        ImageView coinIcon = new ImageView(new Image(getClass().getResource("/coin.png").toExternalForm()));
        coinIcon.setFitWidth(16);
        coinIcon.setFitHeight(16);
        coinIcon.setPreserveRatio(true);

        // Create text content with coin icon
        HBox textContent = new HBox(3);
        textContent.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial Narrow", FontWeight.BOLD, 14));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setEffect(new InnerShadow(2, Color.BLACK));

        HBox valueContent = new HBox(3);
        valueContent.setAlignment(Pos.CENTER);

        Label valueLabel = new Label(value);
        valueLabel.setTextFill(Color.WHITE);
        valueLabel.setFont(Font.font("Arial Narrow", FontWeight.BOLD, 14));
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        valueLabel.setEffect(new InnerShadow(2, Color.BLACK));

        valueContent.getChildren().addAll(coinIcon, valueLabel);

        VBox completeText = new VBox(2);
        completeText.setAlignment(Pos.CENTER);
        completeText.getChildren().addAll(titleLabel, valueContent);

        StackPane.setAlignment(completeText, Pos.CENTER);
        StackPane.setMargin(completeText, new Insets(0, 10, 0, 20));

        container.getChildren().addAll(bg, accentBar, completeText);
        Label wrapper = new Label();
        wrapper.setGraphic(container);
        wrapper.setPrefSize(50, 60);
        return wrapper;
    }

    private void setupWallHealthDisplays() {
        WallConfig wallConfig = getWallConfig();

        for (int i = 0; i < getNumberOfLanes(); i++) {
            VBox laneContainer = new VBox(5);
            laneContainer.setAlignment(Pos.CENTER);

            // Danger level display with icon - positioned on top
            ImageView dangerIcon = new ImageView(
                    new Image(getClass().getResource("/danger_icon.png").toExternalForm()));
            dangerIcon.setFitWidth(20);
            dangerIcon.setFitHeight(20);

            Label dangerLevel = new Label("0");
            dangerLevel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dangerLevel.setTextFill(Color.WHITE);

            HBox dangerBox = new HBox(3, dangerIcon, dangerLevel);
            dangerBox.setAlignment(Pos.CENTER);

            // Enhanced health bar with text overlay
            ProgressBar wallHealth = new ProgressBar(1.0);
            wallHealth.setPrefWidth(wallConfig.progressBarWidth);
            wallHealth.setPrefHeight(20);
            updateProgressBarStyle(wallHealth, 1.0);

            StackPane healthContainer = new StackPane();
            healthContainer.getChildren().addAll(wallHealth);

            // Add danger display on top, then health bar below
            laneContainer.getChildren().addAll(dangerBox, healthContainer);
            wallHealthContainers.add(laneContainer);
            wallDangerLevels.add(dangerLevel);
            wallHealthBars.add(wallHealth);
            double topOffset = wallConfig.baseTopOffset + i * wallConfig.spacing;
            root.getChildren().add(laneContainer);
            AnchorPane.setLeftAnchor(laneContainer, wallConfig.progressBarLeftAnchor + 50.0);
            AnchorPane.setTopAnchor(laneContainer, topOffset);
        }
    }

    private void updateProgressBarStyle(ProgressBar bar, double progress) {
        if (progress > 0.6) {
            bar.setStyle("-fx-accent: linear-gradient(to right, #4CAF50, #8BC34A); " +
                    "-fx-background-color: #333333; " +
                    "-fx-border-color: #555555; " +
                    "-fx-border-radius: 10; " +
                    "-fx-border-width: 2;");
        } else if (progress > 0.3) {
            bar.setStyle("-fx-accent: linear-gradient(to right, #FFC107, #FF9800); " +
                    "-fx-background-color: #333333; " +
                    "-fx-border-color: #555555; " +
                    "-fx-border-radius: 10; " +
                    "-fx-border-width: 2;");
        } else {
            bar.setStyle("-fx-accent: linear-gradient(to right, #F44336, #E91E63); " +
                    "-fx-background-color: #333333; " +
                    "-fx-border-color: #555555; " +
                    "-fx-border-radius: 10; " +
                    "-fx-border-width: 2;");
        }
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

        setupWeaponCardDragAndDrop(card, imagePath);

        AnchorPane.setLeftAnchor(card, x);
        AnchorPane.setTopAnchor(card, y);
        return card;
    }

    private void createWeaponCards() {
        antiTitanShellCard = createWeaponCard("AntiTitanShellCard.png", 600, 5);
        longRangeSpearCard = createWeaponCard("LongRangeSpearCard.png", 740, 5);
        wallSpreadCannonCard = createWeaponCard("WallSpreadCannonCard.png", 880, 5);
        proximityTrapCard = createWeaponCard("ProximityTrapCard.png", 1025, 5);

        root.getChildren().addAll(antiTitanShellCard, longRangeSpearCard, wallSpreadCannonCard, proximityTrapCard);
        antiTitanShellCard.toFront();
        longRangeSpearCard.toFront();
        wallSpreadCannonCard.toFront();
        proximityTrapCard.toFront();
    }

    protected void setupWeaponCardDragAndDrop(Button card, String imagePath) {
        int weaponCode = getWeaponCodeFromImagePath(imagePath);

        card.setOnDragDetected(event -> {
            Dragboard dragboard = card.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(weaponCode));
            dragboard.setContent(content);

            Image cardImage = new Image(imagePath);
            ImageView dragImageView = new ImageView(cardImage);
            dragImageView.setFitWidth(80);
            dragImageView.setFitHeight(96);
            dragImageView.setPreserveRatio(true);
            dragImageView.setOpacity(0.8);

            dragboard.setDragView(dragImageView.snapshot(null, null), 40, 48);
            event.consume();
        });
    }

    protected void setupGridDropHandling() {
        // Set up drag handling on the game grid
        gameGrid.setOnDragOver(event -> {
            if (event.getGestureSource() != gameGrid && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        gameGrid.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                try {
                    int weaponCode = Integer.parseInt(dragboard.getString());
                    double yPosition = event.getY();
                    int laneIndex = calculateLaneFromPosition(yPosition);

                    if (laneIndex > 0) {
                        Controller.getInstance().handleWeaponDropOnLane(weaponCode, laneIndex);
                        success = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid weapon code in drag data");
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });// Set up expanded drag handling on the root pane for wider horizontal drag area
        root.setOnDragOver(event -> {
            if (event.getGestureSource() != root && event.getDragboard().hasString()) {
                // Only accept drops in the horizontal area that includes the game grid and some
                // buffer space
                double xPosition = event.getX();
                double yPosition = event.getY();

                // Calculate the bounds of the expanded drop area with better precision
                GridConfig config = getGridConfig();
                double gridLeft = config.leftAnchor - 50; // 50px buffer to the left
                double gridRight = config.leftAnchor + gameGrid.getPrefWidth() + 100; // 100px buffer to the right
                double gridTop = config.topAnchor;
                double gridBottom = config.topAnchor + gameGrid.getPrefHeight();

                if (xPosition >= gridLeft && xPosition <= gridRight &&
                        yPosition >= gridTop && yPosition <= gridBottom) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
            event.consume();
        });
        root.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                try {
                    int weaponCode = Integer.parseInt(dragboard.getString());

                    // Convert root coordinates to grid-relative coordinates more accurately
                    GridConfig config = getGridConfig();
                    double yPositionInGrid = event.getY() - config.topAnchor;
                    int laneIndex = calculateLaneFromPosition(yPositionInGrid);

                    if (laneIndex > 0) {
                        Controller.getInstance().handleWeaponDropOnLane(weaponCode, laneIndex);
                        success = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid weapon code in drag data");
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void updateWallHealth(int laneIndex, double healthPercentage) {
        if (laneIndex >= 0 && laneIndex < wallHealthBars.size()) {
            ProgressBar healthBar = wallHealthBars.get(laneIndex);
            healthBar.setProgress(healthPercentage);
            updateProgressBarStyle(healthBar, healthPercentage);
        }
    }

    public void updateDangerLevel(int laneIndex, int dangerLevel) {
        if (laneIndex >= 0 && laneIndex < wallDangerLevels.size()) {
            Label dangerLabel = wallDangerLevels.get(laneIndex);
            dangerLabel.setText(String.valueOf(dangerLevel));

            if (dangerLevel < 3) {
                dangerLabel.setTextFill(Color.LIGHTGREEN);
                dangerLabel.setEffect(null);
            } else if (dangerLevel < 6) {
                dangerLabel.setTextFill(Color.ORANGE);
                dangerLabel.setEffect(new DropShadow(5, Color.ORANGE));
            } else {
                dangerLabel.setTextFill(Color.RED);
                dangerLabel.setEffect(new DropShadow(10, Color.RED));
            }
        }
    }

    public void updateScore(int newScore) {
        StackPane container = (StackPane) ((Label) score).getGraphic();
        Label label = (Label) container.getChildren().get(2);
        label.setText("SCORE\n" + newScore);
    }

    public void updatePhase(String newPhase) {
        // Update the UI display
        StackPane container = (StackPane) ((Label) phase).getGraphic();
        Label label = (Label) container.getChildren().get(2);
        label.setText("PHASE\n" + newPhase);

        // Show the big pulsing phase change notification
        showPhaseChange(newPhase);
    }

    public void updateResources(int newResources) {
        StackPane container = (StackPane) ((Label) resources).getGraphic();
        VBox completeText = (VBox) container.getChildren().get(2);
        HBox valueContent = (HBox) completeText.getChildren().get(1);
        Label valueLabel = (Label) valueContent.getChildren().get(1);
        valueLabel.setText(String.valueOf(newResources));
    }

    private void initializePhaseChangeNotification() {
        // Create the phase change notification container
        phaseChangeContainer = new StackPane();
        phaseChangeContainer.setPrefSize(700, 150);
        phaseChangeContainer.setVisible(false);
        phaseChangeContainer.setMouseTransparent(true);

        // Position it in the center of the screen (centered for 800px width)
        AnchorPane.setLeftAnchor(phaseChangeContainer, 240.0);
        AnchorPane.setTopAnchor(phaseChangeContainer, 260.0);

        // Create background with brownish/orangish theme
        Rectangle background = new Rectangle(700, 150);
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 0.8, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(255, 140, 0, 0.9)), // Bright orange center
                new Stop(0.5, Color.rgb(205, 92, 92, 0.8)), // Indian red middle
                new Stop(1, Color.rgb(139, 69, 19, 0.9)) // Saddle brown edge
        ));
        background.setStroke(Color.rgb(255, 165, 0)); // Orange stroke
        background.setStrokeWidth(3);
        background.setEffect(new DropShadow(15, Color.rgb(255, 140, 0)));

        // Create the phase text label matching GameOverView style
        phaseChangeLabel = new Label();
        phaseChangeLabel.setFont(Font.font("Chiller", FontWeight.BOLD, 100));
        phaseChangeLabel.setTextFill(Color.WHITE);
        phaseChangeLabel.setAlignment(Pos.CENTER);
        phaseChangeLabel.setTextAlignment(TextAlignment.CENTER);

        // Apply same drop shadow effect as GameOverView
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setSpread(0.6);
        phaseChangeLabel.setEffect(shadow);

        phaseChangeContainer.getChildren().addAll(background, phaseChangeLabel);
        root.getChildren().add(phaseChangeContainer);
    }

    public void showPhaseChange(String newPhase) {
        // Only show notification if phase actually changed
        if (!newPhase.equals(currentPhase)) {
            currentPhase = newPhase;

            // Update the label text
            phaseChangeLabel.setText("PHASE: " + newPhase.toUpperCase());

            // Bring notification to front
            phaseChangeContainer.toFront();

            // Show the notification
            phaseChangeContainer.setVisible(true);
            phaseChangeContainer.setOpacity(0);
            phaseChangeContainer.setScaleX(0.5);
            phaseChangeContainer.setScaleY(0.5);

            // Create pulsing animation
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), phaseChangeContainer);
            scaleIn.setFromX(0.5);
            scaleIn.setFromY(0.5);
            scaleIn.setToX(1.2);
            scaleIn.setToY(1.2);

            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(300), phaseChangeContainer);
            scaleOut.setFromX(1.2);
            scaleOut.setFromY(1.2);
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);

            // Create fade in animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), phaseChangeContainer);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Create glow effect that pulses
            Glow glow = new Glow(0.8);
            phaseChangeLabel.setEffect(glow);

            // Create fade out animation (delayed)
            FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), phaseChangeContainer);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setDelay(Duration.millis(2000));

            // Combine animations
            ParallelTransition showAnimation = new ParallelTransition(fadeIn, scaleIn);
            showAnimation.setOnFinished(e -> scaleOut.play());

            scaleOut.setOnFinished(e -> fadeOut.play());
            fadeOut.setOnFinished(e -> {
                phaseChangeContainer.setVisible(false);
                // Reset effect to match GameOverView drop shadow
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.BLACK);
                shadow.setRadius(10);
                shadow.setSpread(0.6);
                phaseChangeLabel.setEffect(shadow);
            });

            // Start the animation sequence
            showAnimation.play();
        }
    }

    private int getWeaponCodeFromImagePath(String imagePath) {
        if (imagePath.contains("AntiTitanShellCard"))
            return 1;
        if (imagePath.contains("LongRangeSpearCard"))
            return 2;
        if (imagePath.contains("WallSpreadCannonCard"))
            return 3;
        if (imagePath.contains("ProximityTrapCard"))
            return 4;
        return 1;
    }

    private int calculateLaneFromPosition(double yPosition) {
        int numLanes = getNumberOfLanes();

        // Calculate row height - each row has a fixed height of 50px plus vgap
        GridConfig config = getGridConfig();
        double rowHeight = 50.0 + config.vgap; // Row height + vertical gap

        // Calculate which row we're in (0-based)
        int rowIndex = Math.max(0, (int) (yPosition / rowHeight));

        // Each lane spans 2 rows, so divide by 2 to get lane index
        int laneIndex = (rowIndex / 2) + 1;

        // Clamp to valid lane range
        if (laneIndex < 1)
            laneIndex = 1;
        if (laneIndex > numLanes)
            laneIndex = numLanes;

        return laneIndex;
    }

    protected abstract String getLaneBackgroundPath();

    protected abstract int getNumberOfLanes();

    protected abstract GridConfig getGridConfig();

    protected abstract WallConfig getWallConfig();

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
        }
    }

    public AnchorPane getRoot() {
        return root;
    }

    public GridPane getGameGrid() {
        return gameGrid;
    }

    public Label getScore() {
        return score;
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

    public ArrayList<VBox> getWallHealthContainers() {
        return wallHealthContainers;
    }

    public ArrayList<Label> getWallDangerLevels() {
        return wallDangerLevels;
    }
}