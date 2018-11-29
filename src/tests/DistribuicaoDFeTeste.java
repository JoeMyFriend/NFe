package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;

import java.io.IOException;
import java.util.List;

/**
 * @author Samuel Oliveira
 *
 */
public class DistribuicaoDFeTeste {

    public static void main(String[] args) {

        try {
            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            Config.iniciaConfiguracoes();

            //RetDistDFeInt retorno = consultaNsu();
			RetDistDFeInt retorno = consultaChave();

            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("NSU:" + retorno.getUltNSU());
            System.out.println("Max NSU:" + retorno.getMaxNSU());

            if(StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())){

                List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();

                System.out.println("Encontrado " + listaDoc.size() + " Notas.");
                for (DocZip docZip : listaDoc) {
                    System.out.println("Schema: " + docZip.getSchema());
                    System.out.println("NSU:" + docZip.getNSU());
                    System.out.println("XML: " + XmlUtil.gZipToXml(docZip.getValue()));
                }
            }

        } catch (NfeException | IOException | CertificadoException e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }

    public static RetDistDFeInt consultaNsu() throws NfeException {

        String cnpj = "XXX";
        String nsu = "000000000000000";

        return Nfe.distribuicaoDfe(ConstantesUtil.TIPOS.CNPJ, cnpj , ConstantesUtil.TIPOS.NSU , nsu);

    }
    public static RetDistDFeInt consultaChave() throws NfeException {

        String cnpj = "07133133000185";
        String chave = "35181104615918000104550010000004131000103316";

        return Nfe.distribuicaoDfe(ConstantesUtil.TIPOS.CNPJ, cnpj , ConstantesUtil.TIPOS.CHAVE , chave);

    }

}