
package br.com.projeto_petshop.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import br.com.projeto_petshop.dto.TutorDTO;

public class TutorDAO {
    public TutorDAO(){
    
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    //Método utilizado para inserir um objeto tutorDTO no banco de dados
    public boolean inserirTutor(TutorDTO tutorDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement que será responsável
            stmt = ConexaoDAO.con.createStatement();
            //Comanda SQL que será executado no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "INSERT INTO TUTOR (nome_tutor, cpf_tutor, rg_tutor, datanasc_tutor, genero_tutor, tel_tutor, rua_tutor, num_tutor, cep_tutor, bairro_tutor, cidade_tutor, estado_tutor) VALUES ("
                    + "'" + tutorDTO.getNome_tutor() + "', "
                    + "'" + tutorDTO.getCpf_tutor() + "', "
                    + "'" + tutorDTO.getRg_tutor() + "', "
                    + "to_date('" + data_format.format(tutorDTO.getDatanasc_tutor()) + "', 'dd/mm/yyyy'), "
                    + "'" + tutorDTO.getGenero_tutor() + "', "
                    + "'" + tutorDTO.getTel_tutor() + "', "
                    + "'" + tutorDTO.getRua_tutor() + "', "
                    + "'" + tutorDTO.getNum_tutor() + "', "
                    + "'" + tutorDTO.getCep_tutor() + "', "
                    + "'" + tutorDTO.getBairro_tutor() + "', "
                    + "'" + tutorDTO.getCidade_tutor() + "', "
                    + "'" + tutorDTO.getEstado_tutor() + "')";
            
            //Executa o comando SQL no banco dados
            stmt.execute(comando.toUpperCase());
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código acima, é enviado uma mensagem com o que está havendo.
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro ou não, o banco de dados será fechado
        finally{    
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método inserirTutor
    
    //Método utilizado para alterar um objeto tutorDTO no banco de dados
    public boolean alterarTutor(TutorDTO tutorDTO){
        try{
            
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            
            comando = "UPDATE TUTOR SET "
                    + "nome_tutor = '" + tutorDTO.getNome_tutor() + "', "
                    + "cpf_tutor = '" + tutorDTO.getCpf_tutor() + "', "
                    + "rg_tutor = '" + tutorDTO.getRg_tutor() + "', "
                    + "datanasc_tutor = to_date('" + data_format.format(tutorDTO.getDatanasc_tutor()) + "',' dd/mm/yyyy'), "
                    + "genero_tutor = '" + tutorDTO.getGenero_tutor() + "', "
                    + "tel_tutor = '" + tutorDTO.getTel_tutor() + "', "
                    + "rua_tutor = '" + tutorDTO.getRua_tutor() + "', "
                    + "num_tutor = '" + tutorDTO.getNum_tutor() + "', "
                    + "cep_tutor = '" + tutorDTO.getCep_tutor() + "', "
                    + "bairro_tutor = '" + tutorDTO.getBairro_tutor() + "', "
                    + "cidade_tutor = '" + tutorDTO.getCidade_tutor() + "', "
                    + "estado_tutor = '" + tutorDTO.getEstado_tutor() + "' "
                    + "WHERE id_tutor = " + tutorDTO.getId_tutor();
            
            //Executa o comando SQL no banco de dados
            stmt.execute(comando.toUpperCase());
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código, é enviada uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro, ou não, ele vai fechar o banco de dados
        finally{
            //Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método alterarTutor
    
    //Método utilizado para exclui um objetoDTO no banco de dados
    public boolean excluirTutor(TutorDTO tutorDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "DELETE FROM TUTOR WHERE id_tutor = "
                    + tutorDTO.getId_tutor();
            
            //Executa o comando SQL no banco de daos
            stmt.execute(comando);
            //Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        }//Caso tenha algum erro no código, é enviada uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro, ou não, ele vai fechar o banco de dados
        finally{
            //Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método excluirTutor
    
    //Método utilizado para consultar um objeto tutorDTO no banco de dados
    public ResultSet consultarTutor(TutorDTO tutorDTO, int opcao){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            switch(opcao){
                case 1:
                    comando = "SELECT t.* " +
                            "FROM tutor t " +
                            "WHERE t.nome_tutor ilike '" + tutorDTO.getNome_tutor() + "%' " +
                            "ORDER BY t.nome_tutor";
                break;
                case 2:
                    comando = "SELECT t.* " +
                            "FROM tutor t " +
                            "WHERE t.id_tutor = " + tutorDTO.getId_tutor();
                break;
            }
            
            //Executa o comando SQL no banco de dados
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }//Caso tenha algum erro no código, é enviada uma mensagem com o que está acontecendo
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }//Fecha o método consultarTutor
}
