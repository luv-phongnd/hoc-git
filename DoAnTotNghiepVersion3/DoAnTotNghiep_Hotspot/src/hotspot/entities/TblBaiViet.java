package hotspot.entities;

import java.util.Date;

import javax.servlet.http.Part;

/**
 * @author duyphong170195
 *
 */
public class TblBaiViet {
	private int idBaiViet;
	private String moTa;
	private String noiDung;
	private byte[] imgBaiViet;
	private Date ngayDangBai;
	private int soLuotThich;
	private String huyenXa;
	private int idTinhThanh;
	private int idTaiKhoan;
	private String tenDiaDiem;
	private String pathImage;
	private Part part;
	private String biDanh;
	private String tieuDe;
	public int getIdBaiViet() {
		return idBaiViet;
	}
	public void setIdBaiViet(int idBaiViet) {
		this.idBaiViet = idBaiViet;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public byte[] getImgBaiViet() {
		return imgBaiViet;
	}
	public void setImgBaiViet(byte[] imgBaiViet) {
		this.imgBaiViet = imgBaiViet;
	}
	public Date getNgayDangBai() {
		return ngayDangBai;
	}
	public void setNgayDangBai(Date ngayDangBai) {
		this.ngayDangBai = ngayDangBai;
	}
	public int getSoLuotThich() {
		return soLuotThich;
	}
	public void setSoLuotThich(int soLuotThich) {
		this.soLuotThich = soLuotThich;
	}
	public String getHuyenXa() {
		return huyenXa;
	}
	public void setHuyenXa(String huyenXa) {
		this.huyenXa = huyenXa;
	}
	public int getIdTinhThanh() {
		return idTinhThanh;
	}
	public void setIdTinhThanh(int idTinhThanh) {
		this.idTinhThanh = idTinhThanh;
	}
	public int getIdTaiKhoan() {
		return idTaiKhoan;
	}
	public void setIdTaiKhoan(int idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}
	public String getTenDiaDiem() {
		return tenDiaDiem;
	}
	public void setTenDiaDiem(String tenDiaDiem) {
		this.tenDiaDiem = tenDiaDiem;
	}
	public String getPathImage() {
		return pathImage;
	}
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
		this.pathImage = extractFileName(part);
		System.out.println("pathImage = "+ pathImage);
	}	
	public String getBiDanh() {
		return biDanh;
	}
	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	/*File name of the upload file is included in content-disposition header like this:
	 form-data; name="dataFile"; filename = "PHOTO.JPG"*/
	private String extractFileName(Part part){
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String s: items){
			if(s.trim().startsWith("filename")){
				return s.substring(s.indexOf("=") +2, s.length() -1);
			}
		}
		
		return "";
	}
	
}	
