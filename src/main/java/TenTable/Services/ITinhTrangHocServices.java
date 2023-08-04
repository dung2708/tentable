package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TinhTrangHoc;

import java.util.List;

public interface ITinhTrangHocServices {
    public ObRespon<TinhTrangHoc> themTinhTrangHoc(TinhTrangHoc tinhTrangHoc);
    public ObRespon<TinhTrangHoc> suaTinhTrangHoc(TinhTrangHoc tinhTrangHocSua);
    public ObRespon<TinhTrangHoc> xoaTinhTrangHoc(int tinhTrangHocId);
    public List<TinhTrangHoc> layTinhTrangHoc();
}
