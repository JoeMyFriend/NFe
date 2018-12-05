package tests;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.Chave;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnderEmi;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEndereco;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

/**
 * @author Samuel Oliveira
 *
 */
public class EnvioNfeAssincronoTeste {

    public static void main(String[] args) {

    try {
            // Faça a Montagem da Nfe normalmente. Exemplo: https://github.com/Samuel-Oliveira/Java_NFe/wiki/4-:-Envio-Nfe-Sincrono
            
            Config.iniciaConfiguracoes();
            
            TNFe nfe = new TNFe();
            TNFe.InfNFe infNFe = new TNFe.InfNFe();
            
            //Variaveis que compõe a chave NFe e o XML
            String cUF = "35";
            String cnpj = "04615918000104";
            String mod = "55";
            String serie = "1";
            String nnf = "415";
            String tpEmis = "1";
            String cnf = "00010341";

            Chave chave = new Chave(cUF, cnpj, mod, serie, nnf, tpEmis, cnf);
            
            //Este Trecho pega o digito verificador da chave gerada para ser usada no XML
            char caractere = chave.getChNFe().charAt(chave.getChNFe().length() - 1);
            String CDV = Character.toString(caractere);
            
            System.out.println(chave.getChNFe());
            System.out.println(CDV);
            //System.exit(0);
            
            infNFe.setId(chave.getChNFe());
            infNFe.setVersao("4.00");

            // Dados Nfe
            TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
            ide.setCUF(cUF);
            ide.setCNF(cnf);
            ide.setNatOp("Revenda de Mercadorias");
            ide.setMod(mod);
            ide.setSerie(serie);
            ide.setNNF(nnf);
            ide.setDhEmi("2018-12-05T09:33:00-02:00");
            ide.setTpNF("1");
            ide.setIdDest("1");
            ide.setCMunFG("3550308");
            ide.setTpImp("1");
            ide.setTpEmis(tpEmis);
            ide.setCDV(CDV);
            ide.setTpAmb("2");
            ide.setFinNFe("1");
            ide.setIndFinal("1");
            ide.setIndPres("3");
            ide.setProcEmi("3");
            ide.setVerProc("4.00");
            infNFe.setIde(ide);

            //Emitente
            TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
            emit.setCNPJ(cnpj);
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
            emit.setIE("374053100113");
            emit.setCRT("1");
            infNFe.setEmit(emit);

            //Destinatario
            TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
            dest.setCNPJ("07133133000185");
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
            dest.setIE("374117970113");
            infNFe.setDest(dest);
            
            TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
            det.setNItem("1");
            
            //Produto
            TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
            prod.setCProd("B17025056");
            prod.setCEAN("SEM GTIN");
            prod.setXProd("PAPEL MAXPLOT- 170MX250MX56GRS 3");
            prod.setNCM("48025599");
            prod.setCEST("0000003");
            prod.setIndEscala("S");
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
            TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
            TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
            TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 icms102 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();
            icms102.setOrig("0");
            icms102.setCSOSN("102");
            icms.setICMSSN102(icms102);

            TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
            TNFe.InfNFe.Det.Imposto.PIS.PISAliq pisAliq = new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
            pisAliq.setCST("01");
            pisAliq.setVBC("0");
            pisAliq.setPPIS("0");
            pisAliq.setVPIS("0");
            pis.setPISAliq(pisAliq);

            TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
            TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
            cofinsAliq.setCST("01");
            cofinsAliq.setVBC("0");
            cofinsAliq.setPCOFINS("0");
            cofinsAliq.setVCOFINS("0");
            cofins.setCOFINSAliq(cofinsAliq);

            JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS> icmsElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS>(new QName("ICMS"), TNFe.InfNFe.Det.Imposto.ICMS.class, icms);
            imposto.getContent().add(icmsElement);

            JAXBElement<TNFe.InfNFe.Det.Imposto.PIS> pisElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.PIS>(new QName("PIS"), TNFe.InfNFe.Det.Imposto.PIS.class, pis);
            imposto.getContent().add(pisElement);

            JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS> cofinsElement = new JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS>(new QName("COFINS"), TNFe.InfNFe.Det.Imposto.COFINS.class, cofins);
            imposto.getContent().add(cofinsElement);

            det.setImposto(imposto);
            infNFe.getDet().add(det);

            // TOTAIS
            TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();

            TNFe.InfNFe.Total.ICMSTot icmstot = new TNFe.InfNFe.Total.ICMSTot();
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

            TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();
            transp.setModFrete("9");
            infNFe.setTransp(transp);

            TNFe.InfNFe.InfAdic infAdic = new TNFe.InfNFe.InfAdic();
            infAdic.setInfCpl("DOCUMENTO EMITIDO POR EMPRESA OPTANTE PELO SIMPLES NACIONAL;NAO GERA DIREITO A CREDITO FISCAL DE IPI\";\"PERMITE O APROVEITAMENTO DE CREDITO DE ICMS NO VALOR DE: R$17,66 CORRESPONDENTE A ALIQUOTA DE 2.56%\";Vendedor:1 - Guilherme Kavedikado;Valor Aproximado dos Tributos : R$ 206,97. Fonte IBPT (Instituto Brasileiro de Planejamento Tributario)");
            infNFe.setInfAdic(infAdic);

            TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();
            TNFe.InfNFe.Pag.DetPag detPag = new TNFe.InfNFe.Pag.DetPag();
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

            enviNFe = Nfe.montaNfe(enviNFe, true);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(enviNFe, ConstantesUtil.NFE);

            if (!retorno.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            String recibo = retorno.getInfRec().getNRec();

            TRetConsReciNFe retornoNfe;
            while (true) {
                retornoNfe = Nfe.consultaRecibo(recibo, ConstantesUtil.NFE);
                if (retornoNfe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
                    System.out.println("Lote Em Processamento, vai tentar novamente apos 2 Segundo.");
                    Thread.sleep(2000);
                    continue;
                } else {
                    break;
                }
            }

            if (!retornoNfe.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                throw new NfeException("Status:" + retornoNfe.getCStat() + " - " + retornoNfe.getXMotivo());
            }
            if (!retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                throw new NfeException("Status:" + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
            }

            System.out.println("Status: " + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
            System.out.println("Data: " + retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto());
            System.out.println("Protocolo: " + retornoNfe.getProtNFe().get(0).getInfProt().getNProt());

            System.out.println("XML Final: " + XmlUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0)));

        } catch (NfeException | JAXBException | CertificadoException | InterruptedException e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }
}