/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolb;

/**
 *
 * @author mdani
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolB<String, Integer> arbol = new ArbolB<>(5);
        for (int i = 0; i < 10; i++) {
            arbol.insertar("fsd", i);
        }
        arbol.Graficar();
    }
}
