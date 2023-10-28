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

    Slides slidelift= new Slides(hwmap);

    @Override
    public void runOpMode() {

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()){

            if (gamepad2.a){
                slidelift.slide('a');
                telemetry.addData("Button", "a");

            }
            else if (gamepad2.x){
                slidelift.slide('x' );
                telemetry.addData("Button", "x");

            }
            else if (gamepad2.y){
                slidelift.slide('y');
                telemetry.addData("Button", "y");

            }



        }
    }




}
