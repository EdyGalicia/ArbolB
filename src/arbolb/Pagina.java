/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbolb;

/**
 * 
 * @author Daniel
 */
public class Pagina {
    private int max;
    private Pagina paginaPadre;
    private Clave[] llaves;

    public Pagina(int max) {
        this.max = max;
        this.llaves = new Clave[max];
        paginaPadre = null;
    }
    public void put(int i, Clave llave){
        this.llaves[i] = llave;
    }
    public Clave get(int i){
        return this.llaves[i];
    }
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Pagina getPaginaPadre() {
        return paginaPadre;
    }

    public void setPaginaPadre(Pagina paginaPadre) {
        this.paginaPadre = paginaPadre;
    }

    public Clave[] getLlaves() {
        return llaves;
    }

    public void setLlaves(Clave[] llaves) {
        this.llaves = llaves;
    }
    
}
