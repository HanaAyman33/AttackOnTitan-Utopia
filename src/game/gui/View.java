package game.gui;


import java.util.ArrayList;

import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class View {
	private AnchorPane Scene1;  // Changed from AnchorPane to Pane
    private MainMenuView mainMenuView;
    private GameRulesView gameRulesView;
    private ModeView modeView;
    private EasyView easyView;  // New field
    private HardView hardView;
    private GameOverView gameOverView;
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
	private AnchorPane SelectModePopUp;
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
	private Label selectALane1;
	private Label selectALane2;
	@SuppressWarnings("unused")
	private Button weaponShopButtonEasy;
	@SuppressWarnings("unused")
	private Button weaponShopButtonHard;
	private ArrayList <ImageView> ApproachingTitans;
	private ArrayList<Integer> laneCode;
	private int numberOfTitansPerTurn;
	private GridPane hard;
	private GridPane easy;
	private ArrayList<ArrayList<TitanImageView>> allLanes;
	
	
	public ArrayList<ArrayList<TitanImageView>> getAllLanes() {
		return allLanes;
	}
	public void setAllLanes(ArrayList<ArrayList<TitanImageView>> allLanes) {
		this.allLanes = allLanes;
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
	public Label getScorefinal() {
		return GameOverView.getScoreLabel();
	}
	public void setScore(String score) {
        this.Score = score;
        gameOverView.setScore(score); // Update GameOverView when Score changes
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
        return MainMenuView.getNewGameButton();
    }

    public Button getGameRulesButton() {
        return MainMenuView.getGameRulesButton();
    }

    public Button getExitButton() {
        return MainMenuView.getExitButton();
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
	public GameOverView getGameOverView() {
		return gameOverView;
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

        gameOverView = new GameOverView(Score);  
        GameOverScene = gameOverView.getRoot();
        HardScene.setPrefSize(1200, 700);
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
     
        // Initialize remaining components
        weaponShopButtonEasy = easyView.getWeaponShopButton();
        weaponShopButtonHard = hardView.getWeaponShopButton();
        easy = easyView.getEasyGrid();
        hard = hardView.getHardGrid();

        // Game Over button
        returntoStart = gameOverView.getReturnToStartButton();

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
        return EasyView.getWeaponShopButton();
    }

    public Button getWeaponShopButtonHard() {
        return HardView.getWeaponShopButton();
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
		setScore(score);
		this.Phase = phase;
		this.Resources = resources;
		this.Lanes = lanes;
		this.Turn = turn;
	
		updateLabels(choosenMode, score, turn, phase, resources, lanes);
		updateWallHealthAndDanger(choosenMode, l);
	}
	
	private void updateLabels(String choosenMode, String score, String turn, String phase, String resources, String lanes) {
		if ("Hard".equals(choosenMode)) {
			hardView.getScore().setText("Score: " + score);
			hardView.getTurn().setText("Turn: " + turn);
			hardView.getPhase().setText("Phase: " + phase);
			hardView.getResources().setText("Resources: " + resources);
			hardView.getLanes().setText("Lanes: " + lanes);
		} else {
			easyView.getScore().setText("Score: " + score);
			easyView.getTurn().setText("Turn: " + turn);
			easyView.getPhase().setText("Phase: " + phase);
			easyView.getResources().setText("Resources: " + resources);
			easyView.getLanes().setText("Lanes: " + lanes);
		}
	}
	
	private void updateWallHealthAndDanger(String choosenMode, ArrayList<Lane> l) {
		ArrayList<ProgressBar> wallHealth = "Hard".equals(choosenMode) ? hardView.getWallHealthHard() : easyView.getWallHealthEasy();
		ArrayList<Label> dangerLevel = "Hard".equals(choosenMode) ? hardView.getWallDangerLevelHard() : easyView.getWallDangerLevelEasy();
	
		int expectedLanes = "Hard".equals(choosenMode) ? 5 : 3;
		if (wallHealth.size() < expectedLanes || dangerLevel.size() < expectedLanes) {
			System.err.println("Warning: Insufficient UI elements for " + choosenMode + " mode. Expected " + expectedLanes + 
							   ", got " + wallHealth.size() + " health bars, " + dangerLevel.size() + " danger labels.");
		}
	
		for (int i = 0; i < l.size() && i < wallHealth.size() && i < dangerLevel.size(); i++) {
			double maxHealth = 10000.0;
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
	
			// Update danger level
			dangerLevel.get(i).setText("Danger: " + l.get(i).getDangerLevel());
		}
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
    
	public void AddTurnTitans() {
		int count = 1;
		AnchorPane s = getS();
		for (ArrayList<TitanImageView> lane : this.allLanes) {
			int y = getY(count);
			addTitansInLane(lane, s, y);
			count++;
		}
	}
	
	private AnchorPane getS() {
		return Controller.getChoosenMode().equals("Easy") ? EasyScene : HardScene;
	}
	
	private int getY(int count) {
		int y = 0;
		if (Controller.getChoosenMode().equals("Easy")) {
			switch (count) {
				case 1: y = 210; break;
				case 2: y = 330; break;
				case 3: y = 430; break;
			}
		} else {
			switch (count) {
				case 1: y = 150; break;
				case 2: y = 225; break;
				case 3: y = 330; break;
				case 4: y = 400; break;
				case 5: y = 480; break;
			}
		}
		return y;
	}
	
	private void addTitansInLane(ArrayList<TitanImageView> lane, AnchorPane s, int y) {
		int dis = Controller.getChoosenMode().equals("Easy")?1200:1150;
		int temp = y;
		for (TitanImageView t : lane) {
			int currentDis = dis;
			int currentY = y;
			if (t.titan instanceof ColossalTitan) {
				currentDis = Controller.getChoosenMode().equals("Easy")?1265:1215;
				currentY = temp - 20;
			}
			addTitan(t, s, currentDis, currentY);
		}
	}
	
	private void addTitan(TitanImageView t, AnchorPane s, int dis, int y) {
		ImageView ti = t.titanImageView;
		if (!s.getChildren().contains(ti)) {
			s.getChildren().addAll(ti, t.healthBar);
			AnchorPane.setLeftAnchor(ti, (double) dis);
			AnchorPane.setTopAnchor(ti, (double) y);
			AnchorPane.setLeftAnchor(t.healthBar, (double) (dis - 10));
			AnchorPane.setTopAnchor(t.healthBar, (double) (y - 10));
			translate(ti, 60);
			translate(t.healthBar, 60);
		}
	}
	public void deployWeapon(int weaponCode, int lane) {
		Image i = null;
		switch (weaponCode) {
			case 1: i = new Image("canon.png"); break;
			case 2: i = new Image("sniper.png"); break;
			case 3: i = new Image("volly.png"); break;
			case 4: i = new Image("wallTrap.png"); break;
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
	
				if (Row == lane - 1 && Col == col) { 
					cellFree = false;
					break;
				}
			}
			if (cellFree) {
				p.add(v, col, lane - 1);
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
