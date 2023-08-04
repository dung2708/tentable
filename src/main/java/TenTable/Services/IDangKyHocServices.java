package TenTable.Services;

import TenTable.Model.DangKyHoc;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface IDangKyHocServices {
    public ObRespon<DangKyHoc> themDangKyHoc(DangKyHoc dangKyHoc);
    public ObRespon<DangKyHoc> suaDangKyHoc(DangKyHoc dangKyHoc);
    public ObRespon<DangKyHoc> xoaDangKyHoc(int dangKyHocId);
    public List<DangKyHoc> layDsDangKyHoc();
}
