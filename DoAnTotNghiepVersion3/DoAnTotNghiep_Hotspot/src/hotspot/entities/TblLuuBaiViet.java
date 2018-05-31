package hotspot.entities;

import java.util.Date;

public class TblLuuBaiViet {
	private int idTaiKhoan;
	private int idBaiViet;
	private Date ngayLuu;
	
	public int getIdTaiKhoan() {
		return idTaiKhoan;
	}
	public void setIdTaiKhoan(int idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}
	public int getIdBaiViet() {
		return idBaiViet;
	}
	public void setIdBaiViet(int idBaiViet) {
		this.idBaiViet = idBaiViet;
	}
	public Date getNgayLuu() {
		return ngayLuu;
	}
	public void setNgayLuu(Date ngayLuu) {
		this.ngayLuu = ngayLuu;
	}
	
}
