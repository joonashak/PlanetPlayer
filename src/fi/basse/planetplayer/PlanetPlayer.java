/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer;

import fi.basse.planetplayer.gui.DrawingTimer;
import fi.basse.planetplayer.gui.ControlPane;
import fi.basse.planetplayer.gui.UpdateTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
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
        
        BorderPane layout = new BorderPane();
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("fi/basse/planetplayer/gui/gui.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        
        ControlPane menu = new ControlPane(stage, settings);
        layout.setRight(menu);
        
        double canvasWidth = stage.getWidth() - menu.getMinWidth();
        double canvasHeight = stage.getHeight();
        
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        layout.setLeft(canvas);
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
