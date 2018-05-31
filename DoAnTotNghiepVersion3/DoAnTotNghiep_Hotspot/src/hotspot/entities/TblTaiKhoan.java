package hotspot.entities;

public class TblTaiKhoan {
	private int idTaiKhoan;
	private String tenTaiKhoan;
	private String email;
	private String soDienThoai;
	private String matKhau;
	private String matKhauXacNhan;
	private String salt;
	private String userRole;
	private String biDanh;
	
	public int getIdTaiKhoan() {
		return idTaiKhoan;
	}
	public void setIdTaiKhoan(int idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;		
	}
	public String getMatKhauXacNhan() {
		return matKhauXacNhan;
	}
	public void setMatKhauXacNhan(String matKhauXacNhan) {
		this.matKhauXacNhan = matKhauXacNhan;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getBiDanh() {
		return biDanh;
	}
	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}
	
	public void setDefaultValue(){
		this.tenTaiKhoan = "";
		this.matKhau = "";
		this.soDienThoai = "";
		this.email = "";
	}
}
