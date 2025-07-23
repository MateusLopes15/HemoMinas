import java.util.Date;

public class Doador extends Pessoa implements Cloneable {
    private int id;
    private static int identificaId;

    private Doador(String nome, String cpf, Date dateNasc, String genero, String telefone, Hemocentro hemocentro) {
        super(nome, cpf, genero, dateNasc, telefone, hemocentro);
        this.id = ++identificaId;
    }

    public static Doador getInstance(String nome, String cpf, Date dateNasc, String genero, String telefone,
            Hemocentro hemocentro) {
        if (nome != null && cpf != null && dateNasc != null && genero != null && telefone != null) {
            return new Doador(nome, cpf, dateNasc, genero, telefone, hemocentro);
        } else
            return null;
    }

    @Override
    protected Doador clone() throws CloneNotSupportedException {
        return (Doador) super.clone();
    }

    public int getId() {
        return id;
    }

}
