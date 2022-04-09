package com.nicolettiedu.aed2.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nicoletti.edu
 */
public class Menu {

    private final Scanner read = new Scanner(System.in);

    private final List<Vertice> vertices = new ArrayList<Vertice>();

    public Menu() {
    }

    public void mostraMenu() {
        while (true) {
            System.out.println("----Menu----\n");
            System.out.println("1 - Adicionar Vértice\n");
            System.out.println("2 - Adicionar Aresta\n");
            System.out.println("3 - Ver Vértices\n");
            System.out.println("4 - Buscar Menor Caminho Entre Dois Vértices\n");
            System.out.println("5 - Sair\n");
            int escolha = read.nextInt();
            read.nextLine();
            if (escolha > 0 && escolha < 6) {
                switch (escolha) {
                    case 1:
                        adicionarVertice();
                        break;
                    case 2:
                        adicionarAresta();
                        break;
                    case 3:
                        mostrarVertices();
                        break;
                    case 4:
                        buscarMenorCaminho();
                        break;
                    case 5:
                        return;
                }
            }
        }
    }

    private void adicionarVertice() {
        if (vertices.size() == 20) {
            System.out.println("Limite de vértices atingido!\n");
            return;
        }
        Vertice v = new Vertice();
        vertices.add(v);
        System.out.println("Vértice " + vertices.indexOf(v) + " criado!\n");
    }

    private void adicionarAresta() {
        System.out.println("----Inserir Aresta----\n");
        System.out.println("Insira o id do vértice de origem: ");
        Vertice origem = escolherVertice();
        System.out.println("Insira o id do vértice de destino: ");
        Vertice destino = escolherVertice();
        System.out.println("Peso: ");
        while (!read.hasNextInt()) {
            System.out.println("Apenas numeros inteiros!\n");
            read.next();
        }
        int peso = read.nextInt();
        read.nextLine();
        peso = (peso < 0 ? -peso : peso);
        origem.adicionaAresta(new Aresta(destino, peso));
    }

    private Vertice escolherVertice() {
        Integer idEscolha;
        do {
            idEscolha = lerInputId();
            if (idEscolha == null) {
                break;
            }
            if (idEscolha < 0 || idEscolha > vertices.size() + 1) {
                System.out.println("Indice inválido!\n");
            } else {
                return vertices.get(idEscolha);
            }
        } while (idEscolha < 0 || idEscolha > vertices.size() + 1);
        return null;
    }

    private Integer lerInputId() {
        while (true) {
            String str = read.nextLine();
            if ("".equals(str)) {
                return null;
            }
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida!");
            }
        }
    }

    private void mostrarVertices() {
        System.out.println("----Vértices----\n");
        Vertice v;
        for (Integer i = 0; i < vertices.size(); i++) {
            v = vertices.get(i);
            System.out.println("Vértice " + i.toString() + "\n");
            System.out.println("Destinos: \n");
            v.getArestas().forEach(aresta -> {
                System.out.print("V" + String.valueOf(vertices.indexOf(aresta.destino)) + " ");
            });
            System.out.println("");
            v.getArestas().forEach(aresta -> {
                System.out.print(aresta.peso.toString() + " ");
            });
            System.out.println("");
            System.out.println("------------");
        }
    }

    private void buscarMenorCaminho() {
        System.out.println("----Buscar Menor Caminho Entre Dois Vértices----");
        System.out.println("Indique o vértice inicial: ");
        Vertice origem = escolherVertice();
        System.out.println("Indique o vértice final: ");
        Vertice destino = escolherVertice();
        origem.distancia = 0;
        //Enquanto existem vertices abertos
        while (vertices.stream().anyMatch(vertice -> (!vertice.fechado))) {
            Vertice verticeAtual = buscaVerticeAbertoMenorDistancia();
            verticeAtual.fechado = true;
            verticeAtual.getArestas().forEach(aresta -> {
                Vertice verticeAresta = aresta.destino;
                verticeAresta.predecessor = verticeAtual;
                verticeAresta.distancia = verticeAtual.distancia + aresta.peso;
            });
        }
        mostraCaminho(destino);
        zerarVertices();
    }

    private Vertice buscaVerticeAbertoMenorDistancia() {
        Vertice menor = null;
        for (Vertice vertice : vertices) {
            if (menor == null) {
                if (!vertice.fechado) {
                    menor = vertice;
                } else {
                    continue;
                }
            }
            if (vertice.distancia < menor.distancia && !vertice.fechado) {
                menor = vertice;
            }
        }
        return menor;
    }

    private void mostraCaminho(Vertice destino) {
        System.out.println("Caminho: ");
        Vertice verticeAtual = destino;
        do {
            System.out.println("V" + vertices.indexOf(verticeAtual));
            verticeAtual = verticeAtual.predecessor;
        } while (verticeAtual.predecessor != null);
        System.out.println("V" + vertices.indexOf(verticeAtual));
    }

    private void zerarVertices() {
        vertices.forEach(vertice -> {
            vertice.fechado = false;
            vertice.predecessor = null;
        });
    }
}
