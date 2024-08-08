
package br.com.projeto_petshop.ctr;

import java.sql.ResultSet;
import br.com.projeto_petshop.dto.ServicoDTO;
import br.com.projeto_petshop.dao.ServicoDAO;
import br.com.projeto_petshop.dto.FuncionarioDTO;
import br.com.projeto_petshop.dao.ConexaoDAO;

public class ServicoCTR {
    ServicoDAO servicoDAO = new ServicoDAO();
    
    //Método construtor da classe
    public ServicoCTR(){
        
    }
    
    //Método para inserir Serviço
    public String inserirServico(ServicoDTO servicoDTO, FuncionarioDTO funcionarioDTO){
        try{
            if(servicoDAO.inserirServico(servicoDTO, funcionarioDTO)){
                return "Serviço cadastrado com sucesso!";
            }
            else{
                return "Falha ao cadastrar Serviço!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao cadastrar Serviço!";
        }
    }//Fecha o método inserirServiço
    
    //Método para alterar Servico
    public String alterarServico(ServicoDTO servicoDTO, FuncionarioDTO funcionarioDTO){
        try{
            if(servicoDAO.alterarServico(servicoDTO, funcionarioDTO)){
                return "Serviço alterado com sucesso!";
            }
            else{
                return "Falha ao alterar Serviço!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao alterar Serviço!";
        }
    }//Fecha o método alterarAnimal
    
    //Método utilizado para controlar o acesso ao método excluirServico da classe FuncionarioDAO
    public String excluirServico(ServicoDTO servicoDTO){
        try{
            //Chama o método que está na classe DAO aguardando uma resposta (true or false)
            if(servicoDAO.excluirServico(servicoDTO)){
                return "Serviço excluído com sucesso!";
            }
            else{
                return "Falha ao excluir Serviço!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao excluir Serviço!";
        }
    }//Fecha o método excluirServico
    
    //Método utilizado para controlar o acesso ao método consultarServico da classe FuncionarioDAO
    public ResultSet consultarServico(ServicoDTO servicoDTO, int opcao){
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = servicoDAO.consultarServico(servicoDTO, opcao);
        return rs;
    }//Fecha o método consultarServico
    
    //Método utilizado para fechar o banco de dados
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
}
