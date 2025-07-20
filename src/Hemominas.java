import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public boolean cadastrarHemocentro(Hemocentro hemocentro) throws IllegalArgumentException, RuntimeException {// Privar só pra o gestor
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
                    if (listaHemocentros.get(i).getNome().equals(nome)) {
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
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s", doador.getId(), doador.getNome(), doador.getCpf(), doador.getGenero(), sdf.format(doador.getNascimento()), doador.getTelefone());
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

    public void listaColetas(Hemocentro hemocentro){
           Hemocentro hemocentr;
           int id = getIdHemocentro(hemocentro.getNome());
            hemocentr = retornaHemocentro(id);
        for (int i = 0; i < getQtdHemocentros(); i++) {
           hemocentr.retornaListaColeta().get(i).listarExame();
        }

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
}
