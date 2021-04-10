/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.LongType;

/**
 *
 * @author JESSI
 */
public class OwnerHelper implements Serializable {

    Session session = null;

    public OwnerHelper() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
    }

    public Long getsumorder() {
        session = NewHibernateUtil.getSessionFactory().openSession();
        Long sum = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("select sum(total) as sum from orderr").addScalar("sum", LongType.INSTANCE);
            sum = (Long) query.list().get(0);
            System.out.println("sum " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    public List<Orderdetails> getorder() {
        session = NewHibernateUtil.getSessionFactory().openSession();
        List<Orderdetails> listorder = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Orderdetails");
            listorder = (List<Orderdetails>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listorder;
    }

    public void savemenu(Menu menu) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(menu);
        session.getTransaction().commit();
        session.close();
    }
    
    public boolean isnamaalreadyused(String nama) {
       session = NewHibernateUtil.getSessionFactory().openSession();
        Long count = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Menu m where m.nama = :nama and (m.jenis = 'makanan' or m.jenis = 'minuman')");
            query.setParameter("nama", nama);
            count = (Long) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            return true;
        }else{
            return false;
        }
    }

    public void delete(Long id) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        Menu m;
        m = (Menu) session.get(Menu.class, id);
        m.setJenis("hapus");
        session.saveOrUpdate(m);
        session.beginTransaction().commit();
}
}
