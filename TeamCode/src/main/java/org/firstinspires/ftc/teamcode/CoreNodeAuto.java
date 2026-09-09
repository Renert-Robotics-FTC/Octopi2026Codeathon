package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CoreNodeAuto {

    private final ArmSubsystem armSubsystem;
    private final Odometry odometry;
    private final DriveSubsystem driveSubsystem;
    private final AprilTagScanner aprilTagScanner;

    private boolean aligned = false;

    public CoreNodeAuto(HardwareMap hardwareMap) {

        armSubsystem = new ArmSubsystem(hardwareMap);

        odometry = new Odometry();
        odometry.initializeOdometry(hardwareMap);

        driveSubsystem = new DriveSubsystem(hardwareMap, odometry);

        aprilTagScanner = new AprilTagScanner(hardwareMap);
    }

    public boolean alignToNode(int tagID) {
        odometry.updateOdometry();

        aligned = driveSubsystem.alignToAprilTag(
                aprilTagScanner,
                tagID,
                Constants.DriveConstants.NODE_DISTANCE
        );

        return aligned;
    }

    public boolean alignToCore(int tagID) {
        odometry.updateOdometry();

        aligned = driveSubsystem.alignToAprilTag(
                aprilTagScanner,
                tagID,
                Constants.DriveConstants.CORE_DISTANCE
        );

        return aligned;
    }

    public void updateArm() {
        armSubsystem.updatePower();
    }

    public boolean isAligned() {
        return aligned;
    }
}