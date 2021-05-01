/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1_sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author julio
 */
public class T1_Sudoku {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        game();
    }

    public static int[][] step(int[][] matriz) throws IOException {

        int linha;
        int coluna;
        int n;
        
        
        Scanner ler = new Scanner(System.in);
        System.out.println("Qual posição você deseja colocar um número?");

        System.out.print("Linha: ");
        linha = ler.nextInt();

        System.out.print("Coluna: ");
        coluna = ler.nextInt();

        System.out.println("Qual valor você deseja inserir?");
        n = ler.nextInt();
        //condições para evitar que o usuário coloque como entrada um valor impossível
        if (coluna > 8 || linha > 8) {
            while (true) {
                System.out.println("Ops! Digite um valor entre 0 e 8.");
                step(matriz);
            }
        }

        //condição para caso o valor já esteja preenchido
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (matriz[linha][coluna] != 0) {
                    System.out.println("A posição escolhida já possui um valor. Tente novamente.");
                    step(matriz);
                }
            }
        }

        //matriz[linha][coluna] = n; //matriz[linha][coluna] = n; //inserir N na matriz
        if (valida(matriz, linha, coluna, n) == true) {
            matriz[linha][coluna] = n;
        } else {
            while (valida(matriz, linha, coluna, n) == false) {
                System.out.print("Ops, valor inválido! Digite outro número: ");
                n = ler.nextInt();
            }
        }

        matriz[linha][coluna] = n;
        print(matriz); //peço que imprima para que o usuário veja a matriz atualizada

        
        int x;
        System.out.println("Deseja limpar alguma célula?");
        System.out.println("1 - SIM");
        System.out.println("2 - NÃO");
        x = ler.nextInt();
        if(x == 1){
            limpar(matriz, linha, coluna);
            System.out.println("Célula limpa!");
            print(matriz);
        }else{      
            print(matriz);
        }
        
        return matriz;
    }

    public static boolean valida(int[][] matriz, int linha, int coluna, int numero) {
        // verifico se o número está na linha
        for (int i = 0; i < matriz[0].length; i++) {
            if (matriz[linha][i] == numero) {
                return false;
            }
        }
        // verifico se o número está na coluna
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][coluna] == numero) {
                return false;
            }
        }

        // verifico o box 3x3
        int boxLinha = linha - linha % 3;
        int boxColuna = coluna - coluna % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[boxLinha + i][boxColuna + j] == numero) {
                    return false;
                }
            }
        }
        //caso esteja tudo certo, retorna true
        return true;
    }

    public static int status(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == 0) { //verifico se ainda há espaços não preenchidos
                    return 0; //Se ainda houver espaços não preenchidos, retorno 0 e volto ao jogo
                } else {
                }
            }
        }
        return 1; //Com tudo preenchido, retorno 1
    }

    public static int[][] limpar(int[][] matriz, int linha, int coluna) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha a célula que deseja limpar.");
        System.out.print("Linha: ");
        linha = sc.nextInt();

        System.out.print("Coluna: ");
        coluna = sc.nextInt();
        
        matriz[linha][coluna] = 0; 

        return matriz;
    }

    private static void game() throws FileNotFoundException, IOException {
        int matriz[][] = initialize();
        print(matriz);
        while (status(matriz) == 0) {
            step(matriz);
        }
        if (status(matriz) == 1) {
            System.out.println("Parabéns! Você finalizou o jogo!");
        }
    }

    public static int[][] initialize() throws FileNotFoundException, IOException {

        Scanner entrada = new Scanner(new File("sudokuEntrada.txt"));
        int[][] matriz = new int[9][9];
        while (entrada.hasNextInt()) {
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    try {
                        matriz[i][j] = entrada.nextInt();
                    } catch (java.util.NoSuchElementException e) {
                    }
                }
            }
        }
        return matriz;
    }

    public static void print(int matriz[][]) {
        System.out.println("----------------------------------");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("[%d]", matriz[i][j]);
            }
            System.out.println(":" + i);
        }
        System.out.println(" 0  1  2  3  4  5  6  7  8  ");
        System.out.println("----------------------------------");
    }
}
