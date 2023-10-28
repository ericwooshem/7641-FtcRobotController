package org.firstinspires.ftc.teamcode.drivetrainoptions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



/* This file was for the demo drivetrain that we used in the  outreach event on the October 21st 2023*/

@TeleOp
public class DemoArcadeDrive extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            //double y = -gamepad1.right_stick_y;
            double linearx = gamepad1.right_stick_y;
            double rotationx = -gamepad1.left_stick_x;

           // double demominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (0.4*linearx-0.3*rotationx);
            double frontRightPower = (0.4*linearx+0.3*rotationx);


            frontLeftMotor.setPower(frontLeftPower);
            frontRightMotor.setPower(frontRightPower);


        }

    }


}
