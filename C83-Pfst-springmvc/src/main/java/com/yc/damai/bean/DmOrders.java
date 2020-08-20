package com.yc.damai.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DmOrders {
    private Integer id;
     
    @NumberFormat(pattern = "#,###.00")
    private Double total;
//请求参数格式化注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //json格式化注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    
    private Timestamp createtime;

    private Integer state;

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		//将date值同步到createtime
		this.createtime=new Timestamp(date.getTime());
	}

	private Integer uid;

    private Integer aid;
    
    private List<DmOrderitem> dmOrderitem;
     
    

	public List<DmOrderitem> getDmOrderitem() {
		return dmOrderitem;
	}

	public void setDmOrderitem(List<DmOrderitem> dmOrderitem) {
		this.dmOrderitem = dmOrderitem;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

//    public Timestamp getCreatetime() {
//        return createtime;
//    }
//
//    public void setCreatetime(Timestamp createtime) {
//        this.createtime = createtime;
//    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}