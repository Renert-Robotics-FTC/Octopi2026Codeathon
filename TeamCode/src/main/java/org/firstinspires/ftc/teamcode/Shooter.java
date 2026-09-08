package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    public static boolean shooterFlag=false;
    private DcMotor shooterMotor;
    public Shooter(HardwareMap hardwareMap) {

        shooterMotor = hardwareMap.get(DcMotor.class, "shooter");

        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void updatePower() {
        if (Intake.intakeFlag == true) {
            shooterMotor.setPower(1);
        } else if(Shooter.shooterFlag == true) {
            shooterMotor.setPower(-1);
        } else {
            shooterMotor.setPower(0);
        }
    }

}
