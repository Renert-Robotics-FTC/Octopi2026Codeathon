package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "Main TeleOp")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {

        ArmSubsystem Armsubsystem = new ArmSubsystem(hardwareMap);
        int selectedTagID = 1;

        Odometry odometry = new Odometry();
        odometry.initializeOdometry(hardwareMap);

        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap, odometry);

        AprilTagScanner aprilTagScanner = new AprilTagScanner(hardwareMap);

        telemetry.addLine("Ready!");
        telemetry.update();

        waitForStart();

        boolean clawOpen = false;
        boolean lastLB = false;

        while (opModeIsActive()) {

            // How to drive:
            // Use left-stick for strafe and forwards
            // Right-stick determines turning
            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            // Preset Positions
            if (gamepad1.a) {
                Armsubsystem.setIntakeTarget();
            }

            /* Left bumper controls the claw
            if (gamepad1.left_bumper && !lastLB) {

                clawOpen = !clawOpen;

                if (clawOpen) {
                    clawSubsystem.open();
                } else {
                    clawSubsystem.close();
                }
            }*/

            lastLB = gamepad1.left_bumper;

            // Run the PID every loop
            Armsubsystem.updatePower();

            // Run odometry every loop
            odometry.updateOdometry();

            // Telemetry
            telemetry.addData("X Position", odometry.getxPos());
            telemetry.addData("Y Position", odometry.getyPos());
            telemetry.addData("Heading", odometry.getHeading());

            // Scanning the first tag
            AprilTagDetection tag = aprilTagScanner.getFirstDetection();

            if (tag != null) {
                telemetry.addData("AprilTags", aprilTagScanner.getNumberOfDetections());
                telemetry.addData("Tag ID", aprilTagScanner.getTagID());
                telemetry.addData("Tag X", aprilTagScanner.getTagX(tag));
                telemetry.addData("Tag Y", aprilTagScanner.getTagY(tag));
                telemetry.addData("Tag Z", aprilTagScanner.getTagZ(tag));
                telemetry.addData("Tag Range", aprilTagScanner.getTagRange(tag));
                telemetry.addData("Tag Bearing", aprilTagScanner.getTagBearing(tag));
                telemetry.addData("Tag Yaw", aprilTagScanner.getTagYaw());
            } else {
                telemetry.addData("AprilTag", "No tag detected");
            }

            telemetry.update();

        }
    }
}