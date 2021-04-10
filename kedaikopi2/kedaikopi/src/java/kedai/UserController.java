/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JESSI
 */
@ManagedBean(name = "userc", eager = true)
@SessionScoped
public class UserController implements Serializable {

    private UserHelper helper;
    private String username;
    private String password;
    private String nama;
    private String kasirnama ="";
    private String kasirusername="";
    private Acounts acount;

    public UserController() {
        this.helper = new UserHelper();
    }

    public UserHelper getHelper() {
        return helper;
    }

    public void setHelper(UserHelper helper) {
        this.helper = helper;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return acount.getNama();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String nextpage() {
        acount = helper.CheckUser(username, password);
        if (acount != null) {
            String role = acount.getRole();
            if (role.equalsIgnoreCase("dapur")) {
                return "dapur.xhtml";
            } else if (role.equalsIgnoreCase("kasir")) {
                setKasirnama(acount.getNama());
                setKasirusername(username);
                return "kasir.xhtml";
            } else if (role.equalsIgnoreCase("owner")) {
                return "owner.xhtml";
            } else {
                return "index.xhtml";
            }
        } else {
            return "index.xhtml";
        }
    }

    public String getvalidation() {
        if (acount == null && username != null && password != null) {
            return "Username dan Password anda tidak cocok dalam database! ";
        } else {
            return null;
        }

    }

    public String getKasirnama() {
        return kasirnama;
    }

    public void setKasirnama(String kasirnama) {
        this.kasirnama = kasirnama;
    }

    public String getKasirusername() {
        return kasirusername;
    }

    public void setKasirusername(String kasirusername) {
        this.kasirusername = kasirusername;
    }
 
}
