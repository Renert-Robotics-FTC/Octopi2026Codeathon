package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.opencv.core.Core;

@TeleOp(name = "Main TeleOp")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {

        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap);
        int selectedTagID = 1;

        Odometry odometry = new Odometry();
        odometry.initializeOdometry(hardwareMap);

        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap, odometry);

        ClawSubsystem clawSubsystem = new ClawSubsystem(hardwareMap);

        BeamBreakSensor beamBrakeSensor = new BeamBreakSensor(hardwareMap);


        telemetry.addLine("Ready!");
        telemetry.update();

        waitForStart();

        boolean clawOpen = false;
        boolean lastLB = false;
        boolean lastAState = false;
        boolean lastBState = false;

        while (opModeIsActive()) {

            // How to drive:
            // Use left-stick for strafe and forwards
            // Right-stick determines turning
            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            //calls the code to align to Core and Node, respectively
            if (gamepad1.a) {
                driveSubsystem.coreAlign();
                armSubsystem.setCoreTarget();
            }
            if (gamepad1.b) {
                driveSubsystem.nodeAlign();
                armSubsystem.setNodeTarget();
            }

            //beam brake sensor automatically controls intake
            if (!beamBrakeSensor.getState()){
                Intake.intakeFlag=false;
            }

            //automates the shooter when the arm and robot are both aligned
            if (armSubsystem.getAligned() && DriveSubsystem.aligned && armSubsystem.getTarget() != 0){
                Shooter.shooterFlag=true;
                sleep(1000);
                Shooter.shooterFlag=false;
                armSubsystem.setIntakeTarget();
                Intake.intakeFlag=true;
            }

            //Left bumper controls the claw
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
            armSubsystem.updatePower();

            // Run odometry every loop
            odometry.updateOdometry();

            // Telemetry
            telemetry.addData("X Position", odometry.getxPos());
            telemetry.addData("Y Position", odometry.getyPos());
            telemetry.addData("Heading", odometry.getHeading());

            telemetry.update();

        }
    }
}