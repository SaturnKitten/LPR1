
package br.com.projeto_petshop.ctr;

import java.sql.ResultSet;
import br.com.projeto_petshop.dto.FuncionarioDTO;
import br.com.projeto_petshop.dao.FuncionarioDAO;
import br.com.projeto_petshop.dao.ConexaoDAO;

public class FuncionarioCTR {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); 
    
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO){
            try {
                if(funcionarioDAO.inserirFuncionario(funcionarioDTO)){
                    return "Funcionario cadastrado com sucesso!";
                } else {
                    return "Funcionario não cadastrado!";
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                return "Funcionario não cadastrado";
            }
        }//Fecha método inserirFuncionario()
    
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            if(funcionarioDAO.alterarFuncionario(funcionarioDTO)){
                return "Funcionario alterado com sucesso";
            }
            else {
                return "Funcionario não alterado";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario não alterado";
        }
    }
    
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            if(funcionarioDAO.excluirFuncionario(funcionarioDTO)){
                return "Funcionario excluído com sucesso";
            }
            else{
                return "Funcionario não excluído";
            }
        }
        catch (Exception e){
                System.out.println(e.getMessage());
                return "Funcionario não excluído";
        }
    }
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao){
        ResultSet rs = null;
        
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        
        return rs;
    }
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO){
        
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}//Fecha classe FuncionarioCTR
