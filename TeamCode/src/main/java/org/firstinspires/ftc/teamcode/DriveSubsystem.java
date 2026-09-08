package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class DriveSubsystem {

    private DcMotor BL;
    private DcMotor BR;
    private DcMotor FL;
    private DcMotor FR;
    private Odometry odometry;

    public DriveSubsystem(HardwareMap hardwareMap, Odometry odometry) {

        this.odometry = odometry;

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");

        // Reverse motors if needed
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
    }

    // This sets how the robot is moving, it has a strafe set.
    // This is in your best interest to understand how the Mecanum wheels work with the strafe
    // This public void drive gives the drive variables used later
    public void drive(double forward, double strafe, double turn) {

        double heading = Math.toRadians(odometry.getHeading());
        double rotatedForward = forward * Math.cos(heading) + strafe * Math.sin(heading);
        double rotatedStrafe = -forward * Math.sin(heading) + strafe * Math.cos(heading);

        double flPower = (rotatedForward + rotatedStrafe + turn);
        double frPower = (rotatedForward - rotatedStrafe - turn);
        double blPower = (rotatedForward - rotatedStrafe + turn);
        double brPower = (rotatedForward + rotatedStrafe - turn);

        double maxPower = Math.max(
                1.0,
                Math.max(
                        Math.abs(flPower),
                        Math.max(
                                Math.abs(frPower),
                                Math.max(
                                        Math.abs(blPower),
                                        Math.abs(brPower)
                                )
                        )
                )
        );

        flPower /=maxPower;
        frPower /= maxPower;
        blPower/= maxPower;
        brPower /= maxPower;

        FL.setPower(flPower);
        FR.setPower(frPower);
        BL.setPower(blPower);
        BR.setPower(brPower);
    }

    public boolean alignToAprilTag(
            AprilTagScanner aprilTagScanner,
            int targetID,
            double targetDistance
    ) {

        AprilTagDetection tag = aprilTagScanner.getTag(targetID);

        // No target tag found
        if (tag == null) {
            drive(0, 0, 0);
            return false;
        }

        // Get tag information
        double x = tag.rawPose.x;
        double y = tag.rawPose.y;

        double bearing = Math.toDegrees(Math.atan2(-x, y));

        // Distance from camera to tag
        double distance = Math.hypot(x, y);

        // Calculate errors
        double strafeError = x;
        double turnError = bearing;
        double distanceError = distance - targetDistance;

        // Calculate movement
        double strafe =
                strafeError * Constants.DriveConstants.STRAFE_KP;

        double turn =
                turnError * Constants.DriveConstants.TURN_KP;

        double forward =
                distanceError * Constants.DriveConstants.DRIVE_KP;

        // Stop individual movements when close enough
        if (Math.abs(strafeError)
                < Constants.DriveConstants.X_TOLERANCE) {
            strafe = 0;
        }

        if (Math.abs(turnError)
                < Constants.DriveConstants.BEARING_TOLERANCE) {
            turn = 0;
        }

        if (Math.abs(distanceError)
                < Constants.DriveConstants.DISTANCE_TOLERANCE) {
            forward = 0;
        }

        // Keep powers between -1 and 1
        strafe = Math.max(-1, Math.min(1, strafe));
        turn = Math.max(-1, Math.min(1, turn));
        forward = Math.max(-1, Math.min(1, forward));

        boolean aligned =
                Math.abs(strafeError) < Constants.DriveConstants.X_TOLERANCE
                        && Math.abs(turnError) < Constants.DriveConstants.BEARING_TOLERANCE
                        && Math.abs(distanceError) < Constants.DriveConstants.DISTANCE_TOLERANCE;

        if (aligned) {
            drive(0, 0, 0);
            return true;
        }

        drive(forward, strafe, turn);

        return false;
    }
}