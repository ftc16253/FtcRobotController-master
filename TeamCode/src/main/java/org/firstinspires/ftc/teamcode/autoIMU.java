package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name="autoIMU", group="Linear Opmode")
@Disabled
public class autoIMU extends LinearOpMode {
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
    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;

    @Override
    public void runOpMode() {
        //Initialize motors
        robot.init(hardwareMap, true);
        Util.init(hardwareMap);
        //runtime.reset();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        webcam.openCameraDevice();
        webcam.setPipeline(detector);
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        robot.grabber.setPosition(0);

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            /*
            // Use gyro to drive in a straight line.
            correction = checkDirection();

            telemetry.addData("1 imu heading", lastAngles.firstAngle);
            telemetry.addData("2 global heading", globalAngle);
            telemetry.addData("3 correction", correction);
            telemetry.update();

            robot.frontLeft.setPower(power - correction);
            robot.backLeft.setPower(power - correction);
            robot.frontRight.setPower(power + correction);
            robot.backRight.setPower(power + correction);


             */
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
            //numberOfRings = "NONE";


            if (numberOfRings == "NONE"){
                //Drive to A, which puts you on the line
                telemetry.addData("Right Position: " ,robot.frontRight.getCurrentPosition());
                telemetry.addData("Left Position: ", robot.frontLeft.getCurrentPosition());
                telemetry.update();

                //Util.turnLeft(5,.7);
                Util.PIDloopDrive2(36, -.6);
                sleep(500);
                telemetry.addData("Right Position: " ,robot.frontRight.getCurrentPosition());
                telemetry.addData("Left Position: ", robot.frontLeft.getCurrentPosition());
                telemetry.update();
                sleep(1000);

                //Util.turnLeft(1,.7);
                rotate(-90, .7);
                sleep(250);
                telemetry.addData("Status", "Done");
                telemetry.update();

            }

            else if (numberOfRings == "SINGLE"){
                //Drive to B
                telemetry.addData("Right Position: " ,robot.frontRight.getCurrentPosition());
                telemetry.addData("Left Position: ", robot.frontLeft.getCurrentPosition());
                telemetry.update();

                Util.PIDloopDrive2(75, .7);
                sleep(250);
                telemetry.addData("Right Position: " ,robot.frontRight.getCurrentPosition());
                telemetry.addData("Left Position: ", robot.frontLeft.getCurrentPosition());
                telemetry.update();

                Util.turnRight(90,.7);
                sleep(500);
                robot.wobbleRotate.setPosition(.65);
                sleep(1250);
                robot.grabber.setPosition(.6);
                sleep(750);
                Util.PIDloopDrive2(6, .7);
                sleep(500);
                Util.turnLeft(30, .5);
                sleep(750);
                Util.PIDloopDrive2(-66, .5);
                sleep(250);
                Util.MoveForwardInch(-7, .5);
                robot.grabber.setPosition(0);



            }

            else {
                //Drive to C
                Util.MoveForwardInch(60, .5);
                sleep(2000);
                Util.MoveForwardInch(-44, .25);
            }



            telemetry.addData("Left Motor Position at end", robot.frontLeft.getCurrentPosition());
            telemetry.addData("Right Motor Position at end", robot.frontRight.getCurrentPosition());
            telemetry.update();

            sleep(10000);


        }
    }

    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    private double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     * @param degrees Degrees to turn, + is left - is right
     */
    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        robot.frontLeft.setPower(leftPower);
        robot.backLeft.setPower(leftPower);
        robot.frontRight.setPower(rightPower);
        robot.backRight.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {}

            while (opModeIsActive() && getAngle() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getAngle() < degrees) {}

        // turn the motors off.
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }

}
