package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public void drive(double forward, double strafe, double turn, boolean slowMode) {

        // This slowMode puts the robot in 1/4 speed ratio
        // If you hold X it turns on the slowMode
        double speed = slowMode ? 0.25 : 1.0;

        double heading = Math.toRadians(odometry.getHeading());
        double rotatedForward = forward * Math.cos(heading) + strafe * Math.sin(heading);
        double rotatedStrafe = -forward * Math.sin(heading) + strafe * Math.cos(heading);

        double flPower = (rotatedForward + rotatedStrafe + turn) * speed;
        double frPower = (rotatedForward - rotatedStrafe - turn) * speed;
        double blPower = (rotatedForward - rotatedStrafe + turn) * speed;
        double brPower = (rotatedForward + rotatedStrafe - turn) * speed;

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
}