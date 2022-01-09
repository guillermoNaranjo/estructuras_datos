/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itam.com_111020_02.Proyecto;

/**
 *
 * @author GuillermoNaranjo
 */
public class Main {
    public static void main(String[]args){
        int num, n, i=0;
        double res;
        String s="5000*2+2-1000+3";
        String []ExpresionArreglo=new String[s.length()+1];
        String []ExpresionPostFija=new String[s.length()+1];
        
        ExpresionArreglo=EvalD.expresionToArreglo(s);
        ExpresionPostFija=eval5.notacionPosfija(ExpresionArreglo);
        while(i<ExpresionPostFija.length && ExpresionPostFija[i]!=null){
            System.out.println(ExpresionPostFija[i]);
            i++;
        }
        res=EvalD.evalPostFix(ExpresionPostFija);
        System.out.println(res);
        
    
        
            
        
        
    }
    
}
