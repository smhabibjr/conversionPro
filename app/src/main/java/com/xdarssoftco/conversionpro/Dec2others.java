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

public class Dec2others extends AppCompatActivity {

    EditText et_number;
    Button b_convert;
    TextView tv_result;

    //reset button
    Button reset;

    //fb ads
    private AdView adView;

    //for fb interstatial ads
    private final String TAG = MainActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec2others);

        et_number = (EditText) findViewById(R.id.et_number);
        b_convert = (Button) findViewById(R.id.b_convert);
        tv_result = (TextView) findViewById(R.id.tv_result);

        b_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dec = Integer.parseInt(et_number.getText().toString());
                String bin = Integer.toBinaryString(dec);
                String hex = Integer.toHexString(dec);
                String oct = Integer.toOctalString(dec);

                tv_result.setText("DEC " + dec + "\n\n" +
                        "BIN " + bin + "\n\n" +
                        "HEX " + hex + "\n\n" +
                        "OCT " + oct);

                et_number.setCursorVisible(false);
            }
        });

        //2nd ads
        interstitialAd = new InterstitialAd(this, "518801612690957_522919178945867");
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

        //reset button
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_number.setText("");
                tv_result.setText("Result waiting for input");
                et_number.setCursorVisible(true);

                // For auto play video ads, it's recommended to load the ad
                // at least 30 seconds before it is shown
                interstitialAd.loadAd(
                        interstitialAd.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());
            }
        });

        //fb ads
        adView = new AdView(this, "518801612690957_522901568947628", AdSize.BANNER_HEIGHT_50);

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
                        Dec2others.this,
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