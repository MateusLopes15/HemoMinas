import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Date;

public final class Hemominas {
    // Código/Nome/Endereco/CEP/EMAIL/Telefone
    private static Hemominas instance;
    private String nomeSede;
    private String enderecoSede;
    private String cepSede;
    private String emailSede;
    private String telefoneSede;
    private List<Hemocentro> listaHemocentros;

    public static synchronized Hemominas getInstance() {
        if (instance == null) {
            instance = new Hemominas();
        }
        return instance;
    }

    private Hemominas() {
        this.nomeSede = "HemoMinas";
        this.enderecoSede = "Rua Grão Pará, 882, bairro Funcionários, Belo Horizonte, Minas Gerais";
        this.cepSede = "30150-341";
        this.emailSede = "gabinete@hemominas.mg.gov.br";
        this.telefoneSede = "(31) 3768-7450";
        this.listaHemocentros = new ArrayList<>();

    }

    public int getQtdHemocentros() {
        if (listaHemocentros.size() != 0) {
            return listaHemocentros.size();
        } else
            return 0;
    }

    public List<Hemocentro> retornarHemocentros() {
        List<Hemocentro> HemocentroCopia = new ArrayList<>();

        try {
            for (Hemocentro hemocentroOriginal : listaHemocentros) {
                HemocentroCopia.add(hemocentroOriginal.clone());
            }
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
        }

        return HemocentroCopia;
    }

    public Hemocentro retornaHemocentro(int id) throws NoSuchElementException {
        Hemocentro HemocentroCopia = null;
        try {
            if (listaHemocentros.isEmpty()) {
                throw new NoSuchElementException("Não há hemocentros na lista para clonar."); // tirar essa parte
            }
            HemocentroCopia = listaHemocentros.get(id).clone();
            return HemocentroCopia;
        } catch (CloneNotSupportedException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean cadastrarHemocentro(Hemocentro hemocentro) throws IllegalArgumentException, RuntimeException {// Privar
                                                                                                                 // só
                                                                                                                 // pra
                                                                                                                 // o
                                                                                                                 // gestor
        if (hemocentro == null) {
            throw new IllegalArgumentException();
        }
        if (listaHemocentros.size() == 0) {
            if (listaHemocentros.add(hemocentro)) {
                return true;
            } else
                throw new RuntimeException();
        } else {
            for (Hemocentro hemocentros : listaHemocentros) {
                if (hemocentros.getNome().equals(hemocentro.getNome())) {
                    throw new RuntimeException("Hemocentro com mesmo nome já existente");
                } else {
                    listaHemocentros.add(hemocentro);
                    return true;
                }
            }
        }
        return false;
    }

    public int getIdHemocentro(String nome) throws IllegalArgumentException { // Verificar os returns
        int id = -1;
        try {
            if (listaHemocentros.size() > 0) {
                for (int i = 0; i < listaHemocentros.size(); i++) {
                    if (listaHemocentros.get(i).getNome().trim().equals(nome)) {
                        id = i;
                        break;
                    }

                }
            }
            return id;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void listarHemocentros() {
        List<Hemocentro> hemocentr;
        hemocentr = Hemominas.getInstance().retornarHemocentros();
        for (int i = 0; i < getQtdHemocentros(); i++) {
            System.out.println(hemocentr.get(i).getNome());
        }
    }

    public void removeHemocentro(int id) { // Privar só pra o gestor
        listaHemocentros.remove(id);
    }

    public void atualizaHemocentro(Hemocentro hemocentro) {
        for (Hemocentro atualizado : listaHemocentros) {
            if (atualizado.getNome().equals(hemocentro.getNome())) {

            }
        }
    }

    public void atualizacaoDosHemocentro(int id, Hemocentro hemocentro) { // Privar só pra o gestor
        listaHemocentros.get(id).setCep(hemocentro.getCep());
        listaHemocentros.get(id).setEmail(hemocentro.getEmail());
        listaHemocentros.get(id).setEndereco(hemocentro.getEndereco());
        listaHemocentros.get(id).setNome(hemocentro.getNome());
        listaHemocentros.get(id).setTelefone(hemocentro.getTelefone());
        // listaHemocentros.get(id).
    }

    public void adicionaDoador(Hemocentro hemocentro, Doador doador) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).adicionarDoador(doador);
    }

    public void adicionaColeta(Hemocentro hemocentro, Coleta coleta) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).adicionarColeta(coleta);
    }

    public void listarColetas(Hemocentro hemocentro) {
        Hemocentro hemocentr;
        int qtd = 0;
        int id = getIdHemocentro(hemocentro.getNome());
        hemocentr = retornaHemocentro(id);
        List<Coleta> listaTodasColetas = hemocentr.retornaListaColeta();
        if (listaTodasColetas.size() == 0) {
            System.out.println("Não existem doadores cadastrados.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s", "ID", "TIPO", "CPF", "VALIDADE", "RESULTADO");
        System.out.println();
        for (int i = 0; i < listaTodasColetas.size(); i++) {
            List<Exame> exames = listaTodasColetas.get(i).getListaExames();
            Coleta coleta = listaTodasColetas.get(i);
            for (int j = 0; j < exames.size(); j++) {
                if (exames.get(j).getResultado().equals("negativo")) {
                    qtd++;
                }
            }
            String usabilidade = "";
            if (qtd == 5) {
                usabilidade = "APROVADA";
            } else {
                usabilidade = "NEGADA";
            }
            System.out.printf("%-30s%-30s%-30s%-30s%-30s", coleta.getId(), coleta.getTipo(),
                    coleta.getDoador().getCpf(), coleta.getDataValidade().format(formatter), usabilidade);
            System.out.println();
            qtd = 0;
        }
    }

    public void listarColetas(Hemocentro hemocentro, Doador doador) {
        List<Coleta> listaColetas = hemocentro.retornaListaColeta();
        if (listaColetas.size() == 0) {
            System.out.println("Não existem coletas cadastradas para o doador.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s", "ID", "TIPO", "CPF", "VALIDADE", "RESULTADO");
        System.out.println();
        for (int i = 0; i < listaColetas.size(); i++) {
            int qtd = 0;
            List<Exame> exames = listaColetas.get(i).getListaExames();
            Coleta coleta = listaColetas.get(i);
            if (coleta.getDoador().getCpf().equals(doador.getCpf())) {
                for (int j = 0; j < exames.size(); j++) {
                    if (exames.get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                String usabilidade = switch (qtd) {
                    case 5:
                        yield "APROVADA";
                    default:
                        yield "NEGADA";
                };

                System.out.printf("%-30s%-30s%-30s%-30s%-30s", coleta.getId(), coleta.getTipo(),
                        coleta.getDoador().getCpf(), coleta.getDataValidade().format(formatter), usabilidade);
                System.out.println();
            }
        }
    }

    public void listarExames(Hemocentro hemocentro, Doador doador) {
        List<Coleta> listaColetas = hemocentro.retornaListaColeta();
        if (listaColetas.size() == 0) {
            System.out.println("Não existem coletas cadastradas para o doador.");
            return;
        }

        System.out.printf("%-30s%-30s%-30s", "COLETA", "EXAME", "RESULTADO");
        System.out.println();
        for (int i = 0; i < listaColetas.size(); i++) {
            List<Exame> exames = listaColetas.get(i).getListaExames();
            Coleta coleta = listaColetas.get(i);
            if (coleta.getDoador().getCpf().equals(doador.getCpf())) {
                for (int j = 0; j < exames.size(); j++) {
                    Exame exame = exames.get(j);
                    System.out.printf("%-30s%-30s%-30s", coleta.getId(), exame.getExame(), exame.getResultado());
                    System.out.println();
                }
            }
        }
    }

    public void atualizacaoColeta(Hemocentro hemocentro, Coleta coleta) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).atualizaColetaHemocentro(coleta);
    }

    public void deletarColeta(Hemocentro hemocentro, Coleta coleta) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).deletaColeta(coleta);
    }

