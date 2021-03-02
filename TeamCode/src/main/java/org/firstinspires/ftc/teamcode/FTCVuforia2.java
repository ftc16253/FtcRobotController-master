package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.CameraCalibration;
import com.vuforia.Frame;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@TeleOp
public class FTCVuforia2 extends LinearOpMode {
    private VuforiaLocalizer vuforia = null;


    PushBot2020 rob = new PushBot2020();
    Functions2020 Util = new Functions2020();
    //FTCVuforia Vuforia;
    private static final String VUFORIA_KEY = "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;


    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    public void runOpMode()
    {
        //TFObjectDetector tfod = new TFObjectDetector();
        rob.init(hardwareMap,false);
        Util.init(hardwareMap);

        telemetry.addData("Status", "Initialized" );
        telemetry.update();

        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //VuforiaLocalizer.Parameters param = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //param.vuforiaLicenseKey = VUFORIA_KEY;
         //VuforiaLocalizer vuforia = new VuforiaLocalizer();
        //vuforia = ClassFactory.getInstance().createVuforia(param);
        initVuforia();


    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters param = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //VuforiaLocalizer.Parameters param = new VuforiaLocalizer.Parameters();
        param.vuforiaLicenseKey = VUFORIA_KEY;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(param);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    ////@Override
    public VuforiaTrackables loadTrackablesFromAsset(String assetName) {
        return null;
    }

        ////@Override
    public VuforiaTrackables loadTrackablesFromFile(String absoluteFileName) {
        return null;
    }

       // //@Override
    public Camera getCamera() {
        return null;
    }

        ////@Override
    public CameraName getCameraName() {
        return null;
    }

        ////@Override
        public CameraCalibration getCameraCalibration() {
            return null;
        }

        //@Override
        //public BlockingQueue<CloseableFrame> getFrameQueue() {
        //    return null;
        //}

        //@Override
        public void setFrameQueueCapacity(int capacity) {

        }

        //@Override
        public int getFrameQueueCapacity() {
            return 0;
        }

        //@Override
        public void getFrameOnce(Continuation<? extends Consumer<Frame>> frameConsumer) {

        }

        //@Override
        public void enableConvertFrameToBitmap() {

        }

        //@Override
        public boolean[] enableConvertFrameToFormat(int... pixelFormats) {
            return new boolean[0];
        }

        //@Override
        public Bitmap convertFrameToBitmap(Frame frame) {
            return null;
        }

        //@Override
       // public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {

        //}

        //@Override
        public void loadModelFromAsset(String assetName, String... labels) {

        }

        //@Override
        public void loadModelFromFile(String absoluteFileName, String... labels) {

        }

        //@Override
        public void activate() {

        }

        //@Override
        public void deactivate() {

        }

        //@Override
        public void setClippingMargins(int left, int top, int right, int bottom) {

        }

        //@Override
        public List<Recognition> getUpdatedRecognitions() {
            return null;
        }

        //@Override
        public List<Recognition> getRecognitions() {
            return null;
        }

        //@Override
        public void shutdown() {

        }

        //@Override
        public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {

        }


    /**
     * Initialize the TensorFlow Object Detection engine.

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);*/





}
