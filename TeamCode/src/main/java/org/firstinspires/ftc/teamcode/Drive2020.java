package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Drive2020 extends LinearOpMode {
    public DcMotor frontLeft, backLeft, frontRight, backRight, intake, feeder;
    public Servo grabber, wobbleRotate;

    PushBot2020 robot = new PushBot2020();

    public void runOpMode() {
       robot.init(hardwareMap);

        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;
        intake = robot.intake;
        feeder = robot.feeder;
        grabber = robot.grabber;
        wobbleRotate = robot.wobbleRotate;

//test
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

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
            frontLeft.setPower(drive);
            frontRight.setPower(drive);
            backRight.setPower(drive);
            backLeft.setPower(drive);

            //Turn left or right
            frontLeft.setPower(-turn);
            frontRight.setPower(turn);
            backLeft.setPower(-turn);
            backRight.setPower(turn);

            if (gamepad1.right_bumper == true) {
                intake.setPower(1);
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
            if (gamepad1.a == true){
                wobbleRotate.setPosition(0);
            }

        }
        //telemetry.addData("Current Right Motor Power", robot.frontRight.getPower());
    }
}

