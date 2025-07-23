public enum TipoExame {
    hepatiteB,
    hepatiteC,
    sifilis,
    Chagas,
    HTLV;

    public static TipoExame stringParaTipo(String entrada) {
        if (entrada == null)
            return null;
        switch (entrada.trim().toLowerCase()) {
            case "hepatiteb", "1":
                return hepatiteB;
            case "hepatitec", "2":
                return hepatiteC;
            case "sifílis", "sifilis", "3":
                return sifilis;
            case "Doença de Chagas", "Chagas", "4":
                return Chagas;
            case "htlv", "5":
                return HTLV;
            default:
                return null;
        }
    }

    public static void listarOpcoes() {
        System.out.println("1 - Hepatite B");
        System.out.println("2 - Hepatite C");
        System.out.println("3 - Sifílis");
        System.out.println("4 - Doença de Chagas");
        System.out.println("5 - HTLV");
    }
}
