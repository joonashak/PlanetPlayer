/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.domain.Body;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Joonas
 */
public class BodySpinner extends Spinner<String> {
    private final BodySpinnerValueFactory factory;

    public BodySpinner(Body body, boolean isMassSpinner) {
        
        setEditable(true);
        
        // Initialise value factory
        this.factory = new BodySpinnerValueFactory(body, isMassSpinner);
        setValueFactory(factory);
        
        // Update body when spinner changes value
        focusedProperty().addListener((obs, hadFocus, hasFocus) -> {
            if (hasFocus) {
                return;
            }
            factory.updateBody(getEditor().getText());
        });
        
        getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                getParent().requestFocus();
            }
        });
        
        setOnScroll(event -> {
            if (event.getDeltaY() < 0) {
                factory.decrement(1);
            } else {
                factory.increment(1);
            }
        });
        
        // For velocity spinner, update value as it changes during orbit
        if (!isMassSpinner) {
            new AnimationTimer() {
                private long was = 0;
                @Override
                public void handle(long now) {
                    if (now - was < 1E7 || focusedProperty().get()) {
                        return;
                    }
                    factory.setValue(body.getVelocity().value());
                    was = now;
                }
            }.start();
        }
    }
}
