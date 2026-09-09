package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem {

    //THIS IS ONLY USED FOR THE SWITCH. ONLY USED ONCE PER GAME.
    //THIS DOES NOT COUNT TOWARDS THE BUTTON CYCLE LIMIT
    private Servo claw;

    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void open() {
        claw.setPosition(Constants.ClawConstants.OPEN);
    }

    public void close() {
        claw.setPosition(Constants.ClawConstants.CLOSED);
    }
}