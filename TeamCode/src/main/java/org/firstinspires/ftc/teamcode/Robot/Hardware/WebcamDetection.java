package org.firstinspires.ftc.teamcode.Robot.Hardware;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot.Constants.Alliance;
import org.firstinspires.ftc.teamcode.Robot.Constants.AprilTagConstants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// This detected all of apriltag frames and choose the best option, with a
// Normal Webcam like logitech webcam etc
@Disabled
public class WebcamDetection {
    private AprilTagProcessor processor;

    private VisionPortal vp;
    private Alliance myAlliance = Alliance.UNKNOWN;
    private int detectedId = -1;
    private double d; // the range distance
    private double bearing;
    private double yaw;
    private boolean hasTarget = false;

//    IDS Container
    private static final Set<Integer> RED_IDS = new HashSet<>(Arrays.asList(
            AprilTagConstants.red_scoring_30, AprilTagConstants.red_scoring_31,
            AprilTagConstants.red_scoring_32, AprilTagConstants.red_scoring_33,
            AprilTagConstants.red_audience_34, AprilTagConstants.red_audience_35,
            AprilTagConstants.red_audience_36, AprilTagConstants.red_audience_37
    ));

    private static final Set<Integer> BLUE_IDS = new HashSet<>(Arrays.asList(
            AprilTagConstants.blue_audience_38, AprilTagConstants.blue_audience_39,
            AprilTagConstants.blue_audience_40, AprilTagConstants.blue_audience_41,
            AprilTagConstants.blue_scoring_42, AprilTagConstants.blue_scoring_43,
            AprilTagConstants.blue_scoring_44, AprilTagConstants.blue_scoring_45
    ));

    public void init(HardwareMap map) {
        processor = new AprilTagProcessor.Builder()
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11) // Ordinary biobuzz tag
                .setDrawTagID(true) // Allowing to show the id tag
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.RADIANS) // Output units
                .build();

        vp = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(map.get(WebcamName.class, "webcam")) // Device name
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .setCameraResolution(new Size(1080, 720)) // reset if need
                .build();
    }

    public void setMyAlliance(Alliance myAlliance) {
        this.myAlliance = myAlliance;
    }

    public Alliance getMyAlliance() {
        return myAlliance;
    }

    public Set<Integer> getTargetIds() {
        if (myAlliance == Alliance.RED) return RED_IDS;
        if (myAlliance == Alliance.BLUE) return BLUE_IDS;
//        Fallback if alliance hasn't set
        Set<Integer> all = new HashSet<>(RED_IDS);
        all.addAll(BLUE_IDS);
        return all;
    }

    public void update() {
        List<AprilTagDetection> detections = processor.getDetections();
        hasTarget = false;
        detectedId = -1;

        if (detections == null || detections.isEmpty()) return;
        Set<Integer> targetIds = getTargetIds();

        double bestScore = Double.NEGATIVE_INFINITY;
        AprilTagDetection best = null;

        for (AprilTagDetection d : detections) {
            if (d.metadata == null || !targetIds.contains(d.id)) continue;

            double score = d.decisionMargin - (d.ftcPose.range * 2.0);

            if (score > bestScore) {
                bestScore = score;
                best = d;
            }
        }

        if (best == null) return;

        detectedId = best.id;
        d = best.ftcPose.range;
        bearing = best.ftcPose.bearing;
        yaw = best.ftcPose.yaw;
        hasTarget = true;
    }

    public int getDetectedId() { return detectedId; }
    public boolean isHasTarget() { return hasTarget; }
    public double getRange() { return d;}
    public double getBearing() { return bearing; }
    public double getYaw() { return yaw; }
    public void close() { if (vp != null) vp.close(); }
}
