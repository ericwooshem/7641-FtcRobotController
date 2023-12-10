/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Drone;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Slides;
import org.firstinspires.ftc.teamcode.mechanisms.Spatula;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceRunner;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

/*
 * This OpMode illustrates the basics of AprilTag recognition and pose estimation,
 * including Java Builder structures for specifying Vision parameters.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
@Autonomous(name = "Red Left", group = "Meet 1 autons")

public class RedLeft extends LinearOpMode {

    private int tagID;
    private double tagX;
    private double tagY;
    private ElapsedTime timer = new ElapsedTime();
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    private visiontest2 vision = new visiontest2(telemetry);
    OpenCvWebcam camera;


    @Override
    public void runOpMode() {
        Slides slideLift = new Slides(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Spatula spatula  = new Spatula(hardwareMap);
        Servo PurpleClaw;
        PurpleClaw = hardwareMap.get(Servo.class, "PurpleServo");
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        int liftHeight = 600;
        TrajectorySequence centerPurple = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-48, 0, Math.toRadians(0))) // Center
                //.lineToLinearHeading(new Pose2d(-42, -1.0, Math.toRadians(0)))
                .build();
        TrajectorySequence rightPurple = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-25.0, -0.0, Math.toRadians(0))) // Right
                .turn(Math.toRadians(90))
                .lineToLinearHeading(new Pose2d(-25.0, 10.0, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(-25.0, 3.0, Math.toRadians(90)))
                //.lineToLinearHeading(new Pose2d(-25, 4.0, Math.toRadians(90)))
                .build();
        TrajectorySequence leftPurple = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(-39, -14, Math.toRadians(0))) // Right
                .build();

        TrajectorySequence leftStack = drive.trajectorySequenceBuilder(leftPurple.end())
                .lineToLinearHeading(new Pose2d(-32.0, -0.0, Math.toRadians(0))) // Right

                .lineToLinearHeading(new Pose2d(-50, 0.0, Math.toRadians(0)))


                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(-50, -22.0, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.15, () -> intake.liftToLevel(5))
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.wheelCommands("forward"))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> intake.spin("forward"))
                //.lineToLinearHeading(new Pose2d(-52, -22.0, Math.toRadians(-90)))
                .build();
        TrajectorySequence rightStack = drive.trajectorySequenceBuilder(rightPurple.end())
                .lineToLinearHeading(new Pose2d(-50, -3.0, Math.toRadians(0))) // Right


                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(-50, -22.0, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.15, () -> intake.liftToLevel(5))
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.wheelCommands("forward"))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> intake.spin("forward"))
                //.lineToLinearHeading(new Pose2d(-52, -24.0, Math.toRadians(-90)))
                .build();
        TrajectorySequence centerStack = drive.trajectorySequenceBuilder(centerPurple.end())
                .lineToLinearHeading(new Pose2d(-50, -0.0, Math.toRadians(0))) // Right


                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(-50, -22.0, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.15, () -> intake.liftToLevel(5))
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.wheelCommands("forward"))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> intake.spin("forward"))
                //.lineToLinearHeading(new Pose2d(-52, -24.0, Math.toRadians(-90)))
                .build();

        TrajectorySequence leftNoStack = drive.trajectorySequenceBuilder(leftPurple.end())
                .lineTo(new Vector2d(-53, -14.0))
                .lineToLinearHeading(new Pose2d(-53, 3.0, Math.toRadians(-90)))

                .build();
        TrajectorySequence rightNoStack = drive.trajectorySequenceBuilder(rightPurple.end())
                .lineToLinearHeading(new Pose2d(-53, -3.0, Math.toRadians(-90))) // Right

                .build();
        TrajectorySequence centerNoStack = drive.trajectorySequenceBuilder(centerPurple.end())
                .lineToLinearHeading(new Pose2d(-53, -0.0, Math.toRadians(-90))) // Right

                .build();
        TrajectorySequence centerWhite = drive.trajectorySequenceBuilder(centerStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-33, 84, Math.toRadians(-90))) // y = 84 old vals
                .lineToLinearHeading(new Pose2d(-33, 86, Math.toRadians(-90))) // y = 92
                .build();
        TrajectorySequence leftWhite = drive.trajectorySequenceBuilder(rightStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-21, 84, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-21, 86.0, Math.toRadians(-90)))
                .build();
        TrajectorySequence rightWhite = drive.trajectorySequenceBuilder(leftStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-33, 84, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-33, 86.0, Math.toRadians(-90)))
                .build();

        TrajectorySequence centerYellow = drive.trajectorySequenceBuilder(centerNoStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-26, 80, Math.toRadians(-90))) // y = 84 old vals
                .lineToLinearHeading(new Pose2d(-26, 86.5, Math.toRadians(-90))) // y = 92
                .build();
        TrajectorySequence leftYellow = drive.trajectorySequenceBuilder(leftNoStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-32.75, 80, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-32.75, 86.5, Math.toRadians(-90)))
                .build();
        TrajectorySequence rightYellow = drive.trajectorySequenceBuilder(rightNoStack.end())
                .lineToLinearHeading(new Pose2d(-53, 74.0, Math.toRadians(-90)))
                .addDisplacementMarker(() -> {
                    slideLift.slideRunToPos(liftHeight);
                    intake.spin("stop");
                })
                .lineToLinearHeading(new Pose2d(-21, 80, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-21, 86.5, Math.toRadians(-90)))
                .build();

        TrajectorySequence centerPark = drive.trajectorySequenceBuilder(centerYellow.end())
                .lineToLinearHeading(new Pose2d(-25, 80, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-50, 82, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.spatulaCommand("slotReset"))
                .build();
        TrajectorySequence rightPark = drive.trajectorySequenceBuilder(rightYellow.end())
                .lineToLinearHeading(new Pose2d(-21, 80, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-50, 82, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.spatulaCommand("slotReset"))
                .build();
        TrajectorySequence leftPark = drive.trajectorySequenceBuilder(leftYellow.end())
                .lineToLinearHeading(new Pose2d(-33, 80, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-50, 82, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> spatula.spatulaCommand("slotReset"))
                .build();

        intake.liftToLevel(5);
        PurpleClaw.setPosition(1);

        vision.setDetectedColor("red"); //red or blue, VERY IMPORTANT FOR VISION
        vision.setside("redLeft"); //left or right, VERY IMPORTANT FOR VISION
        vision.setrectangles();


        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.setPipeline(vision);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }


            @Override
            public void onError(int errorCode) {

            }
        });





        //compare x value to determine where to put pixel
        waitForStart();

        camera.closeCameraDevice();
        //auton.pray_to_the_auton_gods(true);
        //compare x value to determine where to put pixel
        waitForStart();
        camera.closeCameraDevice();

        if(vision.getLocation()==1){
            drive.followTrajectorySequence(leftPurple);
            PurpleClaw.setPosition(0.55);
            sleep(500);
            drive.followTrajectorySequence(leftNoStack);
            sleep(500);
            intake.spin("reverse");
            drive.followTrajectorySequence(leftYellow);
        } else if (vision.getLocation() == 2) {
            drive.followTrajectorySequence(centerPurple);
            PurpleClaw.setPosition(0.55);
            sleep(500);
            drive.followTrajectorySequence(centerNoStack);
            sleep(500);
            intake.spin("reverse");
            drive.followTrajectorySequence(centerYellow);
        } else if (vision.getLocation() == 3) {
            drive.followTrajectorySequence(rightPurple);
            PurpleClaw.setPosition(0.55);
            sleep(500);
            drive.followTrajectorySequence(rightNoStack);
            sleep(500);
            intake.spin("reverse");
            drive.followTrajectorySequence(rightYellow);
        }
//        drive.followTrajectorySequence(whiteStack);
//
//        if(vision.getLocation()==1){
//            drive.followTrajectorySequence(rightYellow);
//        } else if (vision.getLocation() == 2) {
//            drive.followTrajectorySequence(centerYellow);
//        } else if (vision.getLocation() == 3) {
//            drive.followTrajectorySequence(leftYellow);
//        }
        spatula.slotForwardAutoMore();
        sleep(1000);
        spatula.wheelCommands("backward");
        sleep(1000);

        if(vision.getLocation()==1){ // This is an untested white pixel cycle. Please be careful, this will likely destroy everything
            drive.followTrajectorySequence(leftPark);
        } else if (vision.getLocation() == 2) {
            drive.followTrajectorySequence(centerPark);
        } else if (vision.getLocation() == 3) {
            drive.followTrajectorySequence(rightPark);
        }
        spatula.wheelCommands("stop");
        slideLift.slideresetpls(true);
        slideLift.slideonoff(true);

        sleep(3000);
        slideLift.slideonoff(false);

        camera.closeCameraDevice();

        telemetry.addData("e",12);
        telemetry.update();


        //code for moving
        //more code for moving
        //code until the purple pixel placement

        initAprilTag();
        while(telemetryAprilTag()==0&&opModeIsActive()){//delete the left argument for it to show detection, currently it exits when it sees a valid apriltag
            telemetryAprilTag();
            telemetry.update();
        }

        visionPortal.close();
        /*while(opModeIsActive())
        {
            telemetry.addData("didnt fail", true);
            telemetry.update();
        }*/

    }   // end method runOpMode()

    /**
     * Initialize the AprilTag processor.
     */
    private void initAprilTag() {

        telemetry.addLine("0");
        telemetry.update();

        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()
                .setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)

                // ... these parameters are fx, fy, cx, cy.

                .build();
        telemetry.addLine("1");
        telemetry.update();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        telemetry.addLine("2");
        telemetry.update();

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();
        telemetry.addLine("3");
        telemetry.update();

        // Disable or re-enable the aprilTag processor at any time.
        //visionPortal.setProcessorEnabled(aprilTag, true);

    }   // end method initAprilTag()


    /**
     * Add telemetry about AprilTag detections.
     */
    private int telemetryAprilTag() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
                tagX = detection.ftcPose.x;
                tagY = detection.ftcPose.y;
                tagID = detection.id;

                return detection.id;
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
                return 0;
            }
        }   // end for() loop


        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");
        return 0;
    }   // end method telemetryAprilTag()

}   // end class
