package org.hyperskill;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreditCardDao {
    protected static final String BASE_NAME = "test-integration.db";
    protected static final String CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS credit_cards(" +
            "id INTEGER, number VARCHAR(16) NOT NULL UNIQUE, pin VARCHAR(4) NOT NULL, balance INTEGER DEFAULT 0);";
    protected static final String CREATE_CARD = "INSERT INTO credit_cards (number, pin) VALUES ('%s', '%s');";
    protected static final String CHECK_CARD = "SELECT * FROM credit_cards WHERE number = '%s';";
    protected static final String READ_CARD = "SELECT * FROM credit_cards WHERE number = '%s' AND pin = '%s';";
    protected static final String UPDATE_CARD = "UPDATE credit_cards SET balance = balance + %d WHERE number = '%s';";
    protected static final String DELETE_CARD = "DELETE FROM credit_cards WHERE number = %s";

    protected static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:sqlite:%s", BASE_NAME);
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return dataSource.getConnection();
    }

    public boolean createDatabase(){
        return executeUpdate(CREATE_DATABASE);
    }

    public boolean createCard(CreditCard card) {
        return executeUpdate(String.format(CREATE_CARD, card.getCreditCardNumber(), card.getPin()));
    }

    public CreditCard readCard(String number, String pin){
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(String.format(READ_CARD, number, pin));
                if (resultSet.next()) {
                    String readCreditCardNumber = resultSet.getString("number");
                    String readPin = resultSet.getString("pin");
                    int readBalance = resultSet.getInt("balance");
                    return new CreditCard(readCreditCardNumber, readPin, readBalance);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return CreditCard.NULL_CARD;
    }

    public boolean checkCard(String number){
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(String.format(CHECK_CARD, number));
                if (!resultSet.next()){
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateCard(String creditCardNumber, int amount){
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(String.format(UPDATE_CARD, amount, creditCardNumber));
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

    public boolean deleteCard(String creditCardNumber){
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(String.format(DELETE_CARD, creditCardNumber));
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

    private boolean executeUpdate(String query) {
        try (Connection connection = getConnection()){
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

    private ResultSet executeQuery(String query){
        ResultSet resultSet = null;
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery(query);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
