package TenTable.Services;

import TenTable.Model.LoaiKhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface ILoaiKhoaHocServices {
    public ObRespon<LoaiKhoaHoc> themLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc);
    public ObRespon<LoaiKhoaHoc> xoaLoaiKhoaHoc(int loaiKhoaHocID);
    public ObRespon<LoaiKhoaHoc> suaLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc);
    public List<LoaiKhoaHoc> hienThi();
}
