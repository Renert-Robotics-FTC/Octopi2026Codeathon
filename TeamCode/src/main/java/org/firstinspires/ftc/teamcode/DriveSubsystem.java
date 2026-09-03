package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {

    private DcMotor BL;
    private DcMotor BR;
    private DcMotor FL;
    private DcMotor FR;

    public DriveSubsystem(HardwareMap hardwareMap) {

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

        double flPower = (forward + strafe + turn) * speed;
        double frPower = (forward - strafe - turn) * speed;
        double blPower = (forward - strafe + turn) * speed;
        double brPower = (forward + strafe - turn) * speed;

        FL.setPower(flPower);
        FR.setPower(frPower);
        BL.setPower(blPower);
        BR.setPower(brPower);
    }
}