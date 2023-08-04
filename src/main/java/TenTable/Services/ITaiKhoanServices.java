package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TaiKhoan;

import java.util.List;

public interface ITaiKhoanServices {
    public ObRespon<TaiKhoan> themTaiKhoan(TaiKhoan taiKhoan);
    public ObRespon<TaiKhoan> suaTaiKhoan(TaiKhoan taiKhoanSua);
    public ObRespon<TaiKhoan> xoaTaiKhoan(int taiKhoanId);
    public List<TaiKhoan> timKiem(String tenTK);

}
