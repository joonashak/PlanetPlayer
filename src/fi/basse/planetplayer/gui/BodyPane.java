/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import fi.basse.planetplayer.domain.Body;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * A view for displaying information about and controlling the Bodies in the
 * simulation.
 * @author Joonas
 */
public class BodyPane extends ScrollPane {
    private final ArrayList<BodyBox> bodyBoxes;
    
    public BodyPane(Settings settings) {
        this.bodyBoxes = new ArrayList();
        
        setId("body-pane");
        setHbarPolicy(ScrollBarPolicy.NEVER);
        // TODO (hidden for styling)
        setVbarPolicy(ScrollBarPolicy.NEVER);
        
        VBox container = new VBox();
        setContent(container);
        
        for (Body body : settings.getSolarSystem().getBodies()) {
            BodyBox bodyBox = new BodyBox(body);
            container.getChildren().add(bodyBox);
            bodyBoxes.add(bodyBox);
        }
    }
    
    /**
     * Updates the visual appearance of every BodyBox to reflect the state
     * of its bound Body.
     */
    public void updateVisuals() {
        bodyBoxes.stream().forEach(b -> b.updateVisual());
    }
}
