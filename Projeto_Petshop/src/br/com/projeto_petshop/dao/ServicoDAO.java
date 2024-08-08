
package br.com.projeto_petshop.dao;

import java.sql.*;
import br.com.projeto_petshop.dto.ServicoDTO;
import br.com.projeto_petshop.dto.FuncionarioDTO;

public class ServicoDAO {
    //Método construtor da classe
    public ServicoDAO(){
        
    }
    
    //Atributo do tipo Result utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    //Método utilizado para inserir um objeto funcionarioDTO no banco de dados
    public boolean inserirServico(ServicoDTO servicoDTO, FuncionarioDTO funcionarioDTO){
        try{
            //Chama o método ques está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement que será responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "INSERT INTO SERVICO (tipo_servico, valor_servico, id_funcionario) VALUES ("
                    + "'" + servicoDTO.getTipo_servico() + "', "
                    + "'" + servicoDTO.getValor_servico() + "', "                    
                    + funcionarioDTO.getId_funcionario() + ") ";
            
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
    }//Fecha o método inserirServico
    
    //Método utilizado para alterar um objeto servicoDTO no banco de dados
    public boolean alterarServico(ServicoDTO servicoDTO, FuncionarioDTO funcionarioDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "UPDATE SERVICO SET "
                    + "tipo_servico = '" + servicoDTO.getTipo_servico() + "', "
                    + "valor_servico = '" + servicoDTO.getValor_servico() + "', "
                    + "id_funcionario = " + funcionarioDTO.getId_funcionario() + " "
                    + "WHERE id_servico = " + servicoDTO.getId_servico();
            
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
    }//Fecha o método alterarServico
    
    //Método utilizado para excluir um objeto servicoDTO no banco de dados
    public boolean excluirServico(ServicoDTO servicoDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "DELETE FROM SERVICO WHERE id_servico = "
                    + servicoDTO.getId_servico();
            
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
    }//Fecha o método excluirServico
    
    //Método utilizado para consultar um objeto servicoDTO no banco de dados
    public ResultSet consultarServico(ServicoDTO servicoDTO, int opcao){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "SELECT s.* " +
                            "FROM servico s " +
                            "WHERE s.tipo_servico ilike '" + servicoDTO.getTipo_servico() + "%' " +
                            "ORDER BY s.tipo_servico";
                break;
                case 2:
                    comando = "SELECT s.*, f.nome_funcionario, f.id_funcionario " +
                            "FROM servico s, funcionario f " +
                            "WHERE s.id_funcionario = f.id_funcionario AND " +
                            "s.id_servico = " + servicoDTO.getId_servico();
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
