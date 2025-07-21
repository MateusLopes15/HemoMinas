import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Coleta implements Cloneable{
    private int id;
    private TipoSanguineo tipo;
    private List<Exame> listaExames;
    private Doador doador;
    private LocalDate dataValidade;
    private static int geraid;
    


    public static Coleta getInstance(TipoSanguineo tipo, Doador doador, LocalDate dataAtual){ //fazer o trycatch
      if(tipo!= null && doador != null && dataAtual != null){
        return new Coleta(tipo, doador, dataAtual);
      }else{
        return null;
      }

    }
    private Coleta(TipoSanguineo tipo, Doador doador, LocalDate dataAtual){
      this.id = ++geraid;  
      this.tipo = tipo;

        this.doador = doador;
        this.dataValidade = dataAtual.plusYears(1);
        this.listaExames = new ArrayList<>();
    }

    public Coleta(Coleta coleta, List<Exame> listadosExames){
      this.tipo = coleta.getTipo();
        this.doador = coleta.getDoador();
        this.dataValidade = coleta.getDataValidade();
        this.listaExames = new ArrayList<>(listadosExames);
    }

    public int retornaTotal(){
      return geraid;
    }
    public void adicionarExame(Exame exame){ //Fazer as verificações
      if(exame!=null){
        this.listaExames.add(exame);
      }
      
    }
    public void setExame(List<Exame> exames){
      this.listaExames = new ArrayList<>(exames);
    }

    public int getId(){
      return id;
    }
    public TipoSanguineo getTipo() {
      return tipo;
    }
    public List<Exame> getListaExames() { 
      List<Exame> listaDeExamesTemp = new ArrayList<>();
      for(Exame exames: listaExames){
        listaDeExamesTemp.add(exames);
      }if(listaDeExamesTemp.size()>0){
        return listaDeExamesTemp;
      }
      
      return null;
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




    
 
public void setTipo(TipoSanguineo tipo) {
      this.tipo = tipo;
    }
    public void setListaExames(List<Exame> listaExames) {
      this.listaExames = new ArrayList<>(listaExames);
    }
    public void setDoador(Doador doador) {
      this.doador = doador;
    }
    public void setDataValidade(LocalDate dataValidade) {
      this.dataValidade = dataValidade;
    }
@Override
protected Coleta clone() throws CloneNotSupportedException {
    return (Coleta)super.clone();
}
    



}
