package game.gui;


import java.util.ArrayList;

import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
import javafx.util.Duration;

public class View {
	private AnchorPane Scene1;  // Changed from AnchorPane to Pane
    private MainMenuView mainMenuView;
    private GameRulesView gameRulesView;
    private ModeView modeView;
    private EasyView easyView;  // New field
    private HardView hardView;
	private AnchorPane EasyScene;
	private AnchorPane HardScene;
	private AnchorPane GameInstructionScene;
	private AnchorPane GameOverScene;
	private AnchorPane ModeScene;
	private BorderPane WeaponShop; 
	private HBox info;	
	private String Score;
	private String Turn;
	private String Resources;
	private String Phase;
	private String Lanes;
	private AnchorPane InsufficientResourcesPopUp;
	private AnchorPane InvalidLanePopUp;
	private AnchorPane PassOrBuyPopUp;
	private AnchorPane SelectLanePopUpEasy;
	private AnchorPane SelectLanePopUpHard;
	private AnchorPane SelectModePopUp;
	private AnchorPane SelectWeaponPopUp;
	private ComboBox<String> Mode;
	private ComboBox<String> EasyLanes;
	private ComboBox<String> HardLanes;
	private Button next;
	private Button pass;
	private Button buy;
	private Button returntoStart;
	private Button PiercingCannon;
	private Button SniperCannon;
	private Button VolleySpread;
	private Button WallTrap;
	private Button deployWeapon1;
	private Button deployWeapon2;
	private static ImageView abnormal;
	private static ImageView armored;
	private static ImageView colossal;
	private static ImageView pure;
	private Label score;
	private Label turn;
	private Label phase;
	private Label resources;
	private Label lanes;
	private Label score2;
	private Label turn2;
	private Label phase2;
	private Label resources2;
	private Label lanes2;
	private Label selectALane1;
	private Label selectALane2;
	private Button weaponShopButtonEasy;
	private Button weaponShopButtonHard;
	private ArrayList <ImageView> ApproachingTitans;
	private ArrayList <Label> WallHealthEasy;
	private ArrayList <Label> WallHealthHard;
	private ArrayList <Label> WallDangerLevelEasy;
	private ArrayList <Label> WallDangerLevelHard;
	private ArrayList<Integer> laneCode;
	private int numberOfTitansPerTurn;
	private GridPane hard;
	private GridPane easy;
	private Label scorefinal;
	private ArrayList<ArrayList<TitanImageView>> allLanes;
	
	
	public ArrayList<ArrayList<TitanImageView>> getAllLanes() {
		return allLanes;
	}
	public void setAllLanes(ArrayList<ArrayList<TitanImageView>> allLanes) {
		this.allLanes = allLanes;
	}
	public Label getScorefinal() {
		return scorefinal;
	}
	public Label getSelectALane1() {
		return selectALane1;
	}
	public void setApproachingTitansHealth(ArrayList<Label> approachingTitansHealth) {
	}
	public Label getSelectALane2() {
		return selectALane2;
	}
	public Button getDeployWeapon1() {
		return deployWeapon1;
	}
	public Button getDeployWeapon2() {
		return deployWeapon2;
	}
	public String getEasyLanes() {
		int lastIndex = ((String)EasyLanes.getValue()).lastIndexOf(' ');
		return ((String)EasyLanes.getValue()).substring(lastIndex + 1);
	}
	public String getHardLanes() {
		int lastIndex = ((String)HardLanes.getValue()).lastIndexOf(' ');
		return ((String)HardLanes.getValue()).substring(lastIndex + 1);
	}
	public Button getPiercingCannon() {
		return PiercingCannon;
	}
	public Button getSniperCannon() {
		return SniperCannon;
	}
	public Button getVolleySpread() {
		return VolleySpread;
	}
	public Button getWallTrap() {
		return WallTrap;
	}
	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}
	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}
	public ImageView getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(ImageView abnormal) {
		View.abnormal = abnormal;
	}
	public ImageView getArmored() {
		return armored;
	}
	public void setArmored(ImageView armored) {
		View.armored = armored;
	}
	public ImageView getColossal() {
		return colossal;
	}
	public void setColossal(ImageView colossal) {
		View.colossal = colossal;
	}
	public ImageView getPure() {
		return pure;
	}
	public void setPure(ImageView pure) {
		View.pure = pure;
	}
	public String getScore() {
		return Score;
	}
	public void setScore(String score) {
		Score = score;
	}
	public String getTurn() {
		return Turn;
	}
	public void setTurn(String turn) {
		Turn = turn;
	}
	public String getResources() {
		return Resources;
	}
	public void setResources(String resources) {
		Resources = resources;
	}
	public String getPhase() {
		return Phase;
	}
	public void setPhase(String phase) {
		Phase = phase;
	}
	public String getLanes() {
		return Lanes;
	}
	public void setLanes(String lanes) {
		Lanes = lanes;
	}
	public Button getPass() {
		return pass;
	}
	public Button getBuy() {
		return buy;
	}
	public Button getNext() {
		return next;
	}
	
	public String getMode() {
		return (String) Mode.getValue();
	}
	public Button getReturntoStart() {
		return returntoStart;
	}
	
	public void setApproachingTitans(ArrayList<ImageView> approachingTitans) {
		ApproachingTitans = approachingTitans;
	}
	public ArrayList<Integer> getLaneCode() {
		return laneCode;
	}
	public void setLaneCode(ArrayList<Integer> laneCode) {
		this.laneCode = laneCode;
	}
	public ArrayList<ImageView> getApproachingTitans() {
		return ApproachingTitans;
	}
	
	
	public Group loadScene1(){
        Group root = new Group();
        root.getChildren().add(Scene1);
        return root;
    }

    // Add getters for the new button names if needed elsewhere
    public Button getNewGameButton() {
        return mainMenuView.getNewGameButton();
    }

    public Button getGameRulesButton() {
        return mainMenuView.getGameRulesButton();
    }

    public Button getExitButton() {
        return mainMenuView.getExitButton();
    }
    public Button getBack() {
		return GameRulesView.getBack();
	}

	public MainMenuView getMainMenuView() {
		return mainMenuView;
	}
	public GameRulesView getGameRulesView() {
		return gameRulesView;
	}
	public ModeView getModeView() {
		return modeView;
	}

	public View() {
        // Initializing all Scenes
        mainMenuView = new MainMenuView();
        Scene1 = mainMenuView.getRoot();
        Scene1.setPrefSize(1200, 700);

        modeView = new ModeView();
        ModeScene = modeView.getRoot();
        ModeScene.setPrefSize(1200, 700);

        easyView = new EasyView();  
        EasyScene = easyView.getRoot();
        EasyScene.setPrefSize(1200, 700);

        hardView = new HardView();  
        HardScene = hardView.getRoot();
        HardScene.setPrefSize(1200, 700);

        gameRulesView = new GameRulesView();
        GameInstructionScene = gameRulesView.getRoot();
        GameInstructionScene.setPrefSize(1200, 700);
        
		WeaponShop = new BorderPane();
		WeaponShop.setPrefSize(1200, 700);
		
		InsufficientResourcesPopUp = new AnchorPane();
		InsufficientResourcesPopUp.setPrefSize(600, 200);
		
		InvalidLanePopUp=new AnchorPane();
		InvalidLanePopUp.setPrefSize(600, 200);
		
		PassOrBuyPopUp=new AnchorPane();
		PassOrBuyPopUp.setPrefSize(600, 200);
		
		SelectLanePopUpEasy=new AnchorPane();
		SelectLanePopUpEasy.setPrefSize(600, 300);
		
		SelectLanePopUpHard=new AnchorPane();
		SelectLanePopUpHard.setPrefSize(600, 300);
		
		SelectModePopUp=new AnchorPane();
		SelectModePopUp.setPrefSize(600, 200);
		
		SelectWeaponPopUp=new AnchorPane();
		SelectWeaponPopUp.setPrefSize(600, 200);
		
		GameOverScene=new AnchorPane();
		GameOverScene.setPrefSize(1200,700);
//----------------------------------------------------------------------------------------------------------
		
		//Intializing Titan Images
		Image Abnormal = new Image("abnormal.png");
        Image Armored = new Image("armored.png");
        Image Colossal = new Image("colossal.png");
        Image Pure = new Image("pure.png");
        abnormal = new ImageView(Abnormal);
        armored = new ImageView(Armored);
        colossal = new ImageView(Colossal);
        pure = new ImageView(Pure);
        pure.setPreserveRatio(true);
        abnormal.setPreserveRatio(true);
        armored.setPreserveRatio(true);
        colossal.setPreserveRatio(true);
        pure.setFitHeight(100);
        abnormal.setFitHeight(80);
        armored.setFitHeight(100);
        colossal.setFitHeight(210);
	
	}
	@SuppressWarnings("static-access")
    public void addAllComponents() {
        // Removed info bar and wall health/danger level setup from here
        // Now managed by EasyView and HardView

        // Initialize remaining components
        weaponShopButtonEasy = easyView.getWeaponShopButton();
        weaponShopButtonHard = hardView.getWeaponShopButton();
        easy = easyView.getEasyGrid();
        hard = hardView.getHardGrid();

        // Game Over Scene (unchanged)
        returntoStart = new Button();
        returntoStart.setText("Main menu");
        returntoStart.setPrefSize(200, 40);
        returntoStart.setFont(new Font(22));
        returntoStart.setAlignment(Pos.CENTER);

        String videoPath = getClass().getResource("/GameOverVideo.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(1);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(700);
        mediaView.setPreserveRatio(false);
        AnchorPane.setTopAnchor(mediaView, 0.0);
        AnchorPane.setBottomAnchor(mediaView, 0.0);
        AnchorPane.setLeftAnchor(mediaView, 0.0);
        AnchorPane.setRightAnchor(mediaView, 0.0);
        Label label10 = new Label();
        label10.setText("Game Over");
        label10.setFont(new Font(30));
        label10.setVisible(true);
        label10.setAlignment(Pos.CENTER);

        scorefinal = new Label();
        scorefinal.setText("your Score is " + Score);
        scorefinal.setFont(new Font(30));
        scorefinal.setAlignment(Pos.CENTER);
     
        GameOverScene.getChildren().add(0, mediaView);
        GameOverScene.getChildren().addAll(returntoStart, label10, scorefinal);

        GameOverScene.setLeftAnchor(label10, 400.0);
        GameOverScene.setRightAnchor(label10, 400.0);
        GameOverScene.setTopAnchor(label10, 100.0);
        GameOverScene.setLeftAnchor(scorefinal, 400.0);
        GameOverScene.setRightAnchor(scorefinal, 400.0);
        GameOverScene.setTopAnchor(scorefinal, 300.0);
        GameOverScene.setLeftAnchor(returntoStart, 400.0);
        GameOverScene.setRightAnchor(returntoStart, 400.0);
        GameOverScene.setTopAnchor(returntoStart, 500.0);

        // Characters (unchanged)
        Image Abnormal = new Image("abnormal.png");
        Image Armored = new Image("armored.png");
        Image Colossal = new Image("colossal.png");
        Image Pure = new Image("pure.png");
        abnormal = new ImageView(Abnormal);
        armored = new ImageView(Armored);
        colossal = new ImageView(Colossal);
        pure = new ImageView(Pure);
        pure.setPreserveRatio(true);
        abnormal.setPreserveRatio(true);
        armored.setPreserveRatio(true);
        colossal.setPreserveRatio(true);
        pure.setFitHeight(52.5);
        abnormal.setFitHeight(35);
        armored.setFitHeight(52.5);
        colossal.setFitHeight(210);
    }
	public Group getInfo() {
		Group root = new Group();
		root.getChildren().add(info);
		return root;
	}
	public Group loadGameOverScene(){
		Group root = new Group();
		root.getChildren().add(this.GameOverScene);
		return root;
	}
	public Group loadGameInstructionScene(){
		Group root = new Group();
		root.getChildren().add(GameInstructionScene);
		return root;
	}
	public Group loadSelectModePopUp(){
		Group root = new Group();
		root.getChildren().add(SelectModePopUp);
		return root;
	}
	public Group loadWeaponShop(){
		Group root = new Group();
		root.getChildren().add(WeaponShop);
		return root;
	}
	public Group GameOverScene(){
		Group root = new Group();
		root.getChildren().add(GameOverScene);
		return root;
	}
	public Group loadModeScene(){
		Group root = new Group();
		root.getChildren().add(ModeScene);
		return root;
	}
	public Button getWeaponShopButtonEasy() {
        return easyView.getWeaponShopButton();
    }

    public Button getWeaponShopButtonHard() {
        return hardView.getWeaponShopButton();
    }

    public Group loadEasyScene() {
        Group root = new Group();
        root.getChildren().add(EasyScene);
        return root;
    }

    public Group loadHardScene() {
        Group root = new Group();
        root.getChildren().add(HardScene);
        return root;
    }
 
    public void updateInfo(String phase, String resources, String lanes, String turn, String score, String choosenMode, ArrayList<Lane> l) {
        this.Score = score;
        this.Phase = phase;
        this.Resources = resources;
        this.Lanes = lanes;
        this.Turn = turn;

        if ("Hard".equals(choosenMode)) {
            // Update info labels directly
            hardView.getScore().setText("Score: " + Score);
            hardView.getTurn().setText("Turn: " + Turn);
            hardView.getPhase().setText("Phase: " + Phase);
            hardView.getResources().setText("Resources: " + Resources);
            hardView.getLanes().setText("Lanes: " + Lanes);

            // Update wall health (ProgressBar) and danger level (Label)
            ArrayList<ProgressBar> wallHealth = hardView.getWallHealthHard();
            ArrayList<Label> dangerLevel = hardView.getWallDangerLevelHard(); // Changed to Label
            for (int i = 0; i < wallHealth.size() && i < l.size(); i++) {
                double maxHealth = 10000.0; // Assuming max wall health is 10000, adjust if different
                double currentHealth = l.get(i).getLaneWall().getCurrentHealth();
                double healthFraction = currentHealth / maxHealth;
                wallHealth.get(i).setProgress(healthFraction);
                // Dynamic color change
                if (healthFraction <= 0.25) {
                    wallHealth.get(i).setStyle("-fx-accent: red;");
                } else if (healthFraction <= 0.5) {
                    wallHealth.get(i).setStyle("-fx-accent: orange;");
                } else {
                    wallHealth.get(i).setStyle("-fx-accent: green;");
                }

                // Update danger level (simple text)
                dangerLevel.get(i).setText("Danger: " + l.get(i).getDangerLevel());
            }
        } else {
            // Update info labels directly
            easyView.getScore().setText("Score: " + Score);
            easyView.getTurn().setText("Turn: " + Turn);
            easyView.getPhase().setText("Phase: " + Phase);
            easyView.getResources().setText("Resources: " + Resources);
            easyView.getLanes().setText("Lanes: " + Lanes);

            // Update wall health (ProgressBar) and danger level (Label)
            ArrayList<ProgressBar> wallHealth = easyView.getWallHealthEasy();
            ArrayList<Label> dangerLevel = easyView.getWallDangerLevelEasy(); // Changed to Label
            for (int i = 0; i < wallHealth.size() && i < l.size(); i++) {
                double maxHealth = 10000.0; // Assuming max wall health is 10000, adjust if different
                double currentHealth = l.get(i).getLaneWall().getCurrentHealth();
                double healthFraction = currentHealth / maxHealth;
                wallHealth.get(i).setProgress(healthFraction);
                // Dynamic color change
                if (healthFraction <= 0.25) {
                    wallHealth.get(i).setStyle("-fx-accent: red;");
                } else if (healthFraction <= 0.5) {
                    wallHealth.get(i).setStyle("-fx-accent: orange;");
                } else {
                    wallHealth.get(i).setStyle("-fx-accent: green;");
                }

                // Update danger level (simple text)
                dangerLevel.get(i).setText("Danger: " + l.get(i).getDangerLevel());
            }
        }
    }
	
	public boolean sameImg(ImageView imageView1, ImageView imageView2) {
		if (imageView1 == null || imageView2 == null) {
            return false;
        }
        String imagePath1 = getImagePath(imageView1);
        String imagePath2 = getImagePath(imageView2);

        return imagePath1 != null && imagePath1.equals(imagePath2);
    }
    private static String getImagePath(ImageView imageView) {
        if (imageView == null || imageView.getImage() == null) {
            return null;
        }
        String imagePath = imageView.getImage().getUrl();
        return imagePath;
    }

	public void titanAttack(Node Titan){
		TranslateTransition translate = new TranslateTransition();//backward
		TranslateTransition translate2 = new TranslateTransition();//forward
		translate.setNode(Titan);
		translate.setDuration(Duration.millis(300));
		translate.setByX(-50);
		translate2.setNode(Titan);
		translate2.setDuration(Duration.millis(300));
		translate2.setByX(50);
        SequentialTransition sequentialTransition = new SequentialTransition(translate2, translate);
        sequentialTransition.play();
		
	}
	//here
	public void performTurnTitans() {
        if (Controller.getChoosenMode().equals("Easy")) {
        }
        for (ArrayList<TitanImageView> lane : this.allLanes) {
            for (TitanImageView t : lane) {
                // Update progress bar based on current health
                double maxHealth;
                if (t.titan instanceof PureTitan || t.titan instanceof AbnormalTitan) maxHealth = 100;
                else if (t.titan instanceof ArmoredTitan) maxHealth = 200;
                else maxHealth = 1000;
                
                double healthFraction = t.titan.getCurrentHealth() / maxHealth;
                t.healthBar.setProgress(healthFraction);
                
                // Change color to red when health is 25% or below
                if (healthFraction <= 0.25) {
                	 t.healthBar.setStyle("-fx-accent: red;");
                } else if (healthFraction <= 0.5) {
                	 t.healthBar.setStyle("-fx-accent: orange;");
                } else {
                	 t.healthBar.setStyle("-fx-accent: green;");
                }

                
                if (t.titan.isDefeated()) {
                    fade(t.titanImageView);
                    fade(t.healthBar);
                }
                else {
                    if (!t.titan.hasReachedTarget()) {
                        translate(t.titanImageView, t.titan.getSpeed() * 3);
                        translate(t.healthBar, t.titan.getSpeed() * 3);
                    }
                    else {
                        titanAttack(t.titanImageView);
                        titanAttack(t.healthBar);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("static-access")
    public void AddTurnTitans() {
        int count = 1;
        AnchorPane s = Controller.getChoosenMode().equals("Easy") ? EasyScene : HardScene;
        if (Controller.getChoosenMode().equals("Easy")) count++;
        for (ArrayList<TitanImageView> lane : this.allLanes) {
            int y = 0;
            switch (count) {
                case 1: y = 60; break;
                case 2: y = 170; break;
                case 3: y = 310; break;
                case 4: y = 410; break;
                case 5: y = 510; break;
            }
            for (TitanImageView t : lane) {
                ImageView ti = t.titanImageView;
                if (!s.getChildren().contains(ti)) {
                    s.getChildren().addAll(ti, t.healthBar);
                    s.setLeftAnchor(ti, (double)1200);
                    s.setTopAnchor(ti, (double)y);
                    s.setLeftAnchor(t.healthBar, (double)1190);
                    s.setTopAnchor(t.healthBar, (double)y-10);
                    translate(ti, 60);
                    translate(t.healthBar, 60);
                }
            }
            count++;
        }
    }

	//here
	public void deployWeapon(int weaponCode, int lane) {
	    Image i = null;
	    switch (weaponCode) {
	        case 1:i = new Image("canon.png");break;
	        case 2:i = new Image("sniper.png");break;
	        case 3:i = new Image("volly.png");break;
	        case 4:i = new Image("wallTrap.png");break;
	    }

	        ImageView v = new ImageView(i);
	        v.setPreserveRatio(true);
   			v.setFitHeight(45);
	        GridPane p = Controller.getChoosenMode().equals("Hard") ? hard : easy;
	        for (int col = 0; col < 10; col++) {
	            boolean cellFree = true;

	            for (Node node : p.getChildren()) {
	                Integer Row = GridPane.getRowIndex(node);
	                Integer Col = GridPane.getColumnIndex(node);
	                if (Row == null) Row = 0;
	                if (Col == null) Col = 0;

	                if (Row == lane && Col == col) {
	                    cellFree = false;
	                    break;
	                }
	            }
	            if (cellFree) {
	                p.add(v, col, lane);
	                break;
	            }
	        }
	    }


	private void translate(Node Titan,int distance){	//used to move the titan
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(Titan);
		translate.setDuration(Duration.millis(1200));
		distance*=-1;
		translate.setByX(distance);
		translate.play();
	}
	private void fade(Node Titan) { // Used when Titan dies
	    FadeTransition fadeTitan = new FadeTransition();
	    fadeTitan.setDuration(Duration.millis(1000));
	    fadeTitan.setNode(Titan);
	    fadeTitan.setToValue(0);

	    // Remove the node after fade transition is complete
	    fadeTitan.setOnFinished(event -> {
	        ((Pane) Titan.getParent()).getChildren().remove(Titan);
	    });

	    fadeTitan.play();
	}

}
