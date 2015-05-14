package tsi.lpv.agendaeletronica.eventos.tarefa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgCadastrarTarefa;

public class TEActionIgCadTarefa implements ActionListener {
	
	private final String MSG_ERRO_DESCRICAO = " O campo descrição não pode ficar vazio!";
	private final String MSG_ERRO_DATA = " A data da tarefa não foi selecionada!";
	private final String MSG_ERRO_HORA = " O campo hora deve receber hora(s) e minuto(s) válido(s)!";
	
	private IgCadastrarTarefa ig;

	public TEActionIgCadTarefa(IgCadastrarTarefa igCadastrarTarefa) {
		super();
		this.ig = igCadastrarTarefa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getCadastrarButton()) {
			if(ValidarDados.validarVazio(ig.getDescricaoTextField().getText())) {
				if(ig.getValorDatePicker() != null) {
					if(ValidarDados.validarHora(ig.getHoraFormattedTextField().getText().replace(":", "").trim())) {
						int itensSelecionados[] = ig.getPessoasList().getSelectedIndices();
						String compromissosInseridos = "";
						
						String descricao = ig.getDescricaoTextField().getText();
						Date data = ig.getValorDatePicker();
						Calendar cal = Calendar.getInstance();
						ig.esconderMsgCamposErrados();
						
						cal.setTime(data);
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ig.getHoraFormattedTextField().getText().replace(":", "").substring(0, 2)));
						cal.set(Calendar.MINUTE, Integer.parseInt(ig.getHoraFormattedTextField().getText().replace(":", "").substring(2, 4)));
						cal.set(Calendar.SECOND, 00);
						cal.set(Calendar.MILLISECOND, 00);
						
						Tarefa tarefa = new Tarefa(descricao, cal.getTime());
						int codigoTarefa = tarefa.proximoValorSequencia();
						tarefa.setCodigoTarefa(codigoTarefa);
						
						// Verifica se foi possível inserir a tarefa no banco de dados.
						if(tarefa.inserir()) {
							for(int i = 0; i < itensSelecionados.length; i++) {
								if(new Compromisso(ig.getArrayListPessoas().get(itensSelecionados[i]).getCodigoPessoa(), codigoTarefa).inserir())
									compromissosInseridos += "\n---- " + ig.getArrayListPessoas().get(itensSelecionados[i]).getNome();
								else
									JOptionPane.showMessageDialog(ig, "Falha no cadastro do compromisso da pessoa de nome '"
											+ ig.getArrayListPessoas().get(itensSelecionados[i]).getNome() + "' !", ig.NOME_MODULO, 0);
							}
							
							if(!compromissosInseridos.equals(""))
								JOptionPane.showMessageDialog(ig, "Cadastro da tarefa de descrição '" + descricao + "' realizado com sucesso!"
										+ "\nPessoas que realizarão a tarefa:" + compromissosInseridos, ig.NOME_MODULO, 1);
							else
								JOptionPane.showMessageDialog(ig, "Cadastro da tarefa de descrição '" + descricao + "' realizado com sucesso!",
										ig.NOME_MODULO, 1);
						}
						else
							JOptionPane.showMessageDialog(ig, "Falha no cadastro da tarefa de descrição '" + descricao + "' !", ig.NOME_MODULO, 0);
						
						// Libera a janela.
						ig.dispose();
					}
					else ig.exibirMsgCamposErrados(MSG_ERRO_HORA, ig.getLblHora());	
				}
				else ig.exibirMsgCamposErrados(MSG_ERRO_DATA, ig.getLblData());
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_DESCRICAO, ig.getLblDescricao());
		}
	}

} // class TEActionIgCadTarefa
