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
    Mat mat = new Mat();
//    public enum Location {
//        Leftt,
//        Rightt,
//        Centerr,
//        Not_Found
//    }
    private int location;
    static final Rect left = new Rect(
            new Point(525, 200),
            new Point(575, 250));

//    static final Rect right = new Rect(
//            new Point(600, 1),
//            new Point(637, 150));

    static final Rect center = new Rect(
            new Point(150, 250),
            new Point(200, 300));

    static double Percent_Color_Threshhold = 0.15;

    public visiontest2(Telemetry t) {
        telemetry = t;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV;
        Scalar highHSV;

        telemetry.addData("Color detecting: ", detectedColor);
        if(detectedColor.equals("red")){
            lowHSV = new Scalar(-10, 25, 25);
            highHSV = new Scalar(10, 255, 255);
            telemetry.addData("e",0);
        }
        else if(detectedColor.equals("blue")){
            lowHSV = new Scalar(90, 75, 75);
            highHSV = new Scalar(110, 255, 255);
            telemetry.addData("e",1);
        }
        else{ //default to red
            lowHSV = new Scalar(-10, 25, 25);
            highHSV = new Scalar(10, 255, 255);
            telemetry.addData("e",2);
        }



        Core.inRange(mat, lowHSV, highHSV, mat);
        Mat leftside = mat.submat(left);
//        Mat rightside = mat.submat(right);
        Mat centerside = mat.submat(center);

        double leftValue = Core.sumElems(leftside).val[0] / left.area() / 255;
//        double rightValue = Core.sumElems(rightside).val[0] / right.area() / 255;
        double centerValue = Core.sumElems(centerside).val[0] / center.area() / 255;


        leftside.release();
        centerside.release();
//        rightside.release();
        telemetry.addData("Left raw value", (int) Core.sumElems(leftside).val[0]);
//        telemetry.addData("Right raw value", (int) Core.sumElems(rightside).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
//        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");

        boolean isLeft = leftValue > Percent_Color_Threshhold;
//        boolean stoneright = rightValue > Percent_Color_Threshhold;
        boolean isCenter = centerValue > Percent_Color_Threshhold;

        if (isLeft) {
            //left
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

            Imgproc.rectangle(mat, left, location == 1? colorSkystone: colorstone);
//            Imgproc.rectangle(mat, right, location == Location.Rightt? colorSkystone: colorstone);
            Imgproc.rectangle(mat, center, location == 2? colorSkystone: colorstone);


        telemetry.addData("v", getLocation());
            for(int i = 0;i<13;i++){
                for(int j = 0;j<10;j++){
                    Imgproc.rectangle(mat, new Rect(new Point(i*50, j*50), new Point((i+1)*50 , (j+1)*50)), new Scalar(255,255,255)) ;
                }
            }

            return mat;
        }
        public int getLocation() {
            return location;
        }

        public void setDetectedColor(String color){
            this.detectedColor = color;
        }

    }

