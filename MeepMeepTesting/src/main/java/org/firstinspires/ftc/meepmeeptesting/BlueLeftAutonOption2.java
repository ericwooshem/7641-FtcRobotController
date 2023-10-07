package org.firstinspires.ftc.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;

public class BlueLeftAutonOption2 extends MeepMeepTestingTemplate{

    BlueLeftAutonOption2(MeepMeep meepMeep){
        super(meepMeep);

    }
    public void moveOnPath() {
        myDefaultBot = this.buildBot(meepMeep);
        this.myBot = myDefaultBot.followTrajectorySequence(drive ->
                drive.trajectorySequenceBuilder(new Pose2d(-65, 15, 0))
                        .forward(35)
                        .back(35)
                        .strafeRight(60)
                        .forward(35)
                        .strafeRight(10)
                        .strafeLeft(35)
                        .back(25)
                        .strafeLeft(65)
                        .forward(20)
                        .turn(Math.toRadians(270))
                        .build()
        );


    }
}
