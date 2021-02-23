package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class GrabberTest  extends LinearOpMode {

    public void runOpMode() {
        Servo grabber;
        Servo wobbleRotate;
        grabber = hardwareMap.get(Servo.class, "grabber");
        wobbleRotate = hardwareMap.get(Servo.class, "wobbleRotate");
        //grabberLeft.setDirection(Servo.Direction.FORWARD);
        //grabberRight.setDirection(Servo.Direction.REVERSE);
        //grabberLeft.setPosition(0);

        //Start position for two servos
        wobbleRotate.setPosition(0);

        //Start position for claw
        grabber.setPosition(.6);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
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

            //right bumper - wobble rotator goes down and claw opens
            if (gamepad1.right_bumper == true){
                wobbleRotate.setPosition(.55);
                sleep(1000);
                grabber.setPosition(.6);
            }

            //left bumper - claw opens
            if (gamepad1.left_bumper == true){
                grabber.setPosition(.6);
            }

        }
    }
}
