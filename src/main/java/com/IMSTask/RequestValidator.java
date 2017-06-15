package com.IMSTask;

import com.IMSTask.JDBCClasses.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yegorm on 30.09.2016.
 */
public class RequestValidator {
    private DBWorker slave = new DBWorker();
    private History history = new History();

    public boolean idempotentChecker(ComplexObjectRequest request) throws SQLException {
        ResultSet resultSet = null;
        String username = request.getField1();
        double newBalance = request.getField2();
        Integer transactionId = request.getField3();
        Statement statement = null;

        String query = "SELECT * FROM ims.history WHERE history.USERNAME=" + "'" + username + "'" + " and "
                + "history.transactionId=" + transactionId + " and "
                + "history.balanceRequest=" + newBalance;
        IMSLogger.getLogger().debug(query);
        try {
            statement = slave.getConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                history.setIdHistory(resultSet.getInt("idhistory"));
                history.setUsername(resultSet.getString("USERNAME"));
                history.setTransactionId(resultSet.getInt("transactionId"));
                history.setBalanceRequest(resultSet.getInt("balanceRequest"));
            }

            transactionId=history.getTransactionId();

            IMSLogger.getLogger().debug(transactionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if ( transactionId == 0) {
            statement.close();
            saveValuesFromRequestIntoHistoryTable(username, newBalance, request.getField3());
            return true;
        } else {
            statement.close();
            return false;
        }
    }

    private void saveValuesFromRequestIntoHistoryTable(String username, double balanceFromRequest, int transactionId) {
        try {
            String insert = "INSERT INTO ims.history (transactionId, username, balanceRequest) " +
                    "VALUES ('" + transactionId + "'" + ", '"+ username + "'" + ", '"+ balanceFromRequest + "');";
            Statement statement = slave.getConnection().createStatement();
            IMSLogger.getLogger().debug(insert);
            statement.execute(insert);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}