package org.firstinspires.ftc.teamcode.drivetrainoptions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.mechanisms.Drone;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Slides;
import org.firstinspires.ftc.teamcode.mechanisms.Spatula;


@TeleOp
public class MecanumArcadeDrive extends LinearOpMode {

    public void driveTrain(DcMotor frontLeftMotor,
                           DcMotor backLeftMotor,
                           DcMotor frontRightMotor,
                           DcMotor backRightMotor){

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double demominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / demominator;
        double backLeftPower = (y - x + rx) / demominator;
        double frontRightPower = (y - x - rx) / demominator;
        double backRightPower = (y + x - rx) / demominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }
    public void checkButtonPress(){
        Slides slidelift = new Slides();
        Intake intake = new Intake(hardwareMap);
        Spatula spatula  = new Spatula();
        Drone drone = new Drone();

        double x = gamepad2.left_stick_x;
        double g2ry = gamepad2.right_stick_y;
/*
        if (gamepad2.a){
            slidelift.slide('a', x,g2ry);
            telemetry.addData("Button", "a");
        }
        else if (gamepad2.x){
            slidelift.slide('x' ,x,g2ry);
            telemetry.addData("Button", "x");

        }
        else if (gamepad2.y){
            slidelift.slide('y' ,x,g2ry);
            telemetry.addData("Button", "y");
        }
        else if (gamepad2.b){

        }
        else if(gamepad2.left_trigger > 0){

        }
        else if(gamepad2.left_bumper) {

        }*/

    }
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            driveTrain(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
            checkButtonPress();

        }

    }


}
