import java.util.Date;

public abstract class Pessoa {

    protected String nome;
    protected String cpf;
    protected String genero;
    protected Date dataNasc;
    protected String telefone;
    protected Hemocentro hemocentro;

    protected Pessoa(String nome, String cpf, String genero, Date dataNasc, String telefone, Hemocentro hemocentro) {
        this.nome = nome;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.hemocentro = hemocentro;
    }

    public static boolean validaCPF(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] cpfIntArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfIntArray[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int digito1 = calculaDigitoVerificador(cpfIntArray, 10);
        int digito2 = calculaDigitoVerificador(cpfIntArray, 11);

        return (cpfIntArray[9] == digito1) && (cpfIntArray[10] == digito2);
    }

    public static int calculaDigitoVerificador(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getNascimento() {
        return dataNasc;
    }

    public String getGenero() {
        return genero;
    }

    public Hemocentro getHemocentro() {
        try {
            return hemocentro.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Erro: " + e);
            return null;
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
