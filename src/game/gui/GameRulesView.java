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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameRulesView {
    private static AnchorPane root;
    private static Button Back;
    private static final String INSTRUCTIONS = "The Mission \n" +
            "     Brace yourself, warrior. Your sacred duty is to shield the towering Utopia District Walls from the relentless onslaught of titans. Hold the line as long as your courage endures.\n\n"
            +
            "The Battlefield \n" +
            "     Titans storm the walls, charging through multiple lanes like a tidal wave of destruction.\n" +
            "     Arm yourself with a deadly arsenal—buy and unleash weapons forged to crush these behemoths.\n\n" +
            "Each Move Counts \n" +
            "     Every turn, you call the shots:\n" +
            "     Drop a weapon into the fray and watch it rip through titan flesh.\n" +
            "     Hold your ground and pass, steeling yourself for the next clash.\n" +
            "     Once you act—or stand firm—the titans advance, your weapons strike, and the walls tremble under their fury.\n"
            +
            "     Fresh titans may rise from the shadows, spawned by the chaos of time and phase.\n\n" +
            "Glory or Ruin \n" +
            "     Victory isn’t the goal—survival is. Endure the storm for as many turns as your will allows.\n" +
            "     When every lane’s walls crumble to dust, it’s over. Your legend? Carved by the titans you’ve felled.\n\n"
            +
            "The Enemy Titans\n" +
            "     These aren’t mere beasts—they’re nightmares in flesh, each with brutal strength and twisted powers.\n"
            +
            "     Crush them to zero health. Every titan you slay fuels your resources and etches your name in blood.\n\n"
            +
            "Your Arsenal\n" +
            "     Command an array of ruthless weapons, each a masterpiece of destruction with its own range and wrath.\n"
            +
            "     Pick your tools wisely—strategy is your edge against the tide.\n\n" +
            "The Phases of War\n" +
            "     The battle escalates through three brutal chapters: Early whispers, Intense roars, and the Grumbling abyss.\n"
            +
            "     As time drags on, the titan horde grows fiercer, their numbers swelling with each phase.\n\n" +
            "Power in Your Hands\n" +
            "     Wield your resources like a warlord—spend them to summon weapons, grow them by shattering titans.\n" +
            "     One misstep, and the walls fall. Master the balance.\n\n" +
            "Final Call\n" +
            "     Steel your soul, tactician. Prioritize the weakest lanes, adapt to the carnage, and carve your saga atop the ruins. The fate of Utopia District rests on your blade—fight on, and may the titans tremble!";
    private String[] pages;
    private int currentPage = 0;
    private AnchorPane contentBox;
    private Label pageTitle;
    private Label pageBody;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private HBox navigationBox;
    private Label pageCounter;

    public GameRulesView() {
        root = new AnchorPane();
        root.setPrefSize(1200, 700);

        // Background Image
        Image backgroundImage = new Image("GameRulesBG.jpg");
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
                new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY));

        // Content Container
        contentBox = new AnchorPane();
        contentBox.setPrefSize(800, 550);
        contentBox.setBackground(new Background(
                new BackgroundFill(Color.rgb(20, 20, 20, 0.3), new CornerRadii(15), Insets.EMPTY)));
        AnchorPane.setTopAnchor(contentBox, 75.0);
        AnchorPane.setLeftAnchor(contentBox, 200.0);

        // Static "Game Rules" Title
        Label title = new Label("Game Rules");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, 50));
        title.setTextFill(Color.rgb(255, 255, 150));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.BLACK);
        titleShadow.setRadius(10);
        titleShadow.setSpread(0.6);
        title.setEffect(titleShadow);
        AnchorPane.setTopAnchor(title, 20.0);
        AnchorPane.setLeftAnchor(title, 0.0);
        AnchorPane.setRightAnchor(title, 0.0);
        title.setAlignment(Pos.CENTER);

        // Split instructions into pages
        pages = INSTRUCTIONS.split("\n\n");

        // Page Title Label
        pageTitle = new Label();
        pageTitle.setWrapText(true);
        pageTitle.setMaxWidth(700);
        pageTitle.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(pageTitle, 120.0);
        AnchorPane.setLeftAnchor(pageTitle, 50.0);
        AnchorPane.setRightAnchor(pageTitle, 50.0);

        // Page Body Label
        pageBody = new Label();
        pageBody.setWrapText(true);
        pageBody.setMaxWidth(700);
        pageBody.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(pageBody, 200.0);
        AnchorPane.setLeftAnchor(pageBody, 50.0);
        AnchorPane.setRightAnchor(pageBody, 50.0); // Navigation Arrows
        leftArrow = new ImageView(new Image("left-arrow.png"));
        leftArrow.setFitWidth(40);
        leftArrow.setFitHeight(40);
        leftArrow.setPreserveRatio(true);
        styleArrowImageView(leftArrow);
        leftArrow.setOnMouseClicked(e -> {
            if (currentPage > 0) {
                currentPage--;
                updatePageContent();
            }
        });

        rightArrow = new ImageView(new Image("right-arrow.png"));
        rightArrow.setFitWidth(40);
        rightArrow.setFitHeight(40);
        rightArrow.setPreserveRatio(true);
        styleArrowImageView(rightArrow);
        rightArrow.setOnMouseClicked(e -> {
            if (currentPage < pages.length - 1) {
                currentPage++;
                updatePageContent();
            }
        }); // Navigation Container
        navigationBox = new HBox(20);
        navigationBox.setAlignment(Pos.CENTER);
        AnchorPane.setBottomAnchor(navigationBox, 50.0);
        AnchorPane.setLeftAnchor(navigationBox, 0.0);
        AnchorPane.setRightAnchor(navigationBox, 0.0);

        // Page Counter
        pageCounter = new Label();
        pageCounter.setFont(Font.font("Chiller", FontWeight.BOLD, 30));
        pageCounter.setTextFill(Color.WHITE);
        AnchorPane.setBottomAnchor(pageCounter, 50.0);
        AnchorPane.setRightAnchor(pageCounter, 30.0);

        // Back Button
        Back = new Button("Back to Main Menu");
        Back.setPrefSize(200, 50);
        Back.setFont(Font.font("Chiller", FontWeight.BOLD, 24));
        Back.setTextFill(Color.WHITE);
        Back.setBackground(new Background(
                new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(10), null)));
        Back.setAlignment(Pos.CENTER);
        applyButtonHoverEffect(Back);
        AnchorPane.setBottomAnchor(Back, 50.0);
        AnchorPane.setLeftAnchor(Back, 500.0);

        // Assemble Content
        contentBox.getChildren().addAll(title, pageTitle, pageBody, navigationBox, pageCounter);
        root.getChildren().addAll(background, contentBox, Back);
        root.setBackground(overlayBackground);

        // Set initial page content after everything is initialized
        updatePageContent();
    }

    // Update the displayed page content with styled title and body
    private void updatePageContent() {
        String[] lines = pages[currentPage].split("\n");
        StringBuilder bodyText = new StringBuilder();

        // Set title
        pageTitle.setText(lines[0].trim());
        pageTitle.setFont(Font.font("Chiller", FontWeight.BOLD, 36));
        pageTitle.setTextFill(Color.WHITE);

        // Set body
        for (int i = 1; i < lines.length; i++) {
            bodyText.append(lines[i].trim()).append("\n");
        }
        pageBody.setText(bodyText.toString());
        pageBody.setFont(Font.font("Chiller", FontWeight.NORMAL, 28));
        pageBody.setTextFill(Color.WHITE);

        // Update page counter
        pageCounter.setText((currentPage + 1) + "/" + pages.length);

        updateNavigationVisibility();
    }

    // Update visibility of navigation arrows
    private void updateNavigationVisibility() {
        navigationBox.getChildren().clear();
        if (currentPage > 0) {
            navigationBox.getChildren().add(leftArrow);
        }
        if (currentPage < pages.length - 1) {
            navigationBox.getChildren().add(rightArrow);
        }
    } // Style the arrow ImageViews

    private void styleArrowImageView(ImageView imageView) {
        imageView.setOpacity(0.8);
        applyImageViewHoverEffect(imageView);
    }

    // Hover Effect for ImageViews
    private void applyImageViewHoverEffect(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setSpread(0.6);

        Glow glow = new Glow();
        glow.setLevel(0.7);

        imageView.setOnMouseEntered(event -> {
            imageView.setScaleX(1.2);
            imageView.setScaleY(1.2);
            imageView.setOpacity(1.0);
            imageView.setEffect(glow);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
            imageView.setOpacity(0.8);
            imageView.setEffect(shadow);
        });
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
            button.setEffect(glow);
        });

        button.setOnMouseExited(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setTextFill(button == Back ? Color.WHITE : Color.WHITE);
            button.setBackground(new Background(
                    new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
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