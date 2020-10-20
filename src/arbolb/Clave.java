/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbolb;

/**
 * 
 * @author Daniel
 * @param <T>
 * @param <V>
 */
public class Clave<T extends Comparable<T>,V> implements Comparable {
    T key;
    V valor;
    Pagina derecha;
    Pagina izquierda;

    public Clave(T key, V valor) {
        this.key = key;
        this.valor = valor;
        this.derecha = null;
        this.izquierda = null;
    }
    
    @Override
    public int compareTo(Object o) {    
        T aux = (T) o;
        if(this.key.compareTo(aux)<0){
            return -1;
        }else if(this.key.compareTo(aux)>0){
            return 1;
        }
        return 0;
    }

    public T getKey() {
        return key;
        
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public Pagina getDerecha() {
        return derecha;
    }

    public void setDerecha(Pagina derecha) {
        this.derecha = derecha;
    }

    public Pagina getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Pagina izquierda) {
        this.izquierda = izquierda;
    }
    
}
