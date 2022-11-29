package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class EKO extends LinearOpMode
{


    @Override
    public void runOpMode() throws InterruptedException{

        //Create the thinghy
        //Thing's name in code = get (whatever the thing's name is in the controller

        //MOTORS
        DcMotorEx frontRight;
        frontRight = (DcMotorEx) hardwareMap.dcMotor.get("frontRight");
        DcMotorEx backRight;
        backRight = (DcMotorEx) hardwareMap.dcMotor.get("backRight");
        DcMotorEx frontLeft;
        frontLeft = (DcMotorEx) hardwareMap.dcMotor.get("frontLeft");
        DcMotorEx backLeft;
        backLeft= (DcMotorEx) hardwareMap.dcMotor.get("backLeft");

        //LINEAR SLIDE MOTOR
        DcMotorEx linearSlide;
        linearSlide = (DcMotorEx) hardwareMap.dcMotor.get("linearSlide");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //INTAKE SERVO
        Servo claw = hardwareMap.servo.get("claw");


        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        if (isStopRequested()) return;

        while(opModeIsActive()){
            //Left Stick = Left n Right , Forward n Backwards
            double x = -gamepad1.left_stick_x * 1;
            double y = gamepad1.left_stick_y;
            //Right Stick = Rotate
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            double frontRightPower = (4*y/5 - 4*x/5 - rx*.8) /denominator;
            double backRightPower = (4*y/5 + 4*x/5 - rx*.8) /denominator;
            double frontLeftPower = (4*y/5 + 4*x/5 + rx*.8) /denominator;
            double backLeftPower =(4*y/5 - 4*x/5 + rx*.8) /denominator;

            //Set up how fast you want the robot to move (.1-1.0)

            frontRight.setPower(frontRightPower * 0.7 );
            backRight.setPower(backRightPower * 0.7 );
            frontLeft.setPower(frontLeftPower * 0.7 );
            backLeft.setPower(backLeftPower * 0.7 );



            //LINEAR SLIDE
            if(gamepad1.left_trigger > .4){
                linearSlide.setPower(.6);
            }

            else if (gamepad1.right_trigger > .4){
                linearSlide.setPower(-.6);
            }

            else{
                linearSlide.setPower(0);
            }

            if(gamepad1.cross){
                claw.setPosition(0);
            } else if (gamepad1.circle) {
                claw.setPosition(.25);
            }
        }
    }
}
