/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author JESSI
 */
@ManagedBean(name = "ownerc", eager = true)
@SessionScoped
public class OwnerController implements Serializable {

    private KasirHelper khelper;
    private OwnerHelper ohelper;
    private Long sumorder;
    private Long jumlahorder;
    DataModel order;
    private String namamenu;
    private String hargamenu;
    private String jenis;
    private String pesan = "";

    public OwnerController() {
        this.khelper = new KasirHelper();
        this.ohelper = new OwnerHelper();
    }

    public Long getSumorder() {
        return ohelper.getsumorder();
    }

    public void setSumorder(Long sumorder) {
        this.sumorder = sumorder;
    }

    public Long getJumlahorder() {
        return khelper.getcurrentorderid();
    }

    public void setJumlahorder(Long jumlahorder) {
        this.jumlahorder = jumlahorder;
    }

    public DataModel getOrder() {
        return new ListDataModel<Orderdetails>(ohelper.getorder());
    }

    public void setOrder(DataModel order) {
        this.order = order;
    }

    public String getNamamenu() {
        return namamenu;
    }

    public void setNamamenu(String namamenu) {
        this.namamenu = namamenu;
    }

    public String getHargamenu() {
        return hargamenu;
    }

    public void setHargamenu(String hargamenu) {
        this.hargamenu = hargamenu;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void submit() {
        if (ohelper.isnamaalreadyused(namamenu)) {
            setPesan("Insert Gagal! Sudah ada nama yang sama di database");
        } else {
            ohelper.savemenu(new Menu(namamenu, Integer.parseInt(hargamenu), jenis));
            setPesan("Insert Berhasil");
        }
    }

    public void delete(Long id) {
        ohelper.delete(id);
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public void clear() {
        setNamamenu("");
        setHargamenu("");
        setJenis("");

    }
}
