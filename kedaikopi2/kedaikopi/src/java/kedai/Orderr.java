package kedai;
// Generated Oct 22, 2020 9:02:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Orderr generated by hbm2java
 */
public class Orderr  implements java.io.Serializable {


     private Long id;
     private Acounts acounts;
     private String tanggal;
     private int nomeja;
     private int total;
     private Set<Orderdetails> orderdetailses = new HashSet<Orderdetails>(0);

    public Orderr() {
    }

	
    public Orderr(Acounts acounts, String tanggal, int nomeja, int total) {
        this.acounts = acounts;
        this.tanggal = tanggal;
        this.nomeja = nomeja;
        this.total = total;
    }
    public Orderr(Acounts acounts, String tanggal, int nomeja, int total, Set<Orderdetails> orderdetailses) {
       this.acounts = acounts;
       this.tanggal = tanggal;
       this.nomeja = nomeja;
       this.total = total;
       this.orderdetailses = orderdetailses;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Acounts getAcounts() {
        return this.acounts;
    }
    
    public void setAcounts(Acounts acounts) {
        this.acounts = acounts;
    }
    public String getTanggal() {
        return this.tanggal;
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public int getNomeja() {
        return this.nomeja;
    }
    
    public void setNomeja(int nomeja) {
        this.nomeja = nomeja;
    }
    public int getTotal() {
        return this.total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    public Set<Orderdetails> getOrderdetailses() {
        return this.orderdetailses;
    }
    
    public void setOrderdetailses(Set<Orderdetails> orderdetailses) {
        this.orderdetailses = orderdetailses;
    }




}

