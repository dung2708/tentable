package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.QuyenHan;

import java.util.List;

public interface IQuyenHanServices {
    public ObRespon<QuyenHan> themQuyenHan(QuyenHan quyenHan);
    public ObRespon<QuyenHan> suaQuyenHan(QuyenHan quyenHanSua);
    public ObRespon<QuyenHan> xoaQuyenHan(int quyenHanId);
    public List<QuyenHan> layQuyenHan();
}
