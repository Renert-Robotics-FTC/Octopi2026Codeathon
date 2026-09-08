package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Core Node Auto")
public class CoreNodeAuto extends LinearOpMode {

    public static boolean aligned;
    @Override
    public void runOpMode() {

        ArmSubsystem Armsubsystem = new ArmSubsystem(hardwareMap);

        int selectedTagID = 1;

        Odometry odometry = new Odometry();
        odometry.initializeOdometry(hardwareMap);

        DriveSubsystem driveSubsystem =
                new DriveSubsystem(hardwareMap, odometry);

        AprilTagScanner aprilTagScanner =
                new AprilTagScanner(hardwareMap);

        telemetry.addLine("Ready!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            driveSubsystem.alignToAprilTag(
                    aprilTagScanner,
                    1,
                    Constants.DriveConstants.NODE_DISTANCE
            );

            odometry.updateOdometry();

            telemetry.addData(
                    "Target Distance",
                    Constants.DriveConstants.CORE_DISTANCE
            );

            telemetry.addData(
                    "AprilTags",
                    aprilTagScanner.getNumberOfDetections()
            );

            aligned = driveSubsystem.alignToAprilTag(
                    aprilTagScanner,
                    1,
                    Constants.DriveConstants.NODE_DISTANCE
            );

            telemetry.update();
        }
    }
}