import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Doador extends Pessoa implements Cloneable {
    
    private List<Date> listaDoacoes;
    private String genero;
    private int id;
    private int identificaId = 0;
    
    
   
    private Doador(String nome, String cpf, Date dateNasc, String genero, String telefone){
   
    this.nome = nome;
    this.cpf = cpf;
    this.dataNasc = dateNasc;
    this.genero = genero;
    this.telefone = telefone;
    this.listaDoacoes = new ArrayList<>();
   }
    public static Doador getInstance(String nome, String cpf, Date dateNasc, String genero,String telefone){
    if(nome!=null && cpf != null && dateNasc!= null && genero!=null)
    {
        return new Doador(nome, cpf, dateNasc, genero,telefone);
   }else
   return null;
}
    public Doador(Doador doador){
        this.nome = doador.getNome();
        this.cpf = doador.getCpf();
        this.dataNasc = doador.getNascimento();
        this.genero = doador.getGenero();
        this.telefone = doador.getTelefone();
        this.listaDoacoes = new ArrayList<>();
    }
    public String getTelefone(){
        return telefone;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public Date getNascimento(){
        return dataNasc;
    }
    public String getGenero() {
        return genero;
    }

@Override
protected Doador clone() throws CloneNotSupportedException {
    
    return (Doador)super.clone();
}
    


   
}
