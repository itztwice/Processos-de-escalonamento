package Escalonamento;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tecl = new Scanner(System.in);
        tecl.useDelimiter("\n");

        int qtdProcessos = 0;
        int tempoChegada = 0;
        int tempoExecucao = 0;
        int prioridade = 0;
        int escolha = -1;
        int timeSlice = 0;

        System.out.println("Digite a quantidade de processos para execução:");
        qtdProcessos = tecl.nextInt();

        System.out.println("Os processos serão populados de forma aleatoria?");
        System.out.println("1- Não");
        System.out.println("2- Sim");

        int escolhaAleatoria = tecl.nextInt();

        if(escolhaAleatoria == 1){
            for(int i = 0; i < qtdProcessos; i++){
                System.out.println("Digite o tempo de chegada do processo ["+i+"]:");
                tempoChegada = tecl.nextInt();
                System.out.println("Digite o tempo de execução do processo ["+i+"]:");
                tempoExecucao = tecl.nextInt();
                System.out.println("Digite a prioridade do processo ["+i+"]:");
                prioridade = tecl.nextInt();

                Processos manuais = new Processos(i, tempoChegada, tempoExecucao, prioridade);
            }
        } else {
            for (int i = 0; i < qtdProcessos; i++) {
                Processos p = new Processos(i);
            }
        }

        while(escolha != 0) {
            Escalonamentos algoritmo = new Escalonamentos();
            System.out.println(">>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<");
            System.out.println("Algoritmos de escalonamento");
            System.out.println("1 - FCFS");
            System.out.println("2 - SJF (Não preemptivo)");
            System.out.println("3 - SJF (Preemptivo)");
            System.out.println("4 - Prioridade (Não preemptivo)");
            System.out.println("5 - Prioridade (Preemptivo)");
            System.out.println("6 - RoundRobin");
            System.out.println("7 - Imprimir lista de processos");
            System.out.println("8 - Popular processos novamente");
            System.out.println("0 - Sair");
            System.out.print("Qual algoritmo deseja executar: ");
            escolha = tecl.nextInt();

            if(escolha == 1){
                algoritmo.FCFS();
            }

            if(escolha == 2){
                algoritmo.SJFNaoPreemptivo();
            }

            if(escolha == 3){
                algoritmo.SJFPreemptivo();
            }

            if(escolha == 4){
                algoritmo.PrioridadeNaoPreemptivo();
            }

            if(escolha == 5){
                algoritmo.PrioridadePreemptivo();
            }

            if(escolha == 6){
                System.out.println("Digite o Time-Slice: ");
                timeSlice = tecl.nextInt();

                algoritmo.RoundRobin(timeSlice);
            }

            if(escolha == 7){
                algoritmo.info();
            }

            if(escolha == 8){
                Processos apaga = new Processos();
                apaga.apagaListaProcessos();
                System.out.println("Digite a quantidade de processos para execução:");
                qtdProcessos = tecl.nextInt();

                System.out.println("Os processos serão populados de forma aleatoria?");
                System.out.println("1- Não");
                System.out.println("2- Sim");
                escolhaAleatoria = tecl.nextInt();

                if(escolhaAleatoria == 1){
                    for(int i = 0; i < qtdProcessos; i++){
                        System.out.println("Digite o tempo de chegada do processo ["+i+"]:");
                        tempoChegada = tecl.nextInt();
                        System.out.println("Digite o tempo de execução do processo ["+i+"]:");
                        tempoExecucao = tecl.nextInt();
                        System.out.println("Digite a prioridade do processo ["+i+"]:");
                        prioridade = tecl.nextInt();

                        Processos manuais = new Processos(i, tempoChegada, tempoExecucao, prioridade);
                    }
                } else {
                    for (int i = 0; i < qtdProcessos; i++) {
                        Processos p = new Processos(i);
                    }
                }
            }
        }
    }
}
