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

            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


           telemetry.addData("Motor Position: ", robot.frontLeft.getCurrentPosition());
           telemetry.update();
           sleep(1000);

            //Forward
            robot.frontLeft.setTargetPosition(-541);
            robot.frontRight.setTargetPosition(-541);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.frontLeft.setPower(.25);
            robot.frontRight.setPower(.25);
            robot.backLeft.setPower(.25);
            robot.backRight.setPower(.25);

/*            //Backwards
            robot.frontLeft.setTargetPosition(541);
            robot.frontRight.setTargetPosition(541);

            robot.frontLeft.setPower(.25);
            robot.frontRight.setPower(-.25);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/

            //while (robot.frontLeft.isBusy() && robot.frontRight.isBusy()) {
            while (robot.frontLeft.isBusy()) {
                telemetry.addData("Left Motor Position", robot.frontLeft.getCurrentPosition());
                telemetry.addData("Right Motor Position", robot.frontRight.getCurrentPosition());
                telemetry.update();
            }

 /*           robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);


       p=1;
            while (p != 0){

                robot.frontRight.setPower(p);
                robot.frontLeft.setPower(p);

                if (p == .5){
                robot.frontRight.setPower(.1);
                robot.frontLeft.setPower(.1);
                }
                p += .1;
                sleep(500);
            }


            //Util.MoveForwardInch(20, .25);
            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            //sleep(5000);

            //robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //robot.frontLeft.setPower(-0.25);
            //robot.frontRight.setPower(0.25);
            //robot.backLeft.setPower(-0.25);
            //robot.backRight.setPower(0.25);

            //sleep(2500);

            //robot.frontLeft.setPower(0);
            //robot.frontRight.setPower(0);
           // robot.backLeft.setPower(0);
            //robot.backRight.setPower(0);
*/
            //Util.turnLeft(90, .40);
            //Util.MoveForwardInch(56, .5);
/*            robot.frontLeft.setPower(.5);
            robot.frontRight.setPower(-.5);
            robot.backRight.setPower(-.5);
            robot.backLeft.setPower(.5);

            while (robot.frontLeft.isBusy() && robot.frontRight.isBusy()) {
                telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
                telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
                telemetry.update();
            }
*/
            sleep(5000);

            //Util.MoveForwardInch(-15, .25);
            //telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            //telemetry.update();



            break;


        }
    }
}

