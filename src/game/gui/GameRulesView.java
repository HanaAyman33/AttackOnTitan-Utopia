package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class GameRulesView {
    private static AnchorPane root;
    private static Button Back;
    private static final String INSTRUCTIONS = 
        "1. Objective:\n" +
        "   - Your goal is to defend the Utopia District Walls from incoming titan attacks for as long as possible.\n\n" +
        "2. Game Setup:\n" +
        "   - Titans are approaching the walls from multiple lanes.\n" +
        "   - You have the option to purchase and deploy different types of weapons to defend the walls.\n\n" +
        "3. Turn Actions:\n" +
        "   - On each turn, you can either:\n" +
        "   - Purchase and deploy a weapon.\n" +
        "   - Pass your turn without taking any actions.\n" +
        "   - After your action (or pass), titans will move closer to the walls, and weapons will attack them.\n" +
        "   - Titans will then attack the walls.\n" +
        "   - New titans may be added to the lanes based on the game phase and elapsed turns.\n\n" +
        "4. Winning and Losing:\n" +
        "   - There's no winning condition; your goal is to survive for as many turns as possible.\n" +
        "   - You lose when all starting lanes have their wall parts destroyed.\n" +
        "     Your final score is based on the number of defeated titans.\n\n" +
        "5. Enemy Characters (Titans):\n" +
        "   - Titans come in different types with varying stats and special traits.\n" +
        "   - Defeat titans by reducing their health points to zero. Each defeated titan adds to your resources and score.\n\n" +
        "6. Friendly Pieces (Weapons):\n" +
        "   - Deploy various types of weapons to attack incoming titans.\n" +
        "   - Each weapon type has different attack actions and ranges. Choose strategically based on the situation.\n\n" +
        "7. Game Phases:\n" +
        "   - The game progresses through three phases: Early, Intense, and Grumbling.\n" +
        "   - The number and types of titans added to lanes change based on the phase and elapsed turns.\n\n" +
        "8. Resource Management:\n" +
        "   - Manage your resources effectively to purchase and deploy weapons.\n" +
        "   - Your resources are deducted when purchasing weapons and increased when defeating titans.\n\n" +
        "Remember to strategize carefully, prioritize defending vulnerable lanes, and adapt your tactics as the game progresses.\n" +
        "Good luck defending the walls of Utopia District!";

    public GameRulesView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Image
        Image backgroundImage = new Image("mainBackground.jpg");
        ImageView background = new ImageView(backgroundImage);
        background.setFitHeight(700);
        background.setFitWidth(1200);
        background.setOpacity(0.7); // Slightly dim for text readability

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
            new BackgroundFill(Color.rgb(20, 20, 20, 0.9), new CornerRadii(15), Insets.EMPTY)
        ));
        contentBox.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(contentBox, 75.0);
        AnchorPane.setLeftAnchor(contentBox, 200.0);

        // Title
        Label title = new Label("Game Rules");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, 50));
        title.setTextFill(Color.rgb(255, 255, 150));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.BLACK);
        titleShadow.setRadius(10);
        titleShadow.setSpread(0.6);
        title.setEffect(titleShadow);

        // Instructions ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(760, 400);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox instructionsBox = new VBox(10);
        instructionsBox.setPadding(new Insets(10));
        instructionsBox.setAlignment(Pos.TOP_LEFT);

        // Parse and Style Instructions
        String[] sections = INSTRUCTIONS.split("\n\n");
        for (String section : sections) {
            String[] lines = section.split("\n");
            Label heading = new Label(lines[0]);
            heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            heading.setTextFill(Color.WHITE);

            VBox sectionBox = new VBox(5);
            sectionBox.getChildren().add(heading);
            for (int i = 1; i < lines.length; i++) {
                Label line = new Label(lines[i].trim());
                line.setFont(Font.font("Arial", 16));
                line.setTextFill(Color.LIGHTGRAY);
                line.setWrapText(true);
                sectionBox.getChildren().add(line);
            }
            instructionsBox.getChildren().add(sectionBox);
        }

        scrollPane.setContent(instructionsBox);

        // Back Button
        Back = new Button("Back to Main Menu");
        Back.setPrefSize(200, 50);
        Back.setFont(Font.font("Chiller", FontWeight.BOLD, 24));
        Back.setTextFill(Color.WHITE);
        Back.setBackground(new Background(
            new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(10), null)
        ));
        Back.setAlignment(Pos.CENTER);
        applyButtonHoverEffect(Back);
        AnchorPane.setBottomAnchor(Back, 50.0);
        AnchorPane.setLeftAnchor(Back, 500.0);

        // Assemble Content
        contentBox.getChildren().addAll(title, scrollPane);
        root.getChildren().addAll(background, contentBox, Back);
        root.setBackground(overlayBackground); // Apply gradient overlay
    }

    // Hover Effect for Button
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

            // Optional: Play hover sound
           
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

    public static Button getBack() {
        return Back;
    }

    public String getInstructions() {
        return INSTRUCTIONS;
    }
}