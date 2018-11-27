package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira
 */
public class CancelarTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            ConfiguracoesIniciaisNfe config = Config.iniciaConfiguracoes();

            String chave = "35181104615918000104550010000004111000103311";
            String protocolo = "135180016094173";
            String cnpj = "04615918000104";
            String motivo = "Teste Cancelamento";

            String id = "ID" + ConstantesUtil.EVENTO.CANCELAR + chave + "01";

            TEnvEvento enviEvento = new TEnvEvento();
            enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            enviEvento.setIdLote("1");

            TEvento eventoCancela = new TEvento();
            eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento infoEvento = new TEvento.InfEvento();
            infoEvento.setId(id);
            infoEvento.setChNFe(chave);
            infoEvento.setCOrgao(String.valueOf(config.getEstado().getCodigoIbge()));
            infoEvento.setTpAmb(config.getAmbiente());
            infoEvento.setCNPJ(cnpj);

            infoEvento.setDhEvento(XmlUtil.dataNfe());
            infoEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            detEvento.setDescEvento("Cancelamento");
            detEvento.setNProt(protocolo);
            detEvento.setXJust(motivo);
            infoEvento.setDetEvento(detEvento);
            eventoCancela.setInfEvento(infoEvento);
            enviEvento.getEvento().add(eventoCancela);

            TRetEnvEvento retorno = Nfe.cancelarNfe(enviEvento, false, ConstantesUtil.NFE);

            if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retorno.getRetEvento().get(0).getInfEvento().getCStat())) {
                throw new NfeException("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            }

            System.out.println("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat());
            System.out.println("Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            System.out.println("Data:" + retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());

            //Cria ProCEvento
           String proc = XmlUtil.criaProcEventoCancelamento(config, enviEvento, retorno.getRetEvento().get(0));

           System.out.println(proc);

        } catch (CertificadoException | NfeException | JAXBException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}