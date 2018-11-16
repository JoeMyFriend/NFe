package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira
 */
public class EnvioNfeSincronoTeste {

    public static void main(String[] args) {

        String caminhoXML = "C:\\Users\\User\\Downloads\\testesTXT_XML\\xml\\TestesJava\\35181104615918000104550010000004111000103346-procNfe.xml";

        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            Config.iniciaConfiguracoes();

            // Monta EnviNfe
            String xml = XmlUtil.leXml(caminhoXML);
            TEnviNFe enviNFe = XmlUtil.xmlToObject(xml, TEnviNFe.class);

            // Monta e Assina o XML
            enviNFe = Nfe.montaNfe(enviNFe, true);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(enviNFe, ConstantesUtil.NFE);

            if (!retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            if (!retorno.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getProtNFe().getInfProt().getCStat() + " - Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
            }

            System.out.println("Status:" + retorno.getProtNFe().getInfProt().getCStat());
            System.out.println("Motivo:" + retorno.getProtNFe().getInfProt().getXMotivo());
            System.out.println("Data:" + retorno.getProtNFe().getInfProt().getDhRecbto());
            System.out.println("Protocolo:" + retorno.getProtNFe().getInfProt().getNProt());

            System.out.println(XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));

        } catch (NfeException | JAXBException | CertificadoException e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }

}