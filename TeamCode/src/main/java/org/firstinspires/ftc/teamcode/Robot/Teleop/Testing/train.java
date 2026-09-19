package org.firstinspires.ftc.teamcode.Robot.Teleop.Testing;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.TankDrive;

public class train extends OpMode {
    private TankDrive drive;

    @Override
    public void init() {
        drive = new TankDrive(hardwareMap, new Pose2d(0,0 ,0));
    }

    @Override
    public void loop() {
        drive.updatePoseEstimate();
        drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-gamepad1.left_stick_y, 0), gamepad1.right_stick_x));
    }
}
