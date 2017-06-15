package com.IMSTask.DAO;

import com.IMSTask.Player;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yegorm on 16.09.2016.
 */
public interface PlayerDao {

    public void addPlayer(Player player)throws SQLException;
    public void deletePlayer(Player player)throws SQLException;
    public Player getPlayer(int id)throws SQLException;
    public List<Player> getPlayers()throws SQLException;
    public Player getPlayer(String username)throws SQLException;
}
