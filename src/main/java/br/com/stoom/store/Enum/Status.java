package br.com.stoom.store.Enum;

public enum Status {
    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível"),
    ESGOTADO("Esgotado"),
    EM_TRANSITO("Em Trânsito"),
    CANCELADO("Cancelado"),
    RETORNADO("Retornado"),
    EM_PROCESSAMENTO("Em Processamento");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

