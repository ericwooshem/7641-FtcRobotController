package org.firstinspires.ftc.teamcode.mechanismsTests;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Slides;

@TeleOp(name = "SlidesTest")
public class SlidesTest extends LinearOpMode {
    HardwareMap hwmap = hardwareMap;

    Slides slidelift= new Slides(hwmap); // I think you should put hardwareMap directly into here

    @Override
    public void runOpMode() {

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()){

            if (gamepad2.x){
                slidelift.slideCommands(0,0);
                telemetry.addData("Button", "x");

            }
            else if (gamepad2.a){
                slidelift.slideCommands(1,0 );
                telemetry.addData("Button", "a");

            }
            else if (gamepad2.b){
                slidelift.slideCommands(2,0);
                telemetry.addData("Button", "b");
            }
            else if (gamepad2.y){
                slidelift.slideCommands(3,0);
                telemetry.addData("Button", "y");
            }



        }
    }




}
