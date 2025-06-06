package game.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class HardView extends BaseGameView {

    private static HardView instance;

    public HardView() {
        super();
        HardView.instance = this;
    }

    @Override
    protected String getLaneBackgroundPath() {
        return "/lane5.png";
    }

    @Override
    protected int getNumberOfLanes() {
        return 5;
    }

    @Override
    protected GridConfig getGridConfig() {
        return new GridConfig(
                10, // padding
                100, // width
                550, // height
                40, // vgap
                5, // rows
                0.0, // leftAnchor
                1050.0, // rightAnchor
                130.0, // topAnchor
                85.0 // bottomAnchor
        );
    }

    @Override
    protected WallConfig getWallConfig() {
        return new WallConfig(
                100, // progressBarWidth
                165.0, // baseTopOffset
                100.0, // spacing
                90.0, // progressBarLeftAnchor
                30.0, // progressBarTopOffset
                150.0 // labelLeftAnchor
        );
    }

    // Methods that maintain compatibility with existing Controller usage
    public GridPane getHardGrid() {
        return gameGrid;
    }

    public ArrayList<ProgressBar> getWallHealthHard() {
        return wallHealthBars;
    }

    public ArrayList<Label> getWallDangerLevelHard() {
        return wallDangerLevels;
    }    // Static weapon card accessor methods for Controller compatibility
    public static Button getAntiTitanShellCard() {
        return instance != null ? instance.antiTitanShellCard : null;
    }

    public static Button getLongRangeSpearCard() {
        return instance != null ? instance.longRangeSpearCard : null;
    }

    public static Button getWallSpreadCannonCard() {
        return instance != null ? instance.wallSpreadCannonCard : null;
    }

    public static Button getProximityTrapCard() {
        return instance != null ? instance.proximityTrapCard : null;
    }
}