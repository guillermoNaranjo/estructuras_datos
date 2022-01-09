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

/**
 * Clase generica abstracta en la que se implementan estructuras de datos y 
 * metodos comunes para todas las implementaciones de colecciones basadas en 
 * arreglos 
 * @author GuillermoNaranjo
 */
abstract public class CollecionArregloAbstracta<E> implements Collection<E>{
    /**
     * Contiene los elementos guardados en el conjunto 
     */
    protected E[]arreglo;
    /**
     * Indica el lugar del ultimo elemento valido del arreglo
     */
    protected int ultimoIndice;
    
    /**
     * factor para incrementar el tamaño del arreglo
     */
    
    private float factorCrecimiento;
    
    /**
     * 
     * @param capacidadInicial tamaño del arreglo 
     * @param factorCrecimiento indica el factor de crecimiento para incrementar 
     * el tamaño del arreglo interno 
     */
    
    public CollecionArregloAbstracta(int capacidadInicial, float factorCrecimiento){
        if(factorCrecimiento>1)
            this.factorCrecimiento=factorCrecimiento;
        else 
            throw new IllegalArgumentException("Argumento de crecimiento invalido");
        
        if(capacidadInicial<0)
            capacidadInicial=-capacidadInicial;
        arreglo=(E[])new Object[capacidadInicial];
        ultimoIndice=-1;
        
    }
    
    /**
     * 
     * @param capacidadInicial capacidad inicial del arreglo 
     */
    
    public CollecionArregloAbstracta(int capacidadInicial){
        this(capacidadInicial,2);
        
    }
    
    public CollecionArregloAbstracta(){
        this(10,2);
    }
    
    /**
     * aumenta el tamaño del arreglo 
     */
    
    protected void incrementaArreglo(){
        int factorCrecimiento=2;
        
        arreglo=Arrays.copyOf(arreglo, arreglo.length*factorCrecimiento);
    }
    
    //interfaz collection
    
    @Override
    public int size() {
        return ultimoIndice+1;
    }

    @Override
    public boolean isEmpty() {
        return ultimoIndice==-1;
    }

    @Override 
    public boolean contains(Object o) { 
        for (int i = 0; i <= ultimoIndice; ++i) { 
            Object elemento_i = arreglo[i]; 
            if (o == null) { 
                if (elemento_i == o){ 
                   return true; 
                }
            } else { 
                if (o.equals(elemento_i)) {
                    return true;
                }
            } 
        } 
        return false;  
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>(){
            private int indice=ultimoIndice;
            @Override
            public boolean hasNext(){
                return indice>=0;
                
            }
            
            @Override
            public E next(){
                if(indice<0)
                    throw new NoSuchElementException("el arreglo no tiene más elementos");
                return arreglo[indice--];
            }
        };
    }
    
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arreglo, ultimoIndice+1);
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
    public boolean addAll(Collection<? extends E> c) {
        boolean valorRegreso=false;
        
        for(E obj : c)
            if(add(obj))
                valorRegreso=true;
        return valorRegreso;  
    }
    
    @Override
    public <T> T[] toArray(T[]a){
        if(a.length<ultimoIndice+1)
            a=Arrays.copyOf(a, ultimoIndice+1);
        int i=0; 
        for(E Objeto:this)
            a[i++]=(T) Objeto;
        for(int j=i; j<a.length; j++)
            a[j]=null;
        return a;
    }
    
    @Override 
    public void clear() { 
        Arrays.fill(arreglo, null); 
        ultimoIndice = -1; 
    }
}
