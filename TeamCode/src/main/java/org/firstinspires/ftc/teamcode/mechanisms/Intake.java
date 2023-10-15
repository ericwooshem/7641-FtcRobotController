package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Encoder;

public class Intake {


    DcMotor Intake;
    Servo Intakelift;
    public Intake(HardwareMap HWMap){
        Intake = HWMap.get(DcMotor.class, "Intake");
        Intakelift = HWMap.get(Servo.class, "IntakeServo");
    }

    public void spin(String direction){ //spin forward or reverse, if none then stop
        if(direction.equals("forward")){
            Intake.setDirection(DcMotorSimple.Direction.FORWARD);
            Intake.setPower(1.0);
        }
        else if(direction.equals("reverse")) {
            Intake.setDirection(DcMotorSimple.Direction.REVERSE);
            Intake.setPower(1.0);
        }
        else{
            Intake.setPower(0.0);
        }
    }

    public void liftToLevel(int level){
        if(level == 1){
            Intakelift.setPosition(0);
        }
    }
      /*Write the mechanism code in this file.
     All functions related to this mechanism should be in this file*/
}
