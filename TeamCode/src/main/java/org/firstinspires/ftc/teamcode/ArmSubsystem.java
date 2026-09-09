package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ArmSubsystem {
    private DcMotor armMotor;
    private double target = 0;
    private final double kP = 0.05;
    public ArmSubsystem(HardwareMap hardwareMap) {

        armMotor = hardwareMap.get(DcMotor.class, "arm");

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setIntakeTarget() {
        target = Constants.ArmConstants.ARM_INTAKE_POSITION;
    }
    public void setCoreTarget() {
        target = Constants.ArmConstants.ARM_CORE_POSITION;
    }
    public void setNodeTarget() {
        target = Constants.ArmConstants.ARM_NODE_POSITION;
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

    public boolean getAligned(){
        if (target==armMotor.getCurrentPosition()){
            return true;
        }else{return false;}
    }
}
