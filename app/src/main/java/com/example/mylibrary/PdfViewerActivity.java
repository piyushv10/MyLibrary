package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfView);
        int pos = getIntent().getIntExtra("BOOK_ID_KEY",0);

        if(pos == 0){
            pdfView.fromAsset("atomic-habits.pdf").load();
        } else if (pos == 1) {
            pdfView.fromAsset("Think-And-Grow-Rich.pdf").load();
        }
//        else{
//                Toast.makeText(this, "Hey!", Toast.LENGTH_SHORT).show();
//            }
        }

    }
