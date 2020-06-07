package weather.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sun extends Canvas{
    public Sun(){
        this.setHeight(200);
        this.setWidth(200);
        Circle circle = new Circle(10, Color.RED);
    }
}
