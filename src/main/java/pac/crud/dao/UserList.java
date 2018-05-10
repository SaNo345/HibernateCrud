package pac.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pac.crud.model.User;

public class UserList {
    static Session sessionObj;
    public final static Logger logger = Logger.getLogger(User.class);

    public List<User> getAllUsers() {

        List usersList = new ArrayList<User>();
        try {
            sessionObj = ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            usersList = sessionObj.createQuery("FROM User").list();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return usersList;

    }

    public void addUser(User user) {
        if (user == null) {
            return;
        }
        try {
            sessionObj = ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            sessionObj.save(user);
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Add User '" + user.getName() + "'  In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public void deleteUser(Integer userID) {
        if (userID == null) {
            return;
        }
        User user = findUserById(userID);
        try {
            sessionObj = ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            if (user == null) {
                logger.info("\n Delete Feild ,Not User by Id '" + userID + "'  In The Database!\n");
                return;
            }
            sessionObj.delete(user);
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Delete User by Id '" + userID + "'  In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method do not close  session !!
    public User findUserById(Integer userId) {
        if (userId == null) {
            return null;
        }
        User findUser = null;
        try {
            sessionObj = ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            findUser = (User) sessionObj.get(User.class, userId);
            if (findUser != null) {
                logger.info("\nSuccessfully Find User by Id '" + userId + "'  In The Database!\n");
            } else {
                logger.info("\n User by Id '" + userId + "' Not Find In The Database!\n");

            }
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
			finally {
			if(sessionObj != null && sessionObj.isOpen()) {
				sessionObj.close();
			}
		}
        return findUser;
    }


    public void updateUser(User user) {
        try {
            sessionObj = ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            sessionObj.update(user);
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Update User by Id '" + user.getUserid() + "'  In The Database!\n");

        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }

    }

}
