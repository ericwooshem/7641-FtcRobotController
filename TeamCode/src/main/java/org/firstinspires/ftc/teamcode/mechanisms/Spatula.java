package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;

public class Spatula {

    private double slotForwardPos = 0.55; // Position where slot is 90 degrees to backboard (verify)
    private double slotForwardPosAuton = 0.43; //wa 0.63
    CRServo PixelSpinner;

    public Servo RightSpatula;

    public Servo LeftSpatula;
    Telemetry telemetry;


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
        LeftSpatula.setPosition(slotForwardPos+0.13);

        //Bring the slot closer to the backdrop and orients it the right way, so the pixel can fall out
    }
    public void slotForwardAuto() {
        RightSpatula.setPosition(1-slotForwardPosAuton);
        LeftSpatula.setPosition(slotForwardPosAuton);

        //Bring the slot closer to the backdrop and orients it the right way, so the pixel can fall out
    }

    public void slotForwardAutoMore() {
        RightSpatula.setPosition(1-(slotForwardPos+0.1));
        LeftSpatula.setPosition(slotForwardPos+0.1);

        //Bring the slot closer to the backdrop and orients it the right way, so the pixel can fall out
    }
    public void slot(double ang) {
        RightSpatula.setPosition(1-ang);
        LeftSpatula.setPosition(ang);

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
        if (directioncommand.equals("forward")){
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



