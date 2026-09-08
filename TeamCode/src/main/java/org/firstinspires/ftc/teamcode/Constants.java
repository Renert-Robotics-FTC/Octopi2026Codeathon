package org.firstinspires.ftc.teamcode;

public class Constants {

    public static final class ArmConstants {
        public static final double ARM_KP = 0.01;
        public static final double ARM_KI = 0;
        public static final double ARM_KD = 0.0005;
        public static final int ARM_INTAKE_POSITION = 0;
        public static final int ARM_NODE_POSITION = 1900;
        public static final int ARM_CORE_POSITION = 1065;

    }

    public static final class DriveConstants {

        public static final int NODE_DISTANCE = 67;
        public static final int CORE_DISTANCE = 76;

        // AprilTag Auto-Align
        public static final double STRAFE_KP = 0.03;
        public static final double TURN_KP = 0.02;
        public static final double DRIVE_KP = 0.03;

        public static final double X_TOLERANCE = 1.0;
        public static final double BEARING_TOLERANCE = 3.0;
        public static final double DISTANCE_TOLERANCE = 2.0;
    }
}