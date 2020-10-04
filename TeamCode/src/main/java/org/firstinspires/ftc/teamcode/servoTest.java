package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class servoTest extends LinearOpMode {
    PushBot2020 robot = new PushBot2020();

    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            robot.Servo1.setPosition(gamepad1.left_stick_y);
        }
    }
}
