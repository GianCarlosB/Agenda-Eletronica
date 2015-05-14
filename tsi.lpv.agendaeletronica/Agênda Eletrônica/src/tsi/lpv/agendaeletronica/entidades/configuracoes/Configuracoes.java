package tsi.lpv.agendaeletronica.entidades.configuracoes;

import tsi.lpv.agendaeletronica.persistencia.ConfiguracoesDAO;


public class Configuracoes extends ConfiguracoesDAO {

	private int lookAndFeel;
	private int idConfiguracao;
	private boolean perspectivaPadrao;
	private int papelDeParede;
	
	public static final int LOOK_AND_FEEL_PADRAO = 1;
	public static final int ID_CONF_PADRAO = 1;
	public static final boolean PERSPECTIVA_PADRAO = true;
	public static final int PAPEL_DE_PAREDE_PADRAO = 4;
	
	public Configuracoes() {
		super();
		this.idConfiguracao = ID_CONF_PADRAO;
	}

	public Configuracoes(int lookAndFeel,
			boolean perspectivaPadrao, int papelDeParede) {
		super();
		this.lookAndFeel = lookAndFeel;
		this.idConfiguracao = ID_CONF_PADRAO;
		this.perspectivaPadrao = perspectivaPadrao;
		this.papelDeParede = papelDeParede;
	}

	public int getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(int lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

	public int getIdConfiguracao() {
		return idConfiguracao;
	}

	public boolean isPerspectivaPadrao() {
		return perspectivaPadrao;
	}

	public void setPerspectivaPadrao(boolean perspectivaPadrao) {
		this.perspectivaPadrao = perspectivaPadrao;
	}

	public int getPapelDeParede() {
		return papelDeParede;
	}

	public void setPapelDeParede(int papelDeParede) {
		this.papelDeParede = papelDeParede;
	}
	
	public boolean alterar() {
		return super.alterar(this);
	}
	
	public Configuracoes consultar() {
		return super.consultar();
	}
	
} // class Configuracoes
