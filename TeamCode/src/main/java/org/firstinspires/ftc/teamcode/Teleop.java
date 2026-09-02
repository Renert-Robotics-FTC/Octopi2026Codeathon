package org.firstinspires.ftc.teamcode;
//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode {

    private GoBildaPinpointDriver odometry;

    @Override
    public void runOpMode() {
        //Odometry initialization
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        odometry.setOffsets(-120.0, -30.0, DistanceUnit.MM);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.resetPosAndIMU();


        waitForStart();

        while (opModeIsActive()) {
            odometry.update(); //updates data

            //get data (x position in mm, y position in mm, heading in degrees)
            double xpos = odometry.getPosX(DistanceUnit.MM);
            double ypos = odometry.getPosY(DistanceUnit.MM);
            double heading = odometry.getHeading(AngleUnit.DEGREES);

            //report data to telemetry
            telemetry.addData("x Position", xpos);
            telemetry.addData("y Position", ypos);
            telemetry.addData("Heading", heading);

            //communicate to driver through telemetry
            telemetry.update();


        }
    }
}
