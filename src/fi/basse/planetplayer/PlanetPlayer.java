package fi.basse.planetplayer;

import fi.basse.planetplayer.gui.DrawingTimer;
import fi.basse.planetplayer.gui.ControlPane;
import fi.basse.planetplayer.gui.UpdateTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Joonas
 */
public class PlanetPlayer extends Application {
    
    @Override
    public void start(Stage stage) {
        
        Settings settings = new Settings();
        SolarSystem solarSystem = new SolarSystem(settings);
        settings.setSolarSystem(solarSystem);
        
        BorderPane root = new BorderPane();
        settings.setRoot(root);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("fi/basse/planetplayer/gui/gui.css");
        // This is required for GaussianBlur to work without white edges
        scene.setFill(Color.BLACK);
        
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        
        ControlPane menu = new ControlPane(stage, settings);
        root.setRight(menu);
        
        double canvasWidth = stage.getWidth() - menu.getMinWidth();
        double canvasHeight = stage.getHeight();
        
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        settings.setCanvas(canvas);
        root.setLeft(canvas);
        GraphicsContext draw = canvas.getGraphicsContext2D();
        
        canvas.setOnScroll(event -> {
            double zoomStep = 0.1;
            zoomStep *= event.getDeltaY() < 0 ? 1 : -1;
            settings.setZoomFactor(settings.getZoomFactor() * (1 - zoomStep));
        });
        
        // AnimationTimers
        new DrawingTimer(draw, settings).start();
        new UpdateTimer(settings).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(PlanetPlayer.class);
    }
    
}
