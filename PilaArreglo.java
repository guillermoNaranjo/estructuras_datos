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
import mx.itam.com111020_02.colecciones.pila;

/**
 * Implementación de la interfaz pila utilizando arreglos en java 
 * @author Guillermon Naranjo 190240
 */
public class PilaArreglo<E> extends CollecionArregloAbstracta<E> implements pila<E>{
   
    
    public PilaArreglo(int tamInicial){
        super(tamInicial);
    }
    
    public PilaArreglo(){
        super();
    }
    
    public PilaArreglo(int capacidadInicial, float factorCrecimiento){
        super(capacidadInicial, factorCrecimiento);
    }
    
    /** 
     * {@inheritDoc }
     */
    
    @Override
    public boolean push(E nuevoElemento) {
        boolean alta;
        
        if(ultimoIndice==arreglo.length-1)
            incrementaArreglo();
        if(nuevoElemento!=null){
            ultimoIndice++;
            arreglo[ultimoIndice]=nuevoElemento;
            alta=true;
        }
        else
            alta=false;
        return alta;
    }
    
    /**
     * 
     * @return el objeto que fue eliminado o null si la pila esta vacía  
     */

    @Override
    public E pop() {
        E valor;
        
        if(ultimoIndice>=0){
             valor=arreglo[ultimoIndice];
             arreglo[ultimoIndice]=null;
             ultimoIndice--;
        } else
            valor=null;
        return valor;
    }
    
    /**
     * 
     * @return el objeto que se encuentra en la ultimoIndice de la pila 
     */

    @Override
    public E peek() {
        E e=null;
        
        if(ultimoIndice>=0)
            e=arreglo[ultimoIndice];
        
        return e;
    }


    
   
    @Override
    public boolean add(E e) {
        return push(e);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Solo se puede eliminar el objeto"
                + "en la cabeza");
        
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(c==null)
            return false;
        
        if(c.size()==0)
            return true;
        
        for(Object obj : c){
            if(!contains(obj))
                return false;
        }
        
       return true;
       
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
