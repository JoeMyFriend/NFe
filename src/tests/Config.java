/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import static tests.A1Pfx.certifidoA1Pfx;

/**
 *
 * @author User
 */
public class Config {
        public static ConfiguracoesIniciaisNfe iniciaConfiguracoes() throws NfeException, CertificadoException {
	Certificado certificado = certifidoA1Pfx();
		
	return ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.SP , ConstantesUtil.AMBIENTE.HOMOLOGACAO,
			certificado, "C:\\Users\\User\\Documents\\NetBeans Projects\\DependenciasDaNFeSamuelGit\\schemas");
    }
}
