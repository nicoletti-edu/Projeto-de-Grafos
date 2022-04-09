package com.nicolettiedu.aed2.grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoletti.edu
 */
public class Vertice {

    public Integer distancia = Integer.MAX_VALUE;

    public Vertice predecessor = null;

    public boolean fechado = false;

    private final List<Aresta> arestas = new ArrayList<Aresta>();

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void adicionaAresta(Aresta a) {
        if (!arestas.contains(a)) {
            arestas.add(a);
        }
    }
}
