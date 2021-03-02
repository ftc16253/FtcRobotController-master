package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="mikessquare", group="Linear Opmode")
public class mikessquare extends LinearOpMode {
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    PushBot2020 rob = new PushBot2020();
    Functions2020 Util = new Functions2020();

    public void runOpMode() {
        telemetry.addData("Status", "Initialized" );
        telemetry.update();


        rob.init(hardwareMap,false);
        Util.init(hardwareMap);



        waitForStart();


        while (opModeIsActive()){

            for (int i = 0; i<4; i++){
                rob.frontLeft.setPower(0.25);
                rob.frontRight.setPower(0.25);
                rob.backLeft.setPower(0.25);
                rob.backRight.setPower(0.25);

                sleep(2000);

                rob.frontLeft.setPower(-0.25);
                rob.frontRight.setPower(0);
                rob.backLeft.setPower(-0.25);
                rob.backRight.setPower(0);

                sleep(2500);
            }

            rob.frontLeft.setPower(0);
            rob.frontRight.setPower(0);
            rob.backLeft.setPower(0);
            rob.backRight.setPower(0);

            break;

        }

    }


}


