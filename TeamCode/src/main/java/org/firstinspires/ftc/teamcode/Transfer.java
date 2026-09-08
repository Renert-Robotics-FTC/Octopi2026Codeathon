package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    private DcMotor transferMotor;
    public Transfer(HardwareMap hardwareMap) {

        transferMotor = hardwareMap.get(DcMotor.class, "transfer");

        transferMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void updatePower() {
        if (Intake.intakeFlag == true) {
            transferMotor.setPower(1);
        } else if(Shooter.shooterFlag == true) {
            transferMotor.setPower(-1);
        } else {
            transferMotor.setPower(0);
        }
    }

}
