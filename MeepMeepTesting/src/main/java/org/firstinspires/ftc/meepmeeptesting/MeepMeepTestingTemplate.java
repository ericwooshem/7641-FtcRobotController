package org.firstinspires.ftc.meepmeeptesting;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


/**
 * Executed as
 * MeepMeepTestingTemplate nameoftemplate
 *
 * e.g
 * MeepMeepTestingTemplate BlueRightAutnOption1
 *
 */
class MeepMeepTestingTemplate {
    MeepMeepTestingTemplate(){
        this.myBot = null;
    }


    MeepMeepTestingTemplate(MeepMeep meepMeep){
        this.meepMeep = meepMeep;
    }
    protected MeepMeep meepMeep;
    protected RoadRunnerBotEntity myBot;
    protected DefaultBotBuilder myDefaultBot;

    public RoadRunnerBotEntity getRobot(){
        System.out.println(this.myBot);
        return this.myBot;
    }
    public DefaultBotBuilder buildBot(MeepMeep meepMeep){
        this.myDefaultBot =  new DefaultBotBuilder(meepMeep)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark());
        return this.myDefaultBot;
    }
    public  void moveOnPath() {

    }
}