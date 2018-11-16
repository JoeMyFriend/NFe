package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

/**
 * @author Samuel Oliveira
 */
public class EnvioNfeSincronoTesteMontaXml {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            Config.iniciaConfiguracoes();

            TNFe nfe = new TNFe();
            TNFe.InfNFe infNFe = new InfNFe();

            infNFe.setId("NFe35181104615918000104550010000004111000103346");
            infNFe.setVersao("4.00");

            // Dados Nfe
            Ide ide = new Ide();
            ide.setCUF("35");
            ide.setCNF("09923465");
            ide.setNatOp("Revenda de Mercadorias");
            ide.setMod("55");
            ide.setSerie("1");
            ide.setNNF("1");
            ide.setDhEmi("2018-11-16T09:40:00-03:00");
            ide.setTpNF("1");
            ide.setIdDest("1");
            ide.setCMunFG("3550308");
            ide.setTpImp("1");
            ide.setTpEmis("1");
            ide.setCDV("1");
            ide.setTpAmb("2");
            ide.setFinNFe("1");
            ide.setIndFinal("1");
            ide.setIndPres("3");
            ide.setProcEmi("3");
            ide.setVerProc("4.00");
            infNFe.setIde(ide);

            //Emitente
            Emit emit = new Emit();
            emit.setCNPJ("00822602000124");
            emit.setXNome("Plotag Sistemas e Suprimentos Ltda");
            emit.setXFant("Plotag Sistemas e Suprimentos Ltda");
            TEnderEmi enderEmit = new TEnderEmi();
            enderEmit.setXLgr("Rua Maria Luiza");
            enderEmit.setNro("321");
            enderEmit.setXCpl("4");
            enderEmit.setXBairro("Vila Pereira");
            enderEmit.setCMun("3550308");
            enderEmit.setXMun("São Paulo");
            enderEmit.setUF(TUfEmi.valueOf("SP"));
            enderEmit.setCEP("01127010");
            enderEmit.setCPais("1058");
            enderEmit.setXPais("BRASIL");
            enderEmit.setFone("1123587604");
            emit.setEnderEmit(enderEmit);
            emit.setIE("114489114119");
            emit.setCRT("1");
            infNFe.setEmit(emit);

            //Destinatario
            Dest dest = new Dest();
            dest.setCNPJ("99999999000191");
            dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
            TEndereco enderDest = new TEndereco();
            enderDest.setXLgr("Rua Jaragua");
            enderDest.setNro("774");
            enderDest.setXBairro("Bom Retiro");
            enderDest.setCMun("3550308");
            enderDest.setXMun("Sao Paulo");
            enderDest.setUF(TUf.valueOf("SP"));
            enderDest.setCEP("01129000");
            enderDest.setCPais("1058");
            enderDest.setXPais("BRASIL");
            enderDest.setFone("33933501");
            dest.setEnderDest(enderDest);
            dest.setEmail("gui_calabria@yahoo.com.br");
            dest.setIndIEDest("1");
            infNFe.setDest(dest);
            
            Det det = new Det();
            det.setNItem("1");
            
            //Produto
            Prod prod = new Prod();
            prod.setCProd("B17025056");
            prod.setCEAN("SEM GTIN");
            prod.setXProd("PAPEL MAXPLOT- 170MX250MX56GRS 3");
            prod.setNCM("48025599");
            prod.setCEST("0000003");
            prod.setIndEscala("N");
            prod.setCFOP("5101");
            prod.setUCom("Rl");
            prod.setQCom("1.0000");
            prod.setVUnCom("138.3000");
            prod.setVProd("138.30");
            prod.setCEANTrib("SEM GTIN");
            prod.setUTrib("RL");
            prod.setQTrib("1.0000");
            prod.setVUnTrib("138.3000");
            prod.setIndTot("1");
            det.setProd(prod);

