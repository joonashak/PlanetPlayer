package fi.basse.planetplayer;

import fi.basse.planetplayer.domain.Body;
import fi.basse.planetplayer.domain.Planet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

/**
 *
 * @author Joonas
 */
public class SolarSystem {
    private final List<Body> bodies;
    private final double systemRadius;
    private final Settings settings;

    public SolarSystem(Settings settings) {
        this.systemRadius = 5E12;
        this.bodies = new ArrayList<>();
        this.settings = settings;
        initialiseSolarSystem();
    }

    public List<Body> getBodies() {
        return bodies;
    }
    
    public List<Body> getActiveBodies() {
        return bodies.stream()
                .filter(body -> !body.isRemoved())
                .collect(Collectors.toList());
    }

    public double getSystemRadius() {
        return systemRadius;
    }
    
    /**
     * Generates Body instances representing the Sun and the planets. Sets them
     * in random angles on their average orbit.
     */
    private void initialiseSolarSystem() {
        addBody(0, 0, 0, 0, 1.98855E30, Color.YELLOW, 20, "Sun");
        
        for (Planet planet : Planet.values()) {
            // Generate random angle (rad) and calculate position based on that
            double angle = Math.random() * 2 * Math.PI;
            
            double xPosition = planet.getOrbitRadius() * Math.cos(angle);
            double yPosition = planet.getOrbitRadius() * Math.sin(angle);
            
            double xVelocity = planet.getVelocity() * Math.sin(angle);
            double yVelocity = -planet.getVelocity() * Math.cos(angle);
            
            addBody(xPosition, yPosition, xVelocity, yVelocity, 
                    planet.getMass(), planet.getColor(), 10.0, 
                    planet.toString());
        }
    }
    
    /**
     * Creates a new instance of Body with the given parameters passed to the
     * constructor and adds it to this SolarSystem's list of bodies.
     * @param xPosition
     * @param yPosition
     * @param xVelocity
     * @param yVelocity
     * @param mass
     * @param color
     * @param drawRadius
     * @param name 
     */
    public void addBody(double xPosition, double yPosition, double xVelocity, 
            double yVelocity, double mass, Color color, double drawRadius, 
            String name) {
        
        bodies.add(new Body(xPosition, yPosition, xVelocity, yVelocity, mass, 
                color, drawRadius, name, settings));
    }
    
    /**
     * Advances the Solar System simulation by the given amount of time. First,
     * computes the total forces on every Body along x- and y-axes. Then
     * applies the changes to each Body's update functions.
     * @param timeDelta How far in time to advance.
     */
    public void forward(double timeDelta) {
        Map<Body, Double> xForces = new HashMap<>();
        Map<Body, Double> yForces = new HashMap<>();
        
        for (Body body : getActiveBodies()) {
            double xSum = 0.0;
            double ySum = 0.0;
            
            for (Body other : getActiveBodies()) {
                if (body == other) continue;
                
                xSum += body.gravityX(other);
                ySum += body.gravityY(other);
            }
            
            xForces.put(body, xSum);
            yForces.put(body, ySum);
        }
        
        // Computing the forces and updating must be done separately
        for (Body body : getActiveBodies()) {
            body.update(xForces.get(body), yForces.get(body), timeDelta);
        }
    }
    
    /**
     * Reset all Bodies to their initial states.
     */
    public void reset() {
        bodies.stream().forEach(body -> body.reset());
    }
}
