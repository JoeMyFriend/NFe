
package tests;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 */
public class A1Pfx {

    public static void main(String[] args) {
        try{
            Certificado certificado = certifidoA1Pfx();
            System.out.println("Alias Certificado :" +certificado.getNome());
            System.out.println("Dias Restantes Certificado :" +certificado.getDiasRestantes());
            System.out.println("Validade Certificado :" +certificado.getVencimento());

            //PARA REGISTRAR O CERTIFICADO NA SESSAO, FAÇA SOMENTE EM PROJETOS EXTERNO
            //JAVA NFE, CTE E OUTRAS APIS MINHAS JA CONTEM ESTA INICIALIZAÇÃO
            CertificadoService.inicializaCertificado(certificado, new FileInputStream(new File("C:\\Users\\User\\Documents\\NetBeans Projects\\Java_NFe-master\\src\\main\\resources\\Cacert")));

        }catch (CertificadoException | FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    public static Certificado certifidoA1Pfx() throws CertificadoException {
        String caminhoCertificado = "C:\\wamp\\www\\myerp\\nfephp\\certs\\bkp_cert\\certificado_a1 mindware.pfx";
        String senha = "753951";

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);
    }
}