package org.firstinspires.ftc.teamcode.drivetrainoptions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.mechanisms.Drone;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Slides;
import org.firstinspires.ftc.teamcode.mechanisms.Spatula;
import org.firstinspires.ftc.teamcode.mechanisms.Drive;



@TeleOp
public class MecanumArcadeDrive extends LinearOpMode {
    private double slideFineAdjust = 0; //use 1 for servo ramp testing
    private int setLine = 0;
    private double TA = 0;
    /*
        This function is where all of the drivetrain movement is.
        In specific the movement is like getting the joystick
        values and executing it to make it move in that direction
    */
    public void driveTrain(DcMotor frontLeftMotor, DcMotor backLeftMotor,
                           DcMotor frontRightMotor, DcMotor backRightMotor, Drive drive){
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        TA = drive.driveRobot( y, x, 0, rx, TA); // rice is cooking
//          Old reliable drive code
//        double demominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//        double frontLeftPower = (y + x + rx) / demominator;
//        double backLeftPower = (y - x + rx) / demominator;
//        double frontRightPower = (y - x - rx) / demominator;
//        double backRightPower = (y + x - rx) / demominator;
//
//        frontLeftMotor.setPower(frontLeftPower);
//        backLeftMotor.setPower(backLeftPower);
//        frontRightMotor.setPower(frontRightPower);
//        backRightMotor.setPower(backRightPower);
    }

    /* It checks the button press from the controller.
    It has a lot of if statements to check for the appropriate action from the controller.
    This function will have all of the mechanism modules called and use the functions from it. */
    public void checkButtonPress(Slides slideLift, Intake intake, Spatula spatula, Drone drone){
        if(gamepad1.dpad_up){
            intake.spin("forward");
        }
        else if(gamepad1.dpad_down){
            intake.spin("reverse");
        }
        else if(gamepad1.dpad_right){
            intake.spin("stop");
        }

        if (gamepad2.dpad_down){  //break else so if driver controls intake and slides at the same time it can do more than one
            intake.liftToLevel(0);
        }
        else if (gamepad2.dpad_left){   // Slides AND Intake lift, may change how intake lift happens. NEEDS testing my brain is dying
            intake.liftToLevel(1);
        }
        else if (gamepad2.dpad_up){
            intake.liftToLevel(2);
        }
        else if (gamepad2.dpad_right){
            intake.liftToLevel(3);
        }
        else if (gamepad2.left_trigger>0.2){
            intake.liftToLevel(4);
        }

        if (gamepad2.right_trigger > 0) {  // Slot
            spatula.slotForward();
        } else if (gamepad2.right_bumper) {
            spatula.slotReset();
        }

        if (gamepad2.left_stick_y > 0.2) {
            spatula.wheelCommands("backward");
        } else if (gamepad2.left_stick_y < -0.2) {
            spatula.wheelCommands("forward");
        }
        else if (gamepad2.left_stick_button) {
            spatula.wheelCommands("stop");
        }

        if (gamepad1.left_trigger>0) {
            drone.shooter("open");
        }

        if (gamepad2.a) {
            intake.spin("stop");
            setLine = 0;
        }
        else if (gamepad2.x) {
            intake.spin("stop");
            setLine = 1;

        }
        else if (gamepad2.y) {
            intake.spin("stop");
            setLine = 2;

        }
        else if (gamepad2.b) {
            intake.spin("stop");
            setLine = 3;

        }
        slideFineAdjust += gamepad2.right_stick_y*5*-1; // use /1000 for servo ramp testing // Fine adjust for slide position for dropping pixels. Added to set line positons.,
        slideLift.slideCommands(setLine,slideFineAdjust);

        //intake.liftToTest(slideFineAdjust); //uncomment to find servo positions
        //telemetry.addData("var",slideFineAdjust);
        //telemetry.update();
    }
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Slides slideLift = new Slides(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Spatula spatula  = new Spatula(hardwareMap);
        Drone drone = new Drone(hardwareMap);
        Drive drive = new Drive(hardwareMap);

        intake.liftToLevel(2);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            driveTrain(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, drive);
            checkButtonPress(slideLift, intake, spatula, drone);


        }

    }


}
