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
import javafx.scene.control.TextArea;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class View {
	private AnchorPane Scene1;  // Changed from AnchorPane to Pane
    private MainMenuView mainMenuView;
    private GameRulesView gameRulesView;
	private AnchorPane EasyScene;
	private AnchorPane HardScene;
	private AnchorPane GameInstructionScene;
	private AnchorPane GameOverScene;
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

	public View(){
		
		//Intializing all Scenes
		mainMenuView = new MainMenuView(); 
        Scene1 = mainMenuView.getRoot();  // Get the root Pane from FXML View
        Scene1.setPrefSize(1200, 700);
        
		EasyScene = new AnchorPane();
		EasyScene.setPrefSize(1200, 700);
		
		HardScene = new AnchorPane();
		HardScene.setPrefSize(1200, 700);
		
		gameRulesView = new GameRulesView();
	    GameInstructionScene = gameRulesView.getRoot(); // FIX: Use gameRulesView.getRoot()
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
	public void addAllComponents(){

		  
//----------------------------------------------------------------------------------------------------------
	
		//HardScene setup
		  weaponShopButtonHard = new Button();
		  weaponShopButtonHard.setText("Weapon Shop");
		  weaponShopButtonHard.setPrefSize(300,25);
		  weaponShopButtonHard.setMaxSize(300,25);
		  weaponShopButtonHard.setMinSize(300,25);
		  weaponShopButtonHard.setFont(new Font(13));
		  weaponShopButtonHard.setAlignment(Pos.CENTER);
		  hard= new GridPane();
		  hard.setPadding(new Insets(50));
		  hard.setStyle("-fx-background-color: transparent;");
		  for (int i = 0; i < 10; i++) {
	            RowConstraints row = new RowConstraints();
	            row.setPercentHeight(20); 
	            hard.getRowConstraints().add(row);

	            ColumnConstraints column = new ColumnConstraints();
	            column.setPercentWidth(10); 
	            hard.getColumnConstraints().add(column);
	        }
		  hard.setVgap(100); 
		  hard.setHgap(0);
		  hard.setPrefSize(100, 700);
		  Image background = new Image("lane5.png");
		  ImageView BackGround = new ImageView(background);
		  BackGround.setFitHeight(700);
		  BackGround.setFitWidth(1200);
		  HardScene.getChildren().add(0, BackGround);
		  HardScene.getChildren().addAll(weaponShopButtonHard,hard);
		  weaponShopButtonHard.setTranslateX(700);
		  HardScene.setLeftAnchor(hard, (double)0);
		  HardScene.setRightAnchor(hard, (double) 1050);
		  HardScene.setBottomAnchor(hard, (double) 100);
		  HardScene.setTopAnchor(hard, (double) 100);
		  hard.setTranslateX(-20);
		  hard.setTranslateY(-165);
		  BackGround.toBack();

//----------------------------------------------------------------------------------------------------------

			//EasyScene setup
		  weaponShopButtonEasy = new Button();
		  weaponShopButtonEasy.setText("Weapon Shop");
		  weaponShopButtonEasy.setPrefSize(300,25);
		  weaponShopButtonEasy.setMaxSize(300,25);
		  weaponShopButtonEasy.setMinSize(300,25);
		  weaponShopButtonEasy.setFont(new Font(13));
		  weaponShopButtonEasy.setAlignment(Pos.CENTER);
		  easy= new GridPane();
		  easy.setPadding(new Insets(30));
		  easy.setStyle("-fx-background-color: transparent;");
		  for (int i = 0; i < 10; i++) {
	            RowConstraints row = new RowConstraints();
	            row.setPercentHeight(33); 
	            easy.getRowConstraints().add(row);

	            ColumnConstraints column = new ColumnConstraints();
	            column.setPercentWidth(10); 
	            easy.getColumnConstraints().add(column);
	        }
		  easy.setVgap(100); 
		  easy.setHgap(0); 
		  easy.setPrefSize(100, 700);
		  Image background3 = new Image("3lanes.png");
		  ImageView BackGround3 = new ImageView(background3);
		  BackGround3.setFitHeight(700);
		  BackGround3.setFitWidth(1200);
		  EasyScene.getChildren().add(0, BackGround3);
		  EasyScene.getChildren().addAll(weaponShopButtonEasy,easy);
		  weaponShopButtonEasy.setTranslateX(700);
		  EasyScene.setLeftAnchor(easy, (double)0);
		  EasyScene.setRightAnchor(easy, (double) 850);
		  EasyScene.setBottomAnchor(easy, (double) 100);
		  EasyScene.setTopAnchor(easy, (double) 200);
		  easy.setTranslateY(-165);
		  BackGround3.toBack();
//----------------------------------------------------------------------------------------------------------

		 		  
  //Player's score,health,turn,resources,weapons,lanes and wall health

		  WallHealthEasy=new ArrayList<Label>();
		  WallHealthHard=new ArrayList<Label>();
		  WallDangerLevelEasy=new ArrayList<Label>();
		  WallDangerLevelHard=new ArrayList<Label>();
		  
		  
		  score = new Label();
		  score.setText("Score: "+Score);
		  score.setTextFill(Color.WHITE);
		  score.setFont(new Font(13));
		  turn = new Label();
		  turn.setText("Turn: "+Turn);
		  turn.setFont(new Font(13));
		  turn.setTextFill(Color.WHITE);
		  phase = new Label();
		  phase.setText("Phase: "+Phase);
		  phase.setFont(new Font(13));
		  phase.setTextFill(Color.WHITE);
		  resources = new Label();
		  resources.setText("Resources: "+Resources);
		  resources.setFont(new Font(13));
		  resources.setTextFill(Color.WHITE);
		  lanes = new Label();
		  lanes.setText("Lanes: "+Lanes);
		  lanes.setTextFill(Color.WHITE);
		  lanes.setFont(new Font(13));
		  info = new HBox(score,turn,phase,resources,lanes);
		  info.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		  info.setSpacing(40);
		  info.setPrefSize(500,25);
		  info.setMinWidth(700);
		  info.setMaxWidth(700);
		  info.setMinHeight(25);
		  info.setMaxHeight(25);
		  info.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		  HardScene.getChildren().add(info);
		  HardScene.setLeftAnchor(info, (double)0);
		  HardScene.setRightAnchor(info, (double)700);
		  HardScene.setTopAnchor(info, (double) 0);
		  HardScene.setBottomAnchor(info, (double) 1000);
		  
		  score2 = new Label();
		  score2.setText("Score: "+Score);
		  score2.setTextFill(Color.WHITE);
		  score2.setFont(new Font(13));
		  turn2 = new Label();
		  turn2.setText("Turn: "+Turn);
		  turn2.setFont(new Font(13));
		  turn2.setTextFill(Color.WHITE);
		  phase2 = new Label();
		  phase2.setText("Phase: "+Phase);
		  phase2.setFont(new Font(13));
		  phase2.setTextFill(Color.WHITE);
		  resources2 = new Label();
		  resources2.setText("Resources: "+Resources);
		  resources2.setFont(new Font(13));
		  resources2.setTextFill(Color.WHITE);
		  lanes2 = new Label();
		  lanes2.setText("Lanes: "+Lanes);
		  lanes2.setTextFill(Color.WHITE);
		  lanes2.setFont(new Font(13));
		  HBox info2=new HBox(score2,turn2,phase2,resources2,lanes2);
		  info2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		  info2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		  info2.setSpacing(40);
		  EasyScene.getChildren().add(info2);
		  EasyScene.setLeftAnchor(info2, (double)0);
		  EasyScene.setRightAnchor(info2, (double)700);
		  EasyScene.setTopAnchor(info2, (double) 0);
		  EasyScene.setBottomAnchor(info2, (double) 975);
		  Label wall1 = new Label();
		  wall1.setText("Health: 10000");
		  wall1.setFont(new Font(16));
		  wall1.setTextFill(Color.WHITE);
		  Label wall2 = new Label();
		  wall2.setText("Health: 10000");
		  wall2.setFont(new Font(16));
		  wall2.setTextFill(Color.WHITE);
		  Label wall3 = new Label();
		  wall3.setText("Health: 10000");
		  wall3.setFont(new Font(16));
		  wall3.setTextFill(Color.WHITE);
		  Label wall4 = new Label();
		  wall4.setText("Health: 10000");
		  wall4.setFont(new Font(16));
		  wall4.setTextFill(Color.WHITE);
		  Label wall5 = new Label();
		  wall5.setText("Health: 10000");
		  wall5.setFont(new Font(16));
		  wall5.setTextFill(Color.WHITE);
		  
		  WallHealthHard.add(wall1);
		  WallHealthHard.add(wall2);
		  WallHealthHard.add(wall3);
		  WallHealthHard.add(wall4);
		  WallHealthHard.add(wall5);

		  HardScene.getChildren().addAll(wall1,wall2,wall3,wall4,wall5);
		  HardScene.setLeftAnchor(wall1, (double)100);
		  HardScene.setTopAnchor(wall1, (double)50);
		  HardScene.setLeftAnchor(wall2, (double)100);
		  HardScene.setTopAnchor(wall2, (double)160);
		  HardScene.setLeftAnchor(wall3, (double)100);
		  HardScene.setTopAnchor(wall3, (double)290);
		  HardScene.setLeftAnchor(wall4, (double)100);
		  HardScene.setTopAnchor(wall4, (double)400);
		  HardScene.setLeftAnchor(wall5, (double)100);
		  HardScene.setTopAnchor(wall5, (double)510);
		  Label Wall1 = new Label();
		  Wall1.setText("Health: 10000");
		  Wall1.setFont(new Font(20));
		  Wall1.setTextFill(Color.WHITE);
		  Label Wall2 = new Label();
		  Wall2.setText("Health: 10000");
		  Wall2.setFont(new Font(20));
		  Wall2.setTextFill(Color.WHITE);
		  Label Wall3 = new Label();
		  Wall3.setText("Health: 10000");
		  Wall3.setFont(new Font(20));
		  Wall3.setTextFill(Color.WHITE);
		  
		  
		  WallHealthEasy.add(Wall1);
		  WallHealthEasy.add(Wall2);
		  WallHealthEasy.add(Wall3);
		  
		  EasyScene.getChildren().addAll(Wall1,Wall2,Wall3);
		  EasyScene.setLeftAnchor(Wall1, (double) 100);
		  EasyScene.setTopAnchor(Wall1, (double) 100);
		  EasyScene.setLeftAnchor(Wall2, (double) 100);
		  EasyScene.setTopAnchor(Wall2, (double) 300);
		  EasyScene.setLeftAnchor(Wall3, (double)100);
		  EasyScene.setTopAnchor(Wall3, (double) 500);
		  Label Lane1DL = new Label();
		  Lane1DL.setText("Danger level:");
		  Lane1DL.setFont(new Font(16));
		  Lane1DL.setTextFill(Color.WHITE);
		  Label Lane2DL = new Label();
		  Lane2DL.setText("Danger level: ");
		  Lane2DL.setFont(new Font(16));
		  Lane2DL.setTextFill(Color.WHITE);
		  Label Lane3DL = new Label();
		  Lane3DL.setText("Danger level: ");
		  Lane3DL.setFont(new Font(16));
		  Lane3DL.setTextFill(Color.WHITE);
		  Label Lane4DL = new Label();
		  Lane4DL.setText("Danger level: ");
		  Lane4DL.setFont(new Font(16));
		  Lane4DL.setTextFill(Color.WHITE);
		  Label Lane5DL = new Label();
		  Lane5DL.setText("Danger level: ");
		  Lane5DL.setFont(new Font(16));
		  Lane5DL.setTextFill(Color.WHITE);
		  
		  WallDangerLevelHard.add(Lane1DL);
		  WallDangerLevelHard.add(Lane2DL);
		  WallDangerLevelHard.add(Lane3DL);
		  WallDangerLevelHard.add(Lane4DL);
		  WallDangerLevelHard.add(Lane5DL);
		  
		  HardScene.getChildren().addAll(Lane1DL,Lane2DL,Lane3DL,Lane4DL,Lane5DL);
		  HardScene.setLeftAnchor(Lane1DL, (double)100);
		  HardScene.setTopAnchor(Lane1DL,(double)110);
		  HardScene.setLeftAnchor(Lane2DL, (double)100);
		  HardScene.setTopAnchor(Lane2DL,(double)210);
		  HardScene.setLeftAnchor(Lane3DL, (double)100);
		  HardScene.setTopAnchor(Lane3DL,(double)340);
		  HardScene.setLeftAnchor(Lane4DL, (double)100);
		  HardScene.setTopAnchor(Lane4DL,(double)450);
		  HardScene.setLeftAnchor(Lane5DL, (double)100);
		  HardScene.setTopAnchor(Lane5DL,(double)565);
		  Label lane1DL = new Label();
		  lane1DL.setText("Danger level: ");
		  lane1DL.setFont(new Font(20));
		  lane1DL.setTextFill(Color.WHITE);
		  Label lane2DL = new Label();
		  lane2DL.setText("Danger level: ");
		  lane2DL.setFont(new Font(20));
		  lane2DL.setTextFill(Color.WHITE);
		  Label lane3DL = new Label();
		  lane3DL.setText("Danger level: ");
		  lane3DL.setFont(new Font(20));
		  lane3DL.setTextFill(Color.WHITE);
		  
		  WallDangerLevelEasy.add(lane1DL);
		  WallDangerLevelEasy.add(lane2DL);
		  WallDangerLevelEasy.add(lane3DL);
		  
		  EasyScene.getChildren().addAll(lane1DL,lane2DL,lane3DL);
		  EasyScene.setLeftAnchor(lane1DL,(double) 100);
		  EasyScene.setTopAnchor(lane1DL,(double) 120);
		  EasyScene.setLeftAnchor(lane2DL,(double) 100);
		  EasyScene.setTopAnchor(lane2DL,(double) 320);
		  EasyScene.setLeftAnchor(lane3DL,(double) 100);
		  EasyScene.setTopAnchor(lane3DL,(double) 520);
		  
//----------------------------------------------------------------------------------------------------------
		  // characters
		  
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
		  
//----------------------------------------------------------------------------------------------------------
		  
		  // Game Over Scene
		  returntoStart=new Button();
		  returntoStart.setText("Main menu");
		  returntoStart.setPrefSize(200,40);
		  returntoStart.setFont(new Font(22));
		  returntoStart.setAlignment(Pos.CENTER);
		  
		  Label label10=new Label();
		  label10.setText("Game Over");
		  label10.setFont(new Font(30));
		  label10.setVisible(true);
		  label10.setAlignment(Pos.CENTER);
		  
		  scorefinal=new Label();
		  scorefinal.setText("your Score is "+Score);
		  scorefinal.setFont(new Font(30));
		  scorefinal.setAlignment(Pos.CENTER);
		  Image background4 = new Image("Snk.jpeg");
		  ImageView BackGround4 = new ImageView(background4);
		  BackGround4.setFitHeight(700);
		  BackGround4.setFitWidth(1200);
		  GameOverScene.getChildren().add(0, BackGround4);
		  GameOverScene.getChildren().addAll(returntoStart,label10,scorefinal);
		  
		  GameOverScene.setLeftAnchor(label10, (double)400);
		  GameOverScene.setRightAnchor(label10, (double)400);
		  GameOverScene.setTopAnchor(label10, (double) 100);
		
		  GameOverScene.setLeftAnchor(scorefinal, (double) 400);
		  GameOverScene.setRightAnchor(scorefinal, (double) 400);
		  GameOverScene.setTopAnchor(scorefinal, (double) 300);
		
		  GameOverScene.setLeftAnchor(returntoStart, (double) 400);
		  GameOverScene.setRightAnchor(returntoStart, (double) 400);
		  GameOverScene.setTopAnchor(returntoStart, (double) 500);
		  
	}
	
	public Button getWeaponShopButtonEasy() {
		return weaponShopButtonEasy;
	}
	public Button getWeaponShopButtonHard() {
		return weaponShopButtonHard;
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
	public Group loadHardScene(){
		Group root = new Group();
		root.getChildren().add(HardScene);
		return root;
	}
	public Group loadEasyScene(){
		Group root = new Group();
		root.getChildren().add(EasyScene);
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
	public void updateInfo(String phase,String resources,String lanes,String turn,String score,String ChoosenMode,ArrayList<Lane> l){
		this.Score=score;
		this.Phase=phase;
		this.Resources=resources;
		this.Lanes=lanes;
		this.Turn=turn;
		if(ChoosenMode=="Hard"){
		this.score.setText("Score: "+Score);
	    this.turn.setText("Turn: "+Turn);
		this.phase.setText("Phase: "+Phase);
		this.resources.setText("Resources: "+Resources);
		this.lanes.setText("Lanes: "+Lanes);
		for(int i=0;i<WallHealthHard.size();i++) {
			WallHealthHard.get(i).setText("Helath: "+l.get(i).getLaneWall().getCurrentHealth());
			WallDangerLevelHard.get(i).setText("Danger Level: "+l.get(i).getDangerLevel());
		}
		}
		else{
			this.score2.setText("Score: "+Score);
		    this.turn2.setText("Turn: "+Turn);
			this.phase2.setText("Phase: "+Phase);
			this.resources2.setText("Resources: "+Resources);
			this.lanes2.setText("Lanes: "+Lanes);
			for(int i=0;i<WallHealthEasy.size();i++) {
				WallHealthEasy.get(i).setText("Health: "+l.get(i).getLaneWall().getCurrentHealth());
				WallDangerLevelEasy.get(i).setText("Danger Level: "+l.get(i).getDangerLevel());
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
                if (healthFraction <= 0.5) {
                    t.healthBar.setStyle("-fx-accent: red;");
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
	private void fade(Node Titan){		//used when titan dies
			FadeTransition fadeTitan = new FadeTransition();
			fadeTitan.setDuration(Duration.millis(1000));
			fadeTitan.setNode(Titan);
			fadeTitan.setToValue(0);
			fadeTitan.play();
	}
}
