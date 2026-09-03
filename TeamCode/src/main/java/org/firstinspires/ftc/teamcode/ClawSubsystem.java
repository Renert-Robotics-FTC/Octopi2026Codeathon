package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem {

    private Servo claw;

    // Change these values after testing, they determine the position of the claw
    // Find the values in which the claw is fully open and fully closed
    private static final double OPEN = 0.8;
    private static final double CLOSED = 0.2;

    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void open() {
        claw.setPosition(OPEN);
    }

    public void close() {
        claw.setPosition(CLOSED);
    }
}