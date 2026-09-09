package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class BeamBreakSensor {

    private DigitalChannel sensor;
    private boolean lastState = true; // true = beam intact

    public BeamBreakSensor(HardwareMap hardwareMap) {
        sensor = hardwareMap.get(DigitalChannel.class, "beambreak");
        sensor.setMode(DigitalChannel.Mode.INPUT);
        lastState = sensor.getState();
    }

    public boolean getState(){
        return sensor.getState();
    }
}
