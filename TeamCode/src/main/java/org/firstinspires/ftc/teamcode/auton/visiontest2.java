package org.firstinspires.ftc.teamcode.auton;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class visiontest2 extends OpenCvPipeline {
    Telemetry telemetry;
    private String detectedColor;
    private String leftOrRight;
    Mat mat = new Mat();
    Mat mat2 = new Mat();

    static Rect right;
    static Rect center;
//    public enum Location {
//        Leftt,
//        Rightt,
//        Centerr,
//        Not_Found
//    }
    private int location;

    public void setrectangles(){
        if(leftOrRight.equals("redLeft")){
            right = new Rect(
                    new Point(50, 100), //400, 300
                    new Point(125, 150)); //450
            center = new Rect(
                    new Point(425, 50),
                    new Point(475, 150));
        }
        else if(leftOrRight.equals("redRight")){
            right = new Rect(
                    new Point(550, 50),
                    new Point(600, 150));
            center = new Rect(
                    new Point(150, 50),
                    new Point(200, 150));
        }
        else if(leftOrRight.equals("blueLeft")){
//            left = new Rect(
//                    new Point(350, 275),
//                    new Point(400, 325));
//            center = new Rect(
//                    new Point(25, 300),
//                    new Point(75, 350));
            right = new Rect(
                    new Point(0, 100),
                    new Point(50, 200));
            center = new Rect(
                    new Point(400, 50),
                    new Point(450, 150));
        }
        else{ //blue right rectangles default
//            left = new Rect(
//                    new Point(200, 250),
//                    new Point(625, 300));
//            center = new Rect(
//                    new Point(150, 300),
//                    new Point(500, 350));
            right = new Rect(
                    new Point(600, 100), //400, 300
                    new Point(625, 200)); //450
            center = new Rect(
                    new Point(200, 50),
                    new Point(250 ,100));
        }
    }
//    static final Rect left = new Rect(
//            new Point(575, 250),
//            new Point(625, 300));

//    static final Rect right = new Rect(
//            new Point(600, 1),
//            new Point(637, 150));
//
//    static final Rect center = new Rect(
//            new Point(150, 300),
//            new Point(200, 350));

    static double Percent_Color_Threshhold = 0.1;

    public visiontest2(Telemetry t) {
        telemetry = t;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Imgproc.cvtColor(input, mat2, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV;
        Scalar highHSV;
        Scalar lowHSV2 = null;
        Scalar highHSV2 = null;
        telemetry.addData("Color detecting: ", detectedColor);
        if(detectedColor.equals("red")){

            lowHSV = new Scalar(150, 30, 30);
            highHSV = new Scalar(179, 140, 255);
            lowHSV2 = new Scalar(0, 10, 10);
            highHSV2 = new Scalar(10, 140, 255); //190
            telemetry.addData("e",0);
        }
        else if(detectedColor.equals("blue")){
            lowHSV = new Scalar(90, 60, 60);
            highHSV = new Scalar(111, 255, 255);
            telemetry.addData("e",1);
        }
        else{ //default to red
            lowHSV = new Scalar(150, 10, 10);
            highHSV = new Scalar(179, 140, 190);
            lowHSV2 = new Scalar(0, 10, 10);
            highHSV2 = new Scalar(15, 140, 190);
            telemetry.addData("e",2);
        }


        Core.inRange(mat, lowHSV, highHSV, mat);
        
        if (detectedColor.equals("red")){
            Core.inRange(mat2, lowHSV2, highHSV2, mat2);
            Core.bitwise_or(mat, mat2, mat);
        }
        Mat rightside = mat.submat(right);
//        Mat rightside = mat.submat(right);
        Mat centerside = mat.submat(center);


        double rightValue = Core.sumElems(rightside).val[0] / right.area() / 255;
//        double rightValue = Core.sumElems(rightside).val[0] / right.area() / 255;
        double centerValue = Core.sumElems(centerside).val[0] / center.area() / 255;


        rightside.release();
        centerside.release();
//        rightside.release();
        telemetry.addData("RIght raw value", (int) Core.sumElems(rightside).val[0]);
//        telemetry.addData("Right raw value", (int) Core.sumElems(rightside).val[0]);
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
//        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");

        boolean isRight = rightValue > Percent_Color_Threshhold;
//        boolean stoneright = rightValue > Percent_Color_Threshhold;
        boolean isCenter = centerValue > Percent_Color_Threshhold;

        if (isRight) {
            //right
            location = 1;
        }
        else if (isCenter) {
            //center
            location = 2;
        }
        else{
          location = 3;
        }
        telemetry.addData("location", location);
        telemetry.update();

Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

            Scalar colorstone = new Scalar (255,0,0);
            Scalar colorSkystone = new Scalar (0,255,0);

        for(int i = 0;i<13;i++){
            for(int j = 0;j<10;j++){
                Imgproc.rectangle(mat, new Rect(new Point(i*50, j*50), new Point((i+1)*50 , (j+1)*50)), new Scalar(255,255,255)) ;
            }
        }

            Imgproc.rectangle(mat, right, location == 1? colorSkystone: colorstone);
//            Imgproc.rectangle(mat, right, location == Location.Rightt? colorSkystone: colorstone);
            Imgproc.rectangle(mat, center, location == 2? colorSkystone: colorstone);


        telemetry.addData("v", getLocation());


            return mat;
        }
        public int getLocation() {
            return location;
        }

        public void setDetectedColor(String color){
            this.detectedColor = color;
        }
    public void setside(String side){
        this.leftOrRight = side;
    }

    }

