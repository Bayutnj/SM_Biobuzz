package org.firstinspires.ftc.teamcode.Robot.Subsystem;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Roadrunner.TankDrive;

public class Drive {
    private TankDrive drive;
    private Pose2d pose;
    private double forward;
    private double rotate;
    private boolean autoHeading = false;

    public void init(HardwareMap map, Pose2d startPose) {
        drive =  new TankDrive(map, startPose);
    }

    public void update() {
        drive.updatePoseEstimate();
        pose = drive.localizer.getPose();

        if (autoHeading) {
          return;
        }

        drive.setDrivePowers(new PoseVelocity2d(new Vector2d(forward, 0), rotate));
    }

    public void setAutoHeading(boolean a) {this.autoHeading = a;}
    public void setForward(double f) {this.forward = f;}
    public void setRotate(double r) {this.rotate = r;}

    public Pose2d getPose() { return pose; }
    public double getPx() { return pose.position.x; }
    public double getPy() { return pose.position.y; }
    public double getAngle() { return pose.heading.toDouble(); }
}
