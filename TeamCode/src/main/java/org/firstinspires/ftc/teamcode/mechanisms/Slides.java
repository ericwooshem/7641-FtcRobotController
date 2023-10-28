package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Slides {
    private DcMotor rightSlidesMotor;
    private DcMotor leftSlidesMotor;

    private int target = 0;

    public Slides(HardwareMap hwmap){
        rightSlidesMotor = hwmap.get(DcMotor.class, "rightSlidesMotor");
        leftSlidesMotor = hwmap.get(DcMotor.class, "leftSlidesMotor");
    }


    public void slide(int targetslides) {

        double current_pos_right = rightSlidesMotor.getCurrentPosition();
        double current_pos_left = leftSlidesMotor.getCurrentPosition();
        double rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        double leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();


        double initPosition = (rightCurrentPosition + leftCurrentPosition) / 2;

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        double current_pos_LplusR = current_pos_left + current_pos_right;





        if (target < 0) {
            target = 0;
        } else if (target > 2130) {
            target = 2130;
        }
        else{
            target = targetslides;
        }

        double difference = target - (current_pos_LplusR) / 2 - initPosition;

        difference = difference * 0.01;
        leftSlidesMotor.setPower(difference);
        rightSlidesMotor.setPower(difference);
    }

}

//2130 Top Slide Position !!! :D