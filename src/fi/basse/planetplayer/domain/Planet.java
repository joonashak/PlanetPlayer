package fi.basse.planetplayer.domain;

import javafx.scene.paint.Color;

/**
 *
 * @author Joonas
 */
public enum Planet {
    MERCURY     (3.30110E+23, 6.98169E+10, 4.33620E+04, "#9D98BA"),    // 4.73620E+04
    VENUS       (4.86750E+24, 1.08939E+11, 3.50200E+04, "#E3CC7D"),
    EARTH       (5.97237E+24, 1.52100E+11, 2.97800E+04, "#4D8EE6"),
    MARS        (6.41710E+23, 2.49200E+11, 2.40770E+04, "#FF8A41"),
    JUPITER     (1.89860E+27, 8.16040E+11, 1.30700E+04, "#C1AD5A"),
    SATURN      (5.68360E+26, 1.50900E+12, 9.69000E+03, "#807D61"),
    URANUS      (8.68100E+25, 3.00800E+12, 6.80000E+03, "#71A8CC"),
    NEPTUNE     (1.02430E+26, 4.54000E+12, 5.43000E+03, "#3352A8");
    
    private final double mass, orbitRadius, velocity;
    private final Color color;

    private Planet(double mass, double orbitRadius, double velocity, 
            String colorCode) {
        
        this.mass = mass;
        this.orbitRadius = orbitRadius;
        this.velocity = velocity;
        this.color = Color.web(colorCode);
    }

    public double getMass() {
        return mass;
    }

    public double getOrbitRadius() {
        return orbitRadius;
    }

    public double getVelocity() {
        return velocity;
    }

    public Color getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase()
                + name().substring(1).toLowerCase();
    }
}
