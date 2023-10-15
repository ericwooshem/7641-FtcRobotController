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
                      double gamepad2_left_stick_x,
                      double gamepad2_right_stick_y,
                      DcMotor rightSlidesMotor,
                      DcMotor leftSlidesMotor
    ) {


        double target = 0;
        double difference = 0;
        double current_pos_right = rightSlidesMotor.getCurrentPosition();
        double current_pos_left = leftSlidesMotor.getCurrentPosition();
        double rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        double leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();

        double initPosition = (rightCurrentPosition + leftCurrentPosition) / 2;

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        double x = gamepad2_left_stick_x;
        double g2ry = gamepad2_right_stick_y;
        double current_pos_LplusR = current_pos_left + current_pos_right;


        target -= g2ry * 5;

        checkButtonPress(target, button_keyname);


        difference = target - (current_pos_LplusR) / 2 - initPosition;
        telemetry.addData("Pos:", (current_pos_LplusR) / 2 - initPosition);
        telemetry.update();

        difference = difference * 0.01;
        leftSlidesMotor.setPower(difference);
        rightSlidesMotor.setPower(difference);
    }

}

//2130 Top Slide Position !!! :D