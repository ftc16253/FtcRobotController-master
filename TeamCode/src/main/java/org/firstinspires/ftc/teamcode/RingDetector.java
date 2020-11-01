package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class RingDetector extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public String numberOfRings = "NONE";
    public double bottomTotal;
    public double topTotal;
    public RingDetector(){

    }

    public final  Mat processFrame(Mat input){
        input.copyTo(workingMatrix);
        if (workingMatrix.empty()){
            return input;
        }
        Imgproc.cvtColor(workingMatrix, workingMatrix, Imgproc.COLOR_RGB2YCrCb);

        /*Mat matCenter = workingMatrix.submat(120, 150, 80, 120);
        Imgproc.rectangle(workingMatrix, new Rect(80,120, 80, 120), new Scalar(0,255,0));
        totalRings = Core.sumElems(matCenter).val[2];*/

        Mat matBottom = workingMatrix.submat(120,150,10, 50);
        Mat matTop = workingMatrix.submat(100, 120, 10, 50);

        Imgproc.rectangle(workingMatrix, new Rect(10,120, 40, 10), new Scalar(0,255,0));
        Imgproc.rectangle(workingMatrix, new Rect(10,100, 40, 10), new Scalar(0,255,0));

        bottomTotal = Core.sumElems(matBottom).val[2];
        topTotal = Core.sumElems(matTop).val[2];

        if (bottomTotal < 120000){
            if (topTotal < 60000) {
                numberOfRings = "QUAD";
            }else {
                numberOfRings = "SINGLE";
            }
        } else {
            numberOfRings = "NONE";
        }

        return workingMatrix;
    }

}
