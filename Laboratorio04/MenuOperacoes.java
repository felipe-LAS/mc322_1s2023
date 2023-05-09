package Laboratorio04;

public enum MenuOperacoes {
    // Menu Principal
    SAIR(0, "Sair"),
    CADASTROS(1, "Cadastros"),
    LISTAR(2, "Listar"),
    EXCLUIR(3, "Excluir"),
    GERAR_SINISTRO(4, "Gerar Sinistro"),
    TRANSFERIR_SEGURO(5, "Transferir Seguro"),
    CALCULAR_RECEITA_SEGURADORA(6, "Calcular Receita Seguradora"),

    // Cadastrar
    CADASTRAR_CLIENTE(11, "Cadastrar Cliente PF/PJ"),
    CADASTRAR_VEICULO(12, "Cadastrar Veiculo"),
    CADASTRAR_SEGURADORA(13, "Cadastrar Seguradora"),
    VOLTAR_CADASTRAR(14, "Voltar"),

    // Listar
    LISTAR_CLIENTE_SEGURO(21, "Listar Cliente (PF/PJ) por Seguro"),
    LISTAR_SINISTROS_SEGURADORA(22, "Listar Sinistros por Seguradora"),
    LISTAR_SINISTRO_CLIENTE(23, "Listar Sinistro por Cliente"),
    LISTAR_VEICULO_CLIENTE(24, "Listar Veiculo por Cliente"),
    LISTAR_VEICULO_SEGURADORA(25, "Listar Veiculo por Seguradora"),
    VOLTAR_LISTAR(26, "Voltar"),

    // Excluir
    EXCLUIR_CLIENTE(31, "Excluir Cliente"),
    EXCLUIR_VEICULO(32, "Excluir Veiculo"),
    EXCLUIR_SINISTRO(33, "Excluir Sinistro"),
    VOLTAR_EXCLUIR(34, "Voltar");

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