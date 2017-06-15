package com.IMSTask.DAO;

import com.IMSTask.HibernateUtil;
import com.IMSTask.Player;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yegorm on 16.09.2016.
 */
public class PlayerDaoImpl implements PlayerDao {
    @Override
    public void addPlayer(Player player) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public void deletePlayer(Player player) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public Player getPlayer(int id) throws SQLException {
        Player result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = session.load(Player.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<Player> getPlayers() throws SQLException {
        List<Player> players = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            players = session.createCriteria(Player.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return players;
    }

    @Override
    public Player getPlayer(String username) throws SQLException {
        Player result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = session.load(Player.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return result;
    }
}
