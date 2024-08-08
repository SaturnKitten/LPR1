
package br.com.projeto_petshop.dao;

import java.sql.*;
import br.com.projeto_petshop.dto.AnimalDTO;
import br.com.projeto_petshop.dto.TutorDTO;

public class AnimalDAO {
    //Método construtor da classe
    public AnimalDAO(){
        
    }
    
    //Atributo do tipo Result utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    //Método utilizado para inserir um objeto animalDTO no banco de dados
    public boolean inserirAnimal(AnimalDTO animalDTO, TutorDTO tutorDTO){
        try{
            //Chama o método ques está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement que será responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "INSERT INTO ANIMAL (nome_animal, especie_animal, sexo_animal, raca_animal, porte_animal, cor_animal, pelagem_animal, id_tutor) VALUES ( "
                    + "'" + animalDTO.getNome_animal() + "', "
                    + "'" + animalDTO.getEspecie_animal() + "', "
                    + "'" + animalDTO.getSexo_animal() + "', "
                    + "'" + animalDTO.getRaca_animal() + "', "
                    + "'" + animalDTO.getPorte_animal() + "', "
                    + "'" + animalDTO.getCor_animal() + "', "
                    + "'" + animalDTO.getPelagem_animal() + "', "
                    + tutorDTO.getId_tutor() + ") ";
            
            //Executa ocomando SQL no banco de dados
            stmt.execute(comando.toUpperCase());
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código, é enviado uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro ou não, ele vai fechar o banco de dados
        finally{
            //Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();            
        }
    }//Fecha o método inserirAnimal
    
    //Método utilizado para alterar um objeto animalDTO no banco de dados
    public boolean alterarAnimal(AnimalDTO animalDTO, TutorDTO tutorDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "UPDATE ANIMAL SET "
                    + "nome_animal = '" + animalDTO.getNome_animal() + "', "
                    + "especie_animal = '" + animalDTO.getEspecie_animal() + "', "
                    + "sexo_animal = '" + animalDTO.getSexo_animal() + "', "
                    + "raca_animal = '" + animalDTO.getRaca_animal() + "', "
                    + "porte_animal = '" + animalDTO.getPorte_animal() + "', "
                    + "cor_animal = '" + animalDTO.getCor_animal() + "', "
                    + "pelagem_animal = '" + animalDTO.getPelagem_animal() + "', "
                    + "id_tutor = " + tutorDTO.getId_tutor() + " "
                    + "WHERE id_animal = " + animalDTO.getId_animal();
            
            //Executa ocomando SQL no banco de dados
            stmt.execute(comando.toUpperCase());
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código, é enviado uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro ou não, ele vai fechar o banco de dados
        finally{
            //Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();            
        }
    }//Fecha o método alterarAnimal
    
    //Método utilizado para excluir um objeto animalDTO no banco de dados
    public boolean excluirAnimal(AnimalDTO animalDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "DELETE FROM ANIMAL WHERE id_animal = "
                    + animalDTO.getId_animal();
            
            //Executa ocomando SQL no banco de dados
            stmt.execute(comando.toUpperCase());
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código, é enviado uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro ou não, ele vai fechar o banco de dados
        finally{
            //Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();            
        }
    }//Fecha o método excluirAnimal
    
    //Método utilizado para consultar um objeto animalDTO no banco de dados
    public ResultSet consultarAnimal(AnimalDTO animalDTO, int opcao){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "SELECT a.* " +
                            "FROM animal a " +
                            "WHERE a.nome_animal ilike '" + animalDTO.getNome_animal() + "%' " +
                            "ORDER BY a.nome_animal";
                break;
                case 2:
                    comando = "SELECT a.*, t.nome_tutor, t.id_tutor " +
                            "FROM animal a, tutor t " +
                            "WHERE a.id_tutor = t.id_tutor AND " +
                            "a.id_animal = " + animalDTO.getId_animal();
                break;
            }
            //Executa ocomando SQL no banco de dados
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }//Caso tenha algum erro no código, é enviado uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }//Fecha o método consultarAnimal
}