    public void listarDoadores(Hemocentro h) {
        int id = getIdHemocentro(h.getNome());
        Hemocentro hemocentro = retornaHemocentro(id);
        List<Doador> listaDoadores = hemocentro.retornaListaDoador();
        if (listaDoadores.size() == 0) {
            System.out.println("Não existem doadores cadastrados.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s", "ID", "NOME", "CPF", "GÊNERO", "DATA NASC", "TELEFONE");
        System.out.println();
        for (int i = 0; i < listaDoadores.size(); i++) {
            Doador doador = listaDoadores.get(i);
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s", doador.getId(), doador.getNome(), doador.getCpf(),
                    doador.getGenero(), sdf.format(doador.getNascimento()), doador.getTelefone());
            System.out.println();
        }
    }

    public int getPosicaoDoador(Hemocentro h, String cpf) {
        int id = getIdHemocentro(h.getNome());
        Hemocentro hemocentro = retornaHemocentro(id);
        List<Doador> listaDoadores = hemocentro.retornaListaDoador();
        for (int i = 0; i < listaDoadores.size(); i++) {
            Doador doadores = listaDoadores.get(i);
            if (doadores.getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }

    public Doador pesquisaDoador(Hemocentro hemocentro, String cpf) {
        int id = getIdHemocentro(hemocentro.getNome());
        for (Doador doadores : listaHemocentros.get(id).retornaListaDoador()) {
            if (doadores.getCpf().equals(cpf)) {
                try {
                    return doadores.clone();
                } catch (CloneNotSupportedException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public void atualizarDoador(Hemocentro h, Doador doador, String cpfAntigo) {
        for (Hemocentro hemocentroAtual : listaHemocentros) {
            if (hemocentroAtual.getNome().equals(h.getNome())) {
                hemocentroAtual.atualizaDoadorHemocentro(cpfAntigo, doador);
                return;
            }
        }
    }

    public void removerDoador(Hemocentro h, Doador doador) {
        for (Hemocentro hemoAtual : listaHemocentros) {
            if (hemoAtual.getNome().equals(h.getNome())) {
                hemoAtual.removerDoador(doador.getCpf());
                return;
            }
        }
    }

    public static void inicializarHemocentrosDeMinas() {
        System.out.println("--- Inicializando Hemocentros de Minas Gerais ---");

        try {
            Hemocentro h1 = Hemocentro.getInstance(
                    "Hemocentro MG - Central",
                    "Rua da Bahia, 111 - Centro",
                    "30160-001",
                    "central@hemominas.mg.gov.br",
                    "(31) 3211-1111");
            if (h1 != null) {
                Hemominas.getInstance().cadastrarHemocentro(h1);
                System.out.println("Hemocentro '" + h1.getNome() + "' inicializado.");
            } else {
                System.err.println("Erro: Não foi possível criar 'Hemocentro MG - Central' devido a parâmetros nulos.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao inicializar Hemocentro MG - Central (Argumento Inválido): " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Erro ao inicializar Hemocentro MG - Central: " + e.getMessage());
        }

        try {
            Hemocentro h2 = Hemocentro.getInstance(
                    "Hemocentro MG - Juiz de Fora",
                    "Av. Rio Branco, 2500 - Centro",
                    "36010-002",
                    "jf@hemominas.mg.gov.br",
                    "(32) 3212-2222");
            if (h2 != null) {
                Hemominas.getInstance().cadastrarHemocentro(h2);
                System.out.println("Hemocentro '" + h2.getNome() + "' inicializado.");
            } else {
                System.err.println(
                        "Erro: Não foi possível criar 'Hemocentro MG - Juiz de Fora' devido a parâmetros nulos.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(
                    "Erro ao inicializar Hemocentro MG - Juiz de Fora (Argumento Inválido): " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Erro ao inicializar Hemocentro MG - Juiz de Fora: " + e.getMessage());
        }

        try {
            Hemocentro h3 = Hemocentro.getInstance(
                    "Hemocentro MG - Uberlândia",
                    "Rua Getúlio Vargas, 300 - Tabajaras",
                    "38400-015",
                    "uberlandia@hemominas.mg.gov.br",
                    "(34) 3233-3333");
            if (h3 != null) {
                Hemominas.getInstance().cadastrarHemocentro(h3);
                System.out.println("Hemocentro '" + h3.getNome() + "' inicializado.");
            } else {
                System.err.println(
                        "Erro: Não foi possível criar 'Hemocentro MG - Uberlândia' devido a parâmetros nulos.");
            }
        } catch (IllegalArgumentException e) {
            System.err
                    .println("Erro ao inicializar Hemocentro MG - Uberlândia (Argumento Inválido): " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Erro ao inicializar Hemocentro MG - Uberlândia: " + e.getMessage());
        }

        System.out.println("--- Inicialização concluída ---");
    }

    public static void inicializarDoadoresParaHemocentros() {
        Hemominas sistemaHemominas = Hemominas.getInstance();

        System.out.println("\n--- Inicializando Doadores para Hemocentros ---");

        List<Hemocentro> hemocentrosCadastrados = sistemaHemominas.retornarHemocentros();

        if (hemocentrosCadastrados.isEmpty()) {
            System.out.println(
                    "Nenhum hemocentro encontrado para adicionar doadores. Primeiro, cadastre os hemocentros.");
            return;
        }

        Random random = new Random();

        String[] nomesMasculinos = {
            "João", "Pedro", "Lucas", "Gabriel", "Felipe", 
            "Bruno", "Gustavo", "Henrique", "Daniel", "Rafael"
        };

        String[] nomesFemininos = {
            "Maria", "Ana", "Juliana", "Fernanda", "Camila",
            "Patrícia", "Gabriela", "Larissa", "Amanda", "Carolina"
        };

        String[] sobrenomes = {
            "Silva", "Santos", "Oliveira", "Souza", "Pereira", 
            "Lima", "Costa", "Ferreira", "Rodrigues", "Almeida"
        };


        for (Hemocentro hemocentroAtual : hemocentrosCadastrados) {
            System.out.println("Adicionando doadores para o Hemocentro: " + hemocentroAtual.getNome());
            int numDoadores = 2 + random.nextInt(2);
            for (int i = 0; i < numDoadores; i++) {
                try {
                    int genero = random.nextInt(2);
                    String nomeDoador = switch (genero) {
                        case 0: yield nomesMasculinos[random.nextInt(nomesMasculinos.length)] + " " + sobrenomes[random.nextInt(sobrenomes.length)];
                        default: yield nomesFemininos[random.nextInt(nomesFemininos.length)] + " " + sobrenomes[random.nextInt(sobrenomes.length)];
                    };
                    String cpfDoador = gerarCPFValido(random);
                    LocalDate dataNascLocalDate = LocalDate.now().minusYears(random.nextInt(43) + 18);
                    Date dataNascDoador = Date.from(dataNascLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    String generoDoador = switch (genero) {
                        case 0: yield "Masculino";
                        default: yield "Feminino";
                    };
                    String telefoneDoador = "319" + (random.nextInt(90000000) + 10000000);
                    Doador novoDoador = Doador.getInstance(
                            nomeDoador, cpfDoador, dataNascDoador, generoDoador, telefoneDoador);
                    if (novoDoador != null) {
                        sistemaHemominas.adicionaDoador(hemocentroAtual, novoDoador);
                    } else {
                        System.err.println("Erro: Não foi possível criar o objeto Doador para " + nomeDoador
                                + " (parâmetros nulos).");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao criar doador (dados inválidos): " + e.getMessage());
                } catch (RuntimeException e) {

                    System.err.println(
                            "Erro ao adicionar doador ao " + hemocentroAtual.getNome() + ": " + e.getMessage());
                }
            }
        }
        System.out.println("--- Inicialização de Doadores concluída ---");
    }

    private static String gerarCPFValido(Random r) {
        int[] cpf = new int[11];

        for (int i = 0; i < 9; i++) {
            cpf[i] = r.nextInt(10);
        }

        // Primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += cpf[i] * (10 - i);
        }
        int resto1 = soma1 % 11;
        cpf[9] = (resto1 < 2) ? 0 : 11 - resto1;

        // Segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += cpf[i] * (11 - i);
        }
        int resto2 = soma2 % 11;
        cpf[10] = (resto2 < 2) ? 0 : 11 - resto2;

        StringBuilder sb = new StringBuilder();
        for (int num : cpf) {
            sb.append(num);
        }

        return sb.toString();
    }

    public static void inicializarColetasParaHemocentrosEDoadores() {
        Hemominas sistemaHemominas = Hemominas.getInstance();

        System.out.println("\n--- Inicializando Coletas com Exames para Doadores Existentes ---");

        List<Hemocentro> hemocentrosCadastrados = sistemaHemominas.retornarHemocentros();

        if (hemocentrosCadastrados.isEmpty()) {
            System.out.println("Nenhum hemocentro encontrado. Coletas não podem ser adicionadas.");
            return;
        }

        Random random = new Random();
        TipoSanguineo[] todosTiposSanguineos = TipoSanguineo.values();
        TipoExame[] todosTiposExame = TipoExame.values();

        for (Hemocentro hemocentroAtual : hemocentrosCadastrados) {
            List<Doador> doadoresDoHemocentro = hemocentroAtual.retornaListaDoador();

            if (doadoresDoHemocentro.isEmpty()) {
                System.out.println("  Hemocentro '" + hemocentroAtual.getNome()
                        + "' não tem doadores. Pulando inicialização de coletas para ele.");
                continue;
            }

            System.out.println("  Processando coletas para Hemocentro: " + hemocentroAtual.getNome());

            for (Doador doadorAtual : doadoresDoHemocentro) {
                try {

                    TipoSanguineo tipoSanguineoColeta = todosTiposSanguineos[random
                            .nextInt(todosTiposSanguineos.length)];
                    LocalDate dataAtualColeta = LocalDate.now().minusDays(random.nextInt(30)); // Coleta nos últimos 30
                                                                                               // dias

                    Coleta novaColeta = Coleta.getInstance(
                            tipoSanguineoColeta, doadorAtual, dataAtualColeta);

                    if (novaColeta == null) {
                        System.err.println("    Erro: Coleta.getInstance() retornou null para Doador "
                                + doadorAtual.getNome() + ". Pulando esta coleta.");
                        continue;
                    }

                    for (TipoExame tipoExameIndividual : todosTiposExame) { // <<-- Itera sobre CADA TIPO DE EXAME
                        String resultadoAleatorio = switch (random.nextInt(20)) { // <<-- Chance de 95% para um exame ser negativo
                            case 19: yield "positivo";
                            default: yield "negativo";
                        };

                        Exame novoExame = Exame.getInstance(tipoExameIndividual, resultadoAleatorio);

                        if (novoExame != null) {
                            novaColeta.adicionarExame(novoExame);

                        } else {
                            System.err.println("      Erro: Não foi possível criar Exame para " + tipoExameIndividual
                                    + " para a coleta de " + doadorAtual.getNome() + ".");
                        }
                    }

                    Hemominas.getInstance().adicionaColeta(hemocentroAtual, novaColeta);
                    System.out.println("    Coleta (Tipo: " + novaColeta.getTipo() + ", Exames: "
                            + novaColeta.getListaExames().size() + ") adicionada para Doador " + doadorAtual.getNome()
                            + " (" + doadorAtual.getCpf() + ") no Hemocentro " + hemocentroAtual.getNome() + ".");

                } catch (IllegalArgumentException e) {
                    System.err.println("    Erro: Dados inválidos ao criar coleta/exame para " + doadorAtual.getNome()
                            + ": " + e.getMessage());
                } catch (RuntimeException e) {
                    System.err.println("    Erro inesperado ao processar coleta para " + doadorAtual.getNome() + " no "
                            + hemocentroAtual.getNome() + ": " + e.getMessage());
                }
            }
        }
        System.out.println("\n--- Inicialização de Coletas e Exames CONCLUÍDA ---");
    }

}
