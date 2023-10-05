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
        System.out.println("Number: " + s);
        if("1".equals(s)){
            System.out.println("In option 1");

            return (new BlueRightAutonOption1(meepMeep));
        }
        else if ("2".equals(s)){
            return (new BlueRightAutonOption2(meepMeep));
        }


        return null;


    }

    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);
        // Decide which template to run


        MeepMeepTestingTemplate m = MeepMeepTestingDriver.mapToClass(args[0], meepMeep);
        System.out.println(args[0]);

        m.moveOnPath();

        // Run that template

        // Set field image
        Image img = null;

        RoadRunnerBotEntity myBot = m.getRobot();
        System.out.println(myBot);
        try {
            img = ImageIO.read(new File("/Users/rahulkalra/AndroidStudioProjects/FtcRobotController/road-runner-quickstart/MeepTEst/lib/src/main/java/org/firstinspires/ftc/lib/field-2023-official.png"));
        } catch (IOException e) {
        }
        meepMeep.setBackground(img)
                .addEntity(myBot)
                .start();
    }
}
