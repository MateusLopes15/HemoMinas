import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Coleta implements Cloneable{
   
    private TipoSanguineo tipo;
    private List<Exame> listaExames;
    private Doador doador;
    private LocalDate dataValidade;
    


    public static Coleta getInstance(TipoSanguineo tipo, Doador doador, LocalDate dataAtual){ //fazer o trycatch
      if(tipo!= null && doador != null && dataAtual != null){
        return new Coleta(tipo, doador, dataAtual);
      }else{
        return null;
      }

    }
    private Coleta(TipoSanguineo tipo, Doador doador, LocalDate dataAtual){
        this.tipo = tipo;
        this.doador = doador;
        this.dataValidade = dataAtual.plusYears(1);
        this.listaExames = new ArrayList<>();
    }

    public Coleta(Coleta coleta){
      this.tipo = coleta.getTipo();
        this.doador = coleta.getDoador();
        this.dataValidade = coleta.getDataValidade();
        this.listaExames = new ArrayList<>();
    }
    public void adicionarExame(Exame exame){ //Fazer as verificações
      this.listaExames.add(exame);
    }


    public TipoSanguineo getTipo() {
      return tipo;
    }
    public List<Exame> getListaExames() { //fazer o clone
      return listaExames;
    }
    public void listarExame(){
       for(Exame exames : listaExames){
        System.out.println(exames.getResultado());
       }
      
    }
    public Doador getDoador() {
      try{
      return doador.clone();

      }catch(CloneNotSupportedException e){
          return null;
      }
      
    }
    public LocalDate getDataValidade() {
      return dataValidade;
    }

@Override
protected Coleta clone() throws CloneNotSupportedException {
    return (Coleta)super.clone();
}
    
}
