package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Slides {
    private DcMotor rightSlidesMotor;
    private DcMotor leftSlidesMotor;

    private int maxTarget = 1800; // Maximum value for slide motors
    private double target = 0; // should this be double?

    private double difference;
    private double initPosition;
    private double avgCurrentPos;
    private double rightCurrentPosition;
    private double leftCurrentPosition;

    private int[] setSlideLiftPos = {0, 600, 800, 1000}; // Unknown values. First value is for slide reset pos.

    public Slides(HardwareMap HWMap){
        rightSlidesMotor = HWMap.get(DcMotor.class, "rightSlidesMotor");
        leftSlidesMotor = HWMap.get(DcMotor.class, "leftSlidesMotor");

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();
        initPosition = (rightCurrentPosition + leftCurrentPosition) / 2;
    }

    public void slide(int targetSlides, double fineAdjust, int setLine) {
        rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();

        avgCurrentPos = (rightCurrentPosition + leftCurrentPosition) / 2;

        targetSlides += fineAdjust;

        if (targetSlides < 0) {
            target = 0;
        } else if (targetSlides > maxTarget) { //Bound needs to be lowered. Changed from 2130 to 1800
            target = maxTarget;
        }
        else{
            target = targetSlides;
        }

        difference = target - (avgCurrentPos - initPosition);

        if (difference > 0) {
            difference = difference * 0.1; // P on difference to generate power for motor
        } else {
            difference = difference * 0.005;
        }
        leftSlidesMotor.setPower(difference);
        rightSlidesMotor.setPower(difference);


    }

    public void slideCommands(int setLine, double fineAdjust) { //[0] is slide reset
        slide(setSlideLiftPos[setLine], fineAdjust, setLine);
    }
}

//2130 Top Slide Position !!! :D