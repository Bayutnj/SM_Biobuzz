package org.firstinspires.ftc.teamcode.Robot.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Intake {
    DcMotorEx I;

    public void init(HardwareMap map) {
        I = map.get(DcMotorEx.class, "I");
        I.setDirection(DcMotorSimple.Direction.REVERSE);
        I.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        I.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void set(double p) {
        I.setPower(p);
    }

    public boolean isBusy() {
        return I.isBusy();
    }
}
