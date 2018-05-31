package hotspot.entities;

public class TblTinhThanh {
	private int idTinhThanh;
	private String tenTinhThanh;
	private byte[] imgTinhThanh;
	
	
	public TblTinhThanh() {}
	
	public int getIdTinhThanh() {
		return idTinhThanh;
	}
	public void setIdTinhThanh(int idTinhThanh) {
		this.idTinhThanh = idTinhThanh;
	}
	public String getTenTinhThanh() {
		return tenTinhThanh;
	}
	public void setTenTinhThanh(String tenTinhThanh) {
		this.tenTinhThanh = tenTinhThanh;
	}
	public byte[] getImgTinhThanh() {
		return imgTinhThanh;
	}
	public void setImgTinhThanh(byte[] imgTinhThanh) {
		this.imgTinhThanh = imgTinhThanh;
	}
	
	
}
