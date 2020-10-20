/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolb;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author mdani
 * @param <T>
 * @param <V>
 */
public class ArbolB<T extends Comparable<T>, V> {

    private int k;
    private Pagina raiz;

    public ArbolB(int k) {
        this.k = k;
        this.raiz = new Pagina(k);
    }

    public void insertar(T llave, V valor) {
        Clave newKey = new Clave(llave, valor);
        if (this.raiz.get(0) == null) {
            this.raiz.put(0, newKey);
        } else if (this.raiz.get(0).getIzquierda() == null) {
            int lugarinsertado = -1;
            Pagina node = this.raiz;    
            for (int i = 0; i < k; i++) {
                if (node.get(i) == null) {
                    node.put(i, newKey);
                    lugarinsertado = i;
                }
                if (lugarinsertado != -1) {
                    if (lugarinsertado == k - 1) {
                        int midle = node.getMax() / 2;
                        Clave llavecentral = node.get(midle);
                        Pagina derecho = new Pagina(k);
                        Pagina izquierdo = new Pagina(k);
                        int indiceIzquierdo = 0, indiceDerecho = 0;
                        for (int j = 0; j < node.getMax(); j++) {
                            if (node.get(j).compareTo(llavecentral.getKey()) < 0) {
                                izquierdo.put(indiceIzquierdo, node.get(j));
                                indiceIzquierdo++;
                                node.put(j, null);
                            } else if (node.get(j).compareTo(llavecentral.getKey()) > 0) {
                                derecho.put(indiceDerecho, node.get(j));
                                indiceDerecho++;
                                node.put(j, null);
                            }
                        }
                        node.put(midle, null);
                        this.raiz = node;
                        this.raiz.put(0, llavecentral);
                        izquierdo.setPaginaPadre(this.raiz);
                        derecho.setPaginaPadre(this.raiz);
                        llavecentral.setIzquierda(izquierdo);
                        llavecentral.setDerecha(derecho);
                        break;
                    } else {
                        break;
                    }
                }
            }
        } else if (this.raiz.get(0).getIzquierda() != null) {
            Pagina node = this.raiz;
            while (node.get(0).getIzquierda() != null) {
                int loop = 0;
                for (int i = 0; i < node.getMax(); i++) {
                    if (node.get(i) != null) {
                        if (node.get(i).compareTo(newKey.getKey()) > 0) {
                            node = node.get(i).getIzquierda();
                            break;
                        }
                    } else {
                        node = node.get(i - 1).getDerecha();
                        break;
                    }
                }
                if (loop == node.getMax()) {
                    node = node.get(loop - 1).getDerecha();
                }
            }
            int indiceColocado = colocarNodo(node, newKey);
            if (indiceColocado == k - 1) {
                while (node.getPaginaPadre() != null) {
                    int indicemedio = node.getMax() / 2;
                    Clave llaveCentral = node.get(indicemedio);
                    Pagina izquierdo = new Pagina(k);
                    Pagina derecho = new Pagina(k);
                    int indiceIzquierdo = 0, indiceDerecho = 0;
                    for (int i = 0; i < k; i++) {
                        if (node.get(i).compareTo(llaveCentral.getKey()) < 0) {
                            izquierdo.put(indiceIzquierdo, node.get(i));
                            indiceIzquierdo++;
                            node.put(i, null);
                        } else if (node.get(i).compareTo(llaveCentral.getKey()) > 0) {
                            derecho.put(indiceDerecho, node.get(i));
                            indiceDerecho++;
                            node.put(i, null);
                        }
                    }
                    node.put(indicemedio, null);
                    llaveCentral.setIzquierda(izquierdo);
                    llaveCentral.setDerecha(derecho);
                    node = node.getPaginaPadre();
                    izquierdo.setPaginaPadre(node);
                    derecho.setPaginaPadre(node);
                    for (int i = 0; i < k; i++) {
                        if (izquierdo.get(i) != null) {
                            if (izquierdo.get(i).getIzquierda() != null) {
                                izquierdo.get(i).getIzquierda().setPaginaPadre(izquierdo);
                            }
                            if (izquierdo.get(i).getDerecha() != null) {
                                izquierdo.get(i).getDerecha().setPaginaPadre(izquierdo);
                            }
                        }
                    }
                    for (int i = 0; i < k; i++) {
                        if (derecho.get(i) != null) {
                            if (derecho.get(i).getIzquierda() != null) {
                                derecho.get(i).getIzquierda().setPaginaPadre(izquierdo);
                            }
                            if (derecho.get(i).getDerecha() != null) {
                                derecho.get(i).getDerecha().setPaginaPadre(izquierdo);
                            }
                        }
                    }
                    int lugarColocado = colocarNodo(node, llaveCentral);
                    if (lugarColocado == k - 1) {
                        if (node.getPaginaPadre() == null) {
                            int indicecentralraiz = k / 2;
                            Clave llavecentralraiz = node.get(indicecentralraiz);
                            Pagina izquierdaRaiz = new Pagina(k);
                            Pagina derechaRaiz = new Pagina(k);
                            int indicederecharaiz = 0, indiceizquierdaraiz = 0;
                            for (int i = 0; i < k; i++) {
                                if (node.get(i).compareTo(llavecentralraiz.getKey()) < 0) {
                                    izquierdaRaiz.put(indiceizquierdaraiz, node.get(i));
                                    indiceizquierdaraiz++;
                                    node.put(i, null);
                                } else if (node.get(i).compareTo(llavecentralraiz.getKey()) > 0) {
                                    derechaRaiz.put(indiceizquierdaraiz, node.get(i));
                                    indicederecharaiz++;
                                    node.put(i, null);
                                }
                            }
                            node.put(indicecentralraiz, null);
                            node.put(0, llavecentralraiz);
                            for (int i = 0; i < k; i++) {
                                if (izquierdaRaiz.get(i) != null) {
                                    izquierdaRaiz.get(i).getIzquierda().setPaginaPadre(izquierdaRaiz);
                                    izquierdaRaiz.get(i).getDerecha().setPaginaPadre(izquierdaRaiz);
                                }
                            }
                            for (int i = 0; i < k; i++) {
                                if (derechaRaiz.get(i) != null) {
                                    derechaRaiz.get(i).getIzquierda().setPaginaPadre(derechaRaiz);
                                    derechaRaiz.get(i).getDerecha().setPaginaPadre(derechaRaiz);
                                }
                            }
                            llavecentralraiz.setIzquierda(izquierdaRaiz);
                            llavecentralraiz.setDerecha(derechaRaiz);
                            izquierdaRaiz.setPaginaPadre(node);
                            derechaRaiz.setPaginaPadre(node);
                            this.raiz = node;
                        }
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private int colocarNodo(Pagina node, Clave newKey) {
        int index = -1;
        for (int i = 0; i < k; i++) {
            if (node.get(i) == null) {
                boolean placed = false;
                for (int j = i - 1; j >= 0; j++) {
                    if (node.get(j).compareTo(newKey.getKey()) > 0) {
                        node.put(j + 1, node.get(j));
                    } else {
                        node.put(j + 1, newKey);
                        node.get(j).setDerecha(newKey.getIzquierda());
                        if (j + 2 < k && node.get(j + 2) != null) {
                            node.get(j + 2).setIzquierda(newKey.getDerecha());
                        }
                        placed = true;
                        break;
                    }
                }
                if (placed == false) {
                    node.put(0, newKey);
                    node.get(1).setIzquierda(newKey.getDerecha());
                }
                index = i;
                break;
            }
        }
        return index;
    }

    public void Graficar() {
        StringBuilder s = new StringBuilder();
        s.append("digraph G{\n").append("node[shape=record]\n");
        Graficar(this.raiz, s, new ArrayList<>(), null, 0);
        s.append("}");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("salida.dot");
            pw = new PrintWriter(fichero);
            pw.append(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();;
            }
            try {
                String cmd = "dot -Tpdf ./salida.dot -o imagen.pdf";
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }

    private void Graficar(Pagina actual, StringBuilder cad, ArrayList<Pagina> arr, Pagina padre, int pos) {
        if (actual == null) {
            return;
        }
        int j = 0;
        if (arr.contains(actual)) {
            arr.remove(actual);
            return;
        } else {
            arr.add(actual);
        }
        cad.append("node").append(actual.hashCode()).append("[label = \"");
        boolean enlace = true;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                return;   
            }else{
                if (enlace) {
                    if (i != actual.getMax() - 1) {
                        cad.append("<f").append(j).append(">|");
                    }else{
                        cad.append("<f").append(j).append(">");
                        break;
                    }
                    enlace = false;
                    i--;
                    j++;
                }else{
                    //cad.append("<f").append(j++).append(">").append(actual.get(i).getKey()).append("|");
                    cad.append("<f").append(j++).append(">").append(actual.get(i).getValor()).append("|");
                    enlace = true;
                    if (1 < actual.getMax() - 1) {
                        if (actual.get(i + 1) == null) {
                            cad.append("<f").append(j++).append(">");
                            break;
                        }
                    }
                }
            }
        }
        cad.append("\"]\n");
        int ji = 0;
        for (int i = 0; i < actual.getMax(); i++) {
            if (actual.get(i) == null) {
                break;
            }
            Graficar(actual.get(i).getIzquierda(), cad, arr, actual, ji++);
            ji++;
            Graficar(actual.get(i).getDerecha(), cad, arr, actual, ji++);
            ji--;
        }
        if(padre != null){
            cad.append("node").append(padre.hashCode()).append(":f").append(pos).append("->node").append(actual.hashCode()).append("\n");
        }
    }
}
