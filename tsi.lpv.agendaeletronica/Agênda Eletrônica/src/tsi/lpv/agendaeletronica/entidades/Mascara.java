package tsi.lpv.agendaeletronica.entidades;

import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/** A classe <code>Mascara</code> manipula mascaras para os campos de texto
 * 
 * @author Gian Carlos Barros Honório
 *
 * @see MaskFormatter
 */
public class Mascara extends MaskFormatter {
	
	/**
	 * @serial
	 */
	private static final long serialVersionUID = -8640286502983414959L;
	
	/** Cria uma <code>Mascara</code> com o formato especificado, por exemplo ###-###
	 *  @param formatoMascara <code>String</code> com o formato da mascara
	 */
	public Mascara(String formatoMascara) { // Exemplo: ###-### ou ####-####
	       try{
	    	   this.setMask(formatoMascara); // Atribui a mascara
	    	   this.setPlaceholderCharacter(' '); // Caracter para preenchimento
	       }
	       catch (Exception e) {
	    	   e.printStackTrace();
	       }
	}

	/** Insere uma máscara em um texto com o formato especificado, por exemplo ###-###
	 * @param texto <code>String</code> com o texto que será formatado
	 * @param formatoMascara <code>String</code> com o formato da mascara
	 * @return <code>String</code> com o texto <code>String</code> passado como parâmetro formatado
	 */
    public static String formatarString(String texto, String formatoMascara) {  
        MaskFormatter mascara = null;
        String textoFormatado = null;
        
		try {
			mascara = new MaskFormatter(formatoMascara);
			mascara.setValueContainsLiteralCharacters(false);
			textoFormatado = mascara.valueToString(texto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return textoFormatado;
    }  
	
} // class Mascara
