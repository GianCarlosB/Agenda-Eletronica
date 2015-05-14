package tsi.lpv.agendaeletronica.eventos.contato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Telefone;
import tsi.lpv.agendaeletronica.entidades.contato.TipoTel;
import tsi.lpv.agendaeletronica.gui.contato.IgCadastrarTel;

public class TEActionIgCadTel implements ActionListener {
	
	private final String MSG_ERRO_TEL = " O campo telefone não pode ficar vazio!";
	
	private IgCadastrarTel ig;

	public TEActionIgCadTel(IgCadastrarTel igCadastrarTel) {
		super();
		this.ig = igCadastrarTel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getBtnInserir()) {
			if(ValidarDados.validarVazio(ig.getTelFormattedTextField().getText().replace("(", "").replace(")", "").replace("-", "").trim())) {
				String tel = ig.getTelFormattedTextField().getText();
				int index = ig.getNomesComboBox().getSelectedIndex() - 1;
				
				ig.esconderMsgCamposErrados();
				
				// Verifica se foi possível inserir o telefone no banco de dados.
				if(new Telefone(tel, TipoTel.obterTipo(ig.obterRadioBtnSelecionado()), ig.getArrayListPessoas().get(index)
						.getCodigoPessoa()).inserir())
					JOptionPane.showMessageDialog(ig, "Inserção do número de telefone: " + tel + " , realizada com sucesso!",
							ig.NOME_MODULO, 1);
				else
					JOptionPane.showMessageDialog(ig, "Falha na inserção do número de telefone: " + tel + " !",
							ig.NOME_MODULO, 0);
				
				// Libera a janela.
				ig.dispose();
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_TEL, ig.getLblTelefone());
		}
	}

} // class TEActionIgCadTel
