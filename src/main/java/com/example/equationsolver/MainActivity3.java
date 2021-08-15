package com.example.equationsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText text1, text2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        text1 = findViewById(R.id.editTextTextPersonName);
        text2 = findViewById(R.id.editTextTextPersonName2);
        textView = findViewById(R.id.textView);

    }
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void BTNPush(View view){
        double x = 0, y = 0;
        String strText1 = text1.getText().toString();
        String strText2 = text2.getText().toString();

        int check = checkWrongString(strText1, strText2);
        if(check == 1){
            Toast.makeText(this, "Enter all the equations", Toast.LENGTH_SHORT).show();
        }

        else if(check == 2){
            Toast.makeText(this, "Input not recognised", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Use x, y and z as variables", Toast.LENGTH_SHORT).show();
        }

        else if(check == 3 || check == 9 || check == 12 || check == 15 || check == 18 ){
            Toast.makeText(this, "1st expression is not an equation", Toast.LENGTH_SHORT).show();
        }

        else if(check == 4 || check == 10 || check == 13 || check == 16 || check == 19){
            Toast.makeText(this, "2nd expression is not an equation", Toast.LENGTH_SHORT).show();
        }

        else if(check == 6){
            Toast.makeText(this, "Don't use same variable more than once", Toast.LENGTH_SHORT).show();
        }

        else if(check == 7){
            Toast.makeText(this, "Don't use same variable more than once", Toast.LENGTH_SHORT).show();
        }

        else if(check == 21){
            Toast.makeText(this, "Don't use more than one constant in an equation", Toast.LENGTH_SHORT).show();
        }

        else if(check == 22){
            Toast.makeText(this, "Don't use more than one constant in an equation", Toast.LENGTH_SHORT).show();
        }

        else if(check == 23){
            Toast.makeText(this, "Don't use more than one constant in an equation", Toast.LENGTH_SHORT).show();
        }

        else if(check == 24){
            Toast.makeText(this, "Use coefficients before the variables", Toast.LENGTH_SHORT).show();
        }

        else {

            double xCoefficient1 = getXCo(strText1)*getSignX(strText1);
            double yCoefficient1 = getYCo(strText1)*getSignY(strText1);

            double xCoefficient2 = getXCo(strText2)*getSignX(strText2);
            double yCoefficient2 = getYCo(strText2)*getSignY(strText2);

            double constant1 = getCons(strText1)*getSignCons(strText1);
            double constant2 = getCons(strText2)*getSignCons(strText2);

            double detA = xCoefficient1*yCoefficient2 - yCoefficient1*xCoefficient2;
            double detX = constant1*yCoefficient2 - yCoefficient1*constant2;
            double detY = xCoefficient1*constant2 - constant1*xCoefficient2;

            if(detA == 0){
                textView.setText("The system of equations has no feasible solution");
            }
            else{
                x = detX/detA;
                y = detY/detA;
                textView.setText(String.format("x = %.2f, y = %.2f", x, y));
            }
        }
    }

    public static int checkWrongString(String string1, String string2){
        if(string1.isEmpty() || string2.isEmpty()){
            return 1;
        }
        string1.replaceAll("\\s", "");
        string2.replaceAll("\\s", "");

        for(int i = 0; i < string1.length(); i++){
            if(!(string1.charAt(i) == '1' || string1.charAt(i) == '2' || string1.charAt(i) == '3' || string1.charAt(i) == '4' || string1.charAt(i) == '5' ||
                    string1.charAt(i) == '6' || string1.charAt(i) == '7' || string1.charAt(i) == '8' || string1.charAt(i) == '9' || string1.charAt(i) == '0' ||
                    string1.charAt(i) == '+' || string1.charAt(i) == '-' || string1.charAt(i) == '=' || string1.charAt(i) == 'x' || string1.charAt(i) == 'y' ||
                    string1.charAt(i) == 'z' || string1.charAt(i) == ' ')){
                return 2;
            }
        }
        for(int i = 0; i < string2.length(); i++){
            if(!(string2.charAt(i) == '1' || string2.charAt(i) == '2' || string2.charAt(i) == '3' || string2.charAt(i) == '4' || string2.charAt(i) == '5' ||
                    string2.charAt(i) == '6' || string2.charAt(i) == '7' || string2.charAt(i) == '8' || string2.charAt(i) == '9' || string2.charAt(i) == '0' ||
                    string2.charAt(i) == '+' || string2.charAt(i) == '-' || string2.charAt(i) == '=' || string2.charAt(i) == 'x' || string2.charAt(i) == 'y' ||
                    string2.charAt(i) == ' ')){
                return 2;
            }
        }

        if(!(string1.contains("="))){
            return 3;
        }
        else if(!(string2.contains("="))){
            return 4;
        }

        int XFlag = 0, YFlag = 0, equalFlag = 0;
        for(int i = 0; i < string1.length(); i++){
            if(string1.charAt(i) == 'x'){
                XFlag++;
            }
            if(string1.charAt(i) == 'y'){
                YFlag++;
            }

            if(string1.charAt(i) == '='){
                equalFlag++;
            }
        }
        if(XFlag > 1 || YFlag > 1){
            return 6;
        }
        if(equalFlag > 1){
            return 12;
        }

        XFlag = 0; YFlag = 0; equalFlag = 0;
        for(int i = 0; i < string2.length(); i++){
            if(string2.charAt(i) == 'x'){
                XFlag++;
            }
            if(string2.charAt(i) == 'y'){
                YFlag++;
            }
            if(string2.charAt(i) == '='){
                equalFlag++;
            }
        }
        if(XFlag > 1 || YFlag > 1){
            return 7;
        }
        if(equalFlag > 1){
            return 13;
        }

        if(string1.charAt(string1.length()-1) == '='){
            return 9;
        }
        if(string2.charAt(string2.length()-1) == '='){
            return 10;
        }

        if(string1.contains("++") || string1.contains("+-") || string1.contains("--") || string1.contains("-+") || string1.contains("-=") || string1.contains("+=")){
            return 15;
        }
        if(string2.contains("++") || string2.contains("+-") || string2.contains("--") || string2.contains("-+") || string2.contains("-=") || string2.contains("+=")){
            return 16;
        }
        if(string1.charAt(string1.length()-1) == '+' || string1.charAt(string1.length()-1) == '-'){
            return 18;
        }
        if(string2.charAt(string2.length()-1) == '+' || string2.charAt(string2.length()-1) == '-'){
            return 19;
        }
        //x(0...9), y(0...9), z(0...9)
        if(wrongFormat(string1) == 1 || wrongFormat(string2) == 1){
            return 24;
        }
        //(0...9)+, (0...9)-, (0...9)=
        int consTerm = 0;
        consTerm = calculateConsTerm(string1);
        if(consTerm > 1){
            return 21;
        }
        consTerm = 0;
        consTerm = calculateConsTerm(string2);
        if(consTerm > 1){
            return 22;
        }

        return 0;
    }

    public static double getXCo(String string){
        int indexX = 0, flagX = 0;
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == 'x'){
                indexX = i;
                flagX = 1;
                break;
            }
        }
        if(flagX == 0) return 0;
        if(indexX == 0) return 1;
        for(int i = indexX; i >= 0; i--){
            if(string.charAt(i) == '+'){
                if(string.substring(i+1, indexX).replaceAll("\\s", "").isEmpty()){
                    return 1;
                }
                else{
                    return Double.parseDouble(string.substring(i, indexX).replaceAll("\\s", ""));
                }
            }
            else if(string.charAt(i) == '-'){
                if(string.substring(i+1, indexX).replaceAll("\\s", "").isEmpty()){
                    return -1;
                }
                else{
                    return Double.parseDouble(string.substring(i, indexX).replaceAll("\\s", ""));
                }
            }
            else if(string.charAt(i) == '='){
                if(string.substring(i+1, indexX).replaceAll("\\s", "").isEmpty()){
                    return 1;
                }
                else{
                    return Double.parseDouble(string.substring(i+1, indexX).replaceAll("\\s", ""));
                }
            }
        }
        return Double.parseDouble(string.substring(0, indexX).replaceAll("\\s", ""));
    }

    public static double getYCo(String string){
        int indexY = 0, flagY = 0;
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == 'y'){
                indexY = i;
                flagY = 1;
                break;
            }
        }
        if(flagY == 0) return 0;
        if(indexY == 0) return 1;
        for(int i = indexY; i >= 0; i--){
            if(string.charAt(i) == '+'){
                if(string.substring(i+1, indexY).replaceAll("\\s", "").isEmpty()){
                    return 1;
                }
                else{
                    return Double.parseDouble(string.substring(i, indexY).replaceAll("\\s", ""));
                }
            }
            else if(string.charAt(i) == '-'){
                if(string.substring(i+1, indexY).replaceAll("\\s", "").isEmpty()){
                    return -1;
                }
                else{
                    return Double.parseDouble(string.substring(i, indexY).replaceAll("\\s", ""));
                }
            }
            else if(string.charAt(i) == '='){
                if(string.substring(i+1, indexY).replaceAll("\\s", "").isEmpty()){
                    return 1;
                }
                else{
                    return Double.parseDouble(string.substring(i+1, indexY).replaceAll("\\s", ""));
                }
            }
        }
        return Double.parseDouble(string.substring(0, indexY).replaceAll("\\s", ""));
    }

    public static double getCons(String string){

        string.replaceAll("//s", "");
        int p = string.length()-1;
        if(string.charAt(string.length()-1) == '1' || string.charAt(string.length()-1) == '2' || string.charAt(string.length()-1) == '3' || string.charAt(string.length()-1) == '4' ||
                string.charAt(string.length()-1) == '5' || string.charAt(string.length()-1) == '6' || string.charAt(string.length()-1) == '7' || string.charAt(string.length()-1) == '8' ||
                string.charAt(string.length()-1) == '9' || string.charAt(string.length()-1) == '0'){
            while(!(string.charAt(p) == '+' || string.charAt(p) == '-' || string.charAt(p) == '=')){
                p--;
            }
            if(string.charAt(p) == '-') return -(Double.parseDouble(string.substring(p+1)));
            else return Double.parseDouble(string.substring(p+1));
        }

        int c = 0, flag = 0;
        if(string.charAt(0) == '+' || string.charAt(0) == '-') c = 1;
        int j = 0, k = 0;
        for(int i = c; i < string.length(); i++){
            if((string.charAt(i) == '+' || string.charAt(i) == '-' || string.charAt(i) == '=') && (string.charAt(i-1) == '0' ||
                    string.charAt(i-1) == '1' ||string.charAt(i-1) == '2' ||string.charAt(i-1) == '3' ||string.charAt(i-1) == '4' ||
                    string.charAt(i-1) == '5' ||string.charAt(i-1) == '6' ||string.charAt(i-1) == '7' ||string.charAt(i-1) == '8' ||
                    string.charAt(i-1) == '9')){
                j = i-1;
                flag = 1;
                break;
            }
        }
        if(flag == 1){

            k = j;
            while(!(string.charAt(j) == '+' || string.charAt(j) == '-' || string.charAt(j) == '=' || j == 0)){
                j--;
            }
            if(j == 0) return Double.parseDouble(string.substring(j, k+1));
            else if(string.charAt(j) == '+') return Double.parseDouble(string.substring(j+1,k+1));
            else if(string.charAt(j) == '-') return -(Double.parseDouble(string.substring(j+1,k+1)));
            else if(string.charAt(j) == '=') return Double.parseDouble(string.substring(j+1,k+1));
        }
        return 0;
    }

    public static int calculateConsTerm(String string){
        int count = 0;
        if(string.contains("0+")){
            count++;
        }
        if(string.contains("1+")){
            count++;
        }
        if(string.contains("2+")){
            count++;
        }
        if(string.contains("3+")){
            count++;
        }
        if(string.contains("4+")){
            count++;
        }
        if(string.contains("5+")){
            count++;
        }
        if(string.contains("6+")){
            count++;
        }
        if(string.contains("7+")){
            count++;
        }
        if(string.contains("8+")){
            count++;
        }
        if(string.contains("9+")){
            count++;
        }
        if(string.contains("0-")){
            count++;
        }
        if(string.contains("1-")){
            count++;
        }
        if(string.contains("2-")){
            count++;
        }
        if(string.contains("3-")){
            count++;
        }
        if(string.contains("4-")){
            count++;
        }
        if(string.contains("5-")){
            count++;
        }
        if(string.contains("6-")){
            count++;
        }
        if(string.contains("7-")){
            count++;
        }
        if(string.contains("8-")){
            count++;
        }
        if(string.contains("9-")){
            count++;
        }
        if(string.contains("0=")){
            count++;
        }
        if(string.contains("1=")){
            count++;
        }
        if(string.contains("2=")){
            count++;
        }
        if(string.contains("3=")){
            count++;
        }
        if(string.contains("4=")){
            count++;
        }
        if(string.contains("5=")){
            count++;
        }
        if(string.contains("6=")){
            count++;
        }
        if(string.contains("7=")){
            count++;
        }
        if(string.contains("8=")){
            count++;
        }
        if(string.contains("9=")){
            count++;
        }
        if(string.charAt(string.length()-1) == '1' || string.charAt(string.length()-1) == '2' || string.charAt(string.length()-1) == '3' || string.charAt(string.length()-1) == '4' ||
                string.charAt(string.length()-1) == '5' || string.charAt(string.length()-1) == '6' || string.charAt(string.length()-1) == '7' || string.charAt(string.length()-1) == '8' ||
                string.charAt(string.length()-1) == '9' || string.charAt(string.length()-1) == '0'){
            count++;
        }
        return  count;
    }

    public static int wrongFormat(String string){
        if(string.contains("x0") || string.contains("x1") || string.contains("x2") || string.contains("x3") || string.contains("x4") || string.contains("x5") ||
                string.contains("x6") || string.contains("x7") || string.contains("x8") || string.contains("x9") || string.contains("y0") || string.contains("y1") ||
                string.contains("y2") || string.contains("y3") || string.contains("y4") || string.contains("y5") || string.contains("y6") || string.contains("y7") ||
                string.contains("y8") || string.contains("y9")){
            return 1;
        }
        return 0;
    }

    public static int getSignX(String string){
        int i = string.indexOf('x');
        int j = string.indexOf('=');
        if(i < j) return 1;
        return -1;
    }

    public static int getSignY(String string){
        int i = string.indexOf('y');
        int j = string.indexOf('=');
        if(i < j) return 1;
        return -1;
    }

    public static int getSignCons(String string){
        if(string.charAt(string.length()-1) == '1' || string.charAt(string.length()-1) == '2' || string.charAt(string.length()-1) == '3' || string.charAt(string.length()-1) == '4' ||
                string.charAt(string.length()-1) == '5' || string.charAt(string.length()-1) == '6' || string.charAt(string.length()-1) == '7' || string.charAt(string.length()-1) == '8' ||
                string.charAt(string.length()-1) == '9' || string.charAt(string.length()-1) == '0'){
            return 1;
        }
        if(string.contains("0=")){
            return -1;
        }
        if(string.contains("1=")){
            return -1;
        }
        if(string.contains("2=")){
            return -1;
        }
        if(string.contains("3=")){
            return -1;
        }
        if(string.contains("4=")){
            return -1;
        }
        if(string.contains("5=")){
            return -1;
        }
        if(string.contains("6=")){
            return -1;
        }
        if(string.contains("7=")){
            return -1;
        }
        if(string.contains("8=")){
            return -1;
        }
        if(string.contains("9=")){
            return -1;
        }
        int i = string.indexOf('=');
        int j = 0;
        if(string.contains("0+")){
            j = string.indexOf("0+");
        }
        if(string.contains("1+")){
            j = string.indexOf("1+");
        }
        if(string.contains("2+")){
            j = string.indexOf("2+");
        }
        if(string.contains("3+")){
            j = string.indexOf("3+");
        }
        if(string.contains("4+")){
            j = string.indexOf("4+");
        }
        if(string.contains("5+")){
            j = string.indexOf("5+");
        }
        if(string.contains("6+")){
            j = string.indexOf("6+");
        }
        if(string.contains("7+")){
            j = string.indexOf("7+");
        }
        if(string.contains("8+")){
            j = string.indexOf("8+");
        }
        if(string.contains("9+")){
            j = string.indexOf("9+");
        }
        if(string.contains("0-")){
            j = string.indexOf("0-");
        }
        if(string.contains("1-")){
            j = string.indexOf("1-");
        }
        if(string.contains("2-")){
            j = string.indexOf("2-");
        }
        if(string.contains("3-")){
            j = string.indexOf("3-");
        }
        if(string.contains("4-")){
            j = string.indexOf("4-");
        }
        if(string.contains("5-")){
            j = string.indexOf("5-");
        }
        if(string.contains("6-")){
            j = string.indexOf("6-");
        }
        if(string.contains("7-")){
            j = string.indexOf("7-");
        }
        if(string.contains("8-")){
            j = string.indexOf("8-");
        }
        if(string.contains("9-")){
            j = string.indexOf("9-");
        }
        if(j < i){
            return -1;
        }
        return 1;

    }


}