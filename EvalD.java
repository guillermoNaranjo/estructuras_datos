/*
 * The MIT License
 *
 * Copyright 2020 Guillermo Naranjo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining p copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mx.itam.com_111020_02.Proyecto;

import java.util.ArrayList;
import mx.itam.com111020_02.colecciones.pila;
import mx.itam.com111020_02.collecciones.impl.arreglos.PilaArreglo;


/**
 *
 * @author Guillermo Naranjo 
 */
public class EvalD {
    /**
     * 
     * @param s el string que se va p evaluar
     * @return si el string evaluado tiene parentesis asi ()
     */
    public static boolean revisorParentesis(String s){
        boolean balanceada=true;
        int i=0;
        pila<Character> a = new PilaArreglo();
        char c;
        
        while(i<s.length() && balanceada){
            c= s.charAt(i);
            if(c=='('){
                a.push(c);
            } else{
                if(c==')' && a.isEmpty()){
                    balanceada = false;
                } else{
                    if(c==')' && a.peek()=='('){
                        a.pop();
                    }
                }
            }
            i++;
        }
        
        if(!a.isEmpty()){
            balanceada = false;
        }
        
        return balanceada;
    }
    
    /**
     * 
     * @param s
     * @return revisa que no haya caracteres dentro de la expresion 
     */
    
    public static boolean revisorCaracteres(String s){
        boolean sinC=true;
        int i=0;
        char c;
        
        while(sinC && i<s.length()){
            c=s.charAt(i);
            if( !isOperand(c) && !isOperator(c) && c!='(' && c!=')') {
                sinC=false;
            }
             i++;
        }    
        return sinC;  
    }
    
    /**
     * 
     * @param c char que va a revisar 
     * @return determina si el char dado es un operador 
     */
    
    protected static boolean isOperator(char c){
        boolean operador=false;
        
        if(c=='/' || c=='%' || c=='+' || c=='-' || c=='*'){
            operador=true;
        }
        return operador;
    }
    
    /**
     * 
     * @param c char que va a revisar
     * @return determina si el char dado es un operando
     */
    
    protected static boolean isOperand(char c){
        boolean operand=false;
        
        if(c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' ||  c=='7' || c=='8' || c=='9' || c=='0')
            operand=true;
        return operand;
    }
    
    /**
     * 
     * @param s String de expresion a modificar 
     * @return la expresion ingresada en forma de arreglo 
     */
    
    protected static String[] expresionToArreglo(String s){
        String[] exp = new String[s.length()+1];
        int k, i=0;
        String aux;
        
        while(i<s.length()){
            if(s.charAt(i)=='(' || s.charAt(i)==')' || isOperator(s.charAt(i))){
                exp[i]=s.charAt(i)+"";
                i++;
            } else{
                k=0;
                aux="";
                while(i+k<s.length() && isOperand(s.charAt(i+k))){
                    aux+=s.charAt(i+k)+"";
                    k++;
                }
                exp[i]=aux;
                i+=k;
            }
        }
        for(int j=0; j<exp.length; j++){
            if(exp[j]==null){
                for(int l=j; l<exp.length-1; l++){
                    exp[l]=exp[l+1];
                }
            }
        }
        
        return exp;
    }
    
    /**
     * 
     * @param c char a revisar
     * @return el valor de la jerarquia de la operación
     */
    
    protected static int precedence(char c){
        int precedence=0;
        
        if(c=='*' || c=='/' || c=='%')
            precedence=2;
        else if(c=='+' || c=='-')
            precedence=1;
        
        return precedence;    
    }
    
    /**
     * 
     * @param expArray arreglo de la expresion dada
     * @return la modifica de expresion  infija a postfija
     */
    
    protected static String[] notacionPosfija(String[] expArray){
        pila<String> operadores= new PilaArreglo();
        String []posF=new String[expArray.length];
        int k=0;
        char c;
        
        operadores.push("(");
        expArray[expArray.length-1]=")";
        
        
        for(int i=0; i<expArray.length; i++){
            if(expArray[i]!=null){
                c=expArray[i].charAt(0);
                if(isOperand(c)){
                    posF[k]=expArray[i];
                    k++;
                }  
                if(c=='('){
                    operadores.push(expArray[i]);
                }
                if(isOperator(c)){
                    while(operadores.peek()!=null && isOperator(operadores.peek().charAt(0)) && precedence(c)<=precedence(operadores.peek().charAt(0))){
                        posF[k]=operadores.pop();
                        k++;
                    }
                    operadores.push(expArray[i]);
                } 
                if(c==')'){
                    while(operadores.peek()!=null && isOperator(operadores.peek().charAt(0)) && operadores.peek().charAt(0)!='('){
                        posF[k]=operadores.pop();
                        k++;
                    }
                    operadores.pop();
                }
            }
        }
        return posF;
    }
    
    /**
     * 
     * @param posfija array de la expresión en formato postfijo
     * @return regresa un double con la evaluación
     */
    
    public static double evalPostFix(String[] posfija){
        pila<Double> p=new PilaArreglo();
        char c;
        double num1, num2;
        
        for(int i=0; i<posfija.length; i++){
            if(posfija[i]!=null){
                c = posfija[i].charAt(0);
                if(isOperand(c))
                    p.push(Double.parseDouble(posfija[i]));
                else{
                    num1=p.pop();
                    num2=p.pop();
                
                    switch(c){  
                      
                        case '/': 
                            p.push(num2/num1); 
                            break; 
                            
                        case '+': 
                           p.push(num2+num1); 
                            break;    
                      
                        case '*': 
                            p.push(num2*num1); 
                            break; 
                         
                        case '-': 
                            p.push(num2- num1); 
                            break;    
                    
                        case '%':
                            p.push(num2%num1);
                    }                
                }    
            }
        }
        return p.pop();
    }
}
