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
    public enum Location {
        Leftt,
        Rightt,
        Centerr,
        Not_Found
    }
    private Location location;
    static final Rect left = new Rect(
            new Point(1, 1),
            new Point(213, 479));

    static final Rect right = new Rect(
            new Point(214, 1),
            new Point(426, 479));

    static final Rect center = new Rect(
            new Point(427, 1),
            new Point(637, 479));

    static double Percent_Color_Threshhold = 0.4;

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
            lowHSV = new Scalar(-10, 50, 50);
            highHSV = new Scalar(10, 255, 255);
            telemetry.addData("e",0);
        }
        else if(detectedColor.equals("blue")){
            lowHSV = new Scalar(90, 50, 50);
            highHSV = new Scalar(110, 255, 255);
            telemetry.addData("e",1);
        }
        else{ //default to red
            lowHSV = new Scalar(-10, 50, 50);
            highHSV = new Scalar(10, 255, 255);
            telemetry.addData("e",2);
        }



        Core.inRange(mat, lowHSV, highHSV, mat);
        Mat leftside = mat.submat(left);
        Mat rightside = mat.submat(right);
        Mat centerside = mat.submat(center);

        double leftValue = Core.sumElems(leftside).val[0] / left.area() / 255;
        double rightValue = Core.sumElems(rightside).val[0] / right.area() / 255;
        double centerValue = Core.sumElems(centerside).val[0] / right.area() / 255;


        leftside.release();
        rightside.release();
        telemetry.addData("Left raw value", (int) Core.sumElems(leftside).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(rightside).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");

        boolean stoneleft = leftValue > Percent_Color_Threshhold;
        boolean stoneright = rightValue > Percent_Color_Threshhold;
        boolean stonecenter = centerValue > Percent_Color_Threshhold;

        if (stoneleft) {
            //left
            location = Location.Leftt;
        }
        else if (stonecenter) {
            //center
            location = Location.Centerr;
        }
        else if(stoneright){
                //right
            location = Location.Rightt;
        }
        else{
          location = Location.Not_Found;
        }
        telemetry.addData("location", location);
        telemetry.update();

Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

            Scalar colorstone = new Scalar (255,0,0);
            Scalar colorSkystone = new Scalar (0,255,0);

            Imgproc.rectangle(mat, left, location == Location.Leftt? colorSkystone: colorstone);
            Imgproc.rectangle(mat, right, location == Location.Rightt? colorSkystone: colorstone);
            Imgproc.rectangle(mat, center, location == Location.Centerr? colorSkystone: colorstone);

            return mat;
        }
        public Location getLocation() {
            return location;
        }

        public void setDetectedColor(String color){
            this.detectedColor = color;
        }

    }

