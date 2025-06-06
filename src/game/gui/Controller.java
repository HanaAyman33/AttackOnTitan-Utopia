package game.gui;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.PriorityQueue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class Controller extends Application {
	private static Controller instance;
	private static Battle battle;
	private static View view;
	private static Group root;
	private static Scene scene;
	private Stage PopUpStage;
	private static Stage MainStage;
	private static String ChoosenMode;
	private Group PopUpRoot;
	private static String passOrBuy = "pass";
	private Image icon;
	private int weaponCode;
	private static int LaneChoosen;
	boolean close = false;
	private static boolean waitingForPlayerChoice = false;
	private static boolean autoPassMode = true; // Game runs automatically
	private Timeline autoPassTimer;
	private Timeline delayTimer; // Track the delay timer for proper cleanup
	private boolean delayedResumeScheduled = false; // Prevent multiple delay timers
	private ChoiceDialog<String> d1;
	private ChoiceDialog<String> d2;
	private ChoiceDialog<String> d3;
	private Alert alert1;
	private Alert alert2;

	public static String getChoosenMode() {
		return ChoosenMode;
	}

	public static View getView() {
		return view;
	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage stage) throws IOException {
		instance = this; // Set the static instance
		MainStage = stage;
		view = new View();
		view.addAllComponents();
		root = view.loadScene1();
		scene = new Scene(root, 1200, 700);
		battle = null;
		view.setScore("0");
		view.setResources("0");
		view.setTurn("0");
		view.setPhase("EARLY");
		view.setLanes("1,2,3,4,5");
		Button BackToMainMenu = GameRulesView.getBack();
		Button returntoStart = view.getReturntoStart();
		// Note: Removed weapon shop buttons - now using weapon cards
		Button NewGame = MainMenuView.getNewGameButton();
		Button GameRules = MainMenuView.getGameRulesButton();
		Button Exit = MainMenuView.getExitButton();
		Button Start = ModeView.getStartButton();
		NewGame.setOnMouseClicked(event -> this.handleNewGameButton(event));
		GameRules.setOnMouseClicked(event -> this.handleButtonGameRules(event));
		Exit.setOnMouseClicked(event -> this.handleExitButton());
		Start.setOnMouseClicked(event -> this.handleButtonStart(event));
		BackToMainMenu.setOnMouseClicked(event -> handleButtonBackToMainMenu(event));
		returntoStart.setOnMouseClicked(event -> handleReturnButton(event));
		// Note: Removed weapon shop button handlers - now using weapon card handlers
		icon = new Image("icon.png");

		stage.setOnCloseRequest(event -> {
			event.consume();
			handleExitButton(); // Reuse the same exit logic
		});

		stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Attack on Titan: Utopia");
		stage.getIcons().add(icon);
		stage.show();
	}

	public void handleNewGameButton(Event event) {
		root = view.loadModeScene();
		Stage stage = MainStage;
		scene = new Scene(root, 1200, 700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void handleButtonGameRules(Event event) {
		root = view.loadGameInstructionScene();
		Stage stage = MainStage;
		scene = new Scene(root, 1200, 700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	private void handleExitButton() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to exit the game?");
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.initOwner(MainStage);
		alert.showAndWait().ifPresent(response -> {
			if (response == javafx.scene.control.ButtonType.OK) {
				close = true;
				// Properly stop all timers before exit
				stopAutoPassMode();
				if (d1 != null)
					d1.close();
				if (d2 != null)
					d2.close();
				if (d3 != null)
					d3.close();
				if (alert1 != null)
					alert1.close();
				if (alert2 != null)
					alert2.close();
				Platform.exit();
			}
		});
	}

	public void handleButtonNext(Event event) {// handles which mode the player chooses and if he did not a pop up will
												// appear
		if (view.getMode() == null) {
			PopUpRoot = view.loadSelectModePopUp();
			Scene scene = new Scene(PopUpRoot);
			PopUpStage = new Stage();
			PopUpStage.setScene(scene);
			PopUpStage.show();
		} else {
			root = view.loadGameInstructionScene();
			Stage stage = MainStage;
			scene = new Scene(root, 1200, 700);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		}
		ChoosenMode = view.getMode();
	}

	public void handleButtonBackToMainMenu(Event event) {
		root = view.loadScene1();
		Stage stage = MainStage;
		scene = new Scene(root, 1200, 700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void handleButtonStart(Event event) {
		ChoosenMode = ModeView.getSelectedMode();

		if (ChoosenMode.equals("Hard")) {
			root = view.loadHardScene();
			Stage stage = MainStage;
			scene = new Scene(root, 1200, 700);
			stage.setScene(scene);
			stage.show();
		} else {
			root = view.loadEasyScene();
			Stage stage = MainStage;
			scene = new Scene(root, 1200, 700);
			stage.setScene(scene);
			stage.show();
		}
		int initialNumOfLanes = ChoosenMode == "Easy" ? 3 : 5;
		int initialResourcesPerLane = ChoosenMode == "Easy" ? 250 : 125;
		try {
			battle = new Battle(1, 0, 291, initialNumOfLanes, initialResourcesPerLane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		battle.refillApproachingTitans();
		String Lane = ChoosenMode == "Hard" ? "1,2,3,4,5" : "1,2,3";
		view.updateInfo("" + battle.getBattlePhase(), "" + battle.getResourcesGathered(), Lane,
				"" + battle.getNumberOfTurns(), "" + battle.getScore(), ChoosenMode, battle.getOriginalLanes());
		view.setApproachingTitans(approachingTitansImages(battle.getApproachingTitans()));
		view.setApproachingTitansHealth(approachingTitansHealth(battle.getApproachingTitans()));
		view.setNumberOfTitansPerTurn(battle.getNumberOfTitansPerTurn());
		view.setLaneCode(lanes(battle.getLanes(), battle.getOriginalLanes()));
		view.setAllLanes(ImgIntializer(battle.getLanes()));
		// Setup button handlers now that the scene is loaded
		setupButtonHandlers();

		// Start automatic game loop
		startAutoPassMode();
	}

	private void startAutoPassMode() {
		autoPassMode = true;
		autoPassTimer = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
			if (autoPassMode && !close) {
				// Automatically pass turn
				Platform.runLater(() -> {
					Controller.passOrBuy = "Pass";
					processPlayerChoice();
				});
			}
		}));
		autoPassTimer.setCycleCount(Timeline.INDEFINITE);
		autoPassTimer.play();
	}

	private void stopAutoPassMode() {
		autoPassMode = false;
		delayedResumeScheduled = false;
		if (autoPassTimer != null) {
			autoPassTimer.stop();
		}
		// Also stop any pending delay timer
		if (delayTimer != null) {
			delayTimer.stop();
			delayTimer = null;
		}
	}

	private void resumeAutoPassMode() {
		// Resume immediately for regular cases
		resumeAutoPassModeWithDelay(0);
	}

	private void resumeAutoPassModeWithDelay(double delaySeconds) {
		// Prevent multiple delay timers from being created
		if (delayedResumeScheduled) {
			return;
		}

		// Stop any existing delay timer first
		if (delayTimer != null) {
			delayTimer.stop();
			delayTimer = null;
		}

		// For immediate resume, just restart auto-pass
		if (delaySeconds <= 0) {
			autoPassMode = true;
			if (autoPassTimer != null) {
				autoPassTimer.play();
			}
			return;
		}

		// Mark that a delayed resume is scheduled
		delayedResumeScheduled = true;

		// Create a single-use timeline for delayed resumption
		delayTimer = new Timeline(new KeyFrame(Duration.seconds(delaySeconds), e -> {
			autoPassMode = true;
			delayedResumeScheduled = false;
			if (autoPassTimer != null) {
				autoPassTimer.play();
			}
			// Clean up the delay timer after use
			delayTimer = null;
		}));
		delayTimer.play();
	}

	public void processPlayerChoice() {
		if (battle.isGameOver()) {
			stopAutoPassMode(); // Stop auto-pass when game ends
			view.getScorefinal().setText("Your Score is: " + battle.getScore());
			root = view.loadGameOverScene();
			Stage stage = MainStage;
			scene = new Scene(root, 1200, 700);
			stage.setScene(scene);
			stage.show();
			return;
		}

		if (this.close == true)
			return;
		if (Controller.passOrBuy.equals("Pass")) {
			battle.passTurn();
			view.AddTurnTitans();
			view.performTurnTitans();
			this.updateViewInfo();
		} else {
			try {
				// Note: weaponCode and lane are set by drag and drop, no need for dialog
				// handleSelectedLane is now called from handleWeaponDropOnLane
				battle.purchaseWeapon(weaponCode, battle.getOriginalLanes().get(Controller.LaneChoosen - 1));
				deployWeapon();
				view.AddTurnTitans();
				view.performTurnTitans();
				this.updateViewInfo();
				// Resume auto-pass after weapon deployment with delay to allow animations to
				// complete
				Controller.passOrBuy = "Pass";
				resumeAutoPassModeWithDelay(1.3); // Wait 1.3 seconds for titan animations to complete
			} catch (InsufficientResourcesException e) {
				alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setContentText("You do not have enough resources to buy this weapon");
				alert2.setTitle("NO RESOURCES");
				alert2.setHeaderText(null);
				alert2.initOwner(MainStage);
				alert2.showAndWait();
				// Resume auto-pass after error - no delay needed since no animations were
				// triggered
				Controller.passOrBuy = "Pass";
				resumeAutoPassMode();
			} catch (InvalidLaneException e) {
				alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setContentText("You cannot by a Weapon in this Lane");
				alert1.setTitle("INVALID LANE");
				alert1.setHeaderText(null);
				alert1.initOwner(MainStage);
				alert1.showAndWait(); // Resume auto-pass after error - no delay needed since no animations were
										// triggered
				Controller.passOrBuy = "Pass";
				resumeAutoPassMode();
			}
		}
	}

	public void OpenWeaponShop() {
		if (this.close == true)
			return;
		d2 = new ChoiceDialog<>("Weapon", "Anti-Titan Shell" + "\n" + "Price: 25" + "\n" + "Damage: 10",
				"Long Range Spear" + "\n" + "Price: 25" + "\n" + "Damage: 35",
				"Wall Spread Cannon" + "\n" + "Price: 100" + "\n" + "Damage: 5" + "\n" + "Range: 20-25",
				"Proximity Trap" + "\n" + "Price: 75" + "\n" + "Damage: 100");
		d2.setTitle("WeaponShop");
		d2.setHeaderText("Please select a Weapon:");
		d2.setContentText("Choose A Weapon");
		d2.getDialogPane().setPrefWidth(600);
		d2.getDialogPane().setPrefHeight(200);
		d2.initOwner(MainStage);
		d2.initModality(Modality.NONE);
		Optional<String> result = d2.showAndWait();
		result.ifPresent(choice -> {
			if (choice.equals("Anti-Titan Shell" + "\n" + "Price: 25" + "\n" + "Damage: 10")) {
				this.weaponCode = 1;
			} else if (choice.equals("Long Range Spear" + "\n" + "Price: 25" + "\n" + "Damage: 35")) {
				this.weaponCode = 2;
			} else if (choice
					.equals("Wall Spread Cannon" + "\n" + "Price: 100" + "\n" + "Damage: 5" + "\n" + "Range: 20-25")) {
				this.weaponCode = 3;
			} else if (choice.equals("Proximity Trap" + "\n" + "Price: 75" + "\n" + "Damage: 100")) {
				this.weaponCode = 4;
			}
		});
	}

	public void deployWeapon() {
		view.deployWeapon(weaponCode, LaneChoosen);
	}

	public void handleSelectedLane(int laneIndex) {
		if (this.close == true)
			return;

		// Direct lane selection without dialog
		Controller.LaneChoosen = laneIndex;
	}

	public void handlePassButton() {
		if (waitingForPlayerChoice) {
			Controller.passOrBuy = "Pass";
			waitingForPlayerChoice = false;
			processPlayerChoice();
		}
	}

	public void handleBuyButton() {
		if (waitingForPlayerChoice) {
			Controller.passOrBuy = "Buy";
			waitingForPlayerChoice = false;
			processPlayerChoice();
		}
	}

	public void setupButtonHandlers() {
		if (ChoosenMode != null) {
			if (ChoosenMode.equals("Hard")) {
				// Setup weapon card handlers for Hard mode
				setupWeaponCardHandlers(
						HardView.getAntiTitanShellCard(),
						HardView.getLongRangeSpearCard(),
						HardView.getWallSpreadCannonCard(),
						HardView.getProximityTrapCard());
			} else {
				// Setup weapon card handlers for Easy mode
				setupWeaponCardHandlers(
						EasyView.getAntiTitanShellCard(),
						EasyView.getLongRangeSpearCard(),
						EasyView.getWallSpreadCannonCard(),
						EasyView.getProximityTrapCard());
			}
		}
	}

	private void setupWeaponCardHandlers(Button antiTitanShell, Button longRangeSpear,
			Button wallSpreadCannon, Button proximityTrap) {
		if (antiTitanShell != null) {
			antiTitanShell.setOnMouseClicked(event -> handleWeaponCardClick(1));
		}
		if (longRangeSpear != null) {
			longRangeSpear.setOnMouseClicked(event -> handleWeaponCardClick(2));
		}
		if (wallSpreadCannon != null) {
			wallSpreadCannon.setOnMouseClicked(event -> handleWeaponCardClick(3));
		}
		if (proximityTrap != null) {
			proximityTrap.setOnMouseClicked(event -> handleWeaponCardClick(4));
		}
	}

	public void handleWeaponCardClick(int weaponCode) {
		// Stop auto-pass when player interacts with weapon card
		stopAutoPassMode();

		this.weaponCode = weaponCode;
		// Don't automatically trigger buy - wait for drag and drop
		System.out.println("Weapon card selected: " + weaponCode + ". Drag to a lane to deploy.");
	}

	public void handleWeaponDropOnLane(int weaponCode, int laneIndex) {
		// Stop auto-pass when player interacts with weapon drop
		stopAutoPassMode();

		this.weaponCode = weaponCode;
		Controller.LaneChoosen = laneIndex;
		Controller.passOrBuy = "Buy";
		processPlayerChoice();
	}

	public ArrayList<TitanImageView> getImg(PriorityQueue<Titan> titans, ArrayList<TitanImageView> titanImages) {
		ArrayList<TitanImageView> res = new ArrayList<TitanImageView>();
		PriorityQueue<Titan> tmp = new PriorityQueue<Titan>();

		while (!titans.isEmpty()) {
			Titan t = titans.remove();
			boolean flag = false;
			for (TitanImageView v : titanImages) {
				if (v.titan == t) {
					flag = true;
					res.add(v);
					tmp.add(t);
					break;
				}
			}

			if (flag)
				continue;

			ImageView v = null;
			ProgressBar health = new ProgressBar(1.0);
			health.setStyle("-fx-accent: green;");
			health.setPrefWidth(75);

			if (t instanceof PureTitan) {
				Image Pure = new Image("pure.png");
				v = new ImageView(Pure);
				v.setPreserveRatio(true);
				v.setFitHeight(100);
			} else if (t instanceof AbnormalTitan) {
				Image Abnormal = new Image("abnormal.png");
				v = new ImageView(Abnormal);
				v.setPreserveRatio(true);
				v.setFitHeight(80);
			} else if (t instanceof ArmoredTitan) {
				Image Armored = new Image("armored.png");
				v = new ImageView(Armored);
				v.setPreserveRatio(true);
				v.setFitHeight(100);
			} else {
				Image Colossal = new Image("colossal.png");
				v = new ImageView(Colossal);
				v.setPreserveRatio(true);
				v.setFitHeight(110);
			}

			TitanImageView x = new TitanImageView(t, v, health);
			res.add(x);
			tmp.add(t);
		}

		titans.addAll(tmp);
		return res;
	}

	public ArrayList<TitanImageView> getImgInitilizer(PriorityQueue<Titan> titans) {
		ArrayList<TitanImageView> res = new ArrayList<TitanImageView>();
		PriorityQueue<Titan> tmp = new PriorityQueue<Titan>();

		while (!titans.isEmpty()) {
			Titan t = titans.remove();
			ImageView v = null;
			ProgressBar health = new ProgressBar(1.0);
			health.setStyle("-fx-accent: green;");
			health.setPrefWidth(75);

			if (t instanceof PureTitan) {
				Image Pure = new Image("pure.png");
				v = new ImageView(Pure);
				v.setPreserveRatio(true);
				v.setFitHeight(100);
			} else if (t instanceof AbnormalTitan) {
				Image Abnormal = new Image("abnormal.png");
				v = new ImageView(Abnormal);
				v.setPreserveRatio(true);
				v.setFitHeight(80);
			} else if (t instanceof ArmoredTitan) {
				Image Armored = new Image("armored.png");
				v = new ImageView(Armored);
				v.setPreserveRatio(true);
				v.setFitHeight(100);
			} else {
				Image Colossal = new Image("colossal.png");
				v = new ImageView(Colossal);
				v.setPreserveRatio(true);
				v.setFitHeight(110);
			}

			TitanImageView x = new TitanImageView(t, v, health);
			res.add(x);
			tmp.add(t);
		}

		titans.addAll(tmp);
		return res;
	}

	public ArrayList<ArrayList<TitanImageView>> img(ArrayList<Lane> lanes) {
		ArrayList<ArrayList<TitanImageView>> res = new ArrayList<ArrayList<TitanImageView>>();
		int i = 0;

		while (i < view.getAllLanes().size()) {
			Lane l = battle.getOriginalLanes().get(i);
			if (!battle.getOriginalLanes().get(i).isLaneLost())
				res.add(getImg(l.getTitans(), view.getAllLanes().get(i)));
			i++;
		}
		return res;
	}

	public ArrayList<ArrayList<TitanImageView>> ImgIntializer(PriorityQueue<Lane> lanes) {
		ArrayList<ArrayList<TitanImageView>> res = new ArrayList<ArrayList<TitanImageView>>();
		PriorityQueue<Lane> tmp = new PriorityQueue<Lane>();

		while (!lanes.isEmpty()) {
			Lane l = lanes.remove();
			tmp.add(l);
			res.add(getImgInitilizer(l.getTitans()));
		}

		lanes.addAll(tmp);
		return res;
	}

	public void handleReturnButton(Event event) {

		root = view.loadScene1();
		scene = new Scene(root);
		Stage stage = MainStage;
		stage.setScene(scene);
		stage.show();

	}

	public ArrayList<ImageView> approachingTitansImages(ArrayList<Titan> approachingTitans) {// change the
																								// approachingTitans
																								// list to ImageView

		ArrayList<ImageView> Images = new ArrayList<ImageView>();
		int i = 0;

		while (i < approachingTitans.size()) {
			Titan t = approachingTitans.get(i);
			if (t instanceof PureTitan) {

				Image Pure = new Image("pure.png");
				ImageView P = new ImageView(Pure);
				P.setPreserveRatio(true);
				P.setFitHeight(100);
				Images.add(P);

			}

			else if (t instanceof AbnormalTitan) {

				Image Abnormal = new Image("abnormal.png");
				ImageView abnormal = new ImageView(Abnormal);
				abnormal.setPreserveRatio(true);
				abnormal.setFitHeight(80);
				Images.add(abnormal);

			}

			else if (t instanceof ArmoredTitan) {

				Image Armored = new Image("armored.png");
				ImageView armored = new ImageView(Armored);
				armored.setPreserveRatio(true);
				armored.setFitHeight(100);
				Images.add(armored);

			}

			else {

				Image Colossal = new Image("colossal.png");
				ImageView colossal = new ImageView(Colossal);
				colossal.setPreserveRatio(true);
				colossal.setFitHeight(110);
				Images.add(colossal);

			}

			i++;

		}

		return Images;

	}

	public ArrayList<Label> approachingTitansHealth(ArrayList<Titan> approachingTitans) {// change the
																							// approachingTitansHealth
																							// list to ImageView

		ArrayList<Label> labels = new ArrayList<Label>();
		int i = 0;

		while (i < approachingTitans.size()) {

			Titan t = approachingTitans.get(i);
			Label health = new Label();
			health.setText("Health: " + t.getBaseHealth());
			health.setFont(new Font(15));
			health.setTextFill(Color.WHITE);
			labels.add(health);
			i++;

		}

		return labels;
	}

	public void updateViewInfo() {

		view.setApproachingTitans(approachingTitansImages(battle.getApproachingTitans()));
		view.setApproachingTitansHealth(approachingTitansHealth(battle.getApproachingTitans()));
		view.setNumberOfTitansPerTurn(battle.getNumberOfTitansPerTurn());
		view.setLaneCode(lanes(battle.getLanes(), battle.getOriginalLanes()));
		Collections.sort(view.getLaneCode());
		view.updateInfo("" + battle.getBattlePhase(), "" + battle.getResourcesGathered(), "" + view.getLaneCode(),
				"" + battle.getNumberOfTurns(), "" + battle.getScore(), ChoosenMode, battle.getOriginalLanes());
		view.setAllLanes(img(battle.getOriginalLanes()));

	}

	public ArrayList<Integer> lanes(PriorityQueue<Lane> lanes, ArrayList<Lane> originalLanes) {// change the lanes list
																								// to int

		ArrayList<Integer> codes = new ArrayList<Integer>();
		PriorityQueue<Lane> tmp = new PriorityQueue<Lane>();

		while (!lanes.isEmpty()) {

			tmp.offer(lanes.peek());
			Lane l = lanes.remove();
			codes.add(originalLanes.indexOf(l) + 1);

		}

		while (!tmp.isEmpty()) {

			lanes.offer(tmp.poll());

		}

		return codes;
	}

	public static void main(String[] args) {

		launch(args);

	}

}
