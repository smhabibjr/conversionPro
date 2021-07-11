package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class hex2others extends AppCompatActivity {
    EditText et_hexNumber;
    Button btn_hexConvert;
    TextView tv_hexResult;

    Button btn_hexReset;

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

        btn_hexReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_hexNumber.setText("");
                tv_hexResult.setText("Result waiting for input");
                et_hexNumber.setCursorVisible(true);
            }
        });
    }
}