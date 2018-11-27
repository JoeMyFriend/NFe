package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;

/**
 * @author Samuel Oliveira
 *
 */
public class ConsultaCadastroTeste {

    public static void main(String[] args) {
        
        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            Config.iniciaConfiguracoes();

            TRetConsCad retorno = Nfe.consultaCadastro(ConstantesUtil.TIPOS.CNPJ, "04615918000104", Estados.SP);

            if(retorno.getInfCons().getCStat().equals(StatusEnum.CADASTRO_ENCONTRADO.getCodigo())){
                System.out.println("Razão Social: "+retorno.getInfCons().getInfCad().get(0).getXNome());
                System.out.println("Cnpj:"+retorno.getInfCons().getInfCad().get(0).getCNPJ());
                System.out.println("Ie:"+retorno.getInfCons().getInfCad().get(0).getIE());
            }else{
                System.err.println(retorno.getInfCons().getCStat()+" - " +retorno.getInfCons().getXMotivo());
            }


        } catch (NfeException | CertificadoException e) {
            System.out.println(e.getMessage());
        }

    }

}