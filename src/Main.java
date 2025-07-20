import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class Main {
     public  static Hemocentro atualHemocentro = null;
    public static void main(String[] args) {
        
        Hemominas.getInstance();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE CONTROLE DO HEMOMINAS ///"); //Modificar para um sistema que contenha atendimento. Doador. Gerente
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Sistema de Atendimento");
            System.out.println("2 - Sistema de Gestão");
            System.out.println("3 - SAIR");

            int escolha = entradaUsuario(sc, 1, 3);
            switch (escolha) {
             case 1:
             sistemaAtualHemocentro(sc);
             break;
            case 2:
             sistemaGestao(sc);
            break;
             case 3:
             sc.close();
             return;
             }
        }
    }

    
    static void sistemaAtualHemocentro(Scanner sc){
        if(atualHemocentro!=null && Hemominas.getInstance().getQtdHemocentros()!=0){
            System.out.println("O hemocentro atual é: " + atualHemocentro.getNome());
            System.out.println("Deseja trocar? 1- SIM ou 2 - Não");
            String escolha = sc.nextLine().trim().toLowerCase();
            switch(escolha){
                case "1", "sim", "1-sim":
                entrarHemocentro(sc);
                case "2", "não", "nao", "2-nao", "2-não":
                sistemaAtendimento(sc);
                
            }
        }else{
            if(Hemominas.getInstance().getQtdHemocentros()==0){
                System.out.println("Nennhum Hemocentro cadastrado. Crie um Hemocentro antes");
                sistemaGestao(sc);
            }else{
                 entrarHemocentro(sc);
            }
           
        }
    }

    static void sistemaAtendimento(Scanner sc){
            System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE ATENDIMENTO ///"); 
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Gerenciar Doadores");
            System.out.println("2 - Gerenciar Coletas");
            System.out.println("3 - Voltar");

            int escolha = entradaUsuario(sc, 1, 3);
            switch (escolha) {
             case 1:
             sistemaDoadores(sc);
             break;
            case 2:
             sistemaColeta(sc);
            break;
             case 3:
             return;
             }
    }
    static void entrarHemocentro(Scanner sc){
        Hemominas.getInstance().listarHemocentros();
        System.out.println("Escolha o nome do Hemocentro que deseja entrar");
        String nomeAtual = sc.nextLine();
        
        atualHemocentro = Hemominas.getInstance().retornaHemocentro(Hemominas.getInstance().getIdHemocentro(nomeAtual));
        return;
    }
    static void sistemaColeta(Scanner sc){
        System.out.println("1 - CRIAR COLETA");
            System.out.println("2 - ATUALIZAR COLETA");
            System.out.println("3 - LISTAR COLETAS");
            System.out.println("4 - APAGAR COLETA");
            System.out.println("5 - Retornar ao menu principal");
            int escolha = entradaUsuario(sc, 1, 5);
            switch (escolha) {
                case 1:
                    adicionarColeta(sc);
                    break;
                case 2:
                    //atualizarDoador(sc);
                    break;
                case 3:
                    //Hemominas.getInstance().listaDoadores(atualHemocentro);
                    break;
                case 4:
                    //deletarDoador(sc);
                    break;
                case 5:
                    return;
                }
    }

    static void adicionarColeta(Scanner sc){
        System.out.println("Digite o nome do Doador");
        String nome = sc.nextLine();
        //Doador doadorAtual = Hemominas.getInstance().atualizaColeta(atualHemocentro, null);; //Mandar o Hemocentro atual e trocar no hemocentro atual
        Doador doadorAtual = Hemominas.getInstance().pesquisaDoador(atualHemocentro, nome);
        System.out.println("Digite o Tipo Sanguineo do doador");
        String escolha = sc.nextLine();
        TipoSanguineo tipo = TipoSanguineo.stringParaTipoSanguineo(escolha);
        Coleta coleta = Coleta.getInstance(tipo, doadorAtual, LocalDate.now());
        System.out.println("Digite o resultado do Exame de Hepatite B");
        String exame1 = sc.nextLine();
        Exame exame = Exame.getInstance(TipoExame.stringParaTipo("HepatiteB"), exame1);
        coleta.adicionarExame(exame);
        // coleta.adicionarExame(null);
        // coleta.adicionarExame(null);
        // coleta.adicionarExame(null);
        // coleta.adicionarExame(null);

        Hemominas.getInstance().atualizaColeta(atualHemocentro, coleta);
    
    }



    static void sistemaGestao(Scanner sc) {
       
        while (true) {
            System.out.println("/// MENU - SISTEMA DE GESTÃO DO HEMOMINAS ///");
            System.out.println("1 - Gerenciar Hemocentros");
            System.out.println("2 - Gerenciar listagens");
            System.out.println("3 - Gerenciar Funcionarios");
            System.out.println("4 - Retornar ao menu principal");
            int escolha = entradaUsuario(sc, 1, 4);
            switch (escolha) {
                case 1:
                    sistemaHemocentro(sc);
                    break;
                case 2:
                    // sistemaListagens(sc);
                    break;
                case 3:
                   
                    break;
                case 4:
                    
                    return;
                
            }
        
    
    }
    }
    static TipoExame lerTipo(Scanner sc) {
        while (true) {
            TipoExame.listarOpcoes();
            String entrada = sc.nextLine();
            TipoExame tipo = TipoExame.stringParaTipo(entrada);
            if (tipo != null) {
                return tipo;
            } else {
                System.out.println("Tipo inválido. Tente novamente.");
            }
        }
    }


    static void sistemaDoadores(Scanner sc){
         System.out.println("/// MENU - SISTEMA DE GESTÃO DE DOADORES ///");
            System.out.println("1 - CRIAR DOADOR");
            System.out.println("2 - ATUALIZAR DOADOR");
            System.out.println("3 - LISTAR DOADOR");
            System.out.println("4 - APAGAR DOADOR");
            System.out.println("5 - Retornar ao menu principal");
            int escolha = entradaUsuario(sc, 1, 5);
            switch (escolha) {
                case 1:
                    adicionarDoador(sc);
                    break;
                case 2:
                    //atualizarDoador(sc);
                    break;
                case 3:
                    Hemominas.getInstance().listaDoadores(atualHemocentro);
                    break;
                case 4:
                    //deletarDoador(sc);
                    break;
                case 5:
                    return;
            }
    }
    static void adicionarDoador(Scanner sc){
        Date dataNasc = null;
        
        while (true) {
        System.out.println("Digite o nome do doador...");
        String nome = sc.nextLine();       
        if(nome.isEmpty()){
            System.out.println("Nome não pode ser vazio");
            continue;
        } 
        System.out.println("Digite O CPF");
        String cpf = sc.nextLine(); 
        if(cpf.isEmpty() || Doador.validaCPF(cpf) == false){
            System.out.println("O CPF não pode ser vazio");
            continue;
        }
        System.out.println("Digite a data de nascimento do doador...");
        String dataNascString = sc.nextLine().trim();
         if(dataNascString.isEmpty()){
            System.out.println("A data de nascimento não pode ser vazio");
            continue;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try{
             dataNasc = sdf.parse(dataNascString);
        }catch(ParseException e ){
            System.out.println("Erro. DATA INVALIDA");
            continue;
        }
        


       
        System.out.println("Digite o genero biologico do doador...");
        String genero = sc.nextLine(); 
        if(genero.isEmpty()){
            System.out.println("Email não pode ser vazio");
            continue;
        }
        System.out.println("Digite o telefone do doador...");
        String telefone = sc.nextLine();
         if(telefone.isEmpty()){
            System.out.println("Telefone não pode ser vazio");
            continue;
         }
        try{
            Doador doador = Doador.getInstance(nome, cpf, dataNasc, genero, telefone);
            Hemominas.getInstance().atualizaDoador(atualHemocentro, doador);
         //Hemocentro hemocentro = Hemocentro.getInstance(nome, endereco, cep, email, telefone);
         //Hemominas.getInstance().cadastrarHemocentro(hemocentro);
        }catch (IllegalArgumentException e){
            System.err.println("Doador cadastrado não pode ser nulo.");
        }catch(RuntimeException e){
            System.out.println("Erro ao adicionar o Doador");
            
        }
         return;
       
    }
    }

    static void sistemaHemocentro(Scanner sc){
while (true) {
            System.out.println("/// MENU - SISTEMA DE GESTÃO DO HEMOCENTRO ///");
            System.out.println("1 - CRIAR HEMOCENTRO");
            System.out.println("2 - ATUALIZAR HEMOCENTRO");
            System.out.println("3 - LISTAR HEMOCENTRO");
            System.out.println("4 - APAGAR HEMOCENTRO");
            System.out.println("5 - Retornar ao menu principal");
            int escolha = entradaUsuario(sc, 1, 5);
            switch (escolha) {
                case 1:
                    adicionarHemocentro(sc);
                    break;
                case 2:
                    atualizarHemocentro(sc);
                    break;
                case 3:
                    listaDeHemocentro();
                    break;
                case 4:
                    deletarHemocentro(sc);
                    break;
                case 5:
                    return;
            }
    }
}


    static void adicionarHemocentro(Scanner sc){
while (true) {
        System.out.println("Digite o nome do hemocentro...");
        String nome = sc.nextLine();       
        if(nome.isEmpty()){
            System.out.println("Nome não pode ser vazio");
            continue;
        } 
        System.out.println("Digite o endereço do hemocentro...");
        String endereco = sc.nextLine(); 
        if(endereco.isEmpty()){
            System.out.println("Endereço não pode ser vazio");
            continue;
        }
        String cep = "0"; 
        while(Hemocentro.isValidCep(cep)==false){
        System.out.println("Digite o CEP do hemocentro...");
        cep = sc.nextLine(); 
       
        if(Hemocentro.isValidCep(cep)==false){
            System.out.println("CEP INVALIDO");
        }
        }
        
        System.out.println("Digite o email do hemocentro...");
        String email = sc.nextLine(); 
        if(email.isEmpty()){
            System.out.println("Email não pode ser vazio");
            continue;
        }
        System.out.println("Digite o telefone do hemocentro...");
        String telefone = sc.nextLine();
         if(telefone.isEmpty()){
            System.out.println("Telefone não pode ser vazio");
            continue;
         }
        try{
         Hemocentro hemocentro = Hemocentro.getInstance(nome, endereco, cep, email, telefone);
         Hemominas.getInstance().cadastrarHemocentro(hemocentro);
        }catch (IllegalArgumentException e){
            System.err.println("Hemocentro a ser cadastrado não pode ser nulo.");
        }catch(RuntimeException e){
            System.out.println("Erro ao adicionar o primeiro hemocentro.");
            
        }
         return;
       
    }
       
    }

    static void atualizarHemocentro(Scanner sc){
        
        System.out.println("Digite o nome do Hemocentro que deseja atualizar");
        String nome = sc.nextLine();
        int id = Hemominas.getInstance().getIdHemocentro(nome);
        Hemocentro hemocentroTemporario = Hemominas.getInstance().retornaHemocentro(id);


        System.out.println("Digite o que deseja alterar: ");
        System.out.println("1 - Nome");
        System.out.println("2 - Endereço");
        System.out.println("3 - CEP");
        System.out.println("4 - Email");
        System.out.println("5 - Telefone");
        System.out.println("6 - Retornar");
        int escolha = entradaUsuario(sc, 1, 6);
        switch (escolha) {
            case 1:
                System.out.println("Digite o nome novo do Hemocentro");
                String novoNome = sc.nextLine();
                hemocentroTemporario.setNome(novoNome);
                break;
            case 2:
                System.out.println("Digite o novo Endereço do Hemocentro");
                String novoEndereco = sc.nextLine();
                hemocentroTemporario.setEndereco(novoEndereco);
                break;
            case 3:
                System.out.println("Digite o novo CEP do Hemocentro");
                String novoCep = sc.nextLine();
                hemocentroTemporario.setCep(novoCep);
                break;
            case 4:
                System.out.println("Digite o novo Email do Hemocentro");
                String novoEmail = sc.nextLine();
                hemocentroTemporario.setEmail(novoEmail);
                break;
            case 5:
                System.out.println("Digite o novo Telefone do Hemocentro");
                String novoTelefone = sc.nextLine();
                hemocentroTemporario.setTelefone(novoTelefone);
                break;
            case 6:

                return;

            
        }
        Hemominas.getInstance().atualizacaoDosHemocentro(id, hemocentroTemporario);

    }
    static void listaDeHemocentro(){
        try{
            Hemominas.getInstance().listarHemocentros();
        }catch(IllegalArgumentException e){
            System.err.println(e);
        }
        
    }


    static void deletarHemocentro(Scanner sc){
        System.out.println("Digite o nome do Hemocentro que deseja remover");
        String nome = sc.nextLine();
        Hemominas.getInstance().removeHemocentro(Hemominas.getInstance().getIdHemocentro(nome));
    }


    static int entradaUsuario(Scanner sc, int min, int max) {
        while (true) {
            System.out.printf("Digite um número de %d a %d: ", min, max);
            try {
                int escolha = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                if (escolha >= min && escolha <= max) {
                    return escolha;
                } else {
                    System.out.printf("O número deve ser de %d a %d. Tente novamente.\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                sc.nextLine(); // Limpa o buffer
            }
        }
    }

}
