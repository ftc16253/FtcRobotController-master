package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class GrabberTest  extends LinearOpMode {

    public void runOpMode() {
        Servo grabberLeft;
        Servo grabberRight;
        grabberLeft = hardwareMap.get(Servo.class, "grabberLeft");
        grabberRight = hardwareMap.get(Servo.class, "grabberRight");
        //grabberLeft.setDirection(Servo.Direction.FORWARD);
        //grabberRight.setDirection(Servo.Direction.REVERSE);
        //grabberLeft.setPosition(0);
        grabberRight.setPosition(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Right trigger - grabber goes up
            if (gamepad1.right_trigger != 0){
                telemetry.addData("status","right trigger pressed");
                telemetry.update();
                //grabberLeft.setDirection(Servo.Direction.REVERSE);
                //grabberRight.setDirection(Servo.Direction.REVERSE);
                //grabberLeft.setPosition(.9);
                grabberRight.setPosition(.6);

            }
            //Left trigger - grabber goes down
            if (gamepad1.left_trigger != 0){
                telemetry.addData("status","left trigger pressed");
                telemetry.update();
                //grabberLeft.setDirection(Servo.Direction.REVERSE);
                //grabberRight.setDirection(Servo.Direction.FORWARD);
                //grabberLeft.setPosition(.9);
                grabberRight.setPosition(.35);

            }

        }
    }
}
