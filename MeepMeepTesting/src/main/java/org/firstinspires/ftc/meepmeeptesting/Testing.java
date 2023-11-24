package org.firstinspires.ftc.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;

public class Testing extends MeepMeepTestingTemplate{
    Testing(MeepMeep meepMeep){
        super(meepMeep);

    }
    public void moveOnPath() {
        myDefaultBot = this.buildBot(meepMeep);
        this.myBot = myDefaultBot.followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.PI))//new Pose2d(-70, 20, 0))

//                                .lineToLinearHeading(new Pose2d(-33.0, 0.0, Math.toRadians(90))) // Left
//                                .lineToLinearHeading(new Pose2d(-33.0, -9.0, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(-33.0, 0.0, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(-48, 18, Math.toRadians(-90)))
//                                .lineToLinearHeading(new Pose2d(-48, 20, Math.toRadians(-90)))

//                                .lineToLinearHeading(new Pose2d(-33.0, 6, Math.toRadians(180))) // Right
//                                .lineToLinearHeading(new Pose2d(-48, 18, Math.toRadians(-90)))
//                                .lineToLinearHeading(new Pose2d(-48, 20, Math.toRadians(-90)))

                                .lineToLinearHeading(new Pose2d(-39, 0.0, Math.toRadians(180))) // Center
                                .lineToLinearHeading(new Pose2d(-48, 18, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(-48, 20, Math.toRadians(-90)))
                                .build()
        );


    }
}
/*

                         (+x)
                          |
                          |
                          |
                          |
          (+y) <----------|----------> (-y)
                          |
                          |
                          |
                          |
                         (-x)
 */