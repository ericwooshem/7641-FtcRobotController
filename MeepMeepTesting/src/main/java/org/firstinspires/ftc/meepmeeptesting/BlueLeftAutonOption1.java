package org.firstinspires.ftc.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;

public class BlueLeftAutonOption1 extends MeepMeepTestingTemplate{
    BlueLeftAutonOption1(MeepMeep meepMeep){
        super(meepMeep);

    }
    public void moveOnPath() {
        myDefaultBot = this.buildBot(meepMeep);
        this.myBot = myDefaultBot.followTrajectorySequence(drive ->
                drive.trajectorySequenceBuilder(new Pose2d(-70, 20, 0))
                        .forward(59)
                        .strafeRight(76)
                        .strafeLeft(90)
                        .back(25)
                        .strafeLeft(13)
                        .turn(Math.toRadians(270))

                        .build()
        );


    }
}
