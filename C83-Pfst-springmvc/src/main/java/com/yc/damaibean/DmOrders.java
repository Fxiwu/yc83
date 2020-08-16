package com.yc.damaibean;

import java.sql.Timestamp;
import java.util.List;

public class DmOrders {
    private Integer id;

    private Double total;

    private Timestamp createtime;

    private Integer state;

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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

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