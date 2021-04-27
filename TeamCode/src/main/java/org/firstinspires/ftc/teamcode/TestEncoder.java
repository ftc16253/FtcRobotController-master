package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TestEncoder extends LinearOpMode {
    public DcMotor frontLeft, backLeft, frontRight, backRight, encoderTest;
    PushBot2020 robot = new PushBot2020();

    public void runOpMode() {

        robot.init(hardwareMap, false);
        encoderTest  = robot.encoderTest;
        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double test = gamepad1.left_stick_x;

            //Forward or backwards
            frontLeft.setPower(drive);
            frontRight.setPower(drive);
            backRight.setPower(drive);
            backLeft.setPower(drive);

            encoderTest.setPower(test);

            //Turn left or right
            frontLeft.setPower(-turn);
            frontRight.setPower(turn);
            backLeft.setPower(-turn);
            backRight.setPower(turn);

            telemetry.addData("Left Motor Position", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position", robot.frontRight.getCurrentPosition());
            telemetry.addData("Encoder Position", encoderTest.getCurrentPosition());
            telemetry.update();

        }

    }
}
