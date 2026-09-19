package org.firstinspires.ftc.teamcode.Robot.SubNeeded;

import org.firstinspires.ftc.teamcode.Robot.Constants.BallsColour;
import org.firstinspires.ftc.teamcode.Robot.Hardware.ColorSensor;

public class BallsDecision {
    ColorSensor cs;

    public void init(ColorSensor cs) {
        this.cs = cs;
    }

    public BallsColour ballReco() {
        return cs.detectColour();
    }
}
