package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.*;

public class hex2others extends AppCompatActivity {
    EditText et_hexNumber;
    Button btn_hexConvert;
    TextView tv_hexResult;

    Button btn_hexReset;

    //fb ads
    private AdView adView;

    //for fb interstatial ads
    private final String TAG = MainActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex2others);

        et_hexNumber = (EditText) findViewById(R.id.et_hexNumber);
        btn_hexConvert = (Button) findViewById(R.id.btn_hexConvert);
        tv_hexResult = (TextView) findViewById(R.id.tv_hexResult);

        btn_hexReset = (Button) findViewById(R.id.btn_hexReset);

        btn_hexConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = et_hexNumber.getText().toString();
                int i = Integer.parseInt(string,16);
                String dec = Integer.toString(i);
                String bin = Integer.toBinaryString(i);
                String oct = Integer.toOctalString(i);

                tv_hexResult.setText("HEX " + string + "\n\n" +
                                "DEC " + dec + "\n\n" +
                        "BIN " + bin + "\n\n" +
                        "OCT " + oct + "\n\n"
                        );
                et_hexNumber.setCursorVisible(false);
            }
        });

        //2nd ads
        interstitialAd = new InterstitialAd(this, "518801612690957_522919482279170");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        btn_hexReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_hexNumber.setText("");
                tv_hexResult.setText("Result waiting for input");
                et_hexNumber.setCursorVisible(true);

                // For auto play video ads, it's recommended to load the ad
                // at least 30 seconds before it is shown
                interstitialAd.loadAd(
                        interstitialAd.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());
            }
        });

        //fb ads
        adView = new AdView(this, "518801612690957_522903492280769", AdSize.BANNER_HEIGHT_50);

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
                        hex2others.this,
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

    //fb ads

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }
}