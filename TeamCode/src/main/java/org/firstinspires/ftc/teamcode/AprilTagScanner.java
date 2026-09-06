package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class AprilTagScanner {

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    public AprilTagScanner(HardwareMap hardwareMap) {

        aprilTagProcessor = new AprilTagProcessor.Builder()
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTagProcessor)
                .build();
    }

    // How many tags are currently detected
    public int getNumberOfDetections() {
        return aprilTagProcessor.getDetections().size();
    }

    // Get the first detected tag
    public AprilTagDetection getFirstDetection() {

        if (aprilTagProcessor.getDetections().size() > 0) {
            return aprilTagProcessor.getDetections().get(0);
        }

        return null;
    }

    // Get a specific detection by its position in the list
    public AprilTagDetection getDetection(int index) {

        if (index >= 0 && index < aprilTagProcessor.getDetections().size()) {
            return aprilTagProcessor.getDetections().get(index);
        }

        return null;
    }

    // Find a tag by its actual ID
    public AprilTagDetection getTag(int tagID) {

        for (AprilTagDetection detection : aprilTagProcessor.getDetections()) {

            if (detection.id == tagID) {
                return detection;
            }
        }

        return null;
    }

    // Get the ID of the first detected tag
    public int getTagID() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {
            return detection.id;
        }

        return -1;
    }

    // Get X position of the first detected tag
    public double getTagX() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {
            return detection.rawPose.x;
        }

        return 0;
    }

    // Get X position of a specific tag
    public double getTagX(AprilTagDetection detection) {

        if (detection != null) {
            return detection.rawPose.x;
        }

        return 0;
    }

    // Get Y position of the first detected tag
    public double getTagY() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {
            return detection.rawPose.y;
        }

        return 0;
    }

    // Get Y position of a specific tag
    public double getTagY(AprilTagDetection detection) {

        if (detection != null) {
            return detection.rawPose.y;
        }

        return 0;
    }

    // Get Z position of the first detected tag
    public double getTagZ() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {
            return detection.rawPose.z;
        }

        return 0;
    }

    //Guess what? This is the Z position of a specific tag
    public double getTagZ(AprilTagDetection detection) {

        if (detection !=null) {
            return detection.rawPose.z;
        }

        return 0;
    }

    // Get distance to the first detected tag
    public double getTagRange() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {

            double x = detection.rawPose.x;
            double y = detection.rawPose.y;

            return Math.hypot(x, y);
        }

        return 0;
    }

    public double getTagRange(AprilTagDetection detection) {

        if (detection != null) {

            double x = detection.rawPose.x;
            double y = detection.rawPose.y;

            return Math.hypot(x, y);
        }

        return 0;
    }

    // Get bearing to the first detected tag
    public double getTagBearing() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {

            double x = detection.rawPose.x;
            double y = detection.rawPose.y;

            return Math.toDegrees(Math.atan2(-x, y));
        }

        return 0;
    }

    // Get bearing to a specific tag
    public double getTagBearing(AprilTagDetection detection) {

        if (detection != null) {

            double x = detection.rawPose.x;
            double y = detection.rawPose.y;

            return Math.toDegrees(Math.atan2(-x, y));
        }

        return 0;
    }

    // Get yaw of the first detected tag
    public double getTagYaw() {

        AprilTagDetection detection = getFirstDetection();

        if (detection != null) {

            Orientation rot = Orientation.getOrientation(
                    detection.rawPose.R,
                    AxesReference.INTRINSIC,
                    AxesOrder.YXZ,
                    AngleUnit.DEGREES
            );

            return -rot.firstAngle;
        }

        return 0;
    }

    public double getTagYaw(AprilTagDetection detection) {

        if (detection != null) {

            Orientation rot = Orientation.getOrientation(
                    detection.rawPose.R,
                    AxesReference.INTRINSIC,
                    AxesOrder.YXZ,
                    AngleUnit.DEGREES
            );

            return -rot.firstAngle;
        }

        return 0;
    }
}