/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status = 0;

        try {
            Connection conexao = conectaDAO.connectDB();

            prep = conexao.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3,produto.getStatus());
            status = prep.executeUpdate();
            System.out.println("Dados inseridos com sucesso...");
            prep.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir dados no banco: " + ex.getMessage());
            ex.printStackTrace(); // Log mais detalhado da exceção
            
        } finally {
            //conectaDAO.desconectar(); // Certifica-se de fechar a conexão, independentemente do que aconteceu no bloco try
        }
        return status;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

