package fi.basse.planetplayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 *
 * @author Joonas
 */
public class Settings {
    private double zoomFactor = 1;
    private double speedFactor = 1;
    private double trailFactor = 10;
    private boolean paused = false;
    private SolarSystem solarSystem;
    private Canvas canvas = null;
    private Pane root = null;

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public double getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public void playOrPause() {
        paused = !paused;
    }
    
    /**
     * Gives the amount of seconds to advance the simulation per step.
     * @return 
     */
    public double getSpeed() {
        return speedFactor * 1E5;
    }

    public double getTrailFactor() {
        return trailFactor;
    }

    public void setTrailFactor(double trailFactor) {
        this.trailFactor = trailFactor;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
    
    
}
