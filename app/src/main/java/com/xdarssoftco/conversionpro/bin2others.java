package com.xdarssoftco.conversionpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bin2others extends AppCompatActivity {
    EditText et_binNumber;
    Button btn_convert;
    TextView tv_result;

    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin2others);

        et_binNumber = (EditText) findViewById(R.id.et_bin_number);
        btn_convert = (Button) findViewById(R.id.btn_convert);
        tv_result = (TextView) findViewById(R.id.tv_result);
        reset = (Button) findViewById(R.id.btn_reset);

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = et_binNumber.getText().toString();
                int i = Integer.parseInt(string,2);
                String dec = Integer.toString(i);
                String hex = Integer.toHexString(i);
                String oct = Integer.toOctalString(i);

                tv_result.setText("BIN " + string + "\n\n" +
                        "DEC " + dec + "\n\n"+
                        "HEX " + hex + "\n\n" +
                        "OCT " + oct + "\n\n"
                );

                et_binNumber.setCursorVisible(false);
            }
        });

        //reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_binNumber.setText("");
                tv_result.setText("Result waiting for input");
                et_binNumber.setCursorVisible(true);
            }
        });
    }
}