package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp

public class Drive2020 extends LinearOpMode {
    public DcMotor frontLeft, backLeft, frontRight, backRight, intake, feeder, shooterFront;//, shooterBack;
    public Servo grabber, wobbleRotate;

    private double kP = 9000000.0;
    private double kI = 0.0;
    private double kD = 10000.0;

    private double integral = 0.0;
    private double derivative = 0.0;

    private double motorOut = 0.0;
    private final double fTarget = 3.0e-6;  //1.2e-6      2.5e-6
    private double fVelocity = 0.0;
    private double fError = 0.0;
    private double fLastError = 0.0;

    private int fEncoder = 0;
    private int fLastEncoder = 0;

    private long fVelocityTime = 0;
    private long fLastVelocityTime = 0;

    PushBot2020 robot = new PushBot2020();

    private void calcVelocity() {
        fVelocityTime = System.nanoTime();
        fEncoder = shooterFront.getCurrentPosition();
        fVelocity = (double) (fEncoder - fLastEncoder) / (fVelocityTime - fLastVelocityTime);

        fLastEncoder = fEncoder;
        fLastVelocityTime = fVelocityTime;
    }

    private void calculatePID(double shooter_power) {
        fVelocityTime = System.nanoTime();
        fEncoder = shooterFront.getCurrentPosition();
        fVelocity = (double) (fEncoder - fLastEncoder) / (fVelocityTime - fLastVelocityTime);
        fError = fTarget - fVelocity;

        integral += fError;
        if (fError == 0) {
            integral = 0;
        }

        if (Math.abs(fError) > 50) {
            integral = 0;
        }

        derivative = fError - fLastError;

        fLastError = fError;
        fLastEncoder = fEncoder;
        fLastVelocityTime = fVelocityTime;

        motorOut = (kP * fError) + (kI * integral) + (kD * derivative);

        motorOut = Range.clip(motorOut, 0.0, shooter_power);

        //Log.wtf(TAG, String.valueOf(fError));

        setFPower(motorOut);
    }

    private void setFPower(double power) {
        shooterFront.setPower(power);
    }

    public void runOpMode() {
       robot.init(hardwareMap, false);

        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;
        intake = robot.intake;
        feeder = robot.feeder;
        grabber = robot.grabber;
        wobbleRotate = robot.wobbleRotate;
        shooterFront = robot.shooterFront;
        ///shooterBack = robot.shooterBack;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //double drive = gamepad1.left_stick_y;
            //double turn = gamepad1.right_stick_x;

            // Drive forward or backward
            /*if (drive == 1) {
                frontLeft.setPower(.5);
                frontRight.setPower(.5);
                backRight.setPower(.5);
                backLeft.setPower(.5);
                telemetry.addData("status", "initilized");
                telemetry.update();
            } else if (drive == -1) {
                frontRight.setPower(-.5);
                frontLeft.setPower(-.5);
                backRight.setPower(-.5);
                backLeft.setPower(-.5);
            } else {
                frontLeft.setPower(drive);
                frontRight.setPower(drive);
                backRight.setPower(drive);
                backLeft.setPower(drive);
            }
*/
            //Forward or backwards
            frontLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.right_stick_y);
            backRight.setPower(gamepad1.right_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);

            //Turn left or right
            /*frontLeft.setPower(-turn);
            frontRight.setPower(turn);
            backLeft.setPower(-turn);
            backRight.setPower(turn);*/

            if (gamepad1.right_bumper == true) {
                //intake.setPower(1);
                feeder.setPower(1);
            }else{
                intake.setPower(0);
                feeder.setPower(0);
            }

            //Right trigger - wobble rotator goes down
            if (gamepad1.right_trigger != 0){
                telemetry.addData("status","right trigger pressed");
                telemetry.update();
                wobbleRotate.setPosition(.65);
            }

            //Left trigger - claw closes and wobble rotator goes up
            if (gamepad1.left_trigger != 0) {
                telemetry.addData("status", "left trigger pressed");
                telemetry.update();

                //Close claw first
                grabber.setPosition(0);
                sleep(500);
                //This is for the two servos
                wobbleRotate.setPosition(.35);
            }

            //left bumper - wobble rotator goes down and claw opens
            if (gamepad1.left_bumper == true){
                wobbleRotate.setPosition(.55);
                sleep(1000);
                grabber.setPosition(.6);
            }

            //Button A - wobble arm to initial position
            /*if (gamepad1.a == true){
                wobbleRotate.setPosition(0);
            }*/

            //Button X - turns on the shooter motors
            if (gamepad1.x == true){
                calculatePID(1.0);
                sleep(100);
                //shooterBack.setPower(1);
            }
            else if (gamepad1.a) {
                shooterFront.setPower(0);
                //shooterBack.setPower(0);
            }



            telemetry.addData("encoder: ", fEncoder);
            telemetry.update();

        }
        //telemetry.addData("Current Right Motor Power", robot.frontRight.getPower());
    }
}

