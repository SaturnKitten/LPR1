
package br.com.projeto_petshop.ctr;

import br.com.projeto_petshop.dao.ConexaoDAO;
import java.sql.ResultSet;
import br.com.projeto_petshop.dto.TutorDTO;
import br.com.projeto_petshop.dao.TutorDAO;
import br.com.projeto_petshop.dao.TutorDAO;

public class TutorCTR {
    
    TutorDAO tutorDAO = new TutorDAO();
    
    //Método construtor da classe
    public TutorCTR(){
        
    }
    
    //Método para inserir Tutor
    public String inserirTutor(TutorDTO tutorDTO){
        try{
            if(tutorDAO.inserirTutor(tutorDTO)){
                return "Tutor cadastrado com sucesso!";
            }
            else{
                return "Falha ao cadastrar Tutor!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao cadastrar Tutor!";
        }
    }//Fecha o método inserirTutor
    
    //Método para alterar Tutor
    public String alterarTutor(TutorDTO tutorDTO){
        try{
            if(tutorDAO.alterarTutor(tutorDTO)){
                return "Tutor alterado com sucesso!";
            }
            else{
                return "Falha ao alterar Tutor!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao alterar Tutor!";
        }
    }//Fecha o método alterarTutor
    
    //Método utilizado para controlar o acesso ao método excluirTutor da classe TutorDAO
    public String excluirTutor(TutorDTO tutorDTO){
        try{
            //Chama o método que está na classe DAO aguardando uma resposta (true or false)
            if(tutorDAO.excluirTutor(tutorDTO)){
                return "Tutor excluído com sucesso!";
            }
            else{
                return "Falha ao excluir Tutor!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao excluir Tutor!";
        }
    }//Fecha o método excluirTutor
    
    //Método utilizado para controlar o acesso ao método consultarTutor da classe TutorDAO
    public ResultSet consultarTutor(TutorDTO tutorDTO, int opcao){
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = tutorDAO.consultarTutor(tutorDTO, opcao);
        return rs;
    }//Fecha o método consultarTutor
    
    //Método utilizado para fechar o banco de dados
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
    
}
