package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira
 */
public class ConsultaXmlTeste {

    public static void main(String[] args) {

        
        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            Config.iniciaConfiguracoes();

            String chave = "35180904756661000100550010000000011000166191";
            TRetConsSitNFe retorno = Nfe.consultaXml(chave, ConstantesUtil.NFE);
            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());

        } catch (CertificadoException | NfeException e) {
            System.err.println(e.getMessage());
        }


    }

}