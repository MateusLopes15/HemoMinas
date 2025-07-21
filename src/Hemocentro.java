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


private Hemocentro(String nome, String endereco, String cep, String email, String telefone){
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
public static Hemocentro getInstance(String nome, String endereco, String cep, String email, String telefone){
    if(nome !=null && endereco !=null && cep != null & email!= null && telefone != null){
        return new Hemocentro(nome, endereco, cep, email, telefone);
    }else
    return null;
    
}
public Hemocentro(Hemocentro hemocentro){
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
//     for Vou criar dia 18 essa parte para fazer funcionar
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


public String getCep() { //Fazer Verificação de CEP
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
public void adicionarColeta(Coleta coleta){
    listaColetas.add(coleta);
}

@Override
public Hemocentro clone() throws CloneNotSupportedException {
        
     return (Hemocentro) super.clone();
    }

public List<Doador> retornaListaDoador(){
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

public List<Coleta> retornaListaColeta(){
   List<Coleta> listacoletaCopia = new ArrayList<>();
    for (Coleta coletaOriginal : this.listaColetas) {
            try {
                listacoletaCopia.add(coletaOriginal.clone());
            } catch (CloneNotSupportedException e) {
               
            }
        }
        return listacoletaCopia;
    }
public Coleta retornaColeta(int ide){
    for (Coleta coletaOriginal : this.listaColetas) {
            try {
                if(coletaOriginal.getId()==ide){
                    return coletaOriginal.clone();
                }
                
            } catch (CloneNotSupportedException e) {
               return null;
            }
        }
        return null;

}


    public void adicionarDoador(Doador doador){ //fazer otry e verificar se é null
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

    public void atualizaDoadorHemocentro(String cpf, Doador doador){
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
    public void atualizaColetaHemocentro(Coleta coleta){
        for(Coleta c: listaColetas){
            if(coleta.getId()==c.getId()){
                c.setDoador(coleta.getDoador());
                c.setDataValidade(coleta.getDataValidade());
                c.setExame(coleta.getListaExames());
                c.setTipo(coleta.getTipo());
            }
        }
    }
    public int getIDColeta(Coleta coleta){
        int x = -1;
        for(int i = 0;i< listaColetas.size() ;i++){
            if(listaColetas.get(i).getId() == coleta.getId()){
                x = i;
            }
        }
        return x;
    }
    public void deletaColeta(Coleta coleta){
        int i = getIDColeta(coleta);
        if(i>=0){
            listaColetas.remove(i);
        }
    }

public void listaDoadores(){
    for(Doador doadores: listaDoadores){
        System.out.println(doadores.getNome());
    }


}
















// public List<Estoque> getListaestoques() {
// 	return listaestoques;
// }


// public List<Doador> getListadoadores() {
// 	return listadoadores;
// }


// public List<Coleta> getListaColetas() {
// 	return listaColetas;
// }

// public void addEstoque(){
//     //listaestoques.add();
// }


}
