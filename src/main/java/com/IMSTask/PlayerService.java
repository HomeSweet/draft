package com.IMSTask;


import com.IMSTask.JDBCClasses.DBWorker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yegorm on 06.09.2016.
 */

@Path("/PlayerService")
public class PlayerService {
    private DBWorker slave = new DBWorker();
    private String query = null;
    private Player user = new Player();
    private RequestValidator requestValidator = new RequestValidator();

    @GET
    @Path("/getBalance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@QueryParam("param") String username) {
        IMSLogger.getLogger().debug("We get from frontend: " + username);
        user = communicator(slave, user, username);
        return Response.status(200).entity("Player balance is: " + user.getBalance()).build();
    }

    @POST
    @Path("/updatePlayerBalance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlayerBalance(ComplexObjectRequest request) throws SQLException {
        if(requestValidator.idempotentChecker(request)) {
            String username = request.getField1();
            Integer newBalance = request.getField2();
            Integer transactionIdFromClient = request.getField3();
            IMSLogger.getLogger().debug("We get from frontend username: " + username + " and balance: " + newBalance);
            user = communicator(slave, user, username);
            int balanceBeforeChange = user.getBalance();
            user = updateBalance(user, username, newBalance);
            return Response.status(200).entity("Transaction id is: " +transactionIdFromClient+ "\n error code: null"
                    + "\n New player balance is: " + user.getBalance() + "\n New balance version is:  "
                    + user.getBalance_version() + "\n Before change balance was: " + balanceBeforeChange).build();
        }else return Response.status(200).entity("error code: 1 description: current request was approved").build();
        }


    public Player communicator(DBWorker slave, Player user, String username) {
        query = "SELECT * FROM ims.Player WHERE player.USERNAME=" + "'" + username + "'";

        try {

            Statement statement = slave.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user.setId(resultSet.getInt("ID"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setBalance(resultSet.getInt("BALANCE"));
                user.setBalance_version(resultSet.getInt("BALANCE_VERSION"));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        IMSLogger.getLogger().debug("Hash is: "+user.hashCode());
        return user;
    }

    public Player updateBalance(Player user, String username, int balance) throws SQLException {

        user = communicator(slave, user, username);
        String updateBalance = "UPDATE ims.Player SET player.BALANCE=" + balance + " WHERE player.ID=" + user.getId();
        String updateBalanceVersion = "UPDATE ims.Player SET BALANCE_VERSION=" + (user.getBalance_version() + 1) + " WHERE player.ID=" + user.getId();
        IMSLogger.getLogger().debug("First :" + updateBalance + " Second: " + updateBalanceVersion);
        Statement statement = slave.getConnection().createStatement();
        int resultSet = statement.executeUpdate(updateBalance);
        if (resultSet == 1) {
            IMSLogger.getLogger().debug("BALANCE was update successfully");
        } else {
            IMSLogger.getLogger().debug("BALANCE wasn't update");
        }
        resultSet = statement.executeUpdate(updateBalanceVersion);
        if (resultSet == 1) {
            IMSLogger.getLogger().debug("BALANCE_VERSION was update successfully");
        } else {
            IMSLogger.getLogger().debug("BALANCE_VERSION wasn't update");
        }
        communicator(slave, user, username);
        statement.close();
        return communicator(slave, user, username);
    }

}
