package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Main TeleOp")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {

        ArmSubsystem Armsubsystem = new ArmSubsystem(hardwareMap);
        ClawSubsystem clawSubsystem = new ClawSubsystem(hardwareMap);
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);

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
                Armsubsystem.setTargetPosition(500);
            }

            if (gamepad1.y) {
                Armsubsystem.setTargetPosition(1000);
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

            // Telemetry
            telemetry.addData("Target", Armsubsystem.getTargetPosition());
            telemetry.addData("Current", Armsubsystem.getCurrentPosition());
            telemetry.addData("Power", Armsubsystem.getPower());

            telemetry.update();
        }
    }
}