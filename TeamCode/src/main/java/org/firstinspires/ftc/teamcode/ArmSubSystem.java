package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ArmSubSystem {
    private DcMotor armMotor;
    private double target = 0;
    private final double kP = 0.05;

    private final int intakePos=0;
    private final int corePos=1065;
    private final int nodePos=1900;

    public void ArmSubsystem(HardwareMap hardwareMap) {

        armMotor = hardwareMap.get(DcMotor.class, "arm");

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setIntakeTarget() {
        target = intakePos;
    }
    public void setCoreTarget() {
        target = corePos;
    }
    public void setNodeTarget() {
        target = nodePos;
    }

    public void updatePower() {
        armMotor.setPower((target - armMotor.getCurrentPosition()) * kP);
    }


    public double getTarget() {
        return target;
    }
    public int getPosition() {
        return armMotor.getCurrentPosition();
    }
}
