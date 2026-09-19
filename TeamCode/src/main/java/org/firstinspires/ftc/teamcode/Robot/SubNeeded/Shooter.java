package org.firstinspires.ftc.teamcode.Robot.SubNeeded;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot.Constants.BallsColour;
import org.firstinspires.ftc.teamcode.Robot.Hardware.ColorSensor;

public class Shooter {
    DcMotorEx f1;
    DcMotorEx f2;
    Servo adjustPress; // adjust the pressure of the ball
    BallsDecision ballsDecision;
    ColorSensor cs;

    double NectarP = .05, PollenP = .4;
    double t; boolean activate = false;

    public void init(HardwareMap map){
        f1 = map.get(DcMotorEx.class, "f1");
        f2 = map.get(DcMotorEx.class, "f2");
        adjustPress = map.get(Servo.class, "AP");

        f1.setDirection(DcMotorSimple.Direction.FORWARD);
        f2.setDirection(DcMotorSimple.Direction.REVERSE);
        f1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        f2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        f1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        f2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        cs = new ColorSensor();
        cs.init(map, "cs");
        ballsDecision = new BallsDecision();
        ballsDecision.init(cs);

        adjustPress.setPosition(PollenP); // Initial pressure
    }

    public double calculate() {
        return 0;
    }
    public void update() {
        BallsColour detection = ballsDecision.ballReco();
        setPressure(detection);

        startShoot();
    }

    private void startShoot() {
        double sum = 0;
        if (activate) {

        }
    }

    public void setActive() {
        if(!activate) { activate = true; }
    }
    private void setPressure(BallsColour color) {
        switch (color){
            case POLLEN:
                adjustPress.setPosition(PollenP);
                break;

            case NECTAR_BLUE:
            case NECTAR_RED:
                adjustPress.setPosition(NectarP);
                break;
        }
    }
}
