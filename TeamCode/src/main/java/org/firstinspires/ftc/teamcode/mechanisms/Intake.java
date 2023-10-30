package org.firstinspires.ftc.teamcode.mechanisms;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Encoder;


public class Intake {

    private double pixelServoHeight = 0.12;
    private double[] servoPos = {0.2,
            0.2 - 0.11 * 1,
            0.2 - 0.8 - pixelServoHeight *1,
            0.2 - 0.8 -pixelServoHeight*2,
            0.2 - 0.8 - pixelServoHeight*3,
            0.2 - 0.8 - pixelServoHeight*4}; // [0] to [5], 0 is ground, 5 is top. Values may need adjustment. Current pixel height in servo = 0.4

    DcMotor Intake;

    Servo Intakelift;

    public Intake(HardwareMap HWMap){
        Intake = HWMap.get(DcMotor.class, "Intake");
        Intakelift = HWMap.get(Servo.class, "IntakeServo");
        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void spin(String direction){ //spin forward or reverse, if none then stop
        if(direction.equals("forward")){
            Intake.setPower(-1.0);
        }
        else if(direction.equals("reverse")) {
            Intake.setPower(0.8);
        }
        else{
            Intake.setPower(0.0);
        }
    }

    public void liftToLevel(int level){
        Intakelift.setPosition(servoPos[level]);
    }
      /*Write the mechanism code in this file.
     All functions related to this mechanism should be in this file*/
}
