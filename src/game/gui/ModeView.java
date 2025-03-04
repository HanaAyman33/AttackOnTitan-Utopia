package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ModeView {
    private AnchorPane root;
    private static Button startButton;
    private ToggleGroup modeToggleGroup;
    private static RadioButton easyModeRadio;
    private static RadioButton hardModeRadio;

    public ModeView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Image
        Image backgroundImage = new Image("GameRulesBG.jpg"); // Reusing same BG as GameRulesView
        ImageView background = new ImageView(backgroundImage);
        background.setFitHeight(700);
        background.setFitWidth(1200);
        background.setOpacity(0.7);

        // Gradient Overlay for Contrast
        Stop[] gradientStops = new Stop[] {
            new Stop(0, Color.rgb(0, 0, 0, 0.6)),
            new Stop(1, Color.rgb(0, 0, 0, 0.4))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null, gradientStops);
        Background overlayBackground = new Background(
            new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)
        );

        // Content Container
        VBox contentBox = new VBox(20);
        contentBox.setPrefSize(800, 550);
        contentBox.setPadding(new Insets(20));
        contentBox.setBackground(new Background(
            new BackgroundFill(Color.rgb(20, 20, 20, 0.3), new CornerRadii(15), Insets.EMPTY)
        ));
        contentBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(contentBox, 75.0);
        AnchorPane.setLeftAnchor(contentBox, 200.0);

        // Title
        Label title = new Label("Choose Your Mode");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, 50));
        title.setTextFill(Color.rgb(255, 255, 150));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.BLACK);
        titleShadow.setRadius(10);
        titleShadow.setSpread(0.6);
        title.setEffect(titleShadow);

        // Mode Selection Toggle Group
        modeToggleGroup = new ToggleGroup();

        // Easy Mode
        RadioButton easyModeRadio = new RadioButton("Easy Mode");
        easyModeRadio.setToggleGroup(modeToggleGroup);
        easyModeRadio.setFont(Font.font("Chiller", FontWeight.BOLD, 30));
        easyModeRadio.setTextFill(Color.WHITE);
        easyModeRadio.setSelected(true); // Default selection
        Label easyDetails = new Label("Initial Number of Lanes: 3\nInitial Resources per Lane: 250");
        easyDetails.setFont(Font.font("Chiller", FontWeight.NORMAL, 24));
        easyDetails.setTextFill(Color.WHITE);
        easyDetails.setAlignment(Pos.CENTER);

        // Hard Mode
        RadioButton hardModeRadio = new RadioButton("Hard Mode");
        hardModeRadio.setToggleGroup(modeToggleGroup);
        hardModeRadio.setFont(Font.font("Chiller", FontWeight.BOLD, 30));
        hardModeRadio.setTextFill(Color.WHITE);
        Label hardDetails = new Label("Initial Number of Lanes: 5\nInitial Resources per Lane: 125");
        hardDetails.setFont(Font.font("Chiller", FontWeight.NORMAL, 24));
        hardDetails.setTextFill(Color.WHITE);
        hardDetails.setAlignment(Pos.CENTER);

        // Start Button
        startButton = new Button("Start Game");
        startButton.setPrefSize(200, 50);
        startButton.setFont(Font.font("Chiller", FontWeight.BOLD, 24));
        startButton.setTextFill(Color.WHITE);
        startButton.setBackground(new Background(
            new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(10), null)
        ));
        startButton.setAlignment(Pos.CENTER);
        applyButtonHoverEffect(startButton);
        AnchorPane.setBottomAnchor(startButton, 50.0);
        AnchorPane.setLeftAnchor(startButton, 500.0);

        // Assemble Content
        contentBox.getChildren().addAll(
            title,
            easyModeRadio,
            easyDetails,
            hardModeRadio,
            hardDetails
        );
        root.getChildren().addAll(background, contentBox, startButton);
        root.setBackground(overlayBackground);

        // Store radio buttons for later access
        this.easyModeRadio = easyModeRadio;
        this.hardModeRadio = hardModeRadio;
    }

    // Hover Effect for Buttons
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

    public AnchorPane getRoot() {
        return root;
    }

    public static Button getStartButton() {
        return startButton;
    }

    public static String getSelectedMode() {
        if (easyModeRadio.isSelected()) {
            return "Easy";
        } else if (hardModeRadio.isSelected()) {
            return "Hard";
        }
        return "Easy"; // Default to Easy if somehow no selection
    }
}