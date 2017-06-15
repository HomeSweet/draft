package com.IMSTask;

import com.IMSTask.DAO.PlayerDao;
import com.IMSTask.DAO.PlayerDaoImpl;

/**
 * Created by yegorm on 17.09.2016.
 */
public class Factory {
    public  static  Factory instance = new Factory();
    public PlayerDao playerDao;

    private Factory(){}

    public static Factory getInstance(){
        return Factory.instance;
    }

    public PlayerDao getPlayerDao(){
        if (playerDao==null){ playerDao = new PlayerDaoImpl();}
        return playerDao;
    }

}
