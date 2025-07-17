import java.util.ArrayList;
import java.util.List;

public class Hemocentro implements Cloneable {
    private String nome;
    private String endereco;
    private String cep;
    private String email;
    private String telefone;
    // private List<Estoque> listaestoques;
    private List<Doador> listadoadores;
    private List<Coleta> listaColetas;


private Hemocentro(String nome, String endereco, String cep, String email, String telefone){
    this.nome = nome;
    this.endereco = endereco;
    this.cep = cep;
    this.email = email;
    this.telefone = telefone;
    this.listaColetas = new ArrayList<>();
    this.listadoadores = new ArrayList<>();
    
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
    this.listadoadores = new ArrayList<>();
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
@Override
public Hemocentro clone() throws CloneNotSupportedException {
        
     return (Hemocentro) super.clone();
    }

 public void adicionarDoador(Doador doador){ //fazer otry e verificar se é null
        listadoadores.add(doador);
    }
public void listaDoadores(){
    for(Doador doadores: listadoadores){
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
