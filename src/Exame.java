public class Exame implements Cloneable {

    private TipoExame exame;
    private String resultado;

    public static Exame getInstance(TipoExame exame, String resultado) {
        return new Exame(exame, resultado);
    }

    private Exame(TipoExame exame, String resultado) {
        this.exame = exame;
        this.resultado = resultado;
    }

    public Exame(Exame exame) {
        this.exame = exame.getExame();
        this.resultado = exame.getResultado();
    }

    public TipoExame getExame() {
        return exame;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    protected Exame clone() throws CloneNotSupportedException {

        return (Exame) super.clone();
    }

}
