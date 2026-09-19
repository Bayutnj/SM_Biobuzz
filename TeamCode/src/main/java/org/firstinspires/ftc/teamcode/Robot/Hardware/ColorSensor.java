package org.firstinspires.ftc.teamcode.Robot.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Constants.BallsColour;

public class ColorSensor {
    private com.qualcomm.robotcore.hardware.ColorSensor sensor;

    public void init(HardwareMap map, String deviceName) {
        sensor = map.get(com.qualcomm.robotcore.hardware.ColorSensor.class, deviceName);
    }

    public int red() { return sensor.red();}
    public int blue() { return sensor.blue();}
    public int green() { return sensor.green();}
    public BallsColour detectColour() {
        int r = red();
        int b = blue();
        int g = green();
        if (r>= g && r >= b) {
            return BallsColour.NECTAR_RED;
        } else if (b >= r && b >= g) {
            return BallsColour.NECTAR_BLUE;
        } else {
            return BallsColour.POLLEN;
        }
    }
}
