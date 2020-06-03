package weather.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(100), node);
        translateTransition.setFromX(-10);
        translateTransition.setByX(10);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(true);
    }

    public void startShake(){
        translateTransition.playFromStart();
    }
}
