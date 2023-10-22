package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


import java.util.List;
@TeleOp
public class Teleoptest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    }

    DcMotor leftSlidesMotor = hardwareMap.dcMotor.get("leftSlidesMotor");
    DcMotor rightSlidesMotor = hardwareMap.dcMotor.get("rightSlidesMotor");


    {
        leftSlidesMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSlidesMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        double x= gamepad1.right_stick_x;
        double y = gamepad1.left_stick_y;
        rightSlidesMotor.setPower(x);
        leftSlidesMotor.setPower(y);
    }
}
