package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
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
    
    public PopUp(Stage mainStage, Settings settings) {
        super(StageStyle.TRANSPARENT);
        this.settings = settings;
        
        VBox popUpPane = new VBox();
        popUpPane.getChildren().add(new Label("Paused"));
        popUpPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.4);");
        popUpPane.setAlignment(Pos.CENTER);
        popUpPane.setMinWidth(600);
        popUpPane.setMinHeight(500);
        
        Button closeButton = new Button("Close");
        popUpPane.getChildren().add(closeButton);
        closeButton.setOnAction(event -> {
            hideAndPlay();
        });
                
        this.initOwner(mainStage);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(popUpPane, Color.TRANSPARENT));
    }
    
    public void pauseAndShow() {
        settings.playOrPause();
        settings.getRoot().setEffect(new GaussianBlur());
        this.show();
    }
    
    public void hideAndPlay() {
        this.hide();
        settings.getRoot().setEffect(null);
        settings.playOrPause();
    }
}
