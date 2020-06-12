package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    private Button btnPerc, btnC, btnCE, btn1x, btnSqrt, btnDiv, btnMulti, btnMinus, btnPlus, btnEqual, btnComma, btnNegate;
    private ImageButton btnDelete;
    private Button btnM_Minus, btnMC, btnMS, btnMR, btnM_Plus;
    private EditText editText;
    private Boolean plus, minus, multi, div, equal, oneX;
    private Boolean czyJuzWypisanoRezultat;
    private Boolean czySprobowanoPodzielicPrzez0;
    private Boolean czyAktualnyZPrzecinkiem;
    private Boolean czyPrzyciskiSaWylaczone, czyPrzyciskiMemorySaWylaczone;
    private Boolean czyNiepowodzenieSQRT;
    private Double rezultat, coAktualnie;
    private Double memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByView();
        setOnClickButtons();
        skasujWszystko();
        memory = 0d;
        wlaczLubWylaczPrzyciskiMemory(false);
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
        btnNegate = findViewById(R.id.btnNegate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnDiv = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMulti);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnEqual = findViewById(R.id.btnEqual);
        btnMinus = findViewById(R.id.btnMinus);
        btnM_Minus = findViewById(R.id.btnMminus);
        btnPlus = findViewById(R.id.btnPlus);
        btnM_Plus = findViewById(R.id.btnMplus);
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
        btnNegate.setOnClickListener(this);
        btnComma.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnM_Minus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnM_Plus.setOnClickListener(this);
        btnMC.setOnClickListener(this);
        btnMS.setOnClickListener(this);
        btnMR.setOnClickListener(this);
    }

    private String format(double d){
        if(editText.getText().toString().contains(".") && !equal){
            return editText.getText().toString();
        }
        else
        {
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
        try
        {
            String aktualnyNapis = editText.getText().toString();
            Double aktualnyRezultat = rezultat;
            Double aktualneCoAktualnie = coAktualnie;

            if (!czyJuzWypisanoRezultat)
            {
                if (coAktualnie == 0d)
                {
                    if (rezultat == 0d && !editText.getText().toString().contains("."))
                    {
                        wypiszRezultat(cyfra);
                    }
                    else
                    {
                        String aktualnaLiczba = format(rezultat);
                        aktualnaLiczba += cyfra;
                        editText.setText(aktualnaLiczba);
                        if((editText.getText().toString().contains(".")) && czyDzialanieZapalone()){
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
                    wypiszRezultat(cyfra);
                    equal = false;
                }
                else
                {
                    coAktualnie = Double.parseDouble(cyfra);
                    editText.setText(fmt(coAktualnie));
                }

                if (oneX){
                    skasujWszystko();
                    wypiszRezultat(cyfra);
                }

                czyJuzWypisanoRezultat = false;
            }

            sprawdzCzyNieZaDlugi(aktualnyNapis, aktualnyRezultat, aktualneCoAktualnie);

        }
        catch(Exception ignored){}
    }

    private void wypiszRezultat(String cyfra){
        String aktualnaLiczba = format(Double.parseDouble(cyfra));
        rezultat = Double.parseDouble(aktualnaLiczba);
        editText.setText(aktualnaLiczba);
    }


    private void zgasWszystkieDzialania(){
        plus = false;
        minus = false;
        multi = false;
        div = false;
        equal = false;
        oneX  = false;
    }

    private void wykonajDzialanie()
    {
        oneX = false;
        sprawdzCzyNieMaPrzecinka();

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
        wlaczJesliPrzyciskiWylaczone();

        switch (v.getId()){
            case R.id.btn1: wcisnietoLiczbe("1"); break;
            case R.id.btn2: wcisnietoLiczbe("2"); break;
            case R.id.btn3: wcisnietoLiczbe("3"); break;
            case R.id.btn4: wcisnietoLiczbe("4"); break;
            case R.id.btn5: wcisnietoLiczbe("5"); break;
            case R.id.btn6: wcisnietoLiczbe("6"); break;
            case R.id.btn7: wcisnietoLiczbe("7"); break;
            case R.id.btn8: wcisnietoLiczbe("8"); break;
            case R.id.btn9: wcisnietoLiczbe("9"); break;
            case R.id.btn0: wcisnietoLiczbe("0"); break;
            case R.id.btnPlus:
                dzialanieArytmetyczne();
                plus = true;
            break;
            case R.id.btnMinus:
                dzialanieArytmetyczne();
                minus = true;
            break;
            case R.id.btnDiv:
                dzialanieArytmetyczne();
                div = true;
            break;
            case R.id.btnMulti:
                dzialanieArytmetyczne();
                multi = true;
            break;
            case R.id.btnEqual: equal();  break;
            case R.id.btnC: skasujWszystko(); break;
            case R.id.btnCE: ce(); break;
            case R.id.btnDelete: delete(); break;
            case R.id.btnPerc: percent(); break;
            case R.id.btn1x: oneX(); break;
            case R.id.btnComma: comma(); break;
            case R.id.btnSqrt: sqrt(); break;
            case R.id.btnMC: MC(); break;
            case R.id.btnMR: MR(); break;
            case R.id.btnMS: MS(); break;
            case R.id.btnMminus: M_Minus(); break;
            case R.id.btnMplus: M_Plus(); break;
            case R.id.btnNegate: negate(); break;
        }
    }

    private void negate()
    {
        Double zanegowanaLiczba = -Double.parseDouble(editText.getText().toString());

        if(czyDzialanieZapalone())
        {
            coAktualnie = zanegowanaLiczba;
            czyJuzWypisanoRezultat = false;
        }
        else
        {
            rezultat = zanegowanaLiczba;
        }

        editText.setText(fmt(zanegowanaLiczba));

    }

    private void MR(){
        if(czyDzialanieZapalone())
        {
            coAktualnie = memory;
            czyJuzWypisanoRezultat = false;
        }
        else
        {
            rezultat = memory;
        }

        editText.setText(fmt(memory));
    }

    private void MS(){
        memory = Double.parseDouble(editText.getText().toString());
        wlaczLubWylaczPrzyciskiMemory(true);
    }

    private void MC(){
        memory = 0d;
        wlaczLubWylaczPrzyciskiMemory(false);
    }

    private void M_Plus(){
        memory += Double.parseDouble(editText.getText().toString());
        wlaczLubWylaczPrzyciskiMemory(true);
    }

    private void M_Minus(){
        memory -= Double.parseDouble(editText.getText().toString());
        wlaczLubWylaczPrzyciskiMemory(true);
    }

    private void equal(){
        wykonajDzialanie();
        if (!czySprobowanoPodzielicPrzez0)
        {
            editText.setText(fmt(rezultat));
            coAktualnie = 0d;
            zgasWszystkieDzialania();
            czyJuzWypisanoRezultat = true;
            equal = true;
        }
        else
        {
            nieMoznaDzielicPrzezZero();
            czySprobowanoPodzielicPrzez0 = false;
        }
    }


    private void sqrt(){
        sprawdzCzyNieMaPrzecinka();

        if(czyJuzWypisanoRezultat && czyDzialanieZapalone()){
            if(coAktualnie == 0d && Double.parseDouble(editText.getText().toString()) != 0)
            {
                coAktualnie = trySqrt(rezultat);
                sprawdzCzyNiepowodzenieSQRT(coAktualnie);

            }
            else
            {
                coAktualnie = trySqrt(coAktualnie);
                sprawdzCzyNiepowodzenieSQRT(coAktualnie);
            }
        }
        else
        {
            if (czyDzialanieZapalone())
            {
                coAktualnie = trySqrt(coAktualnie);
                sprawdzCzyNiepowodzenieSQRT(coAktualnie);
            }
            else
            {
                rezultat = trySqrt(rezultat);
                sprawdzCzyNiepowodzenieSQRT(rezultat);
            }
        }

        czyJuzWypisanoRezultat = true;
    }

    private void sprawdzCzyNiepowodzenieSQRT(Double coWyswietlic){
        if(!czyNiepowodzenieSQRT){
            editText.setText(fmt(coWyswietlic));
        }
        else
        {
            czyNiepowodzenieSQRT = false;
        }
    }


    private Double trySqrt(Double coSqrt)
    {
        if(!Double.isNaN(Math.sqrt(coSqrt)))
        {
            return Math.sqrt(coSqrt);
        }
        else
        {
            skasujWszystko();
            wlaczLubWylaczPrzyciski(false);
            editText.setText("Złe dane wejściowe");
            czyNiepowodzenieSQRT = true;
            return 0d;
        }
    }

    private void ce()
    {
        if(czyDzialanieZapalone())
        {
            coAktualnie = 0d;
            editText.setText("0");
        }
        else
        {
            skasujWszystko();
        }
    }


    private void percent()
    {
        if (!czyJuzWypisanoRezultat && !czyDzialanieZapalone())
        {
            rezultat = 0d;
            editText.setText("0");
        }
        else
        {
            if ((czyDzialanieZapalone() || oneX) && coAktualnie == 0d && Double.parseDouble(editText.getText().toString()) != 0d) {
                if (oneX)
                {
                    rezultat = 0d;
                }
                else
                {
                    coAktualnie = rezultat * rezultat / 100;
                }
            }
            else
            {
                coAktualnie = rezultat * coAktualnie / 100;
            }

            editText.setText(fmt(coAktualnie));
            czyJuzWypisanoRezultat = true;
        }
    }

    private void dzialanieArytmetyczne()
    {
        wykonajOperacje();
        zgasWszystkieDzialania();
        czyJuzWypisanoRezultat = true;
    }


    private void comma()
    {
        if(equal){
         skasujWszystko();
        }

        if (czyJuzWypisanoRezultat)
        {
            czyJuzWypisanoRezultat = false;
            czySprobowanoPodzielicPrzez0 = false;
            czyNiepowodzenieSQRT = false;
            czyAktualnyZPrzecinkiem = false;
            coAktualnie = 0d;
            editText.setText("0");
        }

        if (!editText.getText().toString().contains(".")){
            editText.setText(editText.getText()+".");
        }
    }


    private void delete()
    {
        try
        {
            if (czyZawieraLiczboweZnaki())
            {
                String poUsunieciuOstatniegoZnaku;

                if (!czyJuzWypisanoRezultat){
                    if (editText.getText().toString().length() > 1){
                        poUsunieciuOstatniegoZnaku = removeLastChar(editText.getText().toString());
                    }
                    else
                    {
                        poUsunieciuOstatniegoZnaku = "0";
                    }

                    if (czyDzialanieZapalone()){
                        coAktualnie = Double.parseDouble(poUsunieciuOstatniegoZnaku);
                        if (!czyJuzWypisanoRezultat){
                            editText.setText(poUsunieciuOstatniegoZnaku);
                        }
                    }
                    else
                    {
                        editText.setText(poUsunieciuOstatniegoZnaku);
                        rezultat = Double.parseDouble(poUsunieciuOstatniegoZnaku);
                    }
                }
            }
            else
            {
                if(!editText.getText().toString().contains("E")){
                    skasujWszystko();
                }
            }

        }
        catch (Exception ignored){ }
    }


    private void oneX()
    {
        oneX = true;
        sprawdzCzyNieMaPrzecinka();

        if (rezultat == 0d && (!czyDzialanieZapalone() && !czyJuzWypisanoRezultat))
        {
            nieMoznaDzielicPrzezZero();
        }
        else
        {
            if (czyDzialanieZapalone())
            {
                if(coAktualnie != 0d)
                {
                    coAktualnie = 1 / coAktualnie;
                    editText.setText(fmt(coAktualnie));
                }
                else
                {
                    if((rezultat != 0d && Double.parseDouble(editText.getText().toString()) != 0d)){
                        coAktualnie = 1 / rezultat;
                        editText.setText(fmt(coAktualnie));
                        czyJuzWypisanoRezultat = false;
                    }
                    else
                    {
                        nieMoznaDzielicPrzezZero();
                    }
                }
            }
            else
            {
                if(rezultat != 0d)
                {
                    rezultat = 1 / rezultat;
                    editText.setText(fmt(rezultat));
                }
                else
                {
                    nieMoznaDzielicPrzezZero();
                }
            }
        }
        czyJuzWypisanoRezultat = true;
    }

    private boolean czyDzialanieZapalone()
    {
        return plus || minus || multi || div;
    }

    private void wlaczJesliPrzyciskiWylaczone(){
        if(czyPrzyciskiSaWylaczone)
        {
            wlaczLubWylaczPrzyciski(true);
            czyPrzyciskiSaWylaczone = false;
        }
    }

    private void sprawdzCzyNieMaPrzecinka(){
        if (czyAktualnyZPrzecinkiem){
            coAktualnie = Double.parseDouble(editText.getText().toString());
            czyAktualnyZPrzecinkiem = false;
        }
    }

    private void sprawdzCzyNieZaDlugi(String aktualnyNapis, Double aktualnyRezultat, Double aktualneCoAktualnie)
    {
        if (editText.getText().toString().replace(".","").length() > 11){
            coAktualnie = aktualneCoAktualnie;
            rezultat = aktualnyRezultat;
            editText.setText(aktualnyNapis);
        }
    }

    private void skasujWszystko()
    {
        czyNiepowodzenieSQRT = false;
        czyJuzWypisanoRezultat = false;
        czySprobowanoPodzielicPrzez0 = false;
        czyAktualnyZPrzecinkiem = false;
        czyPrzyciskiSaWylaczone = false;
        zgasWszystkieDzialania();
        coAktualnie = 0d;
        rezultat = 0d;
        editText.setText("0");
    }

    private void nieMoznaDzielicPrzezZero()
    {
        skasujWszystko();
        editText.setText("Nie można dzielić przez zero");
        wlaczLubWylaczPrzyciski(false);
    }

    private void wlaczLubWylaczPrzyciskiMemory(boolean onOFF){
        btnMR.setEnabled(onOFF);
        btnMC.setEnabled(onOFF);
        czyPrzyciskiMemorySaWylaczone = !onOFF;
    }


    private void wlaczLubWylaczPrzyciski(boolean onOFF)
    {
        czyPrzyciskiSaWylaczone = !onOFF;
        btnPerc.setEnabled(onOFF);
        btn1x.setEnabled(onOFF);
        btnComma.setEnabled(onOFF);
        btnSqrt.setEnabled(onOFF);
        btnDiv.setEnabled(onOFF);
        btnMulti.setEnabled(onOFF);
        btnMinus.setEnabled(onOFF);
        btnPlus.setEnabled(onOFF);
        btnM_Minus.setEnabled(onOFF);
        btnM_Plus.setEnabled(onOFF);
        btnMC.setEnabled(onOFF);
        btnMS.setEnabled(onOFF);
        btnMR.setEnabled(onOFF);
        btnNegate.setEnabled(onOFF);
        if(onOFF && czyPrzyciskiMemorySaWylaczone){
            wlaczLubWylaczPrzyciskiMemory(false);
        }

    }

    private boolean czyZawieraLiczboweZnaki()
    {
        return  !editText.getText().toString().equals("Infinity") &&
                !editText.getText().toString().equals("NaN") &&
                !editText.getText().toString().equals("Nie można dzielić przez zero") &&
                !editText.getText().toString().equals("Złe dane wejściowe") &&
                !editText.getText().toString().contains("E");
    }

}
