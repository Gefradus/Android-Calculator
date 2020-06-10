package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    private Button btnPerc, btnC, btnCE, btnDelete, btn1x, btnPow, btnSqrt, btnDiv, btnMulti, btnMinus, btnPlus, btnEqual, btnComma;
    private Button btnMminus, btnMC, btnMS, btnMR, btnMplus;
    private EditText editText;
    private Boolean plus, minus, multi, div, equal;
    private Boolean czyJuzWypisanoRezultat;
    private Boolean czySprobowanoPodzielicPrzez0;
    private Boolean czyAktualnyZPrzecinkiem;
    private Boolean czyPrzyciskiSaWylaczone;
    private Double rezultat, coAktualnie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByView();
        setOnClickButtons();
        skasujWszystko();
    }


    private void findByView(){
        editText = findViewById(R.id.editText);

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
        btnComma = findViewById(R.id.btnComma);
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


    private void setOnClickButtons(){

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
        btnComma.setOnClickListener(this);
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

    private String format(double d){
        if(editText.getText().toString().contains(".")){
            return editText.getText().toString();
        }
        else{
            return fmt(d);
        }
    }


    private String fmt(double d)
    {
        if(d == (int) d)
            return String.format("%d",(int)d);
        else
            return String.format("%s",d);
    }


    private void wcisnietoLiczbe(String cyfra){
        try{
            if (!czyJuzWypisanoRezultat)
            {
                if (coAktualnie == 0d)
                {
                    if (rezultat == 0d && !editText.getText().toString().contains("."))
                    {
                        String aktualnaLiczba = format(Double.parseDouble(cyfra));
                        rezultat = Double.parseDouble(aktualnaLiczba);
                        editText.setText(aktualnaLiczba);
                    }
                    else
                    {
                        String aktualnaLiczba = format(rezultat);
                        aktualnaLiczba += cyfra;
                        editText.setText(aktualnaLiczba);
                        if((editText.getText().toString().contains(".")) && (plus || minus || div || multi)){
                            czyAktualnyZPrzecinkiem = true;
                        }
                        else
                        {
                            rezultat = Double.parseDouble(aktualnaLiczba);
                        }
                    }
                }
                else
                {
                    String aktualnaLiczba = format(coAktualnie);
                    aktualnaLiczba += cyfra;
                    coAktualnie = Double.parseDouble(aktualnaLiczba);
                    editText.setText(aktualnaLiczba);
                }
            }
            else
            {
                if (equal)
                {
                    coAktualnie = 0d;
                    String aktualnaLiczba = fmt(Double.parseDouble(cyfra));
                    rezultat = Double.parseDouble(aktualnaLiczba);
                    editText.setText(aktualnaLiczba);
                    equal = false;
                }
                else
                {
                    coAktualnie = Double.parseDouble(cyfra);
                    editText.setText(fmt(coAktualnie));
                }

                czyJuzWypisanoRezultat = false;
            }
        }
        catch(Exception ignored){}
    }


    private void zgasWszystkieDzialania(){
        plus = false;
        minus = false;
        multi = false;
        div = false;
        equal = false;
    }

    private void wykonajDzialanie(){
            if (czyAktualnyZPrzecinkiem){
                coAktualnie = Double.parseDouble(editText.getText().toString());
                czyAktualnyZPrzecinkiem = false;
            }

            if (plus)
            {
                rezultat += coAktualnie;
                coAktualnie = 0d;
            }
            if (minus)
            {
                rezultat -= coAktualnie;
                coAktualnie = 0d;
            }
            if (div)
            {
                if (coAktualnie != 0d)
                {
                    rezultat /= coAktualnie;
                    coAktualnie = 0d;
                }
                else
                {
                    czySprobowanoPodzielicPrzez0 = true;
                }
            }
            if (multi)
            {
                rezultat *= coAktualnie;
                coAktualnie = 0d;
            }

    }

    private void wykonajOperacje(){
        if (!czyJuzWypisanoRezultat)
        {
            wykonajDzialanie();
        }
        if (!czySprobowanoPodzielicPrzez0) {
            editText.setText(fmt(rezultat));
            czyJuzWypisanoRezultat = true;
        }
        else
        {
            nieMoznaDzielicPrzezZero();
            czySprobowanoPodzielicPrzez0 = false;
        }
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    public void onClick(View v) {
        if(czyPrzyciskiSaWylaczone){
            wlaczLubWylaczPrzyciski(true);
            czyPrzyciskiSaWylaczone = false;
        }

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
                zgasWszystkieDzialania();
                czyJuzWypisanoRezultat = true;
                plus = true;
            break;
            case R.id.btnMinus:
                wykonajOperacje();
                zgasWszystkieDzialania();
                czyJuzWypisanoRezultat = true;
                minus = true;
            break;
            case R.id.btnDiv:
                wykonajOperacje();
                zgasWszystkieDzialania();
                czyJuzWypisanoRezultat = true;
                div = true;
            break;
            case R.id.btnMulti:
                wykonajOperacje();
                zgasWszystkieDzialania();
                czyJuzWypisanoRezultat = true;
                multi = true;
            break;
            case R.id.btnEqual:
                wykonajDzialanie();
                if (!czySprobowanoPodzielicPrzez0) {
                    editText.setText(fmt(rezultat));
                    czyJuzWypisanoRezultat = true;
                    equal = true;
                    coAktualnie = 0d;
                    zgasWszystkieDzialania();
                }
                else
                {
                    nieMoznaDzielicPrzezZero();
                    czySprobowanoPodzielicPrzez0 = false;
                }
            break;

            case R.id.btnC:
               skasujWszystko();
            break;

            case R.id.btnCE:
                if(plus || minus || div || multi){
                    coAktualnie = 0d;
                }
                else {
                    rezultat = 0d;
                }
                editText.setText("0");
            break;

            case R.id.btnDelete:
                try{
                    if (czyZawieraLiczboweZnaki()){
                        String poUsunieciuOstatniegoZnaku;
                        if (editText.getText().toString().length() > 1 && !equal){
                            if (plus || minus || div || multi)
                            {
                                poUsunieciuOstatniegoZnaku = editText.getText().toString();
                            }
                            else {
                                poUsunieciuOstatniegoZnaku = removeLastChar(editText.getText().toString());
                            }
                        }
                        else
                        {
                            poUsunieciuOstatniegoZnaku = "0";
                        }

                        editText.setText(poUsunieciuOstatniegoZnaku);

                        if (plus || minus || div || multi){
                            coAktualnie = Double.parseDouble(poUsunieciuOstatniegoZnaku.replace(".",""));
                        }
                        else
                        {
                            rezultat = Double.parseDouble(poUsunieciuOstatniegoZnaku.replace(".",""));
                        }
                    }
                    else //if(!editText.getText().toString().contains("E"))
                    {
                        skasujWszystko();
                    }

                }catch (Exception ignored){}

            break;

            case R.id.btnPerc:
                if (!(coAktualnie == 0d && !czyJuzWypisanoRezultat)){
                    coAktualnie = rezultat * coAktualnie / 100;
                    editText.setText(fmt(coAktualnie));
                }
                else {
                    editText.setText("0");
                    rezultat = 0d;
                }
            break;

            case R.id.btn1x:

                if (rezultat == 0d && !czyJuzWypisanoRezultat){
                   nieMoznaDzielicPrzezZero();
                }
                else {
                    if (plus || minus || div || multi){
                        if(coAktualnie != 0d) {
                            coAktualnie = 1 / coAktualnie;
                            editText.setText(fmt(coAktualnie));
                        }
                        else{
                            if(rezultat != 0d && !editText.getText().toString().equals("0")){
                                coAktualnie = 1 / rezultat;
                                editText.setText(fmt(coAktualnie));
                            }
                            else{
                                nieMoznaDzielicPrzezZero();
                            }
                        }
                    }
                    else {
                        if(rezultat != 0d){
                            rezultat = 1 / rezultat;
                            editText.setText(fmt(rezultat));
                        }
                        else{
                            nieMoznaDzielicPrzezZero();
                        }
                    }
                }
            break;

            case R.id.btnComma:
                if (czyJuzWypisanoRezultat){
                    skasujWszystko();
                }

                if (!editText.getText().toString().contains(".")){
                   editText.setText(editText.getText()+".");
                }
            break;

        }
    }

    private void skasujWszystko(){
        czyJuzWypisanoRezultat = false;
        czySprobowanoPodzielicPrzez0 = false;
        czyAktualnyZPrzecinkiem = false;
        czyPrzyciskiSaWylaczone = false;
        zgasWszystkieDzialania();
        coAktualnie = 0d;
        rezultat = 0d;
        editText.setText("0");
    }

    private void nieMoznaDzielicPrzezZero(){
        skasujWszystko();
        editText.setText("Nie można dzielić przez zero");
        wlaczLubWylaczPrzyciski(false);
    }


    public void wlaczLubWylaczPrzyciski(boolean onOFF){
        czyPrzyciskiSaWylaczone = !onOFF;
        btnPerc.setEnabled(onOFF);
        btn1x.setEnabled(onOFF);
        btnComma.setEnabled(onOFF);
        btnPow.setEnabled(onOFF);
        btnSqrt.setEnabled(onOFF);
        btnDiv.setEnabled(onOFF);
        btnMulti.setEnabled(onOFF);
        btnMinus.setEnabled(onOFF);
        btnPlus.setEnabled(onOFF);
        btnMinus.setEnabled(onOFF);
        btnMminus.setEnabled(onOFF);
        btnPlus.setEnabled(onOFF);
        btnMplus.setEnabled(onOFF);
        btnMC.setEnabled(onOFF);
        btnMS.setEnabled(onOFF);
        btnMR.setEnabled(onOFF);
    }

    private boolean czyZawieraLiczboweZnaki(){
        return  !editText.getText().toString().equals("Infinity") &&
                //!editText.getText().toString().contains("E") &&
                !editText.getText().toString().equals("Nie można dzielić przez zero");
    }

}
