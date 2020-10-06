package org.hyperskill;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreditCardDao {
    protected static final String CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS credit_cards(" +
            "id INTEGER, number VARCHAR(16) NOT NULL UNIQUE, pin VARCHAR(4) NOT NULL, balance INTEGER DEFAULT 0);";
    protected static final String CREATE_CARD = "INSERT INTO credit_cards (number, pin) VALUES %s, %s;";
    protected static final String READ_CARD = "SELECT * FROM credit_cards WHERE number = %s AND pin = %s;";

    final Connection connection;

    public CreditCardDao(String filename) throws SQLException {
        this.connection = getConnection(filename);
    }

    public static Connection getConnection(String string) throws SQLException {
        String url = String.format("jdbc:sqlite:%s", string);
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return dataSource.getConnection();
    }

    public boolean createDatabase(){
        return executeUpdate(CREATE_DATABASE);
    }

    public boolean createCard(CreditCard card){
        return executeUpdate(String.format(CREATE_CARD, card.getCreditCardNumber(), card.getPin()));
    }

    private boolean executeUpdate(String query) {
        try (connection){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(query);
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean executeQuery(String query){
        return false;
    }
}
