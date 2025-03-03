package game.gui;

import game.engine.titans.Titan;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TitanImageView {
	Titan titan;
	ImageView titanImageView;
	Label healthLabel;
	public TitanImageView(Titan titan,ImageView titanImageView,Label healthLabel) {
		this.titan=titan;
		this.titanImageView=titanImageView;
		this.healthLabel=healthLabel;
	}

}
