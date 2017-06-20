package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import fi.basse.planetplayer.domain.Body;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Draws the current state of the simulation.
 * @author Joonas
 */
public class DrawingTimer extends AnimationTimer {
    
    /**
     * How many times per second to update.
     */
    private final long interval = 60;
    private long was = 0;
    private final GraphicsContext draw;
    private final Settings settings;
    private final double width;
    private final double height;

    /**
     * Creates a new AnimationTimer to draw the current state of the simulation
     * on the canvas.
     * @param draw GraphicsContext to be used for drawing.
     * @param settings Simulation settings object.
     */
    public DrawingTimer(GraphicsContext draw, Settings settings) {
        this.draw = draw;
        this.settings = settings;
        this.width = draw.getCanvas().getWidth();
        this.height = draw.getCanvas().getHeight();
    }

    @Override
    public void handle(long now) {
        if (now - was < interval * 1E6) {
            return;
        }
        
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, width, height);

        for (Body body : settings.getSolarSystem().getActiveBodies()) {
            Point2D drawPosition = toCanvasCoordinate(body.getPosition());
            double x = drawPosition.getX();
            double y = drawPosition.getY();
            
            drawCenteredCircle(x, y, body.getDrawRadius(), body.getColor());
            draw.fillText(body.getName(), x + 10, y + 10);
            
            drawTrail(body.getTrail(), body.getColor());
        }
        
        was = now;
    }
    
    private void drawCenteredCircle(double x, double y, double radius,
            Color color) {
        
        x -= radius / 2;
        y -= radius / 2;
        
        draw.setFill(color);
        draw.fillOval(x, y, radius, radius);
    }
    
    /**
     * Gets all points from a Trail, draws them on the canvas and connects them
     * with a line.
     * @param trail Trail to be drawn out.
     * @param color Color of the Trail.
     */
    private void drawTrail(Trail trail, Color color) {
        draw.setFill(color);
        Point2D previousPoint = null;
        
        for (Point2D point : trail.getPoints()) {
            Point2D drawPosition = toCanvasCoordinate(point);
            
            if (previousPoint != null) {
                draw.setStroke(color);
                draw.strokeLine(previousPoint.getX(), previousPoint.getY(), 
                        drawPosition.getX(), drawPosition.getY());
            }
            
            previousPoint = drawPosition;
        }
    }
    
    public Point2D toCanvasCoordinate(Point2D coordinate) {
        double aspectRatio = width / height;
        double radius = settings.getSolarSystem().getSystemRadius();
        double zoom = settings.getZoomFactor();
        
        double x = width / 2 + width * coordinate.getX() / radius / aspectRatio
                * zoom;
        double y = height / 2 + height * coordinate.getY() / radius * zoom;
        
        return new Point2D(x, y);
    }
}
