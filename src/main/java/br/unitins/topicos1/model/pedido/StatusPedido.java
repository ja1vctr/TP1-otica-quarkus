package br.unitins.topicos1.model.pedido;

public enum StatusPedido { 
    PENDENTE(1, "Pendente"),
    PAGO(2, "Pago"),
    CANCELADO(3, "Cancelado");

    private int id;
    private String descricao;

    StatusPedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        for (StatusPedido status : StatusPedido.values()) {
            if (id == status.id)
                return status;
        }
        throw new IllegalArgumentException("id status invalido");
    }
    
}
