
package br.com.projeto_petshop.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.com.projeto_petshop.dto.FuncionarioDTO;

public class FuncionarioDAO {
    
    //Método construtor da classe
    public FuncionarioDAO(){
        
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    
    //Método utilizado para inserir um objeto funcionarioDTO no banco de dados
    public boolean inserirFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement que será responsável
            stmt = ConexaoDAO.con.createStatement();
            //Comanda SQL que será executado no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "INSERT INTO FUNCIONARIO (user_funcionario, "
                    + "senha_funcionario, nome_funcionario, cpf_funcionario, "
                    + "rg_funcionario, datanasc_funcionario, genero_funcionario, "
                    + "tel_funcionario, rua_funcionario, num_funcionario, "
                    + "cep_funcionario, bairro_funcionario, cidade_funcionario, "
                    + "estado_funcionario, tipo_funcionario) VALUES ("
                    + "'" + funcionarioDTO.getUser_funcionario() + "', "
                    + "'" + criptografar(funcionarioDTO.getSenha_funcionario()) + "', "
                    + "'" + funcionarioDTO.getNome_funcionario().toUpperCase() +"', "
                    + "'" + funcionarioDTO.getCpf_funcionario() + "', "                    
                    + "'" + funcionarioDTO.getRg_funcionario() + "', "
                    + "to_date('" + data_format.format(funcionarioDTO.getDatanasc_funcionario()) + "', 'dd/mm/yyyy'), "
                    + "'" + funcionarioDTO.getGenero_funcionario() + "', "
                    + "'" + funcionarioDTO.getTel_funcionario() + "', "
                    + "'" + funcionarioDTO.getRua_funcionario() + "', "
                    + "'" + funcionarioDTO.getNum_funcionario() + "', "
                    + "'" + funcionarioDTO.getCep_funcionario() + "', "
                    + "'" + funcionarioDTO.getBairro_funcionario() + "', "
                    + "'" + funcionarioDTO.getCidade_funcionario() + "', "
                    + "'" + funcionarioDTO.getEstado_funcionario() + "', "
                    + "'" + funcionarioDTO.getTipo_funcionario() + "')";
            
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
    }//Fecha o método inserirFuncionario
  
    //Método utilizado para alterar um objeto FuncionarioDTO no banco de dados
    public boolean alterarFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            
            comando = "UPDATE FUNCIONARIO SET "
                    + "user_funcionario = '" + funcionarioDTO.getUser_funcionario() + "', "
                    //+ "senha_funcionario = '" + criptografar(funcionarioDTO.getSenha_funcionario()) + "', "
                    + "nome_funcionario = '" + funcionarioDTO.getNome_funcionario().toUpperCase() +"', "
                    + "cpf_funcionario = '" + funcionarioDTO.getCpf_funcionario() + "', "                    
                    + "rg_funcionario = '" + funcionarioDTO.getRg_funcionario() + "', "
                    + "datanasc_funcionario = to_date ('" + data_format.format(funcionarioDTO.getDatanasc_funcionario()) + "', 'dd/mm/yyyy'), "
                    + "genero_funcionario = '" + funcionarioDTO.getGenero_funcionario() + "', "
                    + "tel_funcionario = '" + funcionarioDTO.getTel_funcionario() + "', "
                    + "rua_funcionario = '" + funcionarioDTO.getRua_funcionario() + "', "
                    + "num_funcionario = '" + funcionarioDTO.getNum_funcionario() + "', "
                    + "cep_funcionario = '" + funcionarioDTO.getCep_funcionario() + "', "
                    + "bairro_funcionario = '" + funcionarioDTO.getBairro_funcionario() + "', "
                    + "cidade_funcionario = '" + funcionarioDTO.getCidade_funcionario() + "', "
                    + "estado_funcionario = '" + funcionarioDTO.getEstado_funcionario() + "', ";
                    //+ "tipo_funcionario = '" + funcionarioDTO.getTipo_funcionario() + "', ";
            
            if(funcionarioDTO.getSenha_funcionario() != null){
                comando += "senha_funcionario = '" + criptografar(funcionarioDTO.getSenha_funcionario()) + "', ";
            }
            
            comando += "tipo_funcionario = '" + funcionarioDTO.getTipo_funcionario().toUpperCase() + "' "
                    + "WHERE id_funcionario = " + funcionarioDTO.getId_funcionario();
            
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
    }//Fecha o método alterarFuncionario
    
    //Método utilizado para excluir um objetoDTO no banco de dados
    public boolean excluirFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "DELETE FROM FUNCIONARIO WHERE id_funcionario = "
                    + funcionarioDTO.getId_funcionario();
            
            //Executa o comando SQL no banco de dados
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
    }//Fecha o método excluirFuncionario
    
    //Método utilizado para consultar um objeto tutorDTO no banco de dados
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao){
        try{
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Instancia o Statement responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que será executado no banco de dados
            String comando = "";
            switch(opcao){
                case 1:
                    comando = "SELECT f.* " +
                            "FROM funcionario f " +
                            "WHERE f.nome_funcionario ilike '" + funcionarioDTO.getNome_funcionario() + "%' " +
                            "ORDER BY f.nome_funcionario";
                break;
                case 2:
                    comando = "SELECT f.* " +
                            "FROM funcionario f " +
                            "WHERE f.id_funcionario = " + funcionarioDTO.getId_funcionario();
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
    }//Fecha o método consultarFuncionario
    
    //Método logarFuncionario
    public String logarFuncionario(FuncionarioDTO funcionarioDTO){
        try{
            
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "SELECT f.tipo_funcionario "+
                    "FROM Funcionario f "+
                    "WHERE f.user_funcionario = '" + funcionarioDTO.getUser_funcionario() + "'" +
                    " AND f.senha_funcionario = '" + criptografar(funcionarioDTO.getSenha_funcionario()) + "'";
            
            rs = null;
            rs = stmt.executeQuery(comando);
            if(rs.next()){
                return rs.getString("tipo_funcionario");
            }
            else{
                return "";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método logarFuncionario
    
    ////// código para criptografia ///////
    private static MessageDigest md = null;
    
    static {
        try{
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    private static char[] hexCodes(byte[] text){
        char[] hexOutput = new char[text.length * 3];
        String hexString;
        
        for (int i = 0; i < text.length; i++){
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 3,
                    hexString.length(), hexOutput, i * 3);
        }
        return hexOutput;
    }
    
    public static String criptografar(String pwd) {
        if(md != null){
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }
    
    ///////// Fim Rotinas para criptografia ////////
    
}//Fecha a classe FuncionarioDAO
