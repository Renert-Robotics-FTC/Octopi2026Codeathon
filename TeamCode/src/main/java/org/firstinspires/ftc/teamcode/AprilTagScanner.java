package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

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

    public int getNumberOfDetections() {
        return aprilTagProcessor.getDetections().size();
    }

    public int getTagID() {

        if (aprilTagProcessor.getDetections().size() > 0) {
            return aprilTagProcessor.getDetections().get(0).id;
        }

        return -1;
    }

    public double getTagX() {
        if (aprilTagProcessor.getDetections().size() > 0) {
            return aprilTagProcessor.getDetections().get(0).rawPose.x;
        }

        return 0;
    }

    public double getTagY() {

        if (aprilTagProcessor.getDetections().size() > 0) {
            return aprilTagProcessor.getDetections().get(0).rawPose.y;
        }

        return 0;
    }

    public double getTagZ() {

        if (aprilTagProcessor.getDetections().size() > 0) {
            return aprilTagProcessor.getDetections().get(0).rawPose.z;
        }

        return 0;
    }

    public double getTagRange() {
        if (aprilTagProcessor.getDetections().size() > 0) {

            double x = aprilTagProcessor.getDetections().get(0).rawPose.x;
            double y = aprilTagProcessor.getDetections().get(0).rawPose.y;

            return Math.hypot(x, y);
        }

        return 0;
    }
}