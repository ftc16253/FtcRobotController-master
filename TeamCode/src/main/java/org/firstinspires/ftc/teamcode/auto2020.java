package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

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

        webcam.openCameraDevice();
        webcam.setPipeline(detector);
        webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
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
            numberOfRings = "NONE";


            if (numberOfRings == "NONE"){
                //Drive to A, which puts you on the line
                Util.DriveA();
            }

            else if (numberOfRings == "SINGLE"){
                //Drive to B, then back up onto the line
                Util.DriveB();
                sleep(2000);
                Util.MoveForwardInch(-20, .25);
            }

            else{
                //Drive to C
                Util.DriveC();
                sleep(2000);
                Util.MoveForwardInch(-44, .25);
            }



            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(5000);


            break;


        }
    }
}

