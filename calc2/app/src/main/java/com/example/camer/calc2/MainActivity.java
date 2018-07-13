package com.example.camer.calc2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private TextView resultsView;
    private StringBuilder builder;
    private LocalBroadcastManager manager;
    private CalculatorBroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnZero = findViewById(R.id.btnZero);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);
        Button btnThree = findViewById(R.id.btnThree);
        Button btnFour = findViewById(R.id.btnFour);
        Button btnFive = findViewById(R.id.btnFive);
        Button btnSix = findViewById(R.id.btnSix);
        Button btnSeven = findViewById(R.id.btnSeven);
        Button btnEight = findViewById(R.id.btnEight);
        Button btnNine = findViewById(R.id.btnNine);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnEqual = findViewById(R.id.btnEqual);

        resultsView = findViewById(R.id.resultTextView);
        builder = new StringBuilder();
        manager = LocalBroadcastManager.getInstance(this);
        receiver = new CalculatorBroadcastReceiver();

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateResults((Button) v);
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eval = builder.toString();
                Intent intent = new Intent(getApplicationContext(), CalculatorService.class);
                intent.putExtra(CalculatorService.CALCULATOR_SERVICE_EVALULATE, eval);
                startService(intent);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setLength(0);
                resultsView.setText(builder.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        manager.registerReceiver(receiver, new IntentFilter(CalculatorService.CALCULATOR_SERVICE_SUCCESS));
        super.onResume();
    }

    @Override
    protected void onPause() {
        manager.unregisterReceiver(receiver);
        super.onPause();
    }


    private void updateResults(Button button) {
        builder.append(button.getText());
        resultsView.setText(builder.toString());
    }

    private class CalculatorBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String result = Double.toString(intent.getDoubleExtra(CalculatorService.CALCULATOR_SERVICE_SUCCESS, 0));
            resultsView.setText(result);
        }
    }

}