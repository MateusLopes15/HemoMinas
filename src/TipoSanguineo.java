public enum TipoSanguineo {
    A_POSITIVO,
    A_NEGATIVO,
    B_POSITIVO,
    B_NEGATIVO,
    AB_POSITIVO,
    AB_NEGATIVO,
    O_POSITIVO,
    O_NEGATIVO;


    public static TipoSanguineo stringParaTipoSanguineo(String entrada){
        if(entrada == null)
            return null;
            switch (entrada.trim().toUpperCase().replace(" ", "")) {
                case "A+","APOS","APOSITIVO","1":
                    return A_POSITIVO;
                case "A-","ANEG","ANEGATIVO","2":
                    return A_NEGATIVO;
                case "B+", "BPOS","BPOSITIVO","3":
                    return B_POSITIVO;
                case "B-", "BNEG","BNEGATIVO","4":
                    return B_NEGATIVO;
                case "AB+","ABPOS","ABPOSITIVO","5":
                    return AB_POSITIVO;
                case "AB-","ABNEG","ABNEGATIVO", "6":
                    return AB_NEGATIVO;
                case "O+","OPOS","OPOSITIVO","7":
                    return O_POSITIVO;
                case "O-","ONEG","ONEGATIVO","8":
                default:
                    return null;
            }
    }

    public static void listarOpcoes() {
        System.out.println("1 - A+");
        System.out.println("2 - A-");
        System.out.println("3 - B+");
        System.out.println("4 - B-");
        System.out.println("5 - AB+");
        System.out.println("6 - AB-");
        System.out.println("7 - O+");
        System.out.println("8 - O-");
    }
}
