package org.firstinspires.ftc.teamcode.mechanismsTests;

import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@TeleOp(name = "IntakeTest")
public class IntakeTest extends LinearOpMode{
    HardwareMap hwmap = hardwareMap;

    Intake intake = new Intake(hwmap);

    @Override
    public void runOpMode() {

        waitForStart();

        while(opModeIsActive()){
          /*  if(gamepad1.dpad_down){
                intake.spin("forward");
            }
            else if(gamepad1.dpad_up){
                intake.spin("reverse");
            }
            else{
                intake.spin("stop");//this can be anything but "forward" and "reverse"
            }*/
        }
    }

    /*  Write the code while debugging and testing the mechanism in this file */
}
