package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Joonas
 */
public class PopUp extends Stage {
    private final Settings settings;
    private final Label title;
    private final ScrollPane contentPane;
    
    public PopUp(Stage mainStage, Settings settings) {
        super(StageStyle.TRANSPARENT);
        this.settings = settings;
        
        BorderPane popUpPane = new BorderPane();
        popUpPane.setId("pop-up-pane");
        
        title = new Label();
        title.setId("pop-up-title");
        popUpPane.setTop(title);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        
        contentPane = new ScrollPane();
        contentPane.setId("pop-up-content");
        contentPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        contentPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        popUpPane.setCenter(contentPane);
        
        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("body-button");
        popUpPane.setBottom(closeButton);
        BorderPane.setAlignment(closeButton, Pos.BOTTOM_CENTER);
        closeButton.setOnAction(event -> {
            hideAndPlay();
        });
                
        this.initOwner(mainStage);
        this.initModality(Modality.APPLICATION_MODAL);
        
        Scene scene = new Scene(popUpPane, Color.TRANSPARENT);
        scene.getStylesheets().add("fi/basse/planetplayer/gui/gui.css");
        this.setScene(scene);
    }
    
    /**
     * Pause the simulation and show this PopUp.
     */
    public void pauseAndShow() {
        settings.playOrPause();
        settings.getRoot().setEffect(new GaussianBlur());
        this.show();
    }
    
    /**
     * Hide this PopUp and resume the simulation.
     */
    public void hideAndPlay() {
        this.hide();
        settings.getRoot().setEffect(null);
        settings.playOrPause();
    }
    
    /**
     * Set the title for this PopUp window.
     * @param titleText 
     */
    public void setTitleText(String titleText) {
        title.setText(titleText);
    }
    
    /**
     * Set the content of this PopUp window.
     * @param content 
     */
    public void setContent(Node content) {
        contentPane.setContent(content);
    }
}
