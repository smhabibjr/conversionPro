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

public class oct2others extends AppCompatActivity {
    EditText et_octNumber;
    Button btn_octConvert;
    TextView tv_octResult;
    Button btn_octReset;

    //fb ads
    private AdView adView;

    //for fb interstatial ads
    private final String TAG = MainActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oct2others);

        et_octNumber = (EditText) findViewById(R.id.et_octNumber);
        btn_octConvert = (Button) findViewById(R.id.btn_octConvert);
        tv_octResult = (TextView) findViewById(R.id.tv_octResult);
        btn_octReset = (Button) findViewById(R.id.btn_octReset);

        btn_octConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = et_octNumber.getText().toString();
                int i = Integer.parseInt(string,8);
                String dec = Integer.toString(i);
                String bin = Integer.toBinaryString(i);
                String hex = Integer.toHexString(i);

                tv_octResult.setText("OCT " + string + "\n\n"+
                        "DEC " + dec + "\n\n" +
                        "BIN " + bin + "\n\n" +
                        "HEX " + hex + "\n\n"
                );

                et_octNumber.setCursorVisible(false);
            }
        });

        //2nd ads
        interstitialAd = new InterstitialAd(this, "518801612690957_522919855612466");
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

        btn_octReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_octNumber.setText("");
                et_octNumber.setCursorVisible(true);
                tv_octResult.setText("Result waiting for input");

                // For auto play video ads, it's recommended to load the ad
                // at least 30 seconds before it is shown
                interstitialAd.loadAd(
                        interstitialAd.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());
            }
        });

        //fb ads
        adView = new AdView(this, "518801612690957_522904735613978", AdSize.BANNER_HEIGHT_50);

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
                        oct2others.this,
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