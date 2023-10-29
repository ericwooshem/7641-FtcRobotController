package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Objects;

public class Spatula {

    private double slotForwardPos = 0.7; // Position where slot is 90 degrees to backboard (verify)

    CRServo PixelSpinner;

    Servo RightSpatula;

    Servo LeftSpatula;

    public Spatula(HardwareMap HWMap) {
        PixelSpinner = HWMap.get(CRServo.class, "PixelServo");
        RightSpatula = HWMap.get(Servo.class, "RightSpatulaServo");
        LeftSpatula = HWMap.get(Servo.class, "LeftSpatulaServo");

        slotReset();
        spinWheelStop();
    }

    public void spinWheelForward() { //-1<=x<=1 ///Can these be made private?
        PixelSpinner.setPower(1);
        // Spins pixel into slot
    }

    public void spinwheelBackwards(){
        PixelSpinner.setPower(-1);
        // Spins pixel out of slot
    }

    public void spinWheelStop(){
        PixelSpinner.setPower(0);
        // Stop spinning
    }

    public void slotForward() {
        RightSpatula.setPosition(1-slotForwardPos);
        LeftSpatula.setPosition(slotForwardPos);

        //Bring the slot closer to the backdrop and orients it the right way, so the pixel can fall out
    }

    public void slotReset() {
        RightSpatula.setPosition(1);
        LeftSpatula.setPosition(0);
        //Brings slot back next to the intake, so the slot can store the newly intaked pixels
    }

    public void spatulaCommand( String letterCommand){

        if (Objects.equals(letterCommand, "slotForward")){
            slotForward();
        }
        else if(Objects.equals(letterCommand,"slotReset")){
            slotReset();
        }


    }

    public void wheelCommands(String directioncommand){
        if (Objects.equals(directioncommand, "forward")){
            spinWheelForward();
        }
        else if(Objects.equals(directioncommand,"backward")){
            spinwheelBackwards();
        }
        else if (Objects.equals(directioncommand, "stop")){
            spinWheelStop();
        }
    }



}



