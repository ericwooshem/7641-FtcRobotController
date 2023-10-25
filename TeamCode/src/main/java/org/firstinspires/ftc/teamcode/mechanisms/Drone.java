package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drone{

    private double Shoot;

    Servo Shooter;


    public Drone(HardwareMap HWMap) {
        Shooter = HWMap.get(Servo.class, "IntakeServo");
        Shooter.setPosition(0);
        //Holds rubber band in place after manually put in by someone and code starts

    }

    public void Shooter() {
        Shooter.setPosition(1);
        /*When a certain control is pressed (TBD), the servo will move, moving the rubber band,
        which will launch the servo*\
    }







}
