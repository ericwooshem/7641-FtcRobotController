package org.firstinspires.ftc.meepmeeptesting;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class MeepMeepTestingDriver {



    public  static   MeepMeepTestingTemplate mapToClass (String s, MeepMeep meepMeep){

        switch (s){
            case "1":
                return (new BlueRightAutonOption1(meepMeep));
            case "2":
                return (new BlueRightAutonOption2(meepMeep));
            case "3":
                return (new BlueLeftAutonOption1(meepMeep));
            case "4":
                return (new BlueLeftAutonOption2(meepMeep));
            case "5":
                return (new Testing(meepMeep));
        }


        return null;


    }

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);


        MeepMeepTestingTemplate m = MeepMeepTestingDriver.mapToClass(args[1], meepMeep);
        m.moveOnPath();


        Image img = null;

        RoadRunnerBotEntity myBot = m.getRobot();
        try {
            String pathname = "/Users/rahulkalra/AndroidStudioProjects/FtcRobotController/road-runner-quickstart/MeepTEst/lib/src/main/java/org/firstinspires/ftc/lib/field-2023-official.png";
            //String pathname = "/Users/Oso/Documents/GitHub/7641-FtcRobotController/MeepMeepTesting/src/main/java/org/firstinspires/ftc/meepmeeptesting/field-2023-official.png";
            img = ImageIO.read(new File(pathname));
        } catch (IOException e) {
        }
        meepMeep.setBackground(img)
                .addEntity(myBot)
                .start();
    }
}