            //Impostos
            Imposto imposto = new Imposto();
            ICMS icms = new ICMS();
            ICMS60 icms60 = new ICMS60();
            icms60.setOrig("0");
            icms60.setCST("60");
            icms60.setVBCSTRet("0");
            icms60.setPST("0.00");
            icms60.setVICMSSTRet("0");
            icms.setICMS60(icms60);

            PIS pis = new PIS();
            PISAliq pisAliq = new PISAliq();
            pisAliq.setCST("01");
            pisAliq.setVBC("0");
            pisAliq.setPPIS("0");
            pisAliq.setVPIS("0");
            pis.setPISAliq(pisAliq);

            COFINS cofins = new COFINS();
            COFINSAliq cofinsAliq = new COFINSAliq();
            cofinsAliq.setCST("01");
            cofinsAliq.setVBC("0");
            cofinsAliq.setPCOFINS("0");
            cofinsAliq.setVCOFINS("0");
            cofins.setCOFINSAliq(cofinsAliq);

            JAXBElement<ICMS> icmsElement = new JAXBElement<ICMS>(new QName("ICMS"), ICMS.class, icms);
            imposto.getContent().add(icmsElement);

            JAXBElement<PIS> pisElement = new JAXBElement<PIS>(new QName("PIS"), PIS.class, pis);
            imposto.getContent().add(pisElement);

            JAXBElement<COFINS> cofinsElement = new JAXBElement<COFINS>(new QName("COFINS"), COFINS.class, cofins);
            imposto.getContent().add(cofinsElement);

            det.setImposto(imposto);
            infNFe.getDet().add(det);

            // TOTAIS
            Total total = new Total();

            ICMSTot icmstot = new ICMSTot();
            icmstot.setVBC("0.00");
            icmstot.setVICMS("0.00");
            icmstot.setVICMSDeson("0.00");
            icmstot.setVFCP("0.00");
            icmstot.setVFCPST("0.00");
            icmstot.setVFCPSTRet("0.00");
            icmstot.setVBCST("0.00");
            icmstot.setVST("0.00");
            icmstot.setVProd("138.30");
            icmstot.setVFrete("0.00");
            icmstot.setVSeg("0.00");
            icmstot.setVDesc("0.00");
            icmstot.setVII("0.00");
            icmstot.setVIPI("0.00");
            icmstot.setVIPIDevol("0.00");
            icmstot.setVPIS("0.00");
            icmstot.setVCOFINS("0.00");
            icmstot.setVOutro("0.00");
            icmstot.setVNF("138.30");
            total.setICMSTot(icmstot);
            infNFe.setTotal(total);

            Transp transp = new Transp();
            transp.setModFrete("9");
            infNFe.setTransp(transp);

            InfAdic infAdic = new InfAdic();
            infAdic.setInfCpl("DOCUMENTO EMITIDO POR EMPRESA OPTANTE PELO SIMPLES NACIONAL;NAO GERA DIREITO A CREDITO FISCAL DE IPI\";\"PERMITE O APROVEITAMENTO DE CREDITO DE ICMS NO VALOR DE: R$17,66 CORRESPONDENTE A ALIQUOTA DE 2.56%\";Vendedor:1 - Guilherme Kavedikado;Valor Aproximado dos Tributos : R$ 206,97. Fonte IBPT (Instituto Brasileiro de Planejamento Tributario)");
            infNFe.setInfAdic(infAdic);

            Pag pag = new Pag();
            Pag.DetPag detPag = new Pag.DetPag();
            detPag.setTPag("99");
            detPag.setVPag("0.00");
            pag.getDetPag().add(detPag);

            infNFe.setPag(pag);

            nfe.setInfNFe(infNFe);

            // Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao("4.00");
            enviNFe.setIdLote("1");
            enviNFe.setIndSinc("1");
            enviNFe.getNFe().add(nfe);

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

            System.out.println("Xml Final :" + XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));

        } catch (NfeException | JAXBException | CertificadoException e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }
}
