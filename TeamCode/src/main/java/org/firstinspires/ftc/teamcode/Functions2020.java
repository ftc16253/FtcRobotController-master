package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

class Functions2020 {
    PushBot2020 rob = new PushBot2020();

    public DcMotor frontLeft, frontRight, backLeft, backRight;
    double diameter = 4;
    double circumference = diameter * 3.14;
    int tetrixEncoderTics = 1440;
    int andyMarkEncoderTics = 515;
    public void init(HardwareMap ahwMap) {
        rob.init(ahwMap);

        frontLeft = rob.frontLeft;
        frontRight = rob.frontRight;
        backLeft = rob.backLeft;
        backRight = rob.backRight;
    }

    public void turnLeft (double degrees, double power){

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //frontLeft.setTargetPosition((int) (-andyMarkEncoderTics/360 * degrees));
        //frontRight.setTargetPosition((int) (-andyMarkEncoderTics/360 * degrees));


        frontRight.setTargetPosition(520);
        frontLeft.setTargetPosition(520);

        frontLeft.setPower(-power+0.1);
        frontRight.setPower(-power);
        backLeft.setPower(-power+0.1);
       backRight.setPower(-power);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy() && frontLeft.isBusy()){ // || backLeft.isBusy() || backRight.isBusy()) {

        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);



    }
    public void MoveForwardInch(double distance, double power) {

        double totalRotations = distance/circumference;
        int rotationDistanceofWheel = (int)(andyMarkEncoderTics * totalRotations);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (true) {
            if(Math.abs(frontRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
                break;
            }
            else {
                if (distance > 0) {
                    frontLeft.setPower(-power);
                    frontRight.setPower(-power);
                    backRight.setPower(-power);
                    backLeft.setPower(-power);
                }
                else if (distance < 0) {
                    frontLeft.setPower(power);
                    frontRight.setPower(power);
                    backRight.setPower(power);
                    backLeft.setPower(power);
                }
            }
        }
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void DriveA(){
        MoveForwardInch(72, 0.3);
        //Let go of wobble grabber here
    }
    public void DriveB(){
      MoveForwardInch(92, 0.3);
    }
    public void DriveC(){
      MoveForwardInch(120, 0.3);
    }

    public void CameraRings(){

    }
}

