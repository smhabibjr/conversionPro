package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dec2others extends AppCompatActivity {

    EditText et_number;
    Button b_convert;
    TextView tv_result;

    //reset button
    Button reset;

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

        //reset button
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_number.setText("");
                tv_result.setText("Result waiting for input");
                et_number.setCursorVisible(true);
            }
        });
    }
}