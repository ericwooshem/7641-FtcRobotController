package org.firstinspires.ftc.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;

public class BlueRightAutonOption2 extends MeepMeepTestingTemplate{
    BlueRightAutonOption2(MeepMeep meepMeep){
        super(meepMeep);

    }
    public void moveOnPath() {
        myDefaultBot = this.buildBot(meepMeep);
        this.myBot = myDefaultBot.followTrajectorySequence(drive ->
                drive.trajectorySequenceBuilder(new Pose2d(-70, -34, 0))
                        .forward(62)
                        .strafeRight(24)
                        .strafeLeft(96)
                        .back(24)
                        .strafeLeft(10)
                        .turn(Math.toRadians(270))
                        .build()
        );


    }
}
