package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import com.qualcomm.hardware.bosch.BNO055IMU;

@Autonomous(name="auto2020", group="Linear Opmode")

public class auto2020 extends LinearOpMode {

    //private ElapsedTime runtime = new ElapsedTime();
    static final int MOTOR_TICK_COUNTS = 1120;
    int encoderDrivingTarget = (int) (MOTOR_TICK_COUNTS / 4);
    int motorPos;
    PushBot2020 robot = new PushBot2020();
    Functions2020 Util = new Functions2020();
    int t=0;
    private OpenCvCamera webcam;
    private RingDetector detector = new RingDetector();
    private String numberOfRings;
    private double bottomTotal;
    private double topTotal;

    @Override
    public void runOpMode() {
        //Initialize motors
        robot.init(hardwareMap, true);
        Util.init(hardwareMap);
        //runtime.reset();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        BNO055IMU imu;
        webcam.openCameraDevice();
        webcam.setPipeline(detector);
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        robot.grabber.setPosition(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            while (t<1) {
                numberOfRings = detector.numberOfRings;
                bottomTotal = detector.bottomTotal;
                topTotal = detector.topTotal;
                telemetry.addData("numberOfRings = ", numberOfRings);
                telemetry.addData("bottomTotal = ", bottomTotal);
                telemetry.addData("topTotal = ", topTotal);
                telemetry.update();
                t=t+1;
            }

            Util.DriveAndShoot();
            sleep(1000);

            if (numberOfRings == "NONE"){
                //Drive to A
                Util.PIDloopDrive2(46, -.6);
                sleep(500);
                Util.turnLeft(3.5,.7);
                sleep(250);
                Util.PIDloopDrive2(4,-.6);

                //Drop the wobble
                robot.wobbleRotate.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left onto the line
                Util.PIDloopDrive2(2, -.6);
                sleep(500);
                robot.wobbleRotate.setPosition(0);
                sleep(250);
                Util.turnLeft(2,.7);
                sleep(250);
             }

            else if (numberOfRings == "SINGLE") {
                //Drive forward to square B and position the robot to drop wobble
                Util.PIDloopDrive2(70, -.6);
                sleep(500);
                Util.turnRight(2, .7);
                sleep(250);
                Util.PIDloopDrive2(3, -.6);
                sleep(500);

                //Drop the wobble
                robot.wobbleRotate.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left
                Util.PIDloopDrive2(1, -.6);
                sleep(500);
                robot.wobbleRotate.setPosition(0);
                sleep(250);
                Util.turnRight(5, .7);
                sleep(250);

                //Drive forward onto the line
                Util.PIDloopDrive2(22, -.6);
             }
            else {
                //Drive forward to square C and position the robot to drop wobble
                //Util.PIDloopDrive2(36, -.6);
                Util.PIDloopDrive2(75, -.6);
                sleep(500);
                Util.turnLeft(5.5,.7);
                sleep(250);

                //Drop the wobble
                robot.wobbleRotate.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);

                //Drive forward, lift the grabber, and turn left
                Util.PIDloopDrive2(2, -.6);
                sleep(500);
                robot.wobbleRotate.setPosition(0);
                sleep(250);
                Util.turnLeft(3,.7);
                sleep(250);

                //Drive forward onto the line
                Util.PIDloopDrive2(20, -.6);
            }

            robot.wobbleRotate.setPosition(.65);

            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(30000);
        }
    }
}

