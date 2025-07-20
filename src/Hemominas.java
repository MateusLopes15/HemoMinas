import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

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

    public Hemocentro retornaHemocentro(int id) {
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

    public boolean cadastrarHemocentro(Hemocentro hemocentro) {// Privar só pra o gestor
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

    public int getIdHemocentro(String nome) { // Verificar os returns
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
        for (int i = 0; i < Hemominas.getInstance().getQtdHemocentros(); i++) {

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

    public void atualizaDoador(Hemocentro hemocentro, Doador doador) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).adicionarDoador(doador);
    }

    public void atualizaColeta(Hemocentro hemocentro, Coleta coleta) {
        int id = getIdHemocentro(hemocentro.getNome());
        listaHemocentros.get(id).adicionarColeta(coleta);
    }

    public void listaDoadores(Hemocentro hemocentro) {
        int id = getIdHemocentro(hemocentro.getNome());
        
        Hemocentro hemocentr;
        hemocentr = Hemominas.getInstance().retornaHemocentro(id);
        for (int i = 0; i < Hemominas.getInstance().getQtdHemocentros(); i++) {

            System.out.println(hemocentr.retornaListaDoador().get(i).getNome());
        }


    }
    public void listaColetas(Hemocentro hemocentro){
           Hemocentro hemocentr;
           int id = getIdHemocentro(hemocentro.getNome());
            hemocentr = Hemominas.getInstance().retornaHemocentro(id);
        for (int i = 0; i < Hemominas.getInstance().getQtdHemocentros(); i++) {

           hemocentr.retornaListaColeta().get(i).listarExame();
        }

    }

    public Doador pesquisaDoador(Hemocentro hemocentro, String nome) {
        int id = getIdHemocentro(hemocentro.getNome());

        for (Doador doadores : listaHemocentros.get(id).retornaListaDoador()) {
            if (doadores.getNome().equals(nome)) {
                try {
                    return doadores.clone();
                } catch (CloneNotSupportedException e) {
                    return null;
                }
            }
        }
        return null;

    }


}
