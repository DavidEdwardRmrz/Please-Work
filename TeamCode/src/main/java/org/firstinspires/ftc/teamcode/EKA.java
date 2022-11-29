package org.firstinspires.ftc.teamcode;

import android.bluetooth.BluetoothA2dp;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class EKA extends LinearOpMode {


    String location = "secret";
    String color = "";
    long waitTime = 0;

    //Motors
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    //Color Sensors
    // private ColorSensor JOJO;
    private ColorSensor JOJO;


    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 383.6;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 3.937;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double SPEED = .4;


    @Override
    public void runOpMode() {

        //Motors
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        //Color Sensors
        //JOJO = hardwareMap.colorSensor.get("JOJO");
        JOJO = hardwareMap.colorSensor.get("JOJO");


        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (!opModeIsActive()) {

            if (gamepad1.dpad_up) {
                waitTime = waitTime + 1;
                sleep(200);
            } else if (gamepad1.dpad_down) {
                waitTime = waitTime - 1;
                sleep(200);
            }

            if (gamepad1.cross) {
                location = "right";
                color = "blue";
            } else if (gamepad1.square) {
                location = "left";
                color = "blue";
            } else if (gamepad1.circle) {
                location = "left";
                color = "red";
            } else if (gamepad1.triangle) {
                location = "right";
                color = "red";
            } else if (gamepad1.dpad_right) {
                location = "secret";
                color = "";
            }
            telemetry.addData("running: " + location, color);
            telemetry.addData("Delay in Sec:", waitTime);
            telemetry.update();


        }


        waitForStart();


        //different variations
        if(location == "left" && color == "blue"){

            encoderStrafe(.5,-15,10);

            int Red = JOJO.red();
            int Green = JOJO.green();
            int Blue = JOJO.blue();
            sleep(3000);
            if (1 == 1) {
                if (Red > Green && Red > Blue) {

                    telemetry.addData("Red", 0);
                    encoderStrafe(.4, -30, 10);
                    sleep(1000);//arm going up would be here, mokneys are mid
                    encoderStrafe(.4, 12, 10);
                    driveForward(.4, -10, 10);
                    encoderStrafe(.4, 16, 10);

                } else if (Green > Red && Green > Blue) {

                    telemetry.addData("Green", 1);
                    encoderStrafe(.4, -30, 10);
                    sleep(1000);//arm going up would be here
                    encoderStrafe(.4, 25, 10);

                } else if (Blue > Red && Blue > Green) {

                    telemetry.addData("Blue", 2);
                    encoderStrafe(.4, -30, 10);
                    sleep(1000);//arm going up would be here
                    encoderStrafe(.4, 12, 10);
                    //  driveForward(.4, 10, 10);
                    encoderStrafe(.4, 30, 10);
                }
                telemetry.addData("nothing detected", 3);

            }
        }

        else if (location == "left" && color == "red"){

            encoderStrafe(.2,-22.5,10);
            int Red = JOJO.red();
            int Green = JOJO.green();
            int Blue = JOJO.blue();
            sleep(3000);

            if (Red > Green && Red > Blue) {

                telemetry.addData("Red", 0);
                encoderStrafe(.5,-30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,15,10);
                //driveForward(.5,-10,10);
                backRight.setPower(.5);
                backLeft.setPower(.4);
                frontRight.setPower(.5);
                frontLeft.setPower(.4);
                sleep(1200);
                encoderStrafe(.5,20,10);

            }else if (Green > Red && Green > Blue) {

                telemetry.addData("Green", 1);
                encoderStrafe(.5,-30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,25,10);

            }else if (Blue > Red && Blue > Green) {

                telemetry.addData("Blue", 2);
                encoderStrafe(.5,-30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,12,10);
                driveForward(.5,10,10);
                encoderStrafe(.5,13,10);
            }
        }
        else if (location == "right" && color == "blue"){

            encoderStrafe(.2,22.5,10);

            int Red = JOJO.red();
            int Green = JOJO.green();
            int Blue = JOJO.blue();
            sleep(3000);
            if (Red > Green && Red > Blue) {

                telemetry.addData("Red", 0);
                turn(10,  3, -4.5);
                encoderStrafe(.3,49.9,10);
                sleep(1000);//arm going up would be here IF WE HAD ONE
                encoderStrafe(.3,-14,10);
                sleep(500);
                backRight.setPower(.5);
                backLeft.setPower(.4);
                frontRight.setPower(-.5);//idk which one supposed to be negatives it needa go forward
                frontLeft.setPower(-.4);
                sleep(1200);
                //turn(10,  -3, 4.5);
                //sleep(500);
                encoderStrafe(.5,-20,10);

            }else if (Green > Red && Green > Blue) {

                telemetry.addData("Green", 1);
                turn(10,  3, -4.5);
                encoderStrafe(.3,49.9,10);
                sleep(1000);//arm going up would be here IF WE HAD ONE
                encoderStrafe(.3,-14,10);
                sleep(500);

            }else if (Blue > Red && Blue > Green) {

                telemetry.addData("Blue", 2);
                turn(10,  3, -4.5);
                encoderStrafe(.5,49.9,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.3,-14,10);
                sleep(500);
                backRight.setPower(-.6);
                backLeft.setPower(-.4);
                frontRight.setPower(.6);//idk which one supposed to be negatives so it goes straight jus fix it must gpo backward
                frontLeft.setPower(.4);
                sleep(1200);
                //turn(10,  -3, 4.5);
                //sleep(500);
                encoderStrafe(.5,-20,10);

            }

        }
        else if (location == "right" && color == "red"){

            encoderStrafe(.5,15,10);

            int Red = JOJO.red();
            int Green = JOJO.green();
            int Blue = JOJO.blue();

            if (Red > Green && Red > Blue) {

                telemetry.addData("Red", 0);
                encoderStrafe(.5,30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,-12,10);
                driveForward(.5,10,10);
                encoderStrafe(.5,-13,10);

            }else if (Green > Red && Green > Blue) {

                telemetry.addData("Green", 1);
                encoderStrafe(.5,30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,-25,10);

            }else if (Blue > Red && Blue > Green) {

                telemetry.addData("Blue", 2);
                encoderStrafe(.5,30,10);
                sleep(1000);//arm going up would be here
                encoderStrafe(.5,-12,10);
                driveForward(.5,-10,10);
                encoderStrafe(.5,-13,10);
            }

        }
        else if (location == "secret"){
            driveForward(.4,.5,10);
        }



    }

    public void driveForward(double speed, double dist, double timeout) {
        encoderDrive(speed, dist, dist, timeout);
    }

    public void turn(double timeout, double angleL, double angleR) {
        encoderDrive(.4, angleL, angleR, timeout);
    }

    public void encoderDrive(double speed, double distL, double distR, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;


        if (opModeIsActive()) {


            newLeftTarget = frontLeft.getCurrentPosition() + (int) (distL * COUNTS_PER_INCH);
            newRightTarget = frontRight.getCurrentPosition() + (int) (distR * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(-newLeftTarget);
            backLeft.setTargetPosition(-newLeftTarget);
            frontRight.setTargetPosition(-newRightTarget);
            backRight.setTargetPosition(newRightTarget);


            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));


            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy()) &&
                    (frontLeft.isBusy() && frontRight.isBusy())) {


                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        backLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition(),
                        backRight.getCurrentPosition());
                telemetry.update();
            }


            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);


            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderStrafe(double speed, double dist, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;


        if (opModeIsActive()) {


            newLeftTarget = frontLeft.getCurrentPosition() + (int) (dist * COUNTS_PER_INCH);
            newRightTarget = frontRight.getCurrentPosition() + (int) (dist * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(-newLeftTarget);
            backLeft.setTargetPosition(newLeftTarget);
            frontRight.setTargetPosition(-newRightTarget);
            backRight.setTargetPosition(newRightTarget);


            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));



            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy()) &&
                    (frontLeft.isBusy() && frontRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        backLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition(),
                        backRight.getCurrentPosition());
                telemetry.update();
            }


            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);

            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

}
