package pac.crud.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pac.crud.model.Company;
import pac.crud.model.Order;
import pac.crud.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sano on 4/8/18.
 */
public class OrderList {
    static Session sessionObj;
    public final static Logger logger = Logger.getLogger(OrderList.class);

    public List<Order> getAllOrders(){

        List orderList= new ArrayList<Order>();
        try{
            sessionObj =ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            orderList= sessionObj.createQuery("FROM Order ").list();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return orderList;

    }
    public void addOrder(Order order){
        if(order ==null){
            return;
        }
        try{
            sessionObj =ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            sessionObj.save(order);
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Add Order by ID '" + order.getId() + "'  In The Database!\n");
        }catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    // retunr object orders by year ,, object can be User or Company
   /* public List<Order> getOrdersByYear(int year,Class objclaz){


        List orderList= new ArrayList<Order>();
        try{
            sessionObj =ConnectionDb.buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            if(objclaz==User.class) {
                orderList = sessionObj.createQuery("FROM Order where user_id !=0 and year="+year).list();
            }else if(objclaz==Company.class){
                orderList = sessionObj.createQuery("FROM Order where company_id !=0 and year="+year).list();
            }else {
                return null;
            }

        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return orderList;
    }*/

}
