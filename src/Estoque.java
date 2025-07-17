import java.util.Date;
public class Estoque {
    private TipoSanguineo tipo;
    private int qtd_bolsas;
    private Date dataValidade;

void alterarTipoSanguineo(TipoSanguineo tipo) {
        this.tipo = tipo;
    }

    void alterarTipo(int qtd_bolsas) {
        this.qtd_bolsas = qtd_bolsas;
    }

    void alterarEstado(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
    
}
