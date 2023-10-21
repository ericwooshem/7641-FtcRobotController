package org.firstinspires.ftc.teamcode.drivetrainoptions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class DemoArcadeDrive extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            //double y = -gamepad2.right_stick_y;
            double linearx = gamepad2.left_stick_y;
            double rotationx = -gamepad2.right_stick_x;

           // double demominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (0.5*linearx-0.3*rotationx);
            double frontRightPower = (0.5*linearx+0.3*rotationx);


            frontLeftMotor.setPower(frontLeftPower);
            frontRightMotor.setPower(frontRightPower);


        }

    }


}
