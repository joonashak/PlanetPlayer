/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer;

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
    
    
}
