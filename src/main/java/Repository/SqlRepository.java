package Repository;
import Domain.Recipe;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

public class SqlRepository extends Repository{
    private final String JDBC_URL = "jdbc:sqlite:recipes.sb";

    private Connection connection;
    public SqlRepository(){
        openConnection();
        createTable();
    }
    public void openConnection(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try {
            if (connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable(){
        try (final Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS recipes(name varchar(400), time int, ingredients varchar(400));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * from recipes"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Recipe r = new Recipe(rs.getString("name"), rs.getInt("time"), rs.getString("ingredients"));
                    recipes.add(r);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return recipes;
    }

    @Override
    public void add(Recipe r){
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO recipes VALUES (?,?,?);")){
            stmt.setString(1, r.getName());
            stmt.setInt(2, r.getTime());
            stmt.setString(3, r.getIngredients());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
