package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class Drive2020 extends LinearOpMode {
    public DcMotor frontLeft, backLeft, frontRight, backRight;
    //PushBot2020 robot = new PushBot2020();

    public void runOpMode() {
/*        robot.init(hardwareMap);

        frontLeft = robot.frontLeft;
        frontRight = robot.frontRight;
        backLeft = robot.backLeft;
        backRight = robot.backRight;  */
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);


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

            frontLeft.setPower(drive);
            frontRight.setPower(drive);
            backRight.setPower(drive);
            backLeft.setPower(drive);

/*            if (drive > 0) {
                frontLeft.setPower(1);
                frontRight.setPower(1);
                backRight.setPower(1);
                backLeft.setPower(1);
            } else if (drive < 0) {
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
                backRight.setPower(-1);
                backLeft.setPower(-1);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);
            }
*/
            telemetry.addData("drive=", drive);
            telemetry.update();

            //Turn left or right
            frontLeft.setPower(-turn);
            frontRight.setPower(turn);
            backLeft.setPower(-turn);
            backRight.setPower(turn);

        }
//telemetry.addData("Current Right Motor Power", robot.frontRight.getPower());
    }
}

