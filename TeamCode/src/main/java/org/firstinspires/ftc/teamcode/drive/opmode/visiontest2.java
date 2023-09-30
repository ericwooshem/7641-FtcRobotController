package org.firstinspires.ftc.teamcode.drive.opmode;

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
    Mat mat = new Mat();
    public enum Location {
        Leftt,
        Rightt,
        Centerr,
        Not_Found
    }
    private Location location;
    static final Rect left = new Rect(
            new Point(50, 35),
            new Point(150, 75));

    static final Rect right = new Rect(
            new Point(200, 35),
            new Point(250, 75));

    static final Rect center = new Rect(
            new Point(300, 35),
            new Point(450, 75));

    static double Percent_Color_Threshhold = 0.4;

    public visiontest2(Telemetry t) {
        telemetry = t;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(23, 50, 70);
        Scalar highHSV = new Scalar(32, 255, 255);

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
    }

