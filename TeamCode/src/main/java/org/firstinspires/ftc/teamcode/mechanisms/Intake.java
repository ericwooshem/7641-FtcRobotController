package org.firstinspires.ftc.teamcode.mechanisms;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Encoder;


public class Intake {

    private double[] servoPos = {1,//0.87228696,0.85418 , 0.823556, 0.8001234 // going from top to bottom bc servo aint perfect
            0.8669, 0.8393649, 0.816341, 0.80043874, 0.92 // going from bottom to top, its good enough.
    }; // [0] to [5], 0 is ground, 5 is top. Values may need adjustment. Current pixel height in servo = 0.4

    // {0.8001234, 0.823556, 0.85418, 0.87228696} // reserved

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
        else if(direction.equals("autondrop")) {
            Intake.setPower(0.4);
        }
        else{
            Intake.setPower(0.0);
        }
    }

    public void liftToLevel(int level){
        Intakelift.setPosition(servoPos[level]);
    }//5 for auton lift
    public void liftToTest(double test){
        Intakelift.setPosition(test);
    }
      /*Write the mechanism code in this file.
     All functions related to this mechanism should be in this file*/
}
