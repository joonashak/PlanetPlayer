/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.domain.Body;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Joonas
 */
public class BodyBox extends VBox {
    private final Body body;
    private final Label name;
    private final GridPane grid;
    private final Button remove;
    
    public BodyBox(Body body) {
        this.body = body;
        
        getStyleClass().add("body-box");

        // Name and icon
        Canvas iconCanvas = new Canvas(6, 6);
        GraphicsContext gc = iconCanvas.getGraphicsContext2D();
        gc.setFill(body.getColor());
        gc.fillOval(0, 0, 6, 6);

        this.name = new Label(body.getName().toUpperCase());
        name.getStyleClass().add("body-name");

        HBox nameContainer = new HBox(iconCanvas, name);
        nameContainer.getStyleClass().add("name-box");
        nameContainer.setAlignment(Pos.CENTER_LEFT);

        // Info and control fields & buttons
        this.grid = new GridPane();

        // Mass
        Label massLabel = new Label("Mass");
        massLabel.getStyleClass().add("spinner-label");
        BodySpinner massSpinner = new BodySpinner(body, true);
        massSpinner.getStyleClass().addAll("split-arrows-horizontal",
                "body-spinner");

        grid.add(massLabel, 0, 0);
        grid.add(massSpinner, 1, 0);
        GridPane.setHalignment(massSpinner, HPos.RIGHT);
        grid.add(horizontalDivider(), 0, 1, 2, 1);

        // Velocity
        if (!body.getName().equals("Sun")) {
            Label velocityLabel = new Label("Velocity");
            velocityLabel.getStyleClass().add("spinner-label");
            BodySpinner velocitySpinner = new BodySpinner(body, false);
            velocitySpinner.getStyleClass()
                    .addAll("split-arrows-horizontal", "body-spinner");

            grid.add(velocityLabel, 0, 2);
            grid.add(velocitySpinner, 1, 2);
            GridPane.setHalignment(velocitySpinner, HPos.RIGHT);
            grid.add(horizontalDivider(), 0, 3, 2, 1);
        }


        // Buttons
        Button reset = new Button("RESET");
        reset.getStyleClass().add("body-button");

        this.remove = new Button("REMOVE");
        remove.getStyleClass().add("body-button");
        
        remove.setOnAction(event -> {
            body.cycleRemoved();
            updateVisual();
        });
        reset.setOnAction(event -> {
            body.reset();
            updateVisual();
        });
        

        HBox buttons = new HBox(reset, remove);
        buttons.getStyleClass().add("body-button-container");

        // Add all elements to containers
        getChildren().addAll(nameContainer, grid, buttons);
    }
    
    private HBox horizontalDivider() {
        HBox divider = new HBox();
        divider.getStyleClass().add("horizontal-divider");
        return divider;
    }
    
    /**
     * Update this BodyBox according to the bound Body's state.
     */
    public void updateVisual() {
        name.setDisable(body.isRemoved());
        grid.setDisable(body.isRemoved());
        
        String buttonText = body.isRemoved() ? "PUT BACK" : "REMOVE";
        remove.setText(buttonText);
    }
    
    @Override
    public final ObservableList<Node> getChildren() {
        return super.getChildren();
    }
}
