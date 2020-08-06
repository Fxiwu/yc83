package bean;

public class Favorite implements java.io.Serializable {
                               //序列化接口
	private static final long serialVersionUID=1L;

	private int  fid;
	private String flabel;
	private String furl;
	private String fdesc;
	private String ftags;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFlabel() {
		return flabel;
	}
	public void setFlabel(String flabel) {
		this.flabel = flabel;
	}
	public String getFurl() {
		return furl;
	}
	public void setFurl(String furl) {
		this.furl = furl;
	}
	public String getFdesc() {
		return fdesc;
	}
	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
	public String getFtags() {
		return ftags;
	}
	public void setFtags(String ftags) {
		this.ftags = ftags;
	}
	@Override
	public String toString() {
		return "Favorite [fid=" + fid + ", flabel=" + flabel + ", furl=" + furl + ", fdesc=" + fdesc + ", ftags="
				+ ftags + "]";
	}
	
}
