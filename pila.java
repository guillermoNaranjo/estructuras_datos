/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itam.com111020_02.colecciones;

import java.util.Collection;

/**
 * Interfaz básica para implementar el TDA Pila
 * @author Guillermo Naranjo 190240
 * @param <E> Tipo parmetrizado de los elementos que guarda la pila 
 */
public interface pila<E> extends Collection<E> {
    /**
     * 
     * @param nuevoElemento
     * @return regresa true o false dependiando de si se añadió el elemento o no 
     */
    
    boolean push(E nuevoElemento);
    
    /**
     * elimina el elemento de la cabeza de la pila 
     * @return el elemento eliminado 
     */
    
    E pop();
    
    /**
     * observa el elemento de la cabeza de la pila 
     * @return regresa el último elemento añadido 
     */
    
    E peek();
    
    /**
     * popea n elementos, en caso de no tener n elementos o hace nada 
     * @param n 
     */
    
    
    
}
