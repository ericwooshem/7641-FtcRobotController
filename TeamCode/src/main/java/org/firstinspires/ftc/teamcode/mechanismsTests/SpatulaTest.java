package org.firstinspires.ftc.teamcode.mechanismsTests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Spatula;

public class SpatulaTest extends LinearOpMode {
    HardwareMap hwmap = hardwareMap;
    Spatula spatula = new Spatula(hwmap);

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();

        while(opModeIsActive()){
            if (gamepad2.dpad_up){
                spatula.wheelCommands("forward");
                telemetry.addData("Button", "dpad_up");

            }
            else if (gamepad2.dpad_down){
                spatula.wheelCommands("backward");
                telemetry.addData("Button", "dpad_down");


            }
            else if(gamepad2.a){
                spatula.wheelCommands("stop");
                telemetry.addData("Button", "a");

            }
            else if (gamepad2.y){
                spatula.spatulaCommand("slotForward");
                telemetry.addData("Button", "y");

            }
            else if(gamepad2.x){
                spatula.spatulaCommand("slotReset");
                telemetry.addData("Button", "x");

            }



        }
    }

    /*  Write the code while debugging and testing the mechanism in this file */

}
