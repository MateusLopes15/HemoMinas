import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class Main {
    public static Hemocentro atualHemocentro = null;

    public static void main(String[] args) {

        Hemominas.getInstance();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE CONTROLE DO HEMOMINAS ///"); // Modificar para um
                                                                                               // sistema que contenha
                                                                                               // atendimento. Doador.
                                                                                               // Gerente
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Acessar hemocentros");
            System.out.println("2 - Sistema de Gestão");
            System.out.println("3 - SAIR");

            int escolha = entradaUsuario(sc, 1, 3);
            switch (escolha) {
                case 1:
                    entrarHemocentro(sc);
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

    static void entrarHemocentro(Scanner sc) {
        while (true) {
            if (Hemominas.getInstance().getQtdHemocentros() != 0) {
                Hemominas.getInstance().listarHemocentros();
                System.out.println("Escolha o Hemocentro que deseja entrar: ");
                String entrada = sc.nextLine();
                atualHemocentro = Hemominas.getInstance()
                        .retornaHemocentro(Hemominas.getInstance().getIdHemocentro(entrada));
                if (atualHemocentro == null) {
                    System.out.println("Entrada inválida.");
                    continue;
                }
                sistemaAtendimento(sc);
                break;
            } else {
                System.out.println("Nenhum hemocentro cadastrado.");
                return;
            }
        }
    }

    
    // static void sistemaAtualHemocentro(Scanner sc) {
    //     if (atualHemocentro != null && Hemominas.getInstance().getQtdHemocentros() != 0) {
    //         System.out.println("O hemocentro atual é: " + atualHemocentro.getNome());
    //         System.out.println("Deseja trocar? 1 - SIM ou 2 - NÃO");
    //         String escolha = sc.nextLine().trim().toLowerCase();
    //         switch (escolha) {
    //             case "1", "sim", "1-sim":
    //                 entrarHemocentro(sc);
    //             case "2", "não", "nao", "2-nao", "2-não":
    //                 sistemaAtendimento(sc);
    //         }
    //     } else {
    //         if (Hemominas.getInstance().getQtdHemocentros() == 0) {
    //             System.out.println("Nenhum Hemocentro cadastrado. Crie um Hemocentro antes.");
    //         } else {
    //             entrarHemocentro(sc);
    //         }
    //     }
    // }

    // static void entrarHemocentro(Scanner sc){
    //     Hemominas.getInstance().listarHemocentros();
    //     System.out.println("Escolha o nome do Hemocentro que deseja entrar");
    //     String nomeAtual = sc.nextLine();
    //     atualHemocentro = Hemominas.getInstance().retornaHemocentro(Hemominas.getInstance().getIdHemocentro(nomeAtual));
    //     return;
    // }

    static void sistemaAtendimento(Scanner sc) {
        System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE ATENDIMENTO - " + atualHemocentro.getNome() + " ///");
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

        Hemominas.getInstance().adicionaColeta(atualHemocentro, coleta);
    
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


    static void sistemaDoadores(Scanner sc) {
        while (true) {
            System.out.println("/// MENU - SISTEMA DE GESTÃO DE DOADORES ///");
            System.out.println("1 - CRIAR DOADOR");
            System.out.println("2 - ATUALIZAR DOADOR");
            System.out.println("3 - LISTAR DOADORES");
            System.out.println("4 - APAGAR DOADOR");
            System.out.println("5 - Retornar ao menu principal");
            int escolha = entradaUsuario(sc, 1, 5);
            switch (escolha) {
                case 1:
                    adicionarDoador(sc);
                    break;
                case 2:
                    atualizarDoador(sc);
                    break;
                case 3:
                    Hemominas.getInstance().listarDoadores(atualHemocentro);
                    break;
                case 4:
                    removerDoador(sc);
                    break;
                case 5:
                    return;
            }
        }
    }

    static void adicionarDoador(Scanner sc) {
        Date dataNasc = null;
        while (true) {
            System.out.print("Digite o nome do doador: ");
            String nome = sc.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode ser vazio");
                continue;
            }
            System.out.print("Digite o CPF do doador: ");
            String cpf = sc.nextLine();
            if (cpf.isEmpty() || !Doador.validaCPF(cpf)) {
                System.out.println("CPF inválido.");
                continue;
            } else if (Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpf) != null) {
                System.out.println("O CPF informado já existe no sistema.");
                continue;
            }
            System.out.print("Digite a data de nascimento do doador (no formato DD/MM/YYYY): ");
            String dataNascString = sc.nextLine().trim();
            if (dataNascString.isEmpty()) {
                System.out.println("A data de nascimento não pode ser vazio");
                continue;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                dataNasc = sdf.parse(dataNascString);
            } catch (ParseException e) {
                System.out.println("Erro. Data inválida.");
                continue;
            }

            System.out.print("Digite o genero biologico do doador: ");
            String genero = sc.nextLine();
            if (genero.isEmpty()) {
                System.out.println("Gênero não pode ser vazio");
                continue;
            }
            System.out.print("Digite o telefone do doador: ");
            String telefone = sc.nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Telefone não pode ser vazio");
                continue;
            }
            try {
                Doador doador = Doador.getInstance(nome, cpf, dataNasc, genero, telefone);
                Hemominas.getInstance().adicionaDoador(atualHemocentro, doador);
            } catch (IllegalArgumentException e) {
                System.err.println("Doador cadastrado não pode ser nulo.");
            } catch (RuntimeException e) {
                System.out.println("Erro ao adicionar o Doador");

            }
            return;
        }
    }

    static void atualizarDoador(Scanner sc) {
        String cpfInput;
        do {
            System.out.print("Digite o CPF do doador a ser alterado: ");
            cpfInput = sc.nextLine();
        } while (cpfInput.isEmpty() || !Doador.validaCPF(cpfInput));

        Doador doadorTemporario = Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpfInput);
        if (doadorTemporario == null) {
            System.out.println("O doador informado não existe.");
            return;
        }

        String cpfAntigo = doadorTemporario.getCpf();

        while (true) {
            System.out.println("Digite o que deseja alterar no doador: ");
            System.out.println("1 - NOME");
            System.out.println("2 - CPF");
            System.out.println("3 - DATA DE NASCIMENTO");
            System.out.println("4 - GÊNERO BIOLÓGICO");
            System.out.println("5 - TELEFONE");
            System.out.println("6 - RETORNAR");

            int escolha = entradaUsuario(sc, 1, 6);
            try {
                switch (escolha) {
                    case 1: {
                        String nome = lerEntradaCRUD(sc, "Digite o nome do doador: ");
                        if (nome == null) {
                            System.out.println("Nome inválido.");
                            continue;
                        }
                        doadorTemporario.setNome(nome);
                        System.out.println("Nome alterado com sucesso!");
                        break;
                    }
                    case 2: {
                        String cpf = lerEntradaCRUD(sc, "Digite o novo CPF do doador: ");
                        if (cpf == null || !Doador.validaCPF(cpf)) {
                            System.out.println("CPF inválido.");
                            continue;
                        }
                        if (Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpf) != null) {
                            System.out.println("O CPF inserido já existe no sistema.");
                            continue;
                        }
                        doadorTemporario.setCpf(cpf);
                        System.out.println("CPF alterado com sucesso!");
                        break;
                    }
                    case 3: {
                        String dtNascString = lerEntradaCRUD(sc,
                                "Digite a nova data de nascimento do doador (dd/MM/yyyy): ");
                        if (dtNascString == null) {
                            System.out.println("Data inválida.");
                            continue;
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            sdf.setLenient(false);
                            Date dataNascimento = sdf.parse(dtNascString);
                            doadorTemporario.setDataNasc(dataNascimento);
                            System.out.println("Data de nascimento alterada com sucesso!");
                        } catch (ParseException e) {
                            System.out.println("Formato de data inválido.");
                        }
                        break;
                    }
                    case 4: {
                        String genero = lerEntradaCRUD(sc, "Digite o novo gênero biológico do doador: ");
                        if (genero == null) {
                            System.out.println("Gênero inválido.");
                            continue;
                        }
                        doadorTemporario.setGenero(genero);
                        System.out.println("Gênero alterado com sucesso!");
                        break;
                    }
                    case 5: {
                        String telefone = lerEntradaCRUD(sc, "Digite o novo telefone do doador: ");
                        if (telefone == null) {
                            System.out.println("Telefone inválido.");
                            continue;
                        }
                        doadorTemporario.setTelefone(telefone);
                        System.out.println("Telefone alterado com sucesso!");
                        break;
                    }
                    case 6:
                        Hemominas.getInstance().atualizarDoador(atualHemocentro, doadorTemporario, cpfAntigo);
                        System.out.println("Doador atualizado com sucesso!");
                        return;
                }
            } catch (RuntimeException e) {
                System.out.println("Erro ao atualizar o doador.");
                return;
            }
        }
    }

    static void removerDoador(Scanner sc) {
        String cpfInput;
        do {
            System.out.print("Digite o CPF do doador a ser removido: ");
            cpfInput = sc.nextLine();
        } while (cpfInput.isEmpty() || !Doador.validaCPF(cpfInput));

        Doador doador = Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpfInput);
        try {
            Hemominas.getInstance().removerDoador(atualHemocentro, doador);
        } catch (NoSuchElementException e) {
            e.getMessage();
            return;
        }
        System.out.println("Doador removido com sucesso.");
    }

    static String lerEntradaCRUD(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String entrada = sc.nextLine().trim();
        return entrada.isEmpty() ? null : entrada;
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
                    listarHemocentros();
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
    static void listarHemocentros() {
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
