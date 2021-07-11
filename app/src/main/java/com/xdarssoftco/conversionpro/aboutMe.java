package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class aboutMe extends AppCompatActivity {

    //for share it button
    public static String PACKAGE_NAME;

    private ImageView fbBtn, instraBtn, twitterBtn, youtubeBtn, gitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        //for share it Button
        PACKAGE_NAME = getApplicationContext().getPackageName();

        fbBtn = findViewById(R.id.fbBtn);
        instraBtn = findViewById(R.id.instraBtn);
        twitterBtn = findViewById(R.id.twitterBtn);
        youtubeBtn = findViewById(R.id.ytBtn);
        gitBtn = findViewById(R.id.gitBtn);

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFbUrl("https://www.facebook.com/smhabibjr");
            }
        });

        instraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInstraUrl("https://www.instagram.com/simply.leojr/");
            }
        });

        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTwitterUrl("https://twitter.com/smhabib_abir_jr");
            }
        });

        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToYoutubeUrl("https://www.youtube.com/channel/UCAb6zCUBSCTGhXLME12XD5A/videos");
            }
        });

        gitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotogiturl("https://github.com/smhabibjr");
            }
        });
    }

    private void gotogiturl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void goToYoutubeUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void goToTwitterUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void goToInstraUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void goToFbUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    //Rate me button , alway we need to change the package name
    public void rateMe(View v){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ "com.xdarssoftco.conversionpro")));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http//play.google.com/store/apps/details?=" + getPackageName())));
        }
    }

    //share it method
    public void shareit(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String value = "https://play.google.com/store/apps/details?id="+PACKAGE_NAME;
        intent.putExtra(Intent.EXTRA_TEXT,value);
        startActivity(Intent.createChooser(intent, "Spread Your Love"));
    }
}