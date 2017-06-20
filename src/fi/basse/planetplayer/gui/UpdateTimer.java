/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import javafx.animation.AnimationTimer;

/**
 * Updates the simulation's state forward in time.
 * @author Joonas
 */
public class UpdateTimer extends AnimationTimer {
    
    /**
     * How many times per second to update.
     */
    private final long interval = 60;
    private long was = 0;
    private final Settings settings;

    /**
     * Creates a new AnimationTimer to advance the simulation.
     * @param settings Simulation settings object
     */
    public UpdateTimer(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void handle(long now) {
        if (now - was < interval * 1E6) {
            return;
        }
        
        if (!settings.isPaused()) {
            settings.getSolarSystem().forward(settings.getSpeed());
        }
        
        was = now;
    }
}
