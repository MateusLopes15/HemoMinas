import java.util.Date;

public class Funcionario extends Pessoa implements Cloneable {
    Date dataIngresso;

    private Funcionario(String nome, String cpf, Date dataNasc, String genero, String telefone, Date dataIngresso,
            Hemocentro hemocentro) {
        super(nome, cpf, genero, dataNasc, telefone, hemocentro);
        this.dataIngresso = dataIngresso;
    }

    public static Funcionario getInstance(String nome, String cpf, Date dateNasc, String genero, String telefone,
            Date dataIngresso, Hemocentro hemocentro) {
        if (nome != null && cpf != null && dateNasc != null && genero != null && telefone != null
                && dataIngresso != null) {
            return new Funcionario(nome, cpf, dateNasc, genero, telefone, dataIngresso, hemocentro);
        } else
            return null;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    @Override
    protected Funcionario clone() throws CloneNotSupportedException {
        return (Funcionario) super.clone();
    }
}
