package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Slides extends LinearOpMode{
    @Override
    public void runOpMode()  throws InterruptedException{
        double target = 0;
        double difference = 0;
        DcMotor rightSlidesMotor = hardwareMap.dcMotor.get("rightSlidesMotor");
        DcMotor leftSlidesMotor = hardwareMap.dcMotor.get("leftSlidesMotor");
        double initpos = (rightSlidesMotor.getCurrentPosition() + leftSlidesMotor.getCurrentPosition())/2;
        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double g2ry = gamepad2.right_stick_y;
            target -= g2ry*5;
            if (target < 0){
                target = 0;
            } else if (target > 2130){
                target = 2130;
            }
            double current_pos_right = rightSlidesMotor.getCurrentPosition();
            double current_pos_left = leftSlidesMotor.getCurrentPosition();
            difference = target - (current_pos_left+current_pos_right)/2-initpos;
            telemetry.addData("Pos:", (current_pos_left+current_pos_right)/2-initpos);
            telemetry.update();
            difference = difference * 0.01;
            leftSlidesMotor.setPower(difference);
            rightSlidesMotor.setPower(difference);
        }

    }
}
//2130 Top Slide Position !!! :D