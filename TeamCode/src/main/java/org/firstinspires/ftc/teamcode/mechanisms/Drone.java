package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drone{

    private double Shoot;

    Servo Shooter;


    public Drone(HardwareMap HWMap) {
        Shooter = HWMap.get(Servo.class, "IntakeServo");
        Shooter.setPosition(0);

    }

    public void Shooter() {
        Shooter.setPosition(1);
    }







}
