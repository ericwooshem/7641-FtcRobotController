package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Slides {
    public void checkButtonPress(double target, char button_keyname) {

        if (target < 0) {
            target = 0;
        } else if ("a".equals(button_keyname)) {
            target = 0;
        } else if ("x".equals(button_keyname)) {
            target = 1065;
        } else if ("y".equals(button_keyname)) {
            target = 2130;
        } else if (target > 2130) {
            target = 2130;
        }
    }

    public void slide(char button_keyname,
                      double gamepad1_left_stick_y,
                      double gamepad2_left_stick_x,
                      double gamepad1_right_stick_x,
                      double gamepad2_right_stick_y) {


        double target = 0;
        double difference = 0;
        DcMotor rightSlidesMotor = hardwareMap.dcMotor.get("rightSlidesMotor");
        DcMotor leftSlidesMotor = hardwareMap.dcMotor.get("leftSlidesMotor");
        double initpos = (rightSlidesMotor.getCurrentPosition() + leftSlidesMotor.getCurrentPosition()) / 2;
        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        double y = -(gamepad1_left_stick_y);
        double x = gamepad2_left_stick_x;
        double rx = gamepad1_right_stick_x;
        double g2ry = gamepad2_right_stick_y;
        target -= g2ry * 5;

        checkButtonPress(target, button_keyname);


        double current_pos_right = rightSlidesMotor.getCurrentPosition();
        double current_pos_left = leftSlidesMotor.getCurrentPosition();
        difference = target - (current_pos_left + current_pos_right) / 2 - initpos;
        telemetry.addData("Pos:", (current_pos_left + current_pos_right) / 2 - initpos);
        telemetry.update();
        difference = difference * 0.01;
        leftSlidesMotor.setPower(difference);
        rightSlidesMotor.setPower(difference);
    }

}

//2130 Top Slide Position !!! :D