
package br.com.projeto_petshop.ctr;

import java.sql.ResultSet;
import br.com.projeto_petshop.dto.AnimalDTO;
import br.com.projeto_petshop.dao.AnimalDAO;
import br.com.projeto_petshop.dto.TutorDTO;
import br.com.projeto_petshop.dao.ConexaoDAO;

public class AnimalCTR {
    
    AnimalDAO animalDAO = new AnimalDAO();
    
    //Método construtor da classe
    public AnimalCTR(){
        
    }
    
    //Método para inserir Animal
    public String inserirAnimal(AnimalDTO animalDTO, TutorDTO tutorDTO){
        try{
            if(animalDAO.inserirAnimal(animalDTO, tutorDTO)){
                return "Animal cadastrado com sucesso!";
            }
            else{
                return "Falha ao cadastrar Animal!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao cadastrar Animal!";
        }
    }//Fecha o método inserirAnimal
    
    //Método para alterar Animal
    public String alterarAnimal(AnimalDTO animalDTO, TutorDTO tutorDTO){
        try{
            if(animalDAO.alterarAnimal(animalDTO, tutorDTO)){
                return "Animal alterado com sucesso!";
            }
            else{
                return "Falha ao alterar Animal!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao alterar Animal!";
        }
    }//Fecha o método alterarAnimal
    
    //Método utilizado para controlar o acesso ao método excluirAnimal da classe TutorDAO
    public String excluirAnimal(AnimalDTO animalDTO){
        try{
            //Chama o método que está na classe DAO aguardando uma resposta (true or false)
            if(animalDAO.excluirAnimal(animalDTO)){
                return "Animal excluído com sucesso!";
            }
            else{
                return "Falha ao excluir Animal!";
            }
        }//Caso tenha algum erro no código acima, uma mensagem é enviada com o que está acontecendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Falha ao excluir Animal!";
        }
    }//Fecha o método excluirAnimal
    
    //Método utilizado para controlar o acesso ao método consultarAnimal da classe TutorDAO
    public ResultSet consultarAnimal(AnimalDTO animalDTO, int opcao){
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = animalDAO.consultarAnimal(animalDTO, opcao);
        return rs;
    }//Fecha o método consultarAnimal
    
    //Método utilizado para fechar o banco de dados
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
}
