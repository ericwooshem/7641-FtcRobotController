package org.firstinspires.ftc.teamcode.mechanisms.oldCode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SlidesOld {
    DcMotor rightSlidesMotor;
    DcMotor leftSlidesMotor;
    public SlidesOld(HardwareMap hwmap){
        rightSlidesMotor = hwmap.get(DcMotor.class, "rightSlidesMotor");
        leftSlidesMotor = hwmap.get(DcMotor.class, "leftSlidesMotor");
    }
    /*   The if statements allow you to check
    the right button and then set the target o the right number    */
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

    /* This function allows the slide to go up and down with a difference variable.
    * There is a current position when it is started up*/
    public void slide(char button_keyname,
                      double gamepad2_left_stick_x,
                      double gamepad2_right_stick_y
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