import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

import java.util.List;

public class Main {
    public static Hemocentro atualHemocentro = null;

    public static void main(String[] args) {

        Hemominas.getInstance();
        Scanner sc = new Scanner(System.in);
        Hemominas.inicializarHemocentrosDeMinas();
        Hemominas.inicializarDoadoresParaHemocentros();
        Hemominas.inicializarColetasParaHemocentrosEDoadores();
        while (true) {
            System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE CONTROLE DO HEMOMINAS ///"); // Modificar para um
                                                                                               // sistema que contenha
                                                                                               // atendimento. Doador.
                                                                                               // Gerente
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Acessar Hemocentros");
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
            try {
                if (Hemominas.getInstance().getQtdHemocentros() != 0) {
                    Hemominas.getInstance().listarHemocentrosMenu();
                    System.out.println("Escolha o Hemocentro (digite o nome ou o ID): ");
                    String entrada = sc.nextLine().trim();

                    Hemocentro selecionado = null;

                    if (entrada.matches("\\d+")) {
                        int id = Integer.parseInt(entrada);
                        List<Hemocentro> lista = Hemominas.getInstance().retornarHemocentros();
                        if (id >= 0 && id <= lista.size()) {
                            selecionado = lista.get(id - 1);
                        }
                    } else {
                        int id = Hemominas.getInstance().getIdHemocentro(entrada);
                        if (id != -1) {
                            selecionado = Hemominas.getInstance().retornaHemocentro(id);
                        }
                    }

                    if (selecionado == null) {
                        System.out.println("Entrada inválida.");
                        continue;
                    }

                    atualHemocentro = selecionado;
                    sistemaAtendimento(sc);
                    break;

                } else {
                    System.out.println("Nenhum hemocentro cadastrado.");
                    return;
                }

            } catch (RuntimeException e) {
                System.err.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }

    static void sistemaAtendimento(Scanner sc) {
        while (true) {
            System.out.println("\n/// MENU PRINCIPAL - SISTEMA DE ATENDIMENTO - " + atualHemocentro.getNome() + " ///");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Menu de Funcionário");
            System.out.println("2 - Menu de Doador");
            System.out.println("3 - Voltar");

            int escolha = entradaUsuario(sc, 1, 3);
            switch (escolha) {
                case 1:
                    menuFuncionario(sc);
                    break;
                case 2:
                    menuDoador(sc);
                    break;
                case 3:
                    return;
            }
        }
    }

    static void menuFuncionario(Scanner sc) {
        while (true) {
            System.out.println(
                    "\n/// MENU DE FUNCIONÁRIO - SISTEMA DE ATENDIMENTO - " + atualHemocentro.getNome() + " ///");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Gerenciar doadores");
            System.out.println("2 - Gerenciar coletas");
            System.out.println("3 - Listar os Estoques");
            System.out.println("4 - Voltar");
            int escolha = entradaUsuario(sc, 1, 4);
            switch (escolha) {
                case 1:
                    sistemaDoadores(sc);
                    break;
                case 2:
                    sistemaColeta(sc);
                    break;
                case 3:
                    listarEstoque(sc);
                    break;
                case 4:
                    return;
            }
        }
    }

    static void menuDoador(Scanner sc) {
        System.out.print("Digite o CPF do doador: ");
        String cpf = sc.nextLine();
        if (cpf.isEmpty() || !Doador.validaCPF(cpf)) {
            System.out.println("CPF inválido.");
            return;
        } else if (Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpf) == null) {
            System.out.println("O CPF informado não existe no sistema.");
            return;
        }

        Doador doador = Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpf);
        while (true) {
            System.out.println("\n/// MENU DE DOADOR - " + doador.getNome() + " - SISTEMA DE ATENDIMENTO - "
                    + atualHemocentro.getNome() + " ///");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Listar coletas");
            System.out.println("2 - Listar exames");
            System.out.println("3 - Voltar");
            int escolha = entradaUsuario(sc, 1, 3);
            switch (escolha) {
                case 1:
                    Hemominas.getInstance().listarColetas(atualHemocentro, doador);
                    break;
                case 2:
                    Hemominas.getInstance().listarExames(atualHemocentro, doador);
                    break;
                case 3:
                    return;
            }
        }
    }

    static void sistemaColeta(Scanner sc) {
        while (true) {
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
                    atualizarColeta(sc);
                    break;
                case 3:
                    try {
                        Hemominas.getInstance().listarColetas(atualHemocentro);
                        break;
                    } catch (NoSuchElementException e) {
                        System.err.println("Erro: " + e);
                        break;
                    } catch (NullPointerException e) {
                        System.err.println("Erro: " + e);
                        break;
                    }

                case 4:
                    deletarColeta(sc);
                    break;
                case 5:
                    return;
            }
        }
    }

    static void adicionarColeta(Scanner sc) {
        System.out.println("Digite o CPF do Doador");
        try {
            String cpf = sc.nextLine();

            Doador doadorAtual = Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpf);
            System.out.println("Digite o Tipo Sanguineo do doador");
            String escolha = sc.nextLine();
            TipoSanguineo tipo = TipoSanguineo.stringParaTipoSanguineo(escolha);
            System.out.println(tipo);
            System.out.println(doadorAtual.getNome());
            Coleta coleta = Coleta.getInstance(tipo, doadorAtual, LocalDate.now());

            coleta.setExame(adicionaExame(sc));
            Hemominas.getInstance().adicionaColeta(atualHemocentro, coleta);
        } catch (NoSuchElementException e) {
            System.err.println("Erro: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Erro: " + e);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e);

        } catch (NullPointerException e) {
            System.err.println("Erro: " + e);

        }

    }

    static List<Exame> adicionaExame(Scanner sc) { // Modificar para resultado ser padrão
        List<Exame> exameColetaAtual = new ArrayList<>();

        System.out.println("Digite 'positivo' ou 'negativo'");
        System.out.println("Digite o resultado do exame de Hepatite B");

        String resultado = sc.nextLine().trim().toLowerCase();
        exameColetaAtual.add(Exame.getInstance(TipoExame.hepatiteB, resultado));

        System.out.println("Digite o resultado do exame de Hepatite C");
        resultado = sc.nextLine().trim().toLowerCase();

        exameColetaAtual.add(Exame.getInstance(TipoExame.hepatiteC, resultado));
        System.out.println("Digite o resultado do exame de Sifílis   ");

        resultado = sc.nextLine().trim().toLowerCase();
        exameColetaAtual.add(Exame.getInstance(TipoExame.sifilis, resultado));

        System.out.println("Digite o resultado do exame da Doença de Chagas");
        resultado = sc.nextLine().trim().toLowerCase();

        exameColetaAtual.add(Exame.getInstance(TipoExame.Chagas, resultado));
        System.out.println("Digite o resultado do exame de HTLV");

        resultado = sc.nextLine().trim().toLowerCase();
        exameColetaAtual.add(Exame.getInstance(TipoExame.HTLV, resultado));

        return exameColetaAtual;

    }

    static void atualizarColeta(Scanner sc) {
        System.out.println("Digite o id da coleta que deseja alterar");
        try {
            int id = sc.nextInt();
            Coleta coletaMod = atualHemocentro.retornaColeta(id);
            sc.nextLine();
            System.out.println("O que deseja alterar");
            System.out.println("1 - CPF do Doador");
            System.out.println("2 - Tipo Sanguineo");
            System.out.println("3 - Lista de Exames");
            System.out.println("4 - Voltar");
            String escolha = sc.nextLine().trim().toLowerCase().replace(" ", "").replace("-", "");
            switch (escolha) {
                case "1", "cpf", "1cpfdodoador", "cpfdoador":
                    System.out.println("Digite o CPF do novo doador");
                    String cpfnovo = "0";
                    while (Doador.validaCPF(cpfnovo) == false) {
                        cpfnovo = sc.nextLine();
                        if (Doador.validaCPF(cpfnovo) == false) {
                            System.out.println("CPF Inválido");
                        }
                    }
                    try {
                        coletaMod.setDoador(Hemominas.getInstance().pesquisaDoador(atualHemocentro, cpfnovo));
                        break;
                    } catch (NoSuchElementException e) {
                        System.err.println("Erro: " + e);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Erro: " + e);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Erro: " + e);
                        break;

                    } catch (NullPointerException e) {
                        System.err.println("Erro: " + e);
                        break;

                    }

                case "2", "tiposanguineo", "2tiposanguineo":
                    System.out.println("Digite o Tipo Sanguineo do doador");
                    String tipoS = sc.nextLine();
                    try {
                        TipoSanguineo tipo = TipoSanguineo.stringParaTipoSanguineo(tipoS);
                        coletaMod.setTipo(tipo);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Erro: " + e);
                        break;

                    } catch (NullPointerException e) {
                        System.err.println("Erro: " + e);
                        break;

                    }

                case "3", "listadeexames", "exame", "exames":
                    try {
                        List<Exame> listaExamesnovo = adicionaExame(sc);
                        coletaMod.setListaExames(listaExamesnovo);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Erro: " + e);
                        break;

                    } catch (NullPointerException e) {
                        System.err.println("Erro: " + e);
                        break;

                    }

                default:
                    return;

            }
            try {
                Hemominas.getInstance().atualizacaoColeta(atualHemocentro, coletaMod);
            } catch (IllegalArgumentException e) {
                System.err.println("Erro: " + e);

            } catch (NullPointerException e) {
                System.err.println("Erro: " + e);

            }

        } catch (InputMismatchException e) {
            System.out.println("Digite um número inteiro");
            atualizarColeta(sc);
        }

    }

    static void deletarColeta(Scanner sc) {
        System.out.println("Digite o id da coleta que deseja alterar");
        try {
            int id = sc.nextInt();
            Coleta coletaMod = atualHemocentro.retornaColeta(id);
            Hemominas.getInstance().deletarColeta(atualHemocentro, coletaMod);

        } catch (InputMismatchException e) {
            System.out.println("Digite um número inteiro");
            deletarColeta(sc);

        }
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
                    try {
                        Hemominas.getInstance().listarDoadores(atualHemocentro);
                        break;
                    } catch (NoSuchElementException e) {
                        System.err.println("Erro: " + e);
                        break;
                    } catch (NullPointerException e) {
                        System.err.println("Erro: " + e);
                        break;
                    }

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
        System.out.print("Digite o CPF do doador a ser removido: ");
        cpfInput = sc.nextLine();
        if (cpfInput.isEmpty() || !Doador.validaCPF(cpfInput)) {
            System.out.println("CPF inválido.");
        }

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

    static void sistemaHemocentro(Scanner sc) {
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

    static void adicionarHemocentro(Scanner sc) {
        while (true) {
            System.out.println("Digite o nome do hemocentro...");
            String nome = sc.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode ser vazio");
                continue;
            }
            System.out.println("Digite o endereço do hemocentro...");
            String endereco = sc.nextLine();
            if (endereco.isEmpty()) {
                System.out.println("Endereço não pode ser vazio");
                continue;
            }
            String cep = "0";
            while (Hemocentro.isValidCep(cep) == false) {
                System.out.println("Digite o CEP do hemocentro...");
                cep = sc.nextLine();

                if (Hemocentro.isValidCep(cep) == false) {
                    System.out.println("CEP INVALIDO");
                }
            }

            System.out.println("Digite o email do hemocentro...");
            String email = sc.nextLine();
            if (email.isEmpty()) {
                System.out.println("Email não pode ser vazio");
                continue;
            }
            System.out.println("Digite o telefone do hemocentro...");
            String telefone = sc.nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Telefone não pode ser vazio");
                continue;
            }
            try {
                Hemocentro hemocentro = Hemocentro.getInstance(nome, endereco, cep, email, telefone);
                Hemominas.getInstance().cadastrarHemocentro(hemocentro);
            } catch (IllegalArgumentException e) {
                System.err.println("Hemocentro a ser cadastrado não pode ser nulo.");
            } catch (RuntimeException e) {
                System.out.println("Erro ao adicionar o primeiro hemocentro.");

            }
            return;

        }

    }

    static void atualizarHemocentro(Scanner sc) {

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
        try {
            Hemominas.getInstance().listarHemocentros();
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

    }

    static void listarEstoque(Scanner sc) {
        System.out.println("Digite se quer listar 1 - TODOS ou 2 - VALIDOS");
        String escolha = sc.nextLine().toLowerCase().trim().replace(" ", "");
        switch (escolha) {
            case "listartodos", "todos", "1", "1-todos", "listar1-todos":
                atualHemocentro.listarEstoqueTotal();
                break;
            case "validos", "listarvalidos", "listarválidos", "2", "2-validos", "listar2-validos":
                atualHemocentro.listarEstoqueValido();
                break;
            default:
                return;
        }
    }

    static void deletarHemocentro(Scanner sc) {
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
