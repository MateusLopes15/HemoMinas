import java.util.Date;

public abstract class Pessoa {
    
    protected String nome;
    protected String cpf;
    protected Date dataNasc;
    protected String telefone;

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
    
}
