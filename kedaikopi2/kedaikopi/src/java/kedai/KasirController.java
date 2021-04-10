/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author JESSI
 */
@ManagedBean(name = "kasirc", eager = true)
@SessionScoped
public class KasirController implements Serializable {

    private KasirHelper helper;
    private MenuHelper mhelper;
    private UserHelper uhelper;
    DataModel pesanan;
    DataModel menumakanan;
    DataModel menuminuman;
    private List<Orderdetails> listordertemp = new ArrayList<Orderdetails>();
    private String uangterima;
    private String nomeja;
    private String pesan;
    private String total;
    private Acounts acount;

    public KasirController() {
        this.helper = new KasirHelper();
        this.mhelper = new MenuHelper();
        listordertemp.clear();
       }

    public DataModel<Menu> getMenumakanan() {
        return new ListDataModel<Menu>(helper.getmenu("Makanan"));
    }

    public void setMenumakanan(DataModel menumakanan) {
        this.menumakanan = menumakanan;
    }

    public DataModel<Menu> getMenuminuman() {
        return new ListDataModel<Menu>(helper.getmenu("Minuman"));
    }

    public void setMenuminuman(DataModel menuminuman) {
        this.menuminuman = menuminuman;
    }

    public List<Orderdetails> getListordertemp() {
        return listordertemp;
    }

    public void setListordertemp(List<Orderdetails> listorder) {
        this.listordertemp = listorder;
    }

    public void addorder(String menuid, String keterangan) {
        System.out.println("menuid = " + menuid);
        System.out.println("keterangan = " + keterangan);
        try {
            Menu menu = mhelper.getmenubyid(menuid);
            listordertemp.add(new Orderdetails(listordertemp.size(), menu, 1, keterangan, menu.getHarga()));
            System.out.println("ORDERRRRR " + getListordertemp());
        } catch (Exception e) {
            System.out.println("GAGAL INSERT!!!!!!!!");
        }
    }

    public DataModel getPesanan() {
        return new ListDataModel<Orderdetails>(getListordertemp());
    }

    public void setPesanan(DataModel pesanan) {
        this.pesanan = pesanan;
    }

    public void tambah(ActionEvent event) {
        String buttonId = event.getComponent().getId();
        String item = event.getComponent().getAttributes().get("itemid").toString();
        System.out.println("buttonid = " + buttonId);
        System.out.println("Item = " + item);
        if (buttonId.equalsIgnoreCase("btn1")) {
            addorder(item, "Ice");
        } else if (buttonId.equalsIgnoreCase("btn2")) {
            addorder(item, "Hot");
        } else if (buttonId.equalsIgnoreCase("btn3")) {
            addorder(item, "Tidak Pedas");
        } else if (buttonId.equalsIgnoreCase("btn4")) {
            addorder(item, "Pedas");
        } else if (buttonId.equalsIgnoreCase("btn5")) {
            System.out.println("MAU KECILIN JUMLAH");
            int jumlahnow = listordertemp.get(Integer.parseInt(item)).getJumlah();
            if (jumlahnow > 1) {
                int subtotal = (jumlahnow - 1) * listordertemp.get(Integer.parseInt(item)).getSubtotal() / jumlahnow;
                listordertemp.get(Integer.parseInt(item)).setJumlah(jumlahnow - 1);
                listordertemp.get(Integer.parseInt(item)).setSubtotal(subtotal);
            }
        } else if (buttonId.equalsIgnoreCase("btn6")) {
            System.out.println("MAU tambah JUMLAH");
            int jumlahnow = listordertemp.get(Integer.parseInt(item)).getJumlah();
            int subtotal = (jumlahnow + 1) * listordertemp.get(Integer.parseInt(item)).getSubtotal() / jumlahnow;
            listordertemp.get(Integer.parseInt(item)).setJumlah(jumlahnow + 1);
            listordertemp.get(Integer.parseInt(item)).setSubtotal(subtotal);
        } else if (buttonId.equalsIgnoreCase("btn7")) {
            System.out.println("MAU hapus JUMLAH");
            listordertemp.remove(Integer.parseInt(item));
            for (int i = 0; i < listordertemp.size(); i++) {
                listordertemp.get(i).setIdpesanan(i);
            }
        }
    }

    public String getUangterima() {
        return uangterima;
    }

    public void setUangterima(String uangterima) {
        this.uangterima = uangterima;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getNomeja() {
        return nomeja;
    }

    public void setNomeja(String nomeja) {
        this.nomeja = nomeja;
    }

    public String getTotal() {
        int totals = 0;
        for (int i = 0; i < listordertemp.size(); i++) {
            totals += listordertemp.get(i).getSubtotal();
        }
        return String.valueOf(totals);
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String GetDate() {
        Date currentDate = new Date();
        String dateToStr = DateFormat.getInstance().format(currentDate);
        return dateToStr;
    }

    public void submit(String uname) {
        if (uangterima != null && nomeja != null) {
            try {
                int uangterimanya = Integer.parseInt(uangterima);
                int nomejanya = Integer.parseInt(nomeja);
                int total = Integer.parseInt(getTotal());
                int hasil = uangterimanya - total;
                if (hasil < 0) {
                    setPesan("Uang anda kurang sebesar " + hasil + ", Pembayaran gagal!");
                } else {
                    setPesan("KEMBALIAN = " + hasil + " . Tunggu pesanan di meja nomor " + nomejanya);
                    helper.saveorder(uname, GetDate(), nomejanya, total);
                    for (int i = 0; i < listordertemp.size(); i++) {
                        Orderdetails orderdetails = listordertemp.get(i);
                        helper.saveorderdetail(orderdetails.getMenu().getId(), orderdetails.getJumlah() , orderdetails.getKeterangan());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                setPesan("Masukkan Uang diterima dan No Meja dengan Angka!");
            }
        }
    }

    public void clear() {
        setPesan("");
        setNomeja("");
        setUangterima("");
        listordertemp.clear();

    }
}
