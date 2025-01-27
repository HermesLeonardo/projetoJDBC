
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada para conectar e desconectar do banco de dados.
 */
public class Conexao {
    PreparedStatement stmt;
    Connection conn = null;

    public Connection conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projetointegradoriv", "root", "123456");
            System.out.println("Conectou no banco de dados.");
        } catch (SQLException ex) {
            System.out.println("Erro: Não conseguiu conectar no BD.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Não encontrou o driver do BD.");
        }

        return conn;
    }

    public void desconectar(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu desconectar do BD.");
        }
    }



    public void incluir(Connection conn, String nome, String email, String telefone)
    {

        try {
            String query = "INSERT INTO cliente (nome, email, telefone) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,nome);
            stmt.setString(2,email);
            stmt.setString(3,telefone);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Inclusão realizada com sucesso!");
            }else{
                System.out.println("Nenhuma linha foi inserida.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void excluir(Connection conn, int idCliente)
    {

        try{
            String query = "DELETE FROM cliente WHERE idcliente = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,idCliente);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Exclusão realizada com sucesso");
            }else{
                System.out.println("Nenhuma exlusão realizada");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static List<List<String>> Exibir(Connection conn, String cliente)
    {
        List<List<String>> tabela = new ArrayList<>();

        try{
            String query = "Select * FROM cliente";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColunas = rsmd.getColumnCount();

            while(rs.next()){

                List<String> linha = new ArrayList<>();

                for(int i=1; i<=numColunas; i++){
                    linha.add(rs.getString(i));
                }
                tabela.add(linha);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tabela;
    }

    public void alterar (Connection conn, int idcliente, String nome, String email, String telefone){

        try{

            String query = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE idcliente = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,nome);
            stmt.setString(2,email);
            stmt.setString(3,telefone);
            stmt.setInt(4,idcliente);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Alteração realizada com sucesso!");
            }else{
                System.out.println("Alteração falhou.");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void alterar1 (Connection conn, int idcliente, String nome){
        try{
            String query = "UPDATE cliente SET nome = ? WHERE idcliente = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setInt(2,idcliente);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Alteração realizada com sucesso!");
            }else{
                System.out.println("Alteração falhou.");
            }
        }catch (SQLException e){e.printStackTrace();}
    }

    public void alterar2 (Connection conn, int idcliente, String email){
        try{
            String query = "UPDATE cliente SET email = ? WHERE idcliente = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setInt(2,idcliente);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Alteração realizada com sucesso!");
            }else{
                System.out.println("Alteração falhou.");
            }
        }catch (SQLException e){e.printStackTrace();}
    }

    public void alterar3 (Connection conn, int idcliente, String telefone){
        try{
            String query = "UPDATE cliente SET telefone = ? WHERE idcliente = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, telefone);
            stmt.setInt(2,idcliente);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("Alteração realizada com sucesso!");
            }else{
                System.out.println("Alteração falhou.");
            }
        }catch (SQLException e){e.printStackTrace();}
    }




}