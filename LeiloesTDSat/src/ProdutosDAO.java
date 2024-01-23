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
import java.util.List;


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
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();

        try {
            Connection conexao = conectaDAO.connectDB();
            prep = conexao.prepareStatement("SELECT * FROM produtos WHERE status = 'A Venda';");
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                produtos.add(produto);
            }
            System.out.println("Dados listados na tela.");
            prep.close();
            resultset.close();
            return produtos;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar todos os produtos: " + ex.getMessage());
            return null;
        }
    }

    public void venderProduto(int id) {
        Connection conexao = conectaDAO.connectDB();
        try{
            prep = conexao.prepareStatement("UPDATE produtos SET status = 'vendido' WHERE id = ?;");
            prep.setInt(1, id);
            
            prep.executeUpdate();
            
            prep.close();
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
        }finally {
            
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();

        try {
            Connection conexao = conectaDAO.connectDB();
            prep = conexao.prepareStatement("SELECT * FROM produtos WHERE status = 'vendido';");
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                produtos.add(produto);
            }
            System.out.println("Dados listados na tela.");
            prep.close();
            resultset.close();
            return produtos;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar todos os produtos: " + ex.getMessage());
            return null;
        }
    }
    
        
}

