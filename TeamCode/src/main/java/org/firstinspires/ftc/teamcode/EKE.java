package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class EKE extends LinearOpMode{

    //MOTORS
    DcMotorEx frontRight = (DcMotorEx) hardwareMap.dcMotor.get("frontRight");
    DcMotorEx backRight = (DcMotorEx) hardwareMap.dcMotor.get("backRight");
    DcMotorEx frontLeft = (DcMotorEx) hardwareMap.dcMotor.get("frontLeft");
    DcMotorEx backLeft = (DcMotorEx) hardwareMap.dcMotor.get("backLeft");


    @Override
    public void runOpMode() {

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(0.5);
        backRight.setPower(-0.5);

        sleep(2000);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }
}
