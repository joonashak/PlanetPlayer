/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.planetplayer.domain;

/**
 *
 * @author Joonas
 */
public class Velocity {
    private double x;
    private double y;

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double value() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public void setValue(double newValue) {
        double deltaFactor = newValue / value();
        x *= deltaFactor;
        y *= deltaFactor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
