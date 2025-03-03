package game.gui;

import game.engine.titans.Titan;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class TitanImageView {
    Titan titan;
    ImageView titanImageView;
    ProgressBar healthBar; 
    
    public TitanImageView(Titan titan, ImageView titanImageView, ProgressBar healthBar) {
        this.titan = titan;
        this.titanImageView = titanImageView;
        this.healthBar = healthBar;
    }

}
