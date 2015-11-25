import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;

/**
 * Created by 47419119l on 25/11/15.
 */
public class insertSQLite {

    public static void insertMovies(int ID, String ORIGINAL_TITLE,String RELEASE_DATE) {
       Connection c = null;
        Statement stmt = null;
        try {

            /*
            Conectem amb la BBDD
             */
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            /*
            Fem el insert.
             */
            String sql_insert = "INSERT INTO MOVIES" +
                    " (ID,ORIGINAL_TITLE,RELEASE_DATE) VALUES" +
                    " (?, ?, ?);";

            stmt.executeUpdate(sql_insert);

            PreparedStatement preparedStatement = c.prepareStatement(sql_insert);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, ORIGINAL_TITLE);
            preparedStatement.setString(3, RELEASE_DATE);
            /*
            Executem el insert.
             */
            preparedStatement .executeUpdate();

            stmt.close();
            c.commit();
            c.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void insertActor(int ID,int ID_PELICULA,String NAME, String CHARACTER) {
        String sql_actor = "CREATE TABLE ACTORS " +
                "(ID_PELICULA    INT    NOT NULL," +
                " ID   INT    NOT NULL,"+
                " NAME TEXT    NOT NULL, " +
                " CHARACTER   TEXT    NOT NULL," +
                "PRIMARY KEY(ID_PELICULA, ID))";
        Connection c = null;
        Statement stmt = null;
        try {

            /*
            Conectem amb la BBDD
             */
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            /*
            Fem el insert.
             */
            String sql_insert = "INSERT INTO MOVIES" +
                    " (ID,ID_PELICULA,NAME,CHARACTER) VALUES" +
                    " (?, ?, ?,?);";

            stmt.executeUpdate(sql_insert);

            PreparedStatement preparedStatement = c.prepareStatement(sql_insert);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, ID_PELICULA);
            preparedStatement.setString(3, NAME);
            preparedStatement.setString(4, CHARACTER);
            /*
            Executem el insert.
             */
            preparedStatement .executeUpdate();

            stmt.close();
            c.commit();
            c.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}