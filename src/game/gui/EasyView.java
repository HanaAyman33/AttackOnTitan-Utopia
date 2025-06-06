package game.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class EasyView extends BaseGameView {

    private static EasyView instance;

    public EasyView() {
        super();
        EasyView.instance = this;
    }

    @Override
    protected String getLaneBackgroundPath() {
        return "/3lanes.png";
    }

    @Override
    protected int getNumberOfLanes() {
        return 3;
    }

    @Override
    protected GridConfig getGridConfig() {
        return new GridConfig(
                30, // padding
                100, // width
                320, // height
                10, // vgap (reduced to fit 2 rows per lane)
                6, // rows (doubled to 6 for 2 rows per 3 lanes)
                0.0, // leftAnchor
                850.0, // rightAnchor
                180.0, // topAnchor
                180.0 // bottomAnchor
        );
    }

    @Override
    protected WallConfig getWallConfig() {
        return new WallConfig(
                100, // progressBarWidth
                200.0, // baseTopOffset
                150.0, // spacing
                180.0, // progressBarLeftAnchor
                45.0, // progressBarTopOffset
                150.0 // labelLeftAnchor
        );
    }

    // Methods that maintain compatibility with existing Controller usage
    public GridPane getEasyGrid() {
        return gameGrid;
    }

    public ArrayList<ProgressBar> getWallHealthEasy() {
        return wallHealthBars;
    }

    public ArrayList<Label> getWallDangerLevelEasy() {
        return wallDangerLevels;
    } // Static weapon card accessor methods for Controller compatibility

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