package fi.basse.planetplayer.gui;

import fi.basse.planetplayer.domain.Body;
import java.text.DecimalFormat;
import java.text.ParseException;
import javafx.scene.control.SpinnerValueFactory;

/**
 *
 * @author Joonas
 */
public class BodySpinnerValueFactory extends SpinnerValueFactory<String> {
    private final DecimalFormat notation = new DecimalFormat("0.#E0");
    private final double stepSizeFactor = 0.1;
    private final boolean isMassFactory;
    private final Body body;

    public BodySpinnerValueFactory(Body body, boolean isMassFactory) {
        this.isMassFactory = isMassFactory;
        this.body = body;
        setValue(value());
    }

    @Override
    public void decrement(int steps) {
        double newValue = value() - value() * stepSizeFactor * steps;
        setValue(newValue);
        updateBody(newValue);
    }

    @Override
    public void increment(int steps) {
        double newValue = value() + value() * stepSizeFactor * steps;
        setValue(newValue);
        updateBody(newValue);
    }
    
    public final void setValue(double newValue) {
        setValue(notation.format(newValue));
    }

    public DecimalFormat getNotation() {
        return notation;
    }
    
    private double value() {
        return isMassFactory ? body.getMass() : body.getVelocity().value();
    }
    
    public void updateBody(String newValue) {
        double doubleValue = 0;
        
        try {
            doubleValue = notation.parse(newValue).doubleValue();
        } catch (ParseException ex) {
            // TODO
            System.out.println("Parse ei toimi BodySpinnerissä");
        }
        
        updateBody(doubleValue);
    }
    
    public void updateBody(double newValue) {
        if (isMassFactory) {
            body.setMass(newValue);
        } else {
            body.getVelocity().setValue(newValue);
        }
    }
}
