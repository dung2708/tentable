package TenTable.Services;

import TenTable.Model.ChuDe;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface IChuDeServices {
    public ObRespon<ChuDe> themChuDe(ChuDe chuDe);
    public ObRespon<ChuDe> suaChuDe(ChuDe chuDeSua);
    public ObRespon<ChuDe> xoaChuDe(int chuDeId);
    public List<ChuDe> hienThi();
}
