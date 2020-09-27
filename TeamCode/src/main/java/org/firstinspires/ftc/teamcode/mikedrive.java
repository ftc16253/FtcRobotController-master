package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="mikedrive", group="LinearOpMode")

public class mikedrive extends LinearOpMode {

    DcMotor frontLeft, frontRigth, backLeft, backRight;
    PushBot2020 robot = new PushBot2020();
    Functions2020 Util = new Functions2020();

    public void runOpMode(){

        telemetry.addData("Status", "Initialized" );
        telemetry.update();


        robot.init(hardwareMap);
        Util.init(hardwareMap);



        waitForStart();

        while (opModeIsActive()){

            robot.frontLeft.setPower(-gamepad1.left_stick_y);
            robot.frontRight.setPower(-gamepad1.left_stick_y);
            robot.backLeft.setPower(-gamepad2.left_stick_x);
            robot.backLeft.setPower(-gamepad2.left_stick_x);
        }

    }
}
