package com.example.equationsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1, text2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.editTextTextPersonName);
        text2 = findViewById(R.id.editTextTextPersonName2);
        textView = findViewById(R.id.textView);
    }

    @SuppressLint("DefaultLocale")
    public void BTNPush(View view) {
        int indexX = 0, indexY = 0, indexEqual = 0, indexX2 = 0, indexY2 = 0, indexEqual2 = 0;
        double  xCoefficient = 0, yCoefficient = 0, xCoefficient2 = 0, yCoefficient2 = 0;
        double x = 0, y = 0;

        String strText1 = text1.getText().toString();
        String strText2 = text2.getText().toString();

        int flagX = 0, flagY = 0;
        for (int i = 0; i < strText1.length(); i++) {
            if (strText1.charAt(i) == 'x') {
                indexX = i + 1;
                flagX++;
            }
            if (strText1.charAt(i) == 'y') {
                indexY = i + 1;
                flagY++;
            }
            if (strText1.charAt(i) == '=') {
                indexEqual = i + 1;
            }
        }

        int flagX2 = 0, flagY2 = 0;
        for (int i = 0; i < strText2.length(); i++) {
            if (strText2.charAt(i) == 'x') {
                indexX2 = i + 1;
                flagX2++;
            }
            if (strText2.charAt(i) == 'y') {
                indexY2 = i + 1;
                flagY2++;
            }
            if (strText2.charAt(i) == '=') {
                indexEqual2 = i + 1;
            }
        }

        int check = 0;
        for(int i = 0; i < strText1.length(); i++){

            if(strText1.charAt(i) != 'x' && strText1.charAt(i) != 'y' && strText1.charAt(i) != '+' && strText1.charAt(i) != '-' &&
                    strText1.charAt(i) != '0' && strText1.charAt(i) != '1' && strText1.charAt(i) != '2' && strText1.charAt(i) != '3' &&
                    strText1.charAt(i) != '4' && strText1.charAt(i) != '5' && strText1.charAt(i) != '6' && strText1.charAt(i) != '7' &&
                    strText1.charAt(i) != '8' && strText1.charAt(i) != '9' && strText1.charAt(i) != '.' && strText1.charAt(i) != '=' &&
                    strText1.charAt(i) != ' '){
                check = 1;
                break;
            }
        }
        for(int i = 0; i < strText2.length(); i++){

            if(strText2.charAt(i) != 'x' && strText2.charAt(i) != 'y' && strText2.charAt(i) != '+' && strText2.charAt(i) != '-' &&
                    strText2.charAt(i) != '0' && strText2.charAt(i) != '1' && strText2.charAt(i) != '2' && strText2.charAt(i) != '3' &&
                    strText2.charAt(i) != '4' && strText2.charAt(i) != '5' && strText2.charAt(i) != '6' && strText2.charAt(i) != '7' &&
                    strText2.charAt(i) != '8' && strText2.charAt(i) != '9' && strText2.charAt(i) != '.' && strText2.charAt(i) != '=' &&
                    strText2.charAt(i) != ' '){
                check = 1;
                break;
            }
        }

        if(check == 1){
            Toast.makeText(this, "Use 'x and 'y' as variables", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Maintain the 'ax+by=c' format", Toast.LENGTH_SHORT).show();
        }
        else if(flagX > 1 || flagY > 1 || flagX2 > 1 || flagY2 > 1){
            Toast.makeText(this, "Please maintain the 'ax+by=c' format3", Toast.LENGTH_SHORT).show();
        }
        else if(strText1.isEmpty() || strText2.isEmpty()){
            Toast.makeText(this, "Please enter all the equations", Toast.LENGTH_SHORT).show();
        }
        else if(!(((indexX) <= indexY+1) && (indexY < indexEqual))){
            Toast.makeText(this, "Please maintain the 'ax+by=c' format1", Toast.LENGTH_SHORT).show();
        }
        else if(!((indexX2 <= indexY2+1) && (indexY2 < indexEqual2))){
            Toast.makeText(this, "Please maintain the 'ax+by=c' format2", Toast.LENGTH_SHORT).show();
        }

        else{

            String xString, yString, xString2, yString2, equalString, equalString2;

            equalString = strText1.substring(indexEqual).replaceAll("\\s", "");
            double constant = getCo(equalString);
            equalString2 = strText2.substring(indexEqual2).replaceAll("\\s", "");
            double constant2 = getCo(equalString2);

            if (flagX == 0) xString = "0";
            else xString = strText1.substring(0, indexX - 1).replaceAll("\\s", "");
            if (flagY == 0) yString = "0";
            else yString = strText1.substring(indexX, indexY - 1).replaceAll("\\s", "");
            if (flagX2 == 0) xString2 = "0";
            else xString2 = strText2.substring(0, indexX2 - 1).replaceAll("\\s", "");
            if (flagY2 == 0) yString2 = "0";
            else yString2 = strText2.substring(indexX2, indexY2 - 1).replaceAll("\\s", "");

            if (xString.equals("+")) xString = "1";
            if (xString.equals("-")) xString = "-1";
            if (yString.equals("+")) yString = "1";
            if (yString.equals("-")) yString = "-1";
            if (xString2.equals("+")) xString2 = "1";
            if (xString2.equals("-")) xString2 = "-1";
            if (yString2.equals("+")) yString2 = "1";
            if (yString2.equals("-")) yString2 = "-1";
            if (xString.isEmpty()) xString = "1";
            if (xString2.isEmpty()) xString2 = "1";
            if (yString.isEmpty()) yString = "1";
            if (yString2.isEmpty()) yString2 = "1";

            xCoefficient = getCo(xString);
            yCoefficient = getCo(yString);

            xCoefficient2 = getCo(xString2);
            yCoefficient2 = getCo(yString2);

            double m = (yCoefficient * constant2 - constant * yCoefficient2);
            double n = (xCoefficient2 * constant - xCoefficient * constant2);
            double p = (yCoefficient * xCoefficient2 - xCoefficient * yCoefficient2);

            x = m / p;
            y = n / p;

            textView.setText(String.format("x = %.2f, y = %.2f", x, y));
        }

    }

    public static double getCo(String string) {
        double xCoefficient = 0;
        if (string.charAt(0) == '+') {
            xCoefficient = Double.parseDouble(string.substring(1));
        }
        if (string.charAt(0) == '-') {
            xCoefficient = -(Double.parseDouble(string.substring(1)));
        } else {
            xCoefficient = Double.parseDouble(string.substring(0));
        }
        return xCoefficient;
    }
}
