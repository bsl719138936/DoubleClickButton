package com.bsl.yulin.doubleclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "单击按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //初始化双击按钮
        DoubleClickButton doubleClickButton = (DoubleClickButton) findViewById(R.id.doubleClickButton);
        doubleClickButton.setOnDoubleClickListener(new DoubleClickButton.onDoubleClickListener() {
            @Override
            public void DoubleClick(View v) {
                Toast.makeText(MainActivity.this, "双击按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
