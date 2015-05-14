package tsi.lpv.agendaeletronica.entidades.contato;

public enum TipoTel {
	
	MOVEL('M', "Móvel"),
	FIXO('F', "Fixo");
	
	private char tipo;
	private String descricao;

	private TipoTel(char tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public char getTipo() {
		return tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public static TipoTel obterTipo(char tipo) {
		if(tipo == MOVEL.getTipo())
			return MOVEL;
		if(tipo == FIXO.getTipo())
			return FIXO;
		
		return null;
	}
	
	public static TipoTel obterTipo(String descricao) {
		if(descricao.equalsIgnoreCase(MOVEL.getDescricao()))
			return MOVEL;
		if(descricao.equalsIgnoreCase(FIXO.getDescricao()))
			return FIXO;
		
		return null;
	}

} // enum TipoTel
