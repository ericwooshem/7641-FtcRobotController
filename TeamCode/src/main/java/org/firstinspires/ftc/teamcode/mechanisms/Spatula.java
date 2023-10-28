package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Spatula {

    CRServo PixelSpinner;

    Servo RightSpatula;

    Servo LeftSpatula;

    public Spatula(HardwareMap HWMap) {
        PixelSpinner = HWMap.get(CRServo.class, "PixelServo");
        RightSpatula = HWMap.get(Servo.class, "RightSpatulaServo");
        RightSpatula.setPosition(0);
        LeftSpatula = HWMap.get(Servo.class, "LeftSpatulaServo");
        LeftSpatula.setPosition(0);
    }

    public void Spin(double power) { //-1<=x<=1
        PixelSpinner.setPower(power);
    }
    public void slotForward() {
        RightSpatula.setPosition(0.7);
        LeftSpatula.setPosition(0.7);
    }

    public void slotReset() {
        RightSpatula.setPosition(0);
        LeftSpatula.setPosition(0);
    }
}



