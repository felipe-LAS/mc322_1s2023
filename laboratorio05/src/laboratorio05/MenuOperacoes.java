package laboratorio05;

public enum MenuOperacoes {
    // Menu Principal
    SAIR(0, "Sair"),
    CADASTROS(1, "Cadastros"),
    LISTAR(2, "Listar"),
    EXCLUIR(3, "Excluir"),
    GERAR_SINISTRO(4, "Gerar Sinistro"),

    // Cadastrar
    CADASTRAR_CLIENTE(11, "Cadastrar Cliente PF/PJ"),
    CADASTRAR_VEICULO(12, "Cadastrar Veiculo"),
    CADASTRAR_SEGURADORA(13, "Cadastrar Seguradora"),
    CADASTRAR_SEGURO_PF(14, "Cadastrar SeguroPF"),
    CADASTRAR_SEGURO_PJ(15, "Cadastrar SeguroPJ"),
    CADASTRAR_FROTA(16, "Cadastrar Frota"),
    CADASTRAR_CONDUTOR(17, "Cadastrar Condutor"),
    VOLTAR_CADASTRAR(18, "Voltar"),

    // Listar
    LISTAR_CLIENTE_PF(21, "Listar Clientes PF"),
    LISTAR_CLIENTE_PJ(22, "Listar Clientes PJ"),
    LISTAR_VEICULO_CLIENTE_PF(23, "Listar Veículos dos Clientes PF"),
    LISTAR_FROTAS_CLIENTE_PJ(24, "Listar Frotas dos Clientes PJ"),
    LISTAR_VEICULO_SEGURADORA(25, "Listar Veículos por Seguradora"),
    LISTAR_SEGUROS_PF(26, "Listar SegurosPF"),
    LISTAR_SEGUROS_PJ(27, "Listar SegurosPJ"),
    LISTAR_CONDUTORES(28, "Listar Condutores"),
    VOLTAR_LISTAR(29, "Voltar"),

 // Excluir
    EXCLUIR_CLIENTE(31, "Excluir Cliente"),
    EXCLUIR_VEICULO(32, "Excluir Veiculo"),
    EXCLUIR_SINISTRO(33, "Excluir Sinistro"),
    EXCLUIR_SEGURO_PF(34, "Excluir SeguroPF"),
    EXCLUIR_SEGURO_PJ(35, "Excluir SeguroPJ"),
    EXCLUIR_FROTA(36, "Excluir Frota"),
    EXCLUIR_CONDUTOR(37, "Excluir Condutor"),
    VOLTAR_EXCLUIR(38, "Voltar");

    private final int codigo;
    private final String descricao;

    MenuOperacoes(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static MenuOperacoes fromCodigo(int codigo) {
        for (MenuOperacoes menuOperacao : MenuOperacoes.values()) {
            if (menuOperacao.getCodigo() == codigo) {
                return menuOperacao;
            }
        }

        return null;
    }
}
