package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "Main TeleOp")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {

        ArmSubsystem Armsubsystem = new ArmSubsystem(hardwareMap);
        ClawSubsystem clawSubsystem = new ClawSubsystem(hardwareMap);

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

            // Slow mode: hold X
            driveSubsystem.drive(
                    forward,
                    strafe,
                    turn,
                    gamepad1.x
            );

            // Preset Positions
            if (gamepad1.a) {
                Armsubsystem.setTargetPosition(0);
            }

            if (gamepad1.b) {
                Armsubsystem.setTargetPosition(1065);
            }

            if (gamepad1.y) {
                Armsubsystem.setTargetPosition(1900);
            }

            // Left bumper controls the claw
            if (gamepad1.left_bumper && !lastLB) {

                clawOpen = !clawOpen;

                if (clawOpen) {
                    clawSubsystem.open();
                } else {
                    clawSubsystem.close();
                }
            }

            lastLB = gamepad1.left_bumper;

            // Run the PID every loop
            Armsubsystem.update();

            // Run odometry every loop
            odometry.updateOdometry();

            // Telemetry
            telemetry.addData("Target", Armsubsystem.getTargetPosition());
            telemetry.addData("Current", Armsubsystem.getCurrentPosition());
            telemetry.addData("Power", Armsubsystem.getPower());

            telemetry.addData("X Position", odometry.getxPos());
            telemetry.addData("Y Position", odometry.getyPos());
            telemetry.addData("Heading", odometry.getHeading());

            telemetry.addData("AprilTags", aprilTagScanner.getNumberOfDetections());
            telemetry.addData("Tag ID", aprilTagScanner.getTagID());
            telemetry.addData("Tag X", aprilTagScanner.getTagX());
            telemetry.addData("Tag Y", aprilTagScanner.getTagY());
            telemetry.addData("Tag Z", aprilTagScanner.getTagZ());
            telemetry.addData("Tag Range", aprilTagScanner.getTagRange());
            telemetry.addData("Tag Bearing", aprilTagScanner.getTagBearing());
            telemetry.addData("Tag Yaw", aprilTagScanner.getTagYaw());

            telemetry.update();

            // Find Tag 20
            AprilTagDetection tag = aprilTagScanner.getTag(20);

        }
    }
}