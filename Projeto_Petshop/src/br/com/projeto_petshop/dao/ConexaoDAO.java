
package br.com.projeto_petshop.dao;

import java.sql.*;

public class ConexaoDAO {
    //Cria um atributo do tipo Connection para guardar a conexão com o banco de dados
    public static Connection con = null;
    
    public ConexaoDAO(){
        
    }
    
    public static void ConnectDB(){
        try{
            //Dados para conectar com o banco de dados
            String dsn = "projeto_petshop"; //Nome do banco de dados (igual ao criado no Postgres)
            String user = "postgres"; //Nome do usuário utilizado para conexão
            String senha = "postdba"; //Senha do usuário utilizado para conexão
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + dsn;
            
            con = DriverManager.getConnection(url, user, senha);

            con.setAutoCommit(false);
            if(con == null){
                System.out.println("Erro ao abrir o banco de dados");
            }
        }//Fecha o try
        //Caso ocorra problemas para abrir o banco de dados é emitido a seguinte mensagem
        catch(Exception e){
            System.out.println("Problema ao abrir o banco de dados" +
                e.getMessage());
        }//Fecha o catch
    }//Fecha o método ConnectDB
    public static void CloseDB(){
        try{
            con.close();
        }//Fecha o try
        //Caso ocorra problemas para abrir o banco de dados é emitido a seguinte mensagem
        catch (Exception e){
            System.out.println("Problema ao fechar o banco de dados" +
                e.getMessage());
        }//Fecha o catch
    }//Fecha o método CloseDB
}//Fecha a classe ConexaoDAO
