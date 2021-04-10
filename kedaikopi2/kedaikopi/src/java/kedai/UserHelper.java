/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
/**
 *
 * @author JESSI
 */
public class UserHelper implements Serializable {
    Session session = null;
    private Acounts acount = null;

    public UserHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Acounts CheckUser(String username, String pass) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Acounts where username =:uname and password=:pass");
            q.setString("uname", username);
            q.setString("pass", pass);
            acount = (Acounts) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (acount != null) {
            return acount;
        } else {
            return null;
        }
    }
}
