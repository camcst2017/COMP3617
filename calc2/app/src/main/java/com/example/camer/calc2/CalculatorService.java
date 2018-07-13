package com.example.camer.calc2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorService extends IntentService {

    public static final String CALCULATOR_SERVICE_EVALULATE = "evaluate";
    public static final String CALCULATOR_SERVICE_SUCCESS = "success";

    private Handler handler;

    public CalculatorService() {
        super("CalculatorService");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        if(intent != null ) {
            String toEvaluate = intent.getStringExtra(CalculatorService.CALCULATOR_SERVICE_EVALULATE);


            // calculate
            Expression calc = new ExpressionBuilder(toEvaluate).build();
            final double result;
            result = calc.evaluate();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendBroadCast(result);
                    } catch (ArithmeticException e ) {
                        sendBroadcast(e.getMessage());
                    }
                }
            });
        }
    }

    private void sendBroadCast(double result) {

        Intent intent = new Intent(CalculatorService.CALCULATOR_SERVICE_SUCCESS);
        intent.putExtra(CalculatorService.CALCULATOR_SERVICE_SUCCESS, result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendBroadcast(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}