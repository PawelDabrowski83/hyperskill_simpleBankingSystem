package org.hyperskill;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreditCardDao {
    protected static final String BASE_NAME = "test-integration.db";
    protected static final String CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS credit_cards(" +
            "id INTEGER, number VARCHAR(16) NOT NULL UNIQUE, pin VARCHAR(4) NOT NULL, balance INTEGER DEFAULT 0);";
    protected static final String CREATE_CARD = "INSERT INTO credit_cards (number, pin) VALUES ('%s', '%s');";
    protected static final String READ_CARD = "SELECT * FROM credit_cards WHERE number = '%s' AND pin = '%s';";
    protected static final String READ_ALL_CARDS = "SELECT * FROM credit_cards;";

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

    public Set<CreditCard> getAllCards(){
        Set<CreditCard> creditCards = new HashSet<>();
        try (Connection connection = getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(READ_ALL_CARDS);
                while (resultSet.next()){
                    String readCreditCardNumber = resultSet.getString("number");
                    String readPin = resultSet.getString("pin");
                    int readBalance = resultSet.getInt("balance");
                    System.out.printf("%s %s %d", readCreditCardNumber, readPin, readBalance);
                    System.out.println();
                    CreditCard targetCard = new CreditCard(readCreditCardNumber, readPin, readBalance);
                    creditCards.add(targetCard);
                }
                return creditCards;
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return Collections.emptySet();
        }
        return creditCards;
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
