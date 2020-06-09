package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    Button btnPerc, btnC, btnCE, btnDelete, btn1x, btnPow, btnSqrt, btnDiv, btnMulti, btnMinus, btnPlus, btnEqual;
    Button btnMminus, btnMC, btnMS, btnMR, btnMplus;
    EditText editText;
    Boolean plus, minus, multi, div, equal;
    Boolean czyJuzWypisanoRezultat = false;
    Double rezultat;
    Double coAktualnie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zgasWszystkieDzialania();
        rezultat = 0d;
        coAktualnie = 0d;

        findByView();
        setOnClickButtons();
    }


    public void findByView(){
        editText = findViewById(R.id.editText);
        editText.setText("0");

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPerc = findViewById(R.id.btnPerc);
        btn1x = findViewById(R.id.btn1x);
        btnCE = findViewById(R.id.btnCE);
        btnC = findViewById(R.id.btnC);
        btnDelete = findViewById(R.id.btnDelete);
        btnPow = findViewById(R.id.btnPow);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnDiv = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMulti);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnEqual = findViewById(R.id.btnEqual);
        btnMinus = findViewById(R.id.btnMinus);
        btnMminus = findViewById(R.id.btnMminus);
        btnPlus = findViewById(R.id.btnPlus);
        btnMplus = findViewById(R.id.btnMplus);
        btnMC = findViewById(R.id.btnMC);
        btnMS = findViewById(R.id.btnMS);
        btnMR = findViewById(R.id.btnMR);
    }


    public void setOnClickButtons(){

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPerc.setOnClickListener(this);
        btn1x.setOnClickListener(this);
        btnCE.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnPow.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMminus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMplus.setOnClickListener(this);
        btnMC.setOnClickListener(this);
        btnMS.setOnClickListener(this);
        btnMR.setOnClickListener(this);
    }


    public static String fmt(double d)
    {
        if(d == (int) d)
            return String.format("%d",(int)d);
        else
            return String.format("%s",d);
    }


    public void wcisnietoLiczbe(String cyfra){

            if (!czyJuzWypisanoRezultat){
                if (coAktualnie == 0d){
                    if (rezultat == 0d){
                        rezultat = Double.parseDouble(cyfra);
                        editText.setText(cyfra);
                    }
                    else {
                        String aktualnaLiczba = fmt(rezultat);
                        aktualnaLiczba += cyfra;
                        rezultat = Double.parseDouble(aktualnaLiczba);
                        editText.setText(fmt(rezultat));
                    }
                }
                else {
                    if (!equal){
                        String aktualnaLiczba = fmt(coAktualnie);
                        aktualnaLiczba += cyfra;
                        coAktualnie = Double.parseDouble(aktualnaLiczba);
                        editText.setText(fmt(coAktualnie));
                    }
                    else {
                        coAktualnie = 0d;
                        rezultat = Double.parseDouble(cyfra);
                        editText.setText(fmt(rezultat));
                        zgasWszystkieDzialania();
                    }
                }
            }
            else {
                coAktualnie = Double.parseDouble(cyfra);
                editText.setText(fmt(coAktualnie));
                czyJuzWypisanoRezultat = false;
            }
    }



    public void zgasWszystkieDzialania(){
        plus = false;
        minus = false;
        multi = false;
        div = false;
        equal = false;
    }

    public void wykonajDzialanie(){
        if (plus){
            rezultat += coAktualnie;
        }
        if (minus){
            rezultat -= coAktualnie;
        }
        if (div){
            rezultat /= coAktualnie;
        }
        if (multi){
            rezultat *= coAktualnie;
        }
    }

    public void wykonajOperacje(){
        wykonajDzialanie();
        editText.setText(fmt(rezultat));
        czyJuzWypisanoRezultat = true;
        zgasWszystkieDzialania();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn1:
                wcisnietoLiczbe("1");
            break;
            case R.id.btn2:
                wcisnietoLiczbe("2");
            break;
            case R.id.btn3:
                wcisnietoLiczbe("3");
            break;
            case R.id.btn4:
                wcisnietoLiczbe("4");
            break;
            case R.id.btn5:
                wcisnietoLiczbe("5");
            break;
            case R.id.btn6:
                wcisnietoLiczbe("6");
            break;
            case R.id.btn7:
                wcisnietoLiczbe("7");
            break;
            case R.id.btn8:
                wcisnietoLiczbe("8");
            break;
            case R.id.btn9:
                wcisnietoLiczbe("9");
            break;
            case R.id.btn0:
                wcisnietoLiczbe("0");
                break;
            case R.id.btnPlus:
                wykonajOperacje();
                plus = true;
            break;
            case R.id.btnMinus:
                wykonajOperacje();
                minus = true;
            break;
            case R.id.btnDiv:
                wykonajOperacje();
                div = true;
            break;
            case R.id.btnMulti:
                wykonajOperacje();
                multi = true;
            break;
            case R.id.btnEqual:

                wykonajDzialanie();
                coAktualnie = rezultat;
                editText.setText(fmt(rezultat));
                zgasWszystkieDzialania();
                equal = true;

            break;
            case R.id.btnC:
                zgasWszystkieDzialania();
                coAktualnie = 0d;
                rezultat = 0d;
                editText.setText("");

            break;
        }

    }
}
