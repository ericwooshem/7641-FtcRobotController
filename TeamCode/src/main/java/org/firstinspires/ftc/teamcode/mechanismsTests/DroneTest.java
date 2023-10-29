package org.firstinspires.ftc.teamcode.mechanismsTests;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.mechanisms.Drone;

@TeleOp(name="DroneTest")
public class DroneTest extends LinearOpMode {
    HardwareMap hwmap = hardwareMap;

    Drone drone = new Drone(hwmap);

    @Override
    public void runOpMode() {

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()){

            if (gamepad2.a){
                drone.shooter("open");
                telemetry.addData("Button", "a");
            }




        }
    }


    /*  Write the code while debugging and testing the mechanism in this file */

}
