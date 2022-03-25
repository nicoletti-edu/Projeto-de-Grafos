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

    private final List<Vertice> vertices = new ArrayList();

    public Menu() {
    }

    public void mostraMenu() {
        while (true) {
            System.out.println("----Menu----\n");
            System.out.println("1 - Adicionar Vértice\n");
            System.out.println("2 - Ver Vértices\n");
            System.out.println("3 - Ver Pesos das Arestas de Um Vértice\n");
            System.out.println("4 - Sair\n");
            int escolha = read.nextInt();
            if (escolha > 0 && escolha < 5) {
                switch (escolha) {
                    case 1:
                        adicionarVertice();
                        break;
                    case 2:
                        mostrarVertices();
                        break;
                    case 3:
                        mostrarPesoArestas();
                        break;
                    case 4: {
                        return;
                    }
                }
            }
        }
    }

    private void adicionarVertice() {
        System.out.println("----Inserir Vértice----\n");
        System.out.println("Peso: ");
        while (!read.hasNextInt()) {
            System.out.println("Apenas numeros inteiros!\n");
            read.next();
        }
        int peso = read.nextInt();
        peso = (peso < 0 ? -peso : peso);
        Vertice v = new Vertice(peso);
        if (vertices.isEmpty()) {
            vertices.add(v);
            return;
        }
        System.out.println("Vertices Destino\n");
        System.out.println("Id do Destino(Não Insira Nada Para Avançar): ");
        Integer idEscolha;
        do {
            read.nextLine();
            idEscolha = lerInputId();
            if (idEscolha == null) {
                break;
            }
            if (idEscolha < 0 || idEscolha > vertices.size() + 1) {
                System.out.println("Indice inválido!\n");
                continue;
            }
            Vertice destino = vertices.get(idEscolha);
            v.adicionaDestino(destino);
            destino.adicionaChegada(v);
        } while (idEscolha < 0 || idEscolha > vertices.size() + 1);
        vertices.add(v);
    }

    private Integer lerInputId() {
        String str = read.nextLine();
        if ("".equals(str)) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            System.out.println("Entrada inválida!");
            return lerInputId();
        }
    }

    private void mostrarVertices() {
        System.out.println("----Vértices----\n");
        Vertice v;
        for (Integer i = 0; i < vertices.size(); i++) {
            v = vertices.get(i);
            System.out.println("Vértice " + i.toString() + "\n");
            System.out.println("Peso: " + v.peso.toString() + "\n");
            System.out.println("Destinos: \n");
            v.getDestinos().forEach(destino -> {
                System.out.print(String.valueOf(vertices.indexOf(destino)) + " ");
            });
            System.out.println("Chegadas: \n");
            v.getChegadas().forEach(chegada -> {
                System.out.print(String.valueOf(vertices.indexOf(chegada)) + " ");
            });
            System.out.println("------------");
        }
    }

    private void mostrarPesoArestas() {
        System.out.println("Qual aresta deseja ver o valor: ");
        Integer idEscolha;
        do {
            read.nextLine();
            idEscolha = lerInputId();
            if (idEscolha == null) {
                break;
            }
            if (idEscolha < 0 || idEscolha > vertices.size() + 1) {
                System.out.println("Indice inválido!\n");
            }
        } while (idEscolha < 0 || idEscolha > vertices.size() + 1);
        Vertice v = vertices.get(idEscolha);
        System.out.println("Valor: " + v.getPesoArestas().toString());
    }

}
