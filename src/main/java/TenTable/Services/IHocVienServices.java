package TenTable.Services;

import TenTable.Model.HocVien;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface IHocVienServices {
    public ObRespon<HocVien> themHocVien(HocVien hocVien);
    public ObRespon<HocVien> suaHocVien(HocVien hocVienSua);
    public ObRespon<HocVien> xoaHocVien(int hocVienId);
    public List<HocVien> layDsHocVien();
    public List<HocVien> timTheoTenVaEmail(String ten, String email);

}
