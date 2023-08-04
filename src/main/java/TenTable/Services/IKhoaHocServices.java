package TenTable.Services;

import TenTable.Model.KhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKhoaHocServices {
    public ObRespon<KhoaHoc> themKhoaHoc(KhoaHoc khoaHoc);
    public ObRespon<KhoaHoc> suaKhoaHoc(KhoaHoc khoaHoc);
    public ObRespon<KhoaHoc> xoaKhoaHoc(int khoaHocID);
    public List<KhoaHoc> hienThi();
    public List<KhoaHoc> timKiem(String keyWord);
    public Page<KhoaHoc> timKiemPhanTrang(String keyWord, Pageable pageable);
}
