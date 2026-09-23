package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot.SubNeeded.Drive;
import org.firstinspires.ftc.teamcode.Robot.SubNeeded.Intake;
import org.firstinspires.ftc.teamcode.Robot.SubNeeded.Shooter;

// This is a file which used for gamepad container
public class DrivePlayerPreset {
    private enum preset { SINGLE, DOUBLE }
    private preset currentPreset = preset.SINGLE;

    Gamepad g1;
    Gamepad g2;
    protected Drive d;
    protected Shooter s;
    protected Intake i;

    public void init(Drive d, Shooter s, Intake i) {
        this.d = d;
        this.s = s;
        this.i = i;

        switch (currentPreset) {
            case SINGLE:
                g1 = new Gamepad();
                break;
            case DOUBLE:
                g1 = new Gamepad();
                g2 = new Gamepad();
                break;
        }
    }

    public void toggleAction() {
        switch (currentPreset) {
            case SINGLE:
                d.setAutoHeading(g1.right_bumper);
                d.setForward(g1.left_stick_y);
                d.setRotate(g1.right_stick_x);

                s.setToggle(true);
                s.setButton(g1.y);

                i.setButton(g1.left_trigger);
                break;

            case DOUBLE:
                d.setAutoHeading(g1.right_bumper);
                d.setForward(g1.left_stick_y);
                d.setRotate(g1.right_stick_x);

                s.setToggle(true);
                s.setButton(g2.y);

                i.setButton(g2.left_trigger);
                break;
        }
    }
}
