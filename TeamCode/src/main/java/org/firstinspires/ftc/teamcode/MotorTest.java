package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="MotorTest", group="Linear Opmode")
public class MotorTest extends LinearOpMode {
    PushBot2020 robot = new PushBot2020();

    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.frontLeft.setPower(1);
            robot.frontRight.setPower(1);
            robot.backRight.setPower(1);
            robot.backLeft.setPower(1);

            sleep(10000);
            telemetry.addData("Left Motor Position", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position", robot.frontRight.getCurrentPosition());
            telemetry.update();

            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
            robot.backLeft.setPower(0);
            sleep(20000);

        }

    }
}
