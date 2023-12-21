package Escalonamento;

import java.util.ArrayList;

public class Escalonamentos {

    static ArrayList<Processos> listaPE = new ArrayList<>();


    public Escalonamentos(){
        Processos p = new Processos();
        listaPE = p.escalonamento();
    }

    public void info(){
        for(int i = 0; i < listaPE.size(); i++){
            System.out.println("Processo: " + listaPE.get(i).getProcesso());
            System.out.println("Tempo chegada: " + listaPE.get(i).getTempoChegada());
            System.out.println("Tempo execução: " + listaPE.get(i).getTempoExecucao());
            System.out.println("Tempo restante: " + listaPE.get(i).getTempoRestante());
            System.out.println("Prioridade: " + listaPE.get(i).getPrioridade());
            System.out.println(" ");
        }
    }

    public void FCFS(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


        for(int i = 0; i < listaPE.size(); i++){
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: "+ tempoTotal);


        for(int i = 0; i < listaPE.size(); i++){
            while(listaPE.get(i).getTempoRestante() > 0){

                listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);

                System.out.println("Tempo["+ tempoExecucao + "]: Processo["+ listaPE.get(i).getProcesso()+
                        "] restante "+ listaPE.get(i).getTempoRestante());

                tempoExecucao++;

                for(int j = i + 1; j <= listaPE.size() - 1; j++){
                    listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() +1);
                }

                for(int j = 0; j < i; j++){
                    if(listaPE.get(j).getTempoRestante() > 0) {
                        listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() + 1);
                    }
                }
            }
        }

        for(int i = 0; i < listaPE.size(); i++){
            tempoEspera  += listaPE.get(i).getTempoEspera();
        }


        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

    }

    public void SJFNaoPreemptivo() {
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;

        for (int i = 0; i < listaPE.size(); i++) {
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);

        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        while (primeiroProcesso.getTempoRestante() > 0) {

            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);


            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);
                }
            }

            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    for(int j = i + 1; j <= listaPE.size() - 1; j++){
                        listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() +1);
                    }

                    for(int j = 0; j < i; j++){
                        if(listaPE.get(j).getTempoRestante() > 0) {
                            listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() + 1);
                        }
                    }
                }
            }

        }

        for(int i = 0; i < listaPE.size(); i++){
            Processos processoMaisCurto = new Processos();

            processoMaisCurto = (processoMaisCurto.procuraProcessoExecucao());

            while(processoMaisCurto.verificaTempoExecucao(processoMaisCurto, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;

                for(int j = 0; j < listaPE.size(); j++){
                    listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() + 1);
                }
            }

            while (processoMaisCurto.getTempoRestante() > 0) {
                processoMaisCurto.setTempoRestante(processoMaisCurto.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisCurto.getProcesso() +
                        "] restante " + processoMaisCurto.getTempoRestante());

                tempoExecucao++;
                processoMaisCurto.ajustaTempoRestante(processoMaisCurto);

                for(int j = 0; j <  listaPE.size(); j++){
                    if(processoMaisCurto.getProcesso() == j){
                        listaPE.get(j).setTempoRestante(listaPE.get(j).getTempoRestante() - 1);
                    }
                }

                for(int k = 0; k <  listaPE.size(); k++){
                    if(processoMaisCurto.getProcesso() == k){
                        for(int x = k + 1; x <= listaPE.size() - 1; x++){
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                        }

                        for(int j = 0; j < k; j++){
                            if(listaPE.get(j).getTempoRestante() > 0) {
                                listaPE.get(j).setTempoEspera(listaPE.get(j).getTempoEspera() + 1);
                            }
                        }
                    }
                }
            }

        }


        for(int i = 0; i < listaPE.size(); i++){
            tempoEspera  += listaPE.get(i).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }
    }

    public void SJFPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


        for (int i = 0; i < listaPE.size(); i++) {
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);


        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());


        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        while (primeiroProcesso.getTempoRestante() > 0) {
            if(primeiroProcesso.getTempoRestante() == 999){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado");
                tempoExecucao++;
            }

            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);


            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);


            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);
                }
            }

            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    for(int x = i + 1; x <= listaPE.size() - 1; x++){
                        listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(listaPE.get(x).getTempoRestante() > 0) {
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }

            Processos proximoProcesso = primeiroProcesso;

            proximoProcesso = (proximoProcesso.procuraProcessoExecucaoRestante(tempoExecucao));


            if(proximoProcesso.verificaTempoExecucao(proximoProcesso, tempoExecucao) && proximoProcesso.getTempoRestante() != 0){
                primeiroProcesso = proximoProcesso;

                primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                        "] restante " + primeiroProcesso.getTempoRestante());

                tempoExecucao++;
                primeiroProcesso.ajustaTempoRestante(primeiroProcesso);


                for(int x = 0; x <  listaPE.size(); x++){
                    if(primeiroProcesso.getProcesso() == x){
                        listaPE.get(x).setTempoRestante(listaPE.get(x).getTempoRestante() - 1);
                    }
                }

                for(int k = 0; k <  listaPE.size(); k++){
                    if(primeiroProcesso.getProcesso() == k){
                        for(int x = k + 1; x <= listaPE.size() - 1; x++){
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(listaPE.get(x).getTempoRestante() > 0) {
                                listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }

                if(primeiroProcesso.getTempoRestante() == 0){
                    primeiroProcesso = primeiroProcesso.procuraProcessoExecucaoRestante(tempoExecucao);
                }
            } else {
                for(int i = 0; i < listaPE.size(); i++){
                    tempoEspera  += listaPE.get(i).getTempoEspera();
                }

                tempoEsperaMedio = tempoEspera/(listaPE.size());

                System.out.println("Tempo de espera total: "+ tempoEspera);
                System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

                break;
            }
        }

        for(int i = 0; i < listaPE.size(); i++){
            tempoEspera  += listaPE.get(i).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }
    }

    public void PrioridadeNaoPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


        for (int i = 0; i < listaPE.size(); i++) {
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);

        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        while (primeiroProcesso.getTempoRestante() > 0) {
            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);

            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);
                }
            }

            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    for(int x = i + 1; x <= listaPE.size() - 1; x++){
                        listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(listaPE.get(x).getTempoRestante() > 0) {
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }
        }


        for(int i = 0; i < listaPE.size(); i++){
            Processos processoMaisPrioritario = new Processos();

            processoMaisPrioritario = (processoMaisPrioritario.procuraProcessoPrioridade(tempoExecucao));

            while(processoMaisPrioritario.verificaTempoExecucao(processoMaisPrioritario, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;

                for(int k = 0; i < listaPE.size(); k++){
                    listaPE.get(k).setTempoEspera(listaPE.get(k).getTempoEspera() + 1);
                }
            }

            while (processoMaisPrioritario.getTempoRestante() > 0) {
                processoMaisPrioritario.setTempoRestante(processoMaisPrioritario.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisPrioritario.getProcesso() +
                        "] restante " + processoMaisPrioritario.getTempoRestante());

                tempoExecucao++;
                processoMaisPrioritario.ajustaTempoRestante(processoMaisPrioritario);


                for(int x = 0; x <  listaPE.size(); x++){
                    if(processoMaisPrioritario.getProcesso() == x){
                        listaPE.get(x).setTempoRestante(listaPE.get(x).getTempoRestante() - 1);
                    }
                }

                for(int k = 0; k <  listaPE.size(); k++){
                    if(processoMaisPrioritario.getProcesso() == k){
                        for(int x = k + 1; x <= listaPE.size() - 1; x++){
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(listaPE.get(x).getTempoRestante() > 0) {
                                listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }
            }



        }

        for(int k = 0; k < listaPE.size(); k++){
            tempoEspera  += listaPE.get(k).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }
    }

    public void PrioridadePreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio =  0;



        for (int i = 0; i < listaPE.size(); i++) {
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);


        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());


        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;

            for(int k = 0; k < listaPE.size(); k++){
                listaPE.get(k).setTempoEspera(listaPE.get(k).getTempoEspera() + 1);
            }
        }


        while (primeiroProcesso.getTempoRestante() > 0) {
            if(primeiroProcesso.getPrioridade() == 999){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado");
                tempoExecucao++;
            }

            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);


            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);


            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);
                }
            }


            for(int i = 0; i <  listaPE.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    for(int x = i + 1; x <= listaPE.size() - 1; x++){
                        listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(listaPE.get(x).getTempoRestante() > 0) {
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }

            Processos proximoProcesso = primeiroProcesso;


            proximoProcesso = (proximoProcesso.procuraProcessoPrioridade(tempoExecucao));


            if(proximoProcesso.verificaTempoExecucao(proximoProcesso, tempoExecucao) && proximoProcesso.getTempoRestante() != 0){
                primeiroProcesso = proximoProcesso;


                primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);


                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                        "] restante " + primeiroProcesso.getTempoRestante());

                tempoExecucao++;
                primeiroProcesso.ajustaTempoRestante(primeiroProcesso);


                for(int x = 0; x <  listaPE.size(); x++){
                    if(primeiroProcesso.getProcesso() == x){
                        listaPE.get(x).setTempoRestante(listaPE.get(x).getTempoRestante() - 1);
                    }
                }


                for(int k = 0; k <  listaPE.size(); k++){
                    if(primeiroProcesso.getProcesso() == k){
                        //adiciona 1 tempo de espera para os processos que não estão no processador
                        for(int x = k + 1; x <= listaPE.size() - 1; x++){
                            listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(listaPE.get(x).getTempoRestante() > 0) {
                                listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }

                if(primeiroProcesso.getTempoRestante() == 0){
                    primeiroProcesso = primeiroProcesso.procuraProcessoPrioridade(tempoExecucao);
                }
            } else {
                break;
            }
        }


        for(int k = 0; k < listaPE.size(); k++){
            tempoEspera  += listaPE.get(k).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);


        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }
    }

    public void RoundRobin(int timeSlice) {
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoExecutando = 0;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;

        Processos pivo = new Processos();


        for(int i = 0; i < listaPE.size(); i++){
            tempoTotal += listaPE.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: "+ tempoTotal);


        for(int i = 0; i < listaPE.size(); i++){
            while(listaPE.get(i).getTempoRestante() > 0){
                listaPE.get(i).setTempoRestante(listaPE.get(i).getTempoRestante() - 1);

                tempoExecutando++;

                System.out.println("Tempo["+ tempoExecucao + "]: Processo["+ listaPE.get(i).getProcesso()+
                        "] restante "+ listaPE.get(i).getTempoRestante());

                tempoExecucao++;

                for(int x = i + 1; x <= listaPE.size() - 1; x++){
                    if(listaPE.get(x).getTempoRestante() > 0) {
                        listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                    }
                }

                for(int x = 0; x < i; x++){
                    if(listaPE.get(x).getTempoRestante() > 0) {
                        listaPE.get(x).setTempoEspera(listaPE.get(x).getTempoEspera() + 1);
                    }
                }


                if(tempoExecutando == timeSlice){
                    pivo = listaPE.remove(i);
                    listaPE.add(pivo);

                    tempoExecutando = 0;


                }
                if(listaPE.get(i).getTempoRestante()  == 0){
                    pivo = listaPE.remove(i);
                    listaPE.add(pivo);
                }
            }
        }

        for(int i = 0; i < listaPE.size(); i++){
            tempoEspera  += listaPE.get(i).getTempoEspera();
        }


        for(int i = 0; i < listaPE.size(); i++){
            listaPE.get(i).reiniciaTempoRestante(listaPE.get(i));
        }

        tempoEsperaMedio = tempoEspera/(listaPE.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);
    }

}
