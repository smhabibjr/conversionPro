package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class oct2others extends AppCompatActivity {
    EditText et_octNumber;
    Button btn_octConvert;
    TextView tv_octResult;
    Button btn_octReset;

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

        btn_octReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_octNumber.setText("");
                et_octNumber.setCursorVisible(true);
                tv_octResult.setText("Result waiting for input");
            }
        });

    }
}