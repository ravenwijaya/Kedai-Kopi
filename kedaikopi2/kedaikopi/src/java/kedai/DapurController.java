/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedai;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;

/**
 *
 * @author x.x
 */
@Named(value = "dapurc")
@SessionScoped
public class DapurController implements Serializable {
  private DapurHelper helper;
  private Orderdetails orderdetails = null;
   List <Orderdetails> list=new ArrayList<Orderdetails>();

    public List<Orderdetails> getList() {
        
        return list;
    }

    public void setList(List<Orderdetails> list) {
        this.list = list;
    }
    /**
     * Creates a new instance of DapurController
     */
    public DapurController() {
         helper = new DapurHelper(); 
   
    }
     public List<Orderdetails> GetOrderDetailsBelum() {
         setList(helper.GetOrderDetailsbelum());
//         List <Orderdetails> list=new ArrayList<Orderdetails>();
//         list=helper.GetOrderDetailsbelum();
       
        return list;
    }   
        public List<Orderdetails> GetOrderDetailsSudah() {
             setList(helper.GetOrderDetailssudah());
//         List <Orderdetails> list=new ArrayList<Orderdetails>();
//         list=helper.GetOrderDetailssudah();
       
        return list;
    }   
     public String GetDate(){
        Date currentDate = new Date();  
        String dateToStr = DateFormat.getInstance().format(currentDate);  
        return dateToStr;
     }
     public void Update(long id){
//         System.out.println("aaaaaaaaaaaaaaaa"+id);
         helper.UpdateById(id);
//helper.up(id);
     }
     
}
