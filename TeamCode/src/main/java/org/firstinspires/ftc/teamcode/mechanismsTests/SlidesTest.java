package org.firstinspires.ftc.teamcode.mechanismsTests;
import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.Slides;

@TeleOp(name = "BlueRightAutonOption1")
public class SlidesTest extends LinearOpMode {

    Slides slidelift= new Slides();

    @Override
    public void runOpMode() {

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()){
            telemetry.addData("Start", 1);

            double x = gamepad2.left_stick_x;
            double g2ry = gamepad2.right_stick_y;

            telemetry.addData("Gamepad 2 (x): " ,x);
            telemetry.addData("Gamepad 2 (g2ry): " ,g2ry);



            if (gamepad2.a){
                slidelift.slide('a', x,g2ry);
                telemetry.addData("Button", "a");

            }
            else if (gamepad2.x){
                slidelift.slide('x' ,x,g2ry);
                telemetry.addData("Button", "x");

            }
            else if (gamepad2.y){
                slidelift.slide('y' ,x,g2ry);
                telemetry.addData("Button", "y");

            }



        }
    }




}
