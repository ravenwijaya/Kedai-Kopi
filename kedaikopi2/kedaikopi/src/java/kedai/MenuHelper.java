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
public class MenuHelper implements Serializable {

    Session session = null;
    private Menu menu;

    public MenuHelper() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
    }

    public Menu getmenubyid(String id) {
        menu = null;

        try {
            session.getTransaction().begin();
            Query q = session.createQuery("from Menu where id =:idnya");
            q.setString("idnya", id);
            menu = (Menu) q.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        if (menu != null) {
            System.out.println("MENUU" + menu.getNama());
            return menu;
        }
        System.out.println("GA KETEMU MENUNYA!");
        return null;
    }

}
