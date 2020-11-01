package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.opencv.imgproc.Imgproc.INTER_CUBIC;
import static org.opencv.imgproc.Imgproc.circle;
import static org.opencv.imgproc.Imgproc.rectangle;

public class AutoVisionPipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input) {
        double scaleSize = 8;
        //Imgproc.cvtColor(input, input, Imgproc.COLOR_RGBA2RGB, 3);
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGBA2RGB);

        Mat processingImage = new Mat();

        Imgproc.resize(input, processingImage, new Size(), 1 / scaleSize, 1 / scaleSize, INTER_CUBIC);
        Imgproc.blur(processingImage, processingImage, new Size(3, 3));

        Mat hsv = new Mat();
        //Imgproc.cvtColor(processingImage, hsv, Imgproc.COLOR_RGB2HSV, 3);
        Imgproc.cvtColor(processingImage, hsv, Imgproc.COLOR_RGB2HSV);

        double minHue = 25.0;
        double maxHue = 30.0;

        // #1
        int hSum = 0;
        int sSum = 0;
        int vSum = 0;
        int pixelCount = 0;

        double sampleRadius = 20;
        int sample1X = 0;
        int sample1Y = 0;
        int sampleX_start = (int) ((sample1X - sampleRadius) / scaleSize);
        int sampleX_end = (int) ((sample1X + sampleRadius) / scaleSize);
        int sampleY_start = (int) ((sample1Y - sampleRadius) / scaleSize);
        int sampleY_end = (int) ((sample1Y + sampleRadius) / scaleSize);

        for (int y = sampleY_start; y < sampleY_end; y++) {
            for (int x = sampleX_start; x < sampleX_end; x++) {
                hSum += hsv.get(y, x)[0];
                sSum += hsv.get(y, x)[1];
                vSum += hsv.get(y, x)[2];
                pixelCount++;
            }
        }

        double hAverage1 = (double) hSum / pixelCount;
        double sAverage1 = (double) sSum / pixelCount;
        double vAverage1 = (double) vSum / pixelCount;

        rectangle(input, new Point(sampleX_start * (int) scaleSize, sampleY_start * (int) scaleSize),
                new Point(sampleX_end * (int) scaleSize, sampleY_end * (int) scaleSize),
                new Scalar(255, 255, 0), 3);

        // #2
        int sample2X = 0;
        int sample2Y = 0;
        sampleX_start = (int) ((sample2X - sampleRadius) / scaleSize);
        sampleX_end = (int) ((sample2X + sampleRadius) / scaleSize);
        sampleY_start = (int) ((sample2Y - sampleRadius) / scaleSize);
        sampleY_end = (int) ((sample2Y + sampleRadius) / scaleSize);
        hSum = 0;
        sSum = 0;
        vSum = 0;
        pixelCount = 0;
        for (int y = sampleY_start; y < sampleY_end; y++) {
            for (int x = sampleX_start; x < sampleX_end; x++) {
                hSum += hsv.get(y, x)[0];
                sSum += hsv.get(y, x)[1];
                vSum += hsv.get(y, x)[2];
                pixelCount++;
            }
        }
        //convert the hAverage back to degrees so we can use it
        double hAverage2 = (double) hSum / pixelCount;
        double sAverage2 = (double) sSum / pixelCount;
        double vAverage2 = (double) vSum / pixelCount;
        //draws where the sample was taken
        rectangle(input, new Point(sampleX_start * (int) scaleSize, sampleY_start * (int) scaleSize),
                new Point(sampleX_end * (int) scaleSize, sampleY_end * (int) scaleSize),
                new Scalar(255, 255, 0), 3);


            /*Imgproc.rectangle(input, new Point(sampleX_start * (int) scaleSize, sampleY_start * (int) scaleSize),
                    new Point(sampleX_end * (int) scaleSize, sampleY_end * (int) scaleSize),
                    new Scalar(0, 0, 255), 3);*/

        // #3
        int sample3X = 0;
        int sample3Y = 0;
        sampleX_start = (int) ((sample3X - sampleRadius) / scaleSize);
        sampleX_end = (int) ((sample3X + sampleRadius) / scaleSize);
        sampleY_start = (int) ((sample3Y - sampleRadius) / scaleSize);
        sampleY_end = (int) ((sample3Y + sampleRadius) / scaleSize);

        hSum = 0;
        sSum = 0;
        vSum = 0;
        pixelCount = 0;
        for (int y = sampleY_start; y < sampleY_end; y++) {
            for (int x = sampleX_start; x < sampleX_end; x++) {
                hSum += hsv.get(y, x)[0];
                sSum += hsv.get(y, x)[1];
                vSum += hsv.get(y, x)[2];
                pixelCount++;
            }
        }
        //convert the hAverage back to degrees so we can use it
        double hAverage3 = (double) hSum / pixelCount;
        double sAverage3 = (double) sSum / pixelCount;
        double vAverage3 = (double) vSum / pixelCount;
        //draws where the sample was taken
        rectangle(input, new Point(sampleX_start * (int) scaleSize, sampleY_start * (int) scaleSize),
                new Point(sampleX_end * (int) scaleSize, sampleY_end * (int) scaleSize),
                new Scalar(255, 255, 0), 3);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double targetHue = 23;
        double targetV = 230;
        double targetS = 255;
        double error1 =
                Math.abs(hAverage1 - targetHue) + Math.abs(sAverage1 - targetS) + Math.abs(vAverage1 - targetV);
        double error2 =
                Math.abs(hAverage2 - targetHue) + Math.abs(sAverage2 - targetS) + Math.abs(vAverage2 - targetV);
        double error3 =
                Math.abs(hAverage3 - targetHue) + Math.abs(sAverage3 - targetS) + Math.abs(vAverage3 - targetV);

        //merror1 = error1;
        //merror2 = error2;
        //merror3 = error3;

            /*if (error1 <= 200 && error1 <= error2) {
                circle(input, new Point(sample1X, sample1Y), 20, new Scalar(255, 255, 0), -1);
                whereIsCube = 0;
            } else {
                if (error2 <= 200 && error2 <= error1) {
                    circle(input, new Point(sample2X, sample2Y), 20, new Scalar(255, 255, 0), -1);
                    whereIsCube = 1;
                } else {
                    circle(input, new Point(sample3X, sample3Y), 20, new Scalar(255, 255, 0), -1);
                    whereIsCube = 2;
                }
            }*/

            /*
            see which value is the highest then determine the sample pos
             */
        int cubeLocation;
        if (error1 > 300) {
            circle(input, new Point(sample1X-5, sample1Y-5), 20, new Scalar(255, 255, 0), -1);
            cubeLocation = 0;
        } else if (error2 > 300) {
            circle(input, new Point(sample2X-5, sample2Y-5), 20, new Scalar(255, 255, 0), -1);
            cubeLocation = 1;
        } else if (error3 > 300) {
            circle(input, new Point(sample3X-5, sample3Y-5), 20, new Scalar(255, 255, 0), -1);
            cubeLocation = 2;
        }

        Mat booleanImage = new Mat();

        Core.inRange(hsv, new Scalar(minHue, 100, 75), new Scalar(maxHue, 255, 255), booleanImage);

        return input;
    }
}

