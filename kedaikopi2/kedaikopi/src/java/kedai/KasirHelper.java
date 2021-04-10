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

/**
 *
 * @author JESSI
 */
public class KasirHelper implements Serializable {

    Session session = null;

    public KasirHelper() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
    }

    public List<Menu> getmenu(String jenis) {
        List<Menu> listmenu = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Menu where jenis='" + jenis + "'");
            listmenu = (List<Menu>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listmenu;
    }

    public void saveorder(String idkasir, String tanggal, int nomeja, int total) {
        System.out.println("SAVEEE ORDER");
        session.beginTransaction();
        Query query = session.createSQLQuery("INSERT INTO orderr (id_kasir, tanggal, nomeja, total) VALUES (:idkasir, :tanggal, :nomeja, :total)");
        query.setParameter("idkasir", idkasir);
        query.setParameter("tanggal", tanggal);
        query.setParameter("nomeja", nomeja);
        query.setParameter("total", total);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public Long getcurrentorderid() {
       session = NewHibernateUtil.getSessionFactory().openSession();
        Long count = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Orderr");
            count = (Long) query.uniqueResult();
            System.out.println("count " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
     public void saveorderdetail(Long idmenu, int jumlah, String keterangan) {
        System.out.println("SAVEEE ORDERDETAILS");
        session.beginTransaction();
        Long currid = getcurrentorderid();
         System.out.println("idmenu = "+ idmenu);
         System.out.println("idorder = "+ currid);
         System.out.println("jumlah = "+ jumlah);
         System.out.println("ket = "+ keterangan);
        Query query = session.createSQLQuery("INSERT INTO orderdetails (menu_id, order_id, jumlah, keterangan, status) VALUES (:idmenu, :idorder, :jumlah, :keterangan, :status)");
        query.setParameter("idmenu", idmenu);
        query.setParameter("idorder", currid);
        query.setParameter("jumlah", jumlah);
        query.setParameter("keterangan", keterangan);
        query.setParameter("status", "belum");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
