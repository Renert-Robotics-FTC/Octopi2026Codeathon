package org.firstinspires.ftc.teamcode;
//imports
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Main Teleop")
public class Teleop extends LinearOpMode {

    Odometry odometry = new Odometry();

    @Override
    public void runOpMode() {

        odometry.initializeOdometry(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            odometry.updateOdometry();

            //report data to telemetry
            telemetry.addData("x Position", odometry.getxPos());
            telemetry.addData("y Position", odometry.getyPos());
            telemetry.addData("Heading", odometry.getHeading());

            //communicate to driver through telemetry
            telemetry.update();

        }
    }
}
