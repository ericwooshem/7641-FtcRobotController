package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Slides {
    private DcMotor rightSlidesMotor;
    private DcMotor leftSlidesMotor;

    private int target;
    public Slides(HardwareMap hwmap){
        rightSlidesMotor = hwmap.get(DcMotor.class, "rightSlidesMotor");
        leftSlidesMotor = hwmap.get(DcMotor.class, "leftSlidesMotor");
    }
   
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

    public void slide() {



        double initPosition = 0;//(rightCurrentPosition + leftCurrentPosition) / 2;

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        double current_pos_LplusR = 0;//current_pos_left + current_pos_right;




        //checkButtonPress(target, button_keyname);


       // difference = target - (current_pos_LplusR) / 2 - initPosition;
        telemetry.addData("Pos:", (current_pos_LplusR) / 2 - initPosition);
        telemetry.update();

       // difference = difference * 0.01;
        //leftSlidesMotor.setPower(difference);
        //rightSlidesMotor.setPower(difference);
    }

}

//2130 Top Slide Position !!! :D