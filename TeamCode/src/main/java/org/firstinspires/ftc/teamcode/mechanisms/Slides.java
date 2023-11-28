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

    private int[] setSlideLiftPos = {0, 12288, 20480, 28672}; // encoder pos //{0, 500, 700, 1000, 200}; // Unknown values. First value is for slide reset pos.
    DcMotor Intake;
    public Slides(HardwareMap HWMap){
        Intake = HWMap.get(DcMotor.class, "Intake"); // this is goofy
        rightSlidesMotor = HWMap.get(DcMotor.class, "rightSlidesMotor");
        leftSlidesMotor = HWMap.get(DcMotor.class, "leftSlidesMotor");
        rightSlidesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlidesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();
        initPosition = (rightCurrentPosition + leftCurrentPosition) / 2;
    }

    public void slide(int targetSlides, double fineAdjust, int setLine) {
        rightSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightCurrentPosition = rightSlidesMotor.getCurrentPosition();
        leftCurrentPosition =  leftSlidesMotor.getCurrentPosition();

        avgCurrentPos = (rightCurrentPosition + leftCurrentPosition) / 2;

        avgCurrentPos = Intake.getCurrentPosition(); // bypass dw aobut it

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
            difference = difference * 0.09; // P on difference to generate power for motor
        } else {
            difference = difference * 0.005;
        }

        leftSlidesMotor.setPower(difference);
        rightSlidesMotor.setPower(difference);


    }

    public void slideonoff(boolean hmm) {
        if (hmm) {
            leftSlidesMotor.setPower(-0.3);
            rightSlidesMotor.setPower((-0.3));
        } else {
            leftSlidesMotor.setPower(0);
            rightSlidesMotor.setPower(0);
        }
    }

    public void slideresetpls(boolean hmm) {
        rightSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlidesMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void slideuhhyayonoff(int hmm) {

        rightSlidesMotor.setTargetPosition(hmm);
        leftSlidesMotor.setTargetPosition(hmm);
        rightSlidesMotor.setPower(1);
        leftSlidesMotor.setPower(1);
        rightSlidesMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlidesMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void slideCommands(int setLine, double fineAdjust) { //[0] is slide reset
        slide(setSlideLiftPos[setLine], fineAdjust, setLine);
    }
}

//2130 Top Slide Position !!! :D