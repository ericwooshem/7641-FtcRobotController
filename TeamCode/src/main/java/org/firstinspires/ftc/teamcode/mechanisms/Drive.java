package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Drive {
    private DcMotor BRM; // Back Right Motor
    private DcMotor FRM; // Front Right Motor
    private DcMotor FLM; // Front Left Motor
    private DcMotor BLM; // Back Left Motor
    private BNO055IMU imu; // Internal Measurement Unit

    public double TA = 0;

    public double deltaangle(double h, double t){
        double tempAng = t-h;
        while (tempAng > 180) {
            tempAng -= 360;
        }
        while (tempAng < -180) {
            tempAng += 360;
        }
        return tempAng;
    }
    // Function to return the difference between Current angle and Target angle
    public Drive (HardwareMap HWMap) {
        BRM = HWMap.get(DcMotor.class, "backRightMotor"); // Back Right Motor
        FRM = HWMap.get(DcMotor.class, "frontRightMotor"); // Front Right Motor
        FLM = HWMap.get(DcMotor.class, "frontLeftMotor"); // Front Left Motor
        BLM = HWMap.get(DcMotor.class, "backLeftMotor"); // Back Left Motor
        BRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BNO055IMU.Parameters imuParameters;
        Orientation angles;
        Acceleration gravity;
        imu = HWMap.get(BNO055IMU.class, "imu");
        // Create new IMU Parameters object.
        imuParameters = new BNO055IMU.Parameters();
        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // Express acceleration as m/s^2.
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // Disable logging.
        imuParameters.loggingEnabled = false;
        // Initialize IMU.
        imu.initialize(imuParameters);
    }
    public double driveRobot(double C1RY, double C1RX, double C1LY, double C1LX, double TA) {
//        C1RY = C1RY;
//        C1RX = C1RX;
        C1LX = -C1LX;
        //double C1RY, C1RX, C1LY, C1LX; // Controler 1 Left/Right joystick X/Y
        double C2RX, C2RY; // Controler 2 Right joystick X/Y (Unused currently)
        double FRMP, FLMP, BRMP, BLMP; // Motor powers (P)
        double CA; // Current Angle (CA), Target Angle (TA)
        double rotate; // Something to do with handling field based vs robot based // frankly idr what this does exactly
        double rX, rY; // Right joystick X-axis Movement, Right joystick Y-axis Movement
        double headless; // Toggle for field based vs robot based
        double speed; // Speed multiplier
        double delta; // Used to store difference between CA and TA






        //BRM.setDirection(DcMotorSimple.Direction.REVERSE); // Figure out which motors need reversing later

        //thing happens here yay

            rotate=0;
            FRMP=0;
            FLMP=0;
            BRMP=0;
            BLMP=0;
            headless = 0;
            speed = 1;
            // Set Variables to values

                // Get absolute orientation
                // Get acceleration due to force of gravity.
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        Acceleration gravity = imu.getGravity();


                // Get joystick inputs above
                TA=TA+C1LX*4; // Adjust 1.6 to make turning faster or slower //3 is slow? says lucius
                CA=angles.firstAngle; // (rotation about Z)

        //fix below if i have a life
//                if (gamepad1.a == true){
//                    rotate= - CA;
//                    headless = 0;
//                }
//                if (gamepad1.y == true){
//                    headless=1;
//                }
//
                if (headless == 1){
                    rotate = -CA;
                }
                if (headless == 0){
                    rotate = 0;
                }
//                if (gamepad1.x == true){
//                    rotate =  0;
//                }
//                // Above if statements toggle field based driving vs robot based
//                if (gamepad1.left_trigger >0.5){
//                    speed=4; // Fastmode // was 6
//                } else if (gamepad1.right_trigger >0.5){
//                    speed=1; // Slowmode
//                } else {
//                    speed = 1.5; // Regular speed
//                }

                rX=C1RX*Math.cos((rotate) / 180 * Math.PI)-C1RY*Math.sin((rotate) / 180 * Math.PI);
                rY=C1RX*Math.sin((rotate) / 180 * Math.PI)+C1RY*Math.cos((rotate) / 180 * Math.PI);


                delta = deltaangle(CA,TA); // Get difference in Current Angle and Target Angle

                FRMP = rY + rX * -1+(delta) / 45;
                FLMP = rY + rX * 1-(delta) / 45;
                BRMP = rY + rX * 1+(delta) / 45;
                BLMP = rY + rX * -1-(delta) / 45;
                // Above: Get motor powers

                if (Math.abs(FRMP)>0.018){
                    FRM.setPower(FRMP/speed);
                }
                if (Math.abs(FLMP)>0.018){
                    FLM.setPower(FLMP/speed);
                }
                if (Math.abs(BRMP)>0.018){
                    BRM.setPower(BRMP/speed);
                }
                if (Math.abs(BLMP)>0.018){
                    BLM.setPower(BLMP/speed);
                }
        return TA;
                // If statements above to stop low power going to motors (it whines like crazy)


    }
}