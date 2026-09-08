package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    public static boolean intakeFlag=true;
    private DcMotor intakeMotor;
    public Intake(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void updatePower(){
        if (intakeFlag==true){
            intakeMotor.setPower(1);
        }else{
            intakeMotor.setPower(0);
        }
    }

}
