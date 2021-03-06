package ru.psu.studyit.view.activities

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.SurfaceView
import android.widget.TextView

import ru.psu.studyit.R

import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class CActivityQRScanner : AppCompatActivity()
{

    internal var cameraPreview: SurfaceView? = null
    internal var txtResult: TextView? = null
    //    BarcodeDetector barcodeDetector;
    //    CameraSource cameraSource;
    internal val RequestCameraPermissionID = 1001

    //    @Override
    //    public void onRequestPermissionsResult(int requestCode,
    //                                           String permissions[], int[] grantResults)
    //    {
    //        switch (requestCode) {
    //            case RequestCameraPermissionID:
    //                {
    //                // If request is cancelled, the result arrays are empty.
    //                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
    //                    {
    //                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    //                            return;
    //                        try
    //                        {
    //                            cameraSource.start(cameraPreview.getHolder());
    //                        }
    //                        catch (IOException e)
    //                        {
    //                            e.printStackTrace();
    //                        }
    //                    }
    //                    return;
    //                }
    //        }
    //    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        txtResult = findViewById(R.id.txtResult)

        val integrator = IntentIntegrator(this)
        integrator.initiateScan()

        //        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        //
        //        cameraSource = new CameraSource.Builder(this, barcodeDetector)
        //                .setRequestedPreviewSize(640,480).build();
        //
        //        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
        //            @Override
        //            public void surfaceCreated(SurfaceHolder holder) {
        //                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        //                    ActivityCompat.requestPermissions(CActivityQRScanner.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
        //                    return;
        //                }
        //                try{
        //                    cameraSource.start(cameraPreview.getHolder());
        //                }
        //                catch(IOException e)
        //                {
        //                    e.printStackTrace();
        //                }
        //            }
        //
        //            @Override
        //            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //
        //            }
        //
        //            @Override
        //            public void surfaceDestroyed(SurfaceHolder holder) {
        //                cameraSource.stop();
        //            }
        //        });
        //
        //        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
        //            @Override
        //            public void release() {
        //
        //            }
        //
        //            @Override
        //            public void receiveDetections(Detector.Detections<Barcode> detections) {
        //                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
        //                if(qrcodes.size() != 0)
        //                {
        //                    txtResult.post(new Runnable() {
        //                        @Override
        //                        public void run() {
        //                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService((Context.VIBRATOR_SERVICE));
        //                            vibrator.vibrate(100);
        //                            txtResult.setText(qrcodes.valueAt(0).displayValue);
        //                        }
        //                    });
        //                }
        //            }
        //        });
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?)
    {
        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanResult != null)
        {
            // handle scan result
            txtResult?.text = scanResult.contents
            val x = 0
        }
        super.onActivityResult(requestCode, resultCode, intent)
    }
}
