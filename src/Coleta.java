import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
public class Coleta {
   
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
    }
    
}
