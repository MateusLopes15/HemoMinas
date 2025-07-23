import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hemocentro implements Cloneable {
    private String nome;
    private String endereco;
    private String cep;
    private String email;
    private String telefone;
    // private List<Estoque> listaestoques;
    private List<Doador> listaDoadores;
    private List<Coleta> listaColetas;
    private List<Funcionario> listaFuncionarios;

    private Hemocentro(String nome, String endereco, String cep, String email, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.listaColetas = new ArrayList<>();
        this.listaDoadores = new ArrayList<>();
        this.listaFuncionarios = new ArrayList<>();

        // this.listaestoques = new ArrayList<>();
    }

    public static Hemocentro getInstance(String nome, String endereco, String cep, String email, String telefone) {
        if (nome != null && endereco != null && cep != null & email != null && telefone != null) {
            return new Hemocentro(nome, endereco, cep, email, telefone);
        } else
            return null;

    }

    public Hemocentro(Hemocentro hemocentro) {
        this.nome = hemocentro.getNome();
        this.endereco = hemocentro.getEndereco();
        this.cep = hemocentro.getCep();
        this.email = hemocentro.getEmail();
        this.telefone = hemocentro.getTelefone();
        this.listaColetas = new ArrayList<>();
        this.listaDoadores = new ArrayList<>();
        this.listaFuncionarios = new ArrayList<>();
    }

    // public Doador pesquisarDoador(String cpf){
    // for Vou criar dia 18 essa parte para fazer funcionar
    // }
    public static boolean isValidCep(String cep) {
        String regexCep = "^\\d{5}-\\d{3}$|^\\d{8}$";
        Pattern patternCep = Pattern.compile(regexCep);
        if (cep == null || cep.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = patternCep.matcher(cep.trim());
        return matcher.matches();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() { // Fazer Verificação de CEP
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    void setNome(String novonome) {
        this.nome = novonome;
    }

    void setEndereco(String novoendereco) {
        this.endereco = novoendereco;
    }

    void setCep(String novocep) {
        this.cep = novocep;
    }

    void setEmail(String novoemail) {
        this.email = novoemail;
    }

    void setTelefone(String novotelefone) {
        this.telefone = novotelefone;
    }

    public void adicionarColeta(Coleta coleta) {
        listaColetas.add(coleta);
    }

    @Override
    public Hemocentro clone() throws CloneNotSupportedException {

        return (Hemocentro) super.clone();
    }

    public List<Doador> retornaListaDoador() {
        List<Doador> listadoadoresCopia = new ArrayList<>();
        for (Doador doadorOriginal : this.listaDoadores) {
            try {
                listadoadoresCopia.add(doadorOriginal.clone());
            } catch (CloneNotSupportedException e) {
                System.out.println("Erro ao clonar doador.");
            }
        }
        return listadoadoresCopia;
    }

    public List<Coleta> retornaListaColeta() {
        List<Coleta> listacoletaCopia = new ArrayList<>();
        for (Coleta coletaOriginal : this.listaColetas) {
            try {
                listacoletaCopia.add(coletaOriginal.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        return listacoletaCopia;
    }

    public Coleta retornaColeta(int ide) {
        for (Coleta coletaOriginal : this.listaColetas) {
            try {
                if (coletaOriginal.getId() == ide) {
                    return coletaOriginal.clone();
                }

            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
        return null;

    }

    public void adicionarDoador(Doador doador) { // fazer otry e verificar se é null
        listaDoadores.add(doador);
    }

    public void removerDoador(String cpf) {
        for (Doador d : listaDoadores) {
            if (d.getCpf().equals(cpf)) {
                listaDoadores.remove(d);
                return;
            }
        }
    }

    public void atualizaDoadorHemocentro(String cpf, Doador doador) {
        for (Doador d : listaDoadores) {
            if (d.getCpf().equals(cpf)) {
                d.setNome(doador.getNome());
                d.setCpf(doador.getCpf());
                d.setDataNasc(doador.getNascimento());
                d.setGenero(doador.getGenero());
                d.setTelefone(doador.getTelefone());
                return;
            }
        }
    }

    public void atualizaColetaHemocentro(Coleta coleta) {
        for (Coleta c : listaColetas) {
            if (coleta.getId() == c.getId()) {
                c.setDoador(coleta.getDoador());
                c.setDataValidade(coleta.getDataValidade());
                c.setExame(coleta.getListaExames());
                c.setTipo(coleta.getTipo());
            }
        }
    }

    public int getIDColeta(Coleta coleta) {
        int x = -1;
        for (int i = 0; i < listaColetas.size(); i++) {
            if (listaColetas.get(i).getId() == coleta.getId()) {
                x = i;
            }
        }
        return x;
    }

    public void deletaColeta(Coleta coleta) {
        int i = getIDColeta(coleta);
        if (i >= 0) {
            listaColetas.remove(i);
        }
    }

    public void listaDoadores() {
        for (Doador doadores : listaDoadores) {
            System.out.println(doadores.getNome());
        }

    }
    
    public void listarEstoqueTotal() {
        int[] bolsas = new int[8];
        for (int i = 0; i < listaColetas.size(); i++) {
            if (listaColetas.get(i).getTipo().equals(TipoSanguineo.A_POSITIVO)) {
                
                bolsas[0]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.A_NEGATIVO)) {
                bolsas[1]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.B_POSITIVO)) {
                bolsas[2]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.B_NEGATIVO)) {
                bolsas[3]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.AB_POSITIVO)) {
                bolsas[4]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.AB_NEGATIVO)) {
                bolsas[5]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.O_POSITIVO)) {
                bolsas[6]++;
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.O_NEGATIVO)) {
                bolsas[7]++;
            }
        }
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "A+", "A-", "B+", "B-", "AB+","AB-","O+","O-");
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s",bolsas[0],bolsas[1],bolsas[2],bolsas[3],bolsas[4],bolsas[5],bolsas[6],bolsas[7]);
    }
    public void listarEstoqueValido(){
        int[] bolsas = new int[8];
        int qtd =0;
        for (int i = 0; i < listaColetas.size(); i++) {
            if (listaColetas.get(i).getTipo().equals(TipoSanguineo.A_POSITIVO)) {
                
               
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {

                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[0]++;
                    qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.A_NEGATIVO)) {
                
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[1]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.B_POSITIVO)) {
               
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[2]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.B_NEGATIVO)) {
                
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[3]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.AB_POSITIVO)) {
                
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[4]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.AB_NEGATIVO)) {
               
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[5]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.O_POSITIVO)) {
               
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[6]++;
                     qtd = 0;
                    break;


                };
            } else if (listaColetas.get(i).getTipo().equals(TipoSanguineo.O_NEGATIVO)) {
                
    for (int j = 0; j < listaColetas.get(i).getListaExames().size(); j++) {
                    if (listaColetas.get(i).getListaExames().get(j).getResultado().equals("negativo")) {
                        qtd++;
                    }
                }

                 switch (qtd) {
                    case 5:
                    bolsas[7]++;
                     qtd = 0;
                    break;


                };
            }
        }
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "A+", "A-", "B+", "B-", "AB+","AB-","O+","O-");
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s",bolsas[0],bolsas[1],bolsas[2],bolsas[3],bolsas[4],bolsas[5],bolsas[6],bolsas[7]);


        
    }


    // public List<Estoque> getListaestoques() {
    // return listaestoques;
    // }

    // public List<Doador> getListadoadores() {
    // return listadoadores;
    // }

    // public List<Coleta> getListaColetas() {
    // return listaColetas;
    // }

    // public void addEstoque(){
    // //listaestoques.add();
    // }

}
