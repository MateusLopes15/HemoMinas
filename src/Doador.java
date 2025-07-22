import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Doador extends Pessoa implements Cloneable {

    private List<Date> listaDoacoes;
    private int id;
    private static int identificaId;

    private Doador(String nome, String cpf, Date dateNasc, String genero, String telefone) {
        this.id = ++identificaId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dateNasc;
        this.genero = genero;
        this.telefone = telefone;
        this.listaDoacoes = new ArrayList<>();
    }

    public static Doador getInstance(String nome, String cpf, Date dateNasc, String genero, String telefone) {
        if (nome != null && cpf != null && dateNasc != null && genero != null) {
            return new Doador(nome, cpf, dateNasc, genero, telefone);
        } else
            return null;
    }

    public Doador(Doador doador) {
        this.nome = doador.getNome();
        this.cpf = doador.getCpf();
        this.dataNasc = doador.getNascimento();
        this.genero = doador.getGenero();
        this.telefone = doador.getTelefone();
        this.listaDoacoes = new ArrayList<>();
    }

    @Override
    protected Doador clone() throws CloneNotSupportedException {
        return (Doador) super.clone();
    }

    public int getId() {
        return id;
    }
    public static String geraCPF() {
        Random r = new Random();
        int[] cpf = new int[11];

        for (int i = 0; i < 9; i++) {
            cpf[i] = r.nextInt(10);
        }

        cpf[9] = calculaDigitoVerificador(cpf, 10);
        cpf[10] = calculaDigitoVerificador(cpf, 11);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(cpf[i]);
        }

        return sb.toString();
    }
    public static int calculaDigitoVerificador(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }


}
