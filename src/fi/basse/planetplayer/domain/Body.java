/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.domain;

import fi.basse.planetplayer.Settings;
import fi.basse.planetplayer.gui.Trail;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Body is the basic object in this simulation. Body has a location in a
 * 2D coordinate system defined by its x and y coordinates xPosition and
 * yPosition, respectively. Likewise, it has a velocity vector in that space 
 * expressed in components xVelocity and yVelocity along the x and y axes,
 * respectively. Body also has a mass.
 * 
 * For GUI generation, Body has a name, a color and a radius (in pixels).
 * @author Joonas Häkkinen
 * @version 1.0
 * @since 1.0
 */
public class Body {
    
    // Final fields
    private final Color color;
    private final double drawRadius;
    private final String name;
    private final Settings settings;
    
    // Dynamic fields
    private boolean removed = false;
    private Point2D position;
    private Velocity velocity;
    private double mass;
    private Trail trail;
    
    // Initial values
    private Point2D initPosition;
    private Velocity initVelocity;
    private double initMass;

    public Body(double xPosition, double yPosition, double xVelocity, 
            double yVelocity, double mass, Color color, double drawRadius, 
            String name, Settings settings) {
        
        this.position = new Point2D(xPosition, yPosition);
        this.velocity = new Velocity(xVelocity, yVelocity);
        this.mass = mass;
        this.color = color;
        this.drawRadius = drawRadius;
        this.name = name;
        this.settings = settings;
        this.trail = new Trail(this, settings);
        
        saveInitialValues();
    }
    
    /**
     * Saves field values for use when resetting this Body.
     */
    private void saveInitialValues() {
        this.initPosition = position;
        this.initVelocity = velocity;
        this.initMass = mass;
    }
    
    /**
     * Reset position, velocity and mass to initial values.
     */
    public void reset() {
        position = initPosition;
        velocity = initVelocity;
        mass = initMass;
        removed = false;
        trail = new Trail(this, settings);
    }
    
    /**
     * Computes the distance between this Body and the give Body.
     * @param b Target to compute distance to.
     * @return 
     */
    public double distance(Body b) {
        return Math.sqrt(Math.pow(position.getX() - b.getPosition().getX(), 2) + 
                Math.pow(position.getY() - b.getPosition().getY(), 2));
    }
    
    /**
     * Computes the magnitude of the gravitational force between this Body and 
     * the given Body.
     * @param b 
     * @return 
     */
    public double gravityMagnitude(Body b) {
        return 6.6740831E-11 * (mass * b.getMass()) / Math.pow(distance(b), 2);
    }
    
    /**
     * Computes the gravitational force along x-axis between this Body and the
     * given Body.
     * @param b
     * @return 
     */
    public double gravityX(Body b) {
        return gravityMagnitude(b) * (b.getPosition().getX() - position.getX()) 
                / distance(b);
    }
    
    /**
     * Computes the gravitational force along x-axis between this Body and the
     * given Body.
     * @param b
     * @return 
     */
    public double gravityY(Body b) {
        return gravityMagnitude(b) * (b.getPosition().getY() - position.getY()) 
                / distance(b);
    }
    
    /**
     * Updates this Body's position and velocity according to the parameters.
     * @param xForce Total force on this Body along x-axis.
     * @param yForce Total force on this Body along y-axis.
     * @param timeDelta Duration of the forces' impact.
     */
    public void update(double xForce, double yForce, double timeDelta) {
        double xAcceleration = xForce / mass;
        double yAcceleration = yForce / mass;
        
        double xVelocity = velocity.getX() + timeDelta * xAcceleration;
        double yVelocity = velocity.getY() + timeDelta * yAcceleration;
        
        double xPosition = position.getX() + timeDelta * xVelocity;
        double yPosition = position.getY() + timeDelta * yVelocity;
        
        velocity = new Velocity(xVelocity, yVelocity);
        position = new Point2D(xPosition, yPosition);
        trail.addPoint(xPosition, yPosition);
    }
    
    /**
     * Gives the angle (in radians) between this Body and the y-axis counting
     * counterclockwise from the top.
     * @return 
     */
    public double angle() {
        return Math.atan2(position.getX(), position.getY()) + Math.PI;
    }
    
    // Generated getters & setters

    public Point2D getPosition() {
        if (name.equals("Sun")) {
            return new Point2D(0, 0);
        }
        
        return position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public Color getColor() {
        return color;
    }

    public double getDrawRadius() {
        return drawRadius;
    }

    public String getName() {
        return name;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    
    public void cycleRemoved() {
        this.removed = !removed;
    }
}
