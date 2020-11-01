package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

class RingPipeline extends OpenCvPipeline {
    Telemetry telemetry;
    public RingPipeline(Telemetry t) {telemetry = t;}
    Mat mat = new Mat();
    static final Rect LEFT_ROI = new Rect(
            new Point(60, 35),
            new Point(120, 75));
    static final Rect RIGHT_ROI = new Rect(
            new Point(140, 35),
            new Point(200, 75));
    static final Rect CENTER_ROI = new Rect(
            new Point(100, 35),
            new Point(160, 75));
    double centerValue;

    @Override
    public final Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(30, 100, 60);
        Scalar highHSV = new Scalar(30, 70, 100);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat right = mat.submat(RIGHT_ROI);
        Mat center = mat.submat(CENTER_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;
        centerValue = Core.sumElems(center).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        right.release();
        center.release();

        telemetry.addData("left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("left percentage", Math.round((leftValue * 100)) + "%");
        telemetry.addData("right percentage", Math.round((rightValue * 100)) + "%");
        telemetry.addData("center raw value", (int) Core.sumElems(center).val[0]);
        telemetry.addData("center percentage", Math.round((centerValue * 100)) + "%");
        telemetry.update();

        return mat;
    }

    public double getCenterValue() {
        return centerValue;
    }
}
