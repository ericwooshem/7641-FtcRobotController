package org.firstinspires.ftc.teamcode.auton;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Slides;

@TeleOp(name = "BlueRightAutonOption1")
public class BlueRightAutonOption1 extends LinearOpMode {

    Slides slidelift= new Slides();

    @Override
    public void runOpMode() {

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){
            double y = -(gamepad1.left_stick_y);
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double g2ry = gamepad2.right_stick_y;


            if (gamepad1.a){
                slidelift.slide('a', y,x,rx,g2ry);
            }
            else if (gamepad1.x){
                slidelift.slide('x', y,x,rx,g2ry);
            }
            else if (gamepad1.y){
                slidelift.slide('y', y,x,rx,g2ry);
            }



        }
    }




}
