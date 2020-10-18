package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autoEncTest", group="Linear Opmode")

public class autoEncTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    static final int MOTOR_TICK_COUNTS = 1120;
    int encoderDrivingTarget = (int) (MOTOR_TICK_COUNTS / 4);
    int motorPos;
    PushBot2020 robot = new PushBot2020();
    Functions2020 Util = new Functions2020();
    int p;

    @Override
    public void runOpMode() {
        //Initialize motors
        robot.init(hardwareMap);
        Util.init(hardwareMap);
        runtime.reset();
        int rotationDistanceofWheel = 3772;
        double power = 0.25;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

           //telemetry.addData("Motor Position: ", robot.frontLeft.getCurrentPosition());
           //telemetry.update();
           sleep(1000);

           robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           robot.frontLeft.setPower(power);
           robot.frontRight.setPower(power);
           robot.backRight.setPower(power);
           robot.backLeft.setPower(power);
           while (Math.abs(robot.frontLeft.getCurrentPosition()) < Math.abs(rotationDistanceofWheel) || Math.abs(robot.frontRight.getCurrentPosition()) < Math.abs(rotationDistanceofWheel)) {
               telemetry.addData("Motor Position: ", robot.frontLeft.getCurrentPosition());
               telemetry.update();
           }
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);


            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(5000);


            break;


        }
    }
}

