package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.*;

public class MainActivity extends AppCompatActivity {

    private Button dec2others;
    private Button bin2others;
    private Button oct2others;
    private Button hex2others;

    //for share it button
    public static String PACKAGE_NAME;

    //fb ads
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for share it Button
        PACKAGE_NAME = getApplicationContext().getPackageName();

        dec2others = (Button) findViewById(R.id.btn_dec2others);
        dec2others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_dec2others_activity();
            }

        });
        bin2others = (Button) findViewById(R.id.btn_bin2others);
        bin2others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_bin2others();
            }
        });

        oct2others = (Button) findViewById(R.id.btn_oct2others);
        oct2others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_oct2others_activity();
            }
        });

        hex2others = (Button) findViewById(R.id.btn_hex2others);
        hex2others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_hex2others();
            }
        });

        //fb ads
        adView = new AdView(this, "518801612690957_522283392342779", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(
                        MainActivity.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

    }

    private void open_oct2others_activity() {
        Intent open_oct2others = new Intent(this, oct2others.class);
        startActivity(open_oct2others);
    }

    private void open_bin2others() {
        Intent open_bin2others = new Intent(this, bin2others.class);
        startActivity(open_bin2others);
    }

    private void open_dec2others_activity() {
        Intent open_dec2others = new Intent(this, Dec2others.class);
        startActivity(open_dec2others);
    }

    private void open_hex2others() {
        Intent open_hex2others = new Intent(this, hex2others.class);
        startActivity(open_hex2others);
    }

    //Rate me button
    public void rateMe(View v) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.xdarssoftco.conversionpro")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http//play.google.com/store/apps/details?=" + getPackageName())));
        }
    }

    //share it method
    public void shareit(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String value = "https://play.google.com/store/apps/details?id=" + PACKAGE_NAME;
        intent.putExtra(Intent.EXTRA_TEXT, value);
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public void aboutUs(View view) {
        Intent aboutMe = new Intent(this, aboutMe.class);
        startActivity(aboutMe);
    }

    //fb ads

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}