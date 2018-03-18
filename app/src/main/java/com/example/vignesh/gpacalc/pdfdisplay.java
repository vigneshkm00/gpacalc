package com.example.vignesh.gpacalc;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class pdfdisplay extends AppCompatActivity {
    TextView gr,dsp,lst1,lst2,lst3;
    public String regNo= " ";
    EditText rn;
    Button sve;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfdisplay);
        lst1=(TextView)findViewById(R.id.textView8);
        lst2=(TextView)findViewById(R.id.textView9);
        lst3=(TextView)findViewById(R.id.textView10);
        gr=(TextView)findViewById(R.id.getre);
        dsp=(TextView)findViewById(R.id.disp);
        rn=(EditText)findViewById(R.id.Regno);
        sve=(Button)findViewById(R.id.save);
        final String[] arr1 = getIntent().getStringArrayExtra("subj");
        final int[] crdts1 = getIntent().getIntArrayExtra("cr");
        final String gpa=getIntent().getStringExtra("gpa");
        final String[] selections1 = getIntent().getStringArrayExtra("grds");
        // final String regno = {rn.getText().toString()};
        sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regNo = rn.getText().toString();
                if (!regNo.equalsIgnoreCase(" ")) {
                    dsp.setText("");
                    dsp.append("\n     GPA Calculator's Generated Report\n\n");
                    for (int i = 0; i < arr1.length; i++) {
                        dsp.append("\n" + (i + 1) + "   " + arr1[i] + "   " + selections1[i] + "\n");

                    }
                    dsp.append("\n\n        GPA = " + gpa);

                    if (dsp.getText().toString().isEmpty()) {
                        dsp.setError("Body is empty");
                        dsp.requestFocus();
                        return;
                    }
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else

                {  Toast.makeText(getApplicationContext(),"Please Enter the Registration Number",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            // Log.i(TAG, "Created a new directory for PDF");
        }
        String filename= regNo + ".pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(),filename);
        OutputStream output = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph(dsp.getText().toString()));

        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }
}
