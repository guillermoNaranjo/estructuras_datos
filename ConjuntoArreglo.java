/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itam.com111020_02.collecciones.impl.arreglos;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import mx.itam.com111020_02.colecciones.Conjunto;

/**
 * Implementacion de la interfaz Conjunto utilizando como estructura de datos 
 * interna arreglos
 * @author GuillermoNaranjo 190240
 */
public class ConjuntoArreglo <E> extends CollecionArregloAbstracta<E> implements Conjunto<E> {
    
    public ConjuntoArreglo(int tamInicial, float factorCrecimiento){
        super(tamInicial, factorCrecimiento);
    }
    
    public ConjuntoArreglo(int tamInicial){
        super(tamInicial);
    }
    
    public ConjuntoArreglo(){
        super();
    }
    
    @Override
    public boolean add(E e) {
        boolean valorRegreso=false;
        
        if(!contains(e)){
            if(arreglo.length-1==ultimoIndice)
                incrementaArreglo();
            ultimoIndice++;
            arreglo[ultimoIndice]=e;
            valorRegreso=true;
        }
        return valorRegreso;
    }

    @Override
    public boolean remove(Object o) {
        int i;
        boolean remove=false;
        
        for(i=0; i<=ultimoIndice; i++){
            if(o==null)
                if(arreglo[i]==o)
                    break;
                else if(o.equals(arreglo[i]))
                    break;
        }
        if(i<=ultimoIndice){
            arreglo[i]=arreglo[ultimoIndice];
            arreglo[ultimoIndice]=null;
            ultimoIndice--;
            remove=true;
        }
        return remove;
    }
    
   

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean regreso=true;
        
        return regreso;
    }
    
}
