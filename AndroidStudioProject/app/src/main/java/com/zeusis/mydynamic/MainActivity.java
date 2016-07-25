package com.zeusis.mydynamic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final private String TAG = "MainActivity";
    private int addResult;
    private JavaNative mJavaNative;
    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResult = mJavaNative.javaAdd(5,10);
                mResultText.setText("Result:"+String.valueOf(addResult));
            }
        });
        mResultText = (TextView) findViewById(R.id.textView2);
        TextView helloworld = (TextView) findViewById(R.id.textView);

        mJavaNative = new JavaNative();
        helloworld.setText(mJavaNative.HelloWorld());
        //int result = mjavaNative.javaAdd(5,10);
        //Log.i(TAG,"mjavaNative.javaAdd  result = " + result);
    }
}
