package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.Settings;
import fi.basse.planetplayer.domain.Body;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Joonas
 */
public class Trail {
    private final List<Point2D> points;
    private final Body body;
    private final Settings settings;
    
    public Trail(Body body, Settings settings) {
        this.points = new ArrayList<>();
        this.body = body;
        this.settings = settings;
    }

    public List<Point2D> getPoints() {
        double x = body.getVelocity().value();
        Double y = 2E8 * Math.pow(x, -1.45);
        int lengthLimit = (int) Math.round(y * settings.getTrailFactor());
        
        if (points.size() < lengthLimit || lengthLimit < 0) {
            return points;
        }
        
        return points.subList(points.size() - lengthLimit, points.size());
    }
    
    public void addPoint(double x, double y) {
        points.add(new Point2D(x, y));
    }
}
