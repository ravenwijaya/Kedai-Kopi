/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author x.x
 */
public class DapurHelper {

    Session session = null;
    private Orderdetails orderdetails = null;

    public DapurHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }


    public List<Orderdetails> GetOrderDetailsbelum() {
        List<Orderdetails> list = new ArrayList<Orderdetails>();

        session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            org.hibernate.Transaction tx = session.beginTransaction();
            list = (List) session.createQuery("from Orderdetails where status='belum' ").list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object o = (Object) it.next();
                Orderdetails a = (Orderdetails) o;
                System.out.println("" + a.getMenu());
                System.out.println("" + a.getJumlah());
                System.out.println("aaa" + list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<Orderdetails> GetOrderDetailssudah() {
        List<Orderdetails> list = new ArrayList<Orderdetails>();

        session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            org.hibernate.Transaction tx = session.beginTransaction();
            list = (List) session.createQuery("from Orderdetails where status='sudah' ").list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object o = (Object) it.next();
                Orderdetails a = (Orderdetails) o;
                System.out.println("" + a.getMenu());
                System.out.println("" + a.getJumlah());
                System.out.println("aaa" + list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public void UpdateById(long id) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        Orderdetails m;
        System.out.println("dap[e" + id);
        m = (Orderdetails) session.get(Orderdetails.class, id);
        System.out.println("id:" + m.getId());
        m.setStatus("sudah");
        session.saveOrUpdate(m);
        session.beginTransaction().commit();

    }
//       public void Update(Orderdetails orderdetails){
//          session = HibernateUtil.getSessionFactory().openSession();
//       
//           System.out.println(""+orderdetails.getMenu().getNama());
//        
//          orderdetails.setStatus("sudah");
//         session.saveOrUpdate(orderdetails);
//        session.beginTransaction().commit();
////        session.save(user);
////        session.getTransaction().commit();
////        session.close();
//       }  

//       public void up(long id){
//            session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            org.hibernate.Transaction tx = session.beginTransaction();
//            Query q = session.createQuery("Update Orderdetails set status =:stat where id=:id");
//            q.setString("stat", "sudah");
//            q.setLong("id", id);
//           q.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
//       }
}
