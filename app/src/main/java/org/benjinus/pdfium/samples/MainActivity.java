package org.benjinus.pdfium.samples;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.benjinus.pdfium.Meta;
import org.benjinus.pdfium.PdfDocument;
import org.benjinus.pdfium.PdfiumSDK;
import org.benjinus.pdfium.util.Size;

import java.io.File;

import static android.os.ParcelFileDescriptor.MODE_READ_ONLY;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EnumFileType enumFileType = EnumFileType.asset;

    public enum EnumFileType {
        asset, remote, storage
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        openFile();
    }

    private void openFile() {

        switch (enumFileType) {
            case asset:
                decodePDFPage();
                break;
            case remote:
                openPdfFromMemory();
                break;
            case storage:
                openPdfRemote();
                break;
        }
    }

    private void decodePDFPage() {

        try {

            File pdfFile = ((SamplesApplication) getApplication()).createNewSampleFile("Sample.pdf");

            ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(pdfFile, MODE_READ_ONLY);

            PdfiumSDK pdfiumSDK = new PdfiumSDK();
            PdfDocument pdfDocument = pdfiumSDK.newDocument(fileDescriptor);

            Log.d("PDFSDK", "Page count: " + pdfiumSDK.getPageCount(pdfDocument));

            Meta meta = pdfiumSDK.getDocumentMeta(pdfDocument);
            Log.d("PDFSDK", meta.toString());

            pdfiumSDK.openPage(pdfDocument, 0);

            Size size = pdfiumSDK.getPageSize(pdfDocument, 0);
            Log.d("PDFSDK", "Page size: " + size.toString());

            int width = getScreenWidth();
            int height = getScreenHeight();

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            pdfiumSDK.renderPageBitmap(pdfDocument, bitmap, 0, 0, 0, width, height, true);

            mImageView.setImageBitmap(bitmap);

            pdfiumSDK.closeDocument(pdfDocument);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPdfFromMemory() {

    }

    private void openPdfRemote() {

    }

    private int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

}
