package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Joonas
 */
public class ControlPane extends BorderPane {
    private final double width = 300;
    private final Settings settings;
    
    public ControlPane(Stage stage, Settings settings) {
        this.settings = settings;
        
        setMinWidth(width);
        setMaxWidth(width);
        
        // Title
        Label title = new Label("Planet\n    Player");
        title.setId("title");
        FlowPane titlePane = new FlowPane(title);
        titlePane.setId("title-pane");
        setTop(titlePane);
        
        // Control panel for bodies
        BodyPane bodyPane = new BodyPane(settings);
        setCenter(bodyPane);
        
        // Simulation control
        VBox controls = new VBox();
        controls.setId("controls");
        setBottom(controls);
        
        Label controlsTitle = new Label("SIMULATION CONTROL");
        controlsTitle.setId("controls-title");
        controls.getChildren().add(new HBox(controlsTitle));
        
        // Simulation control: Sliders
        GridPane sliderGrid = new GridPane();
        sliderGrid.setId("slider-grid");
        controls.getChildren().add(sliderGrid);
        
        Label zoomLabel = new Label("ZOOM");
        Slider zoomSlider = new Slider(0.5, 5.0, 1.0);
        sliderGrid.add(zoomLabel, 0, 0);
        sliderGrid.add(zoomSlider, 1, 0);
        GridPane.setHgrow(zoomSlider, Priority.ALWAYS);
        
        Label speedLabel = new Label("SPEED");
        Slider speedSlider = new Slider(0.5, 2.0, 1.0);
        sliderGrid.add(speedLabel, 0, 1);
        sliderGrid.add(speedSlider, 1, 1);
        GridPane.setHgrow(speedSlider, Priority.ALWAYS);
        
        Label trailLabel = new Label("TRAIL");
        Slider trailSlider = new Slider(0.0, 10.0, 1.0);
        sliderGrid.add(trailLabel, 0, 2);
        sliderGrid.add(trailSlider, 1, 2);
        GridPane.setHgrow(trailSlider, Priority.ALWAYS);
        
        zoomSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            settings.setZoomFactor(newValue.doubleValue());
        });
        
        speedSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            settings.setSpeedFactor(newValue.doubleValue());
        });
        
        trailSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            settings.setTrailFactor(newValue.doubleValue());
        });
        
        // Zoom slider to follow scrollwheel zooming
        new AnimationTimer() {
            long was = 0;
            
            @Override
            public void handle(long now) {
                if (now - was < 6E7) return;    // Run 60 times/sec
                zoomSlider.setValue(settings.getZoomFactor());
            }
        }.start();
        
        // Simulation control: Buttons
        Button playButton = new Button();
        setPlayPause(playButton);
        playButton.setOnAction(event -> {
            settings.playOrPause();
            setPlayPause(playButton);
        });
        
        Button resetButton = new Button();
        resetButton.setId("reset-button");
        resetButton.setOnAction(event -> {
            settings.getSolarSystem().reset();
            bodyPane.updateVisuals();
        });

        HBox controlButtonsTop = new HBox(playButton, resetButton);
        controlButtonsTop.getStyleClass().add("control-buttons");
        
        HBox divider = new HBox();
        divider.getStyleClass().add("horizontal-divider-dark");
        
        Button helpButton = new Button();
        helpButton.setId("help-button");
        
        Button settingsButton = new Button();
        settingsButton.setId("settings-button");
        
        Button quitButton = new Button();
        quitButton.setId("quit-button");
        quitButton.setOnAction(event -> stage.close());
        
        HBox controlButtonsBottom = new HBox(helpButton, settingsButton, 
                quitButton);
        controlButtonsBottom.getStyleClass().add("control-buttons");
        
        controls.getChildren().addAll(controlButtonsTop, divider, 
                controlButtonsBottom);
        
        // TODO remove
        // popup sample:
        
        /*
        helpButton.setOnAction(event -> {
            PopUp helpPopUp = new PopUp(stage, settings);
            VBox tallBox = new VBox();
            tallBox.setStyle("-fx-min-height: 800px; -fx-background-color: black;");
            helpPopUp.setTitleText("Help");
            helpPopUp.setContent(tallBox);
            helpPopUp.pauseAndShow();
        });
        */
    }
    
    private void setPlayPause(Button button) {
        String id = settings.isPaused() ? "play-button" : "pause-button";
        button.setId(id);
    }
}
