package com.example.wtfkn.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.lang.Double;

public class MainActivity extends AppCompatActivity {
    boolean primeraVez=true;
    String ultimaOperacion="";
    Double numeroAnt;
    Double resultado=null;
    boolean otroNumero=true;
    String ultimoBoton="a";
    int memoria=0;
    TextView num;
    TextView op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         num=(TextView) findViewById(R.id.numeros);
         op= (TextView) findViewById(R.id.operacion);
    }
    void pulsar(View v) {
        String str = v.getTag().toString();
        switch (str){
            case "x":
                operar(str);
                break;
            case "/":
                operar(str);
                break;
            case "+":
                operar(str);
                break;
            case "-":
                operar(str);
                break;
            case "sen":
                operar(str);
                num.setText(Math.asin(Double.parseDouble((String) num.getText()))+"");
                primeraVez=true;
                otroNumero=true;
                op.setText("");
                resultado=null;
                numeroAnt=null;
                break;
            case "cos":
                operar(str);
                num.setText(Math.acos(Double.parseDouble((String) num.getText()))+"");
                primeraVez=true;
                otroNumero=true;
                op.setText("");
                resultado=null;
                numeroAnt=null;
                break;
            case "tg":
                operar(str);
                num.setText(Math.atan(Double.parseDouble((String) num.getText()))+"");
                primeraVez=true;
                otroNumero=true;
                op.setText("");
                resultado=null;
                numeroAnt=null;
                break;
            case "raiz":
                operar(str);
                num.setText(Math.sqrt(Double.parseDouble((String) num.getText()))+"");
                primeraVez=true;
                otroNumero=true;
                op.setText("");
                resultado=null;
                numeroAnt=null;
                break;
            case "c":
                primeraVez=true;
                otroNumero=true;
                num.setText("0");
                op.setText("");
                resultado=null;
                numeroAnt=null;
                break;
            case ".":
                if(!op.getText().equals("") && siUltimoBotonEsValido(ultimoBoton)){
                    op.setText(op.getText()+str);
                    num.setText(num.getText()+str);
                    ultimoBoton=".";
                }
                break;
            case "=":
                if(resultado!=null && siUltimoBotonEsValido(ultimoBoton)) {
                    num.setText("" + calcular(ultimaOperacion, resultado, Double.parseDouble((String) num.getText())));
                    primeraVez = true;
                    otroNumero = true;
                    resultado=null;
                }else if(numeroAnt!=null && siUltimoBotonEsValido(ultimoBoton)){
                    resultado=calcular(ultimaOperacion, numeroAnt, Double.parseDouble((String) num.getText()));
                    num.setText("" +resultado);
                    primeraVez = true;
                    otroNumero = true;
                }
                primeraVez=true;
                otroNumero=true;
                op.setText("");
                resultado=null;
                break;
            case "MS":
                memoria=(int) Double.parseDouble((String) num.getText());
                break;
            case  "MU":
                if(memoria!=0) {
                    num.setText("" + memoria);
                    op.setText(op.getText() + "" + memoria);
                }
                break;
            case "D":

                if(num.getText().length()>0 && !num.getText().equals("0") && op.getText().length()>0){
                    num.setText(num.getText().subSequence(0,num.getText().length()-1));
                    op.setText(op.getText().subSequence(0,op.getText().length()-1));
                }
                if(num.getText().equals("")){
                    num.setText("0");
                    otroNumero=true;
                }
                break;
            default:
                op.setText(op.getText()+str);
                if(otroNumero){
                    num.setText(str);
                    otroNumero=false;
                }else {
                    num.setText(num.getText() + str);
                }
                break;
        }
        if(!str.equals("D") && !str.equals(".") && !str.equals("MS") && !str.equals("MU")) {
            ultimoBoton = str;
        }
    }

    private void operar(String str) {
        if(num.getText()!="" && siUltimoBotonEsValido(ultimoBoton)) {
            //585*464-544
            if(!primeraVez){
                if(resultado!=null) {
                    resultado = calcular(ultimaOperacion, resultado, Double.parseDouble((String) num.getText()));
                    num.setText("" + resultado);
                    primeraVez = true;
                }else{
                    resultado= calcular(ultimaOperacion,numeroAnt,Double.parseDouble((String) num.getText()));
                    num.setText("" + resultado);
                    primeraVez = true;
                }
            }else{
                    numeroAnt = Double.parseDouble((String) num.getText());
            }
            op.setText(op.getText()+str);
            otroNumero=true;
            primeraVez=false;
            ultimaOperacion=str;
        }else{
            String aux =(String) op.getText();
            aux= aux.substring(0, aux.length()-1);
            op.setText(aux+str);
            ultimaOperacion=str;
        }
    }
    /* private void operar2(String str){
        if(num.getText()!="" && siUltimoBotonEsValido(ultimoBoton)) {
            if(!primeraVez){
                if(resultado!=null) {
                    resultado = calcular(ultimaOperacion, numeroAnt, Double.parseDouble((String) num.getText()));
                    num.setText("" + resultado);
                    primeraVez = true;
                }else{
                    resultado= calcular(ultimaOperacion,numeroAnt,Double.parseDouble((String) num.getText()));
                    num.setText("" + resultado);
                    primeraVez = true;
                }
            }else{
                numeroAnt = Double.parseDouble((String) num.getText());
            }
            op.setText(op.getText()+str);
            otroNumero=true;
            primeraVez=false;
            ultimaOperacion=str;
        }else{
            String aux =(String) op.getText();
            aux= aux.substring(0, aux.length()-1);
            op.setText(aux+str);
            ultimaOperacion=str;
        }
    }*/

    private Double calcular(String operacion,Double a,Double b){
        switch (operacion){
            case "x":
                return a*b;
            case "/":
                return a/b;
            case "+":
                return a+b;
            case "-":
                return a-b;
            default:
                return 0.0;
        }
    }

    private boolean siUltimoBotonEsValido(String str){
        if(str.equals("x") || str.equals("/") || str.equals("+") || str.equals("-") || str.equals(".")){
            return false;
        }else{
            return true;
        }
    }
}