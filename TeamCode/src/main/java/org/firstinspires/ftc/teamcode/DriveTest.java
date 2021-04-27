package org.firstinspires.ftc.teamcode;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class DriveTest extends LinearOpMode {
    private DcMotor right;
    private DcMotor left;

    double prevRight = 0;
    double prevLeft = 0;
    long prevTime = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        right  = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();



        while (opModeIsActive()) {
            /*if (right.getCurrentPosition() > 2000) {
                right.setPower(0);
                left.setPower(0);
            } else {
                right.setPower(0.5);
                left.setPower(0.5);
            }*/

            long time = SystemClock.uptimeMillis();


            right.setPower(-0.515);
            left.setPower(-0.5);

            double deltaRight = right.getCurrentPosition()-prevRight;
            double deltaLeft = left.getCurrentPosition()-prevLeft;
            double deltaTime = (time-prevTime)/1000.0;

            telemetry.addData("Vel Right: ", (deltaRight/deltaTime));
            telemetry.addData("Vel Left: ", (deltaLeft/deltaTime));
            telemetry.update();

            prevRight = right.getCurrentPosition();
            prevLeft = right.getCurrentPosition();
            prevTime = time;
        }
    }
}
