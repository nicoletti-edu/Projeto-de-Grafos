package com.nicolettiedu.aed2.grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoletti.edu
 */
public class Vertice {

    public Integer peso;

    private final List<Vertice> destinos = new ArrayList();

    private final List<Vertice> chegadas = new ArrayList();

    private final List<Vertice> arestas = new ArrayList();

    public Vertice(Integer peso) {
        this.peso = peso;
    }

    public List<Vertice> getDestinos() {
        return destinos;
    }

    public List<Vertice> getChegadas() {
        return chegadas;
    }

    public Integer getPesoArestas() {
        Integer valor = 0;
        valor = arestas.stream().map(aresta -> aresta.peso).reduce(valor, Integer::sum);
        return valor;
    }

    public void adicionaDestino(Vertice v) {
        adicionaAresta(v);
        if (!destinos.contains(v)) {
            destinos.add(v);
        }
    }

    public void adicionaChegada(Vertice v) {
        adicionaAresta(v);
        if (!chegadas.contains(v)) {
            chegadas.add(v);
        }
    }

    private void adicionaAresta(Vertice v) {
        if (!arestas.contains(v)) {
            arestas.add(v);
        }
    }

}
