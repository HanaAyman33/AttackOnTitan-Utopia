package game.gui;

import game.engine.titans.Titan;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TitanImageView {
	Titan t;
	ImageView v;
	Label l;
	public TitanImageView(Titan t,ImageView v,Label l) {
		this.t=t;
		this.v=v;
		this.l=l;
	}

}
