package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ModeView {
    private AnchorPane root;
    private static String selectedMode = "Easy"; // Default selection
    private Button easyModeCard;
    private Button hardModeCard;
    private Label accessTokensLabel;
    private static Button startButton; // Add a static start button

    public ModeView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Image
        Image backgroundImage = new Image("GameRulesBG.jpg");
        ImageView background = new ImageView(backgroundImage);
        background.setFitHeight(700);
        background.setFitWidth(1200);
        background.setOpacity(0.8);

        // Dark overlay for better contrast
        Rectangle overlay = new Rectangle(1200, 700);
        overlay.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(0, 0, 0, 0.7)),
                new Stop(1, Color.rgb(0, 0, 0, 0.5)))); // Title
        Label title = new Label("SELECT MODE");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, 48));
        title.setTextFill(Color.WHITE);
        title.setEffect(new DropShadow(10, Color.BLACK));
        AnchorPane.setTopAnchor(title, 30.0);
        AnchorPane.setLeftAnchor(title, 475.0); // Cards Container
        HBox cardsContainer = new HBox(50);
        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setPrefWidth(800);
        AnchorPane.setTopAnchor(cardsContainer, 100.0);
        AnchorPane.setLeftAnchor(cardsContainer, 200.0);// Easy Mode Card
        easyModeCard = createModeCard("EASY MODE",
                "Can you overcome all Titans with normal difficulty?\nLet's find out.",
                "Initial Lanes: 3\nInitial Resources: 250 per lane",
                "3lanes.png", true);

        // Hard Mode Card
        hardModeCard = createModeCard("HARD MODE",
                "Face the ultimate challenge with increased difficulty.\nAre you ready?",
                "Initial Lanes: 5\nInitial Resources: 125 per lane",
                "lane5.png", false);
        cardsContainer.getChildren().addAll(easyModeCard, hardModeCard); // Create and add start button
        startButton = createStartButton();
        AnchorPane.setBottomAnchor(startButton, 10.0);
        AnchorPane.setLeftAnchor(startButton, 500.0);

        root.getChildren().addAll(background, overlay, title, cardsContainer, startButton);
    }

    private Button createModeCard(String title, String description, String details, String imagePath,
            boolean isSelected) {
        VBox cardContent = new VBox(12);
        cardContent.setPrefSize(350, 500);
        cardContent.setPadding(new Insets(20));
        cardContent.setAlignment(Pos.TOP_CENTER); // Card Background
        Rectangle cardBg = new Rectangle(350, 500);
        cardBg.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(45, 45, 45, 0.7)),
                new Stop(1, Color.rgb(25, 25, 25, 0.7))));
        cardBg.setStroke(isSelected ? Color.ORANGE : Color.rgb(80, 80, 80));
        cardBg.setStrokeWidth(isSelected ? 4 : 2);
        cardBg.setArcWidth(15);
        cardBg.setArcHeight(15);

        // Mode Image
        try {
            Image modeImage = new Image(imagePath);
            ImageView imageView = new ImageView(modeImage);
            imageView.setFitWidth(310);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);

            // Add orange border to image
            Rectangle imageBorder = new Rectangle(310, 200);
            imageBorder.setFill(Color.TRANSPARENT);
            imageBorder.setStroke(Color.ORANGE);
            imageBorder.setStrokeWidth(3);
            imageBorder.setArcWidth(10);
            imageBorder.setArcHeight(10);

            StackPane imageContainer = new StackPane();
            imageContainer.getChildren().addAll(imageView, imageBorder);
            cardContent.getChildren().add(imageContainer);
        } catch (Exception e) {
            // Fallback if image not found
            Rectangle placeholder = new Rectangle(310, 200);
            placeholder.setFill(Color.rgb(60, 60, 60));
            placeholder.setStroke(Color.ORANGE);
            placeholder.setStrokeWidth(3);
            cardContent.getChildren().add(placeholder);
        }

        // Orange strip with mode indicator
        Rectangle orangeStrip = new Rectangle(25, 15);
        orangeStrip.setFill(Color.ORANGE);
        HBox stripContainer = new HBox();
        stripContainer.getChildren().add(orangeStrip);
        stripContainer.setAlignment(Pos.CENTER_LEFT);
        cardContent.getChildren().add(stripContainer); // Title
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Chiller", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setEffect(new DropShadow(5, Color.BLACK));
        cardContent.getChildren().add(titleLabel); // Description
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Chiller", FontWeight.NORMAL, 18));
        descLabel.setTextFill(Color.rgb(200, 200, 200));
        descLabel.setWrapText(true);
        descLabel.setTextAlignment(TextAlignment.CENTER);
        descLabel.setAlignment(Pos.CENTER);
        descLabel.setPrefWidth(300);
        descLabel.setPrefHeight(100);
        cardContent.getChildren().add(descLabel);

        // Details
        Label detailsLabel = new Label(details);
        detailsLabel.setFont(Font.font("Chiller", FontWeight.BOLD, 18));
        detailsLabel.setTextFill(Color.WHITE);
        detailsLabel.setTextAlignment(TextAlignment.CENTER);
        detailsLabel.setAlignment(Pos.CENTER);
        cardContent.getChildren().add(detailsLabel);

        // Stack card background and content
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardBg, cardContent);

        Button finalCard = new Button();
        finalCard.setGraphic(cardStack);
        finalCard.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        setupCardEffects(finalCard, cardBg, title.equals("EASY MODE"));

        return finalCard;
    }

    private Button createStartButton() { // Create start button background
        Rectangle startBg = new Rectangle(200, 60);
        startBg.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(200, 120, 50, 0.8)),
                new Stop(1, Color.rgb(180, 100, 30, 0.8))));
        startBg.setStroke(Color.rgb(200, 120, 50));
        startBg.setStrokeWidth(2);
        startBg.setArcWidth(30);
        startBg.setArcHeight(30);

        // Start button text
        Label startLabel = new Label("START GAME");
        startLabel.setFont(Font.font("Chiller", FontWeight.BOLD, 24));
        startLabel.setTextFill(Color.WHITE);
        startLabel.setEffect(new DropShadow(5, Color.BLACK));

        StackPane startStack = new StackPane();
        startStack.getChildren().addAll(startBg, startLabel);

        Button startButton = new Button();
        startButton.setGraphic(startStack);
        startButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // Add hover effects
        startButton.setOnMouseEntered(event -> {
            startButton.setScaleX(1.1);
            startButton.setScaleY(1.1);
            startBg.setEffect(new DropShadow(15, Color.ORANGE));
        });

        startButton.setOnMouseExited(event -> {
            startButton.setScaleX(1.0);
            startButton.setScaleY(1.0);
            startBg.setEffect(null);
        });

        return startButton;
    }

    private void setupCardEffects(Button card, Rectangle cardBg, boolean isEasy) {
        String mode = isEasy ? "Easy" : "Hard";

        // Glow effect for hover
        Glow glow = new Glow(0.3);

        card.setOnMouseEntered(event -> {
            card.setScaleX(1.05);
            card.setScaleY(1.05);
            card.setEffect(glow);
            if (!selectedMode.equals(mode)) {
                cardBg.setStroke(Color.ORANGE);
                cardBg.setStrokeWidth(4);
            }
        });

        card.setOnMouseExited(event -> {
            card.setScaleX(1.0);
            card.setScaleY(1.0);
            if (!selectedMode.equals(mode)) {
                card.setEffect(null);
                cardBg.setStroke(Color.rgb(80, 80, 80));
                cardBg.setStrokeWidth(2);
            } else {
                card.setEffect(null);
            }
        });

        card.setOnMouseClicked(event -> {
            selectedMode = mode;
            updateCardSelection();
            event.consume(); // Prevent event bubbling
        });

        // Set initial selection state - only border, no effects
        if (isEasy && selectedMode.equals("Easy")) {
            cardBg.setStroke(Color.ORANGE);
            cardBg.setStrokeWidth(4);
        }
    }

    private void updateCardSelection() {
        // Update Easy card
        StackPane easyStack = (StackPane) easyModeCard.getGraphic();
        Rectangle easyBg = (Rectangle) easyStack.getChildren().get(0);

        // Update Hard card
        StackPane hardStack = (StackPane) hardModeCard.getGraphic();
        Rectangle hardBg = (Rectangle) hardStack.getChildren().get(0);

        if (selectedMode.equals("Easy")) {
            // Select Easy card - only change border
            easyBg.setStroke(Color.ORANGE);
            easyBg.setStrokeWidth(4);

            // Deselect Hard card - reset border
            hardBg.setStroke(Color.rgb(80, 80, 80));
            hardBg.setStrokeWidth(2);
        } else {
            // Select Hard card - only change border
            hardBg.setStroke(Color.ORANGE);
            hardBg.setStrokeWidth(4);

            // Deselect Easy card - reset border
            easyBg.setStroke(Color.rgb(80, 80, 80));
            easyBg.setStrokeWidth(2);
        }
    }

    public AnchorPane getRoot() {
        return root;
    }

    public static Button getStartButton() {
        // Return the start button that was created in createPlayButton
        return startButton;
    }

    public static String getSelectedMode() {
        return selectedMode;
    }

    // Method to get the Easy mode card for external access if needed
    public Button getEasyModeCard() {
        return easyModeCard;
    }

    // Method to get the Hard mode card for external access if needed
    public Button getHardModeCard() {
        return hardModeCard;
    }
}