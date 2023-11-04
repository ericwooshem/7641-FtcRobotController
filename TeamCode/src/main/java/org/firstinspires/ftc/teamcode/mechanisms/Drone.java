package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drone {

    private double Shoot;

    Servo Shooter;


    public Drone(HardwareMap HWMap) {
        Shooter = HWMap.get(Servo.class, "DroneServo");
        Shooter.setPosition(1); }
        //Holds rubber band in place after manually put in by someone and code starts

    public void shooter(String position) {
        if (position.equals("open")) {
            Shooter.setPosition(0.6);
        } else if (position.equals("closed")) {
            Shooter.setPosition(1);
        }
        //When a certain control is pressed (TBD), the servo will move, moving the rubber band,
        //which will launch the drone
    }
  }

