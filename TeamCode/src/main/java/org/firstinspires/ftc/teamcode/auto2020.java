package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto2020", group="Linear Opmode")

public class auto2020 extends LinearOpMode {

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


            //Forward
/*            robot.frontLeft.setTargetPosition(-541);
            robot.frontRight.setTargetPosition(-541);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.frontLeft.setPower(.25);
            robot.frontRight.setPower(.30);
            robot.backLeft.setPower(.25);
            robot.backRight.setPower(.30);
*/

            //Backwards
/*            robot.frontLeft.setTargetPosition(541);
            robot.frontRight.setTargetPosition(541);

            robot.frontLeft.setPower(-.25);
            robot.frontRight.setPower(-.30);
            robot.backLeft.setPower(-.25);
            robot.backRight.setPower(-.30);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //while (robot.frontLeft.isBusy() && robot.frontRight.isBusy()) {
            while (robot.frontLeft.isBusy()) {
                telemetry.addData("Left Motor Position", robot.frontLeft.getCurrentPosition());
                telemetry.addData("Right Motor Position", robot.frontRight.getCurrentPosition());
                telemetry.update();
            }

           robot.frontLeft.setPower(0);
           robot.frontRight.setPower(0);
           robot.backLeft.setPower(0);
           robot.backRight.setPower(0);
*/


            //Drive to A, which puts you on the line
            //Util.DriveA();


            //Drive to B, then back up onto the line
            Util.DriveB();
            sleep(2000);
            Util.MoveForwardInch(-20, .25);


            //Drive to C
            //Util.DriveC();
            //sleep(2000);
            //Util.MoveForwardInch(-44, .25);


            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(5000);


            break;


        }
    }
}

