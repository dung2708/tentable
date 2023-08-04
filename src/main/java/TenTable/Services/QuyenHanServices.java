package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.QuyenHan;
import TenTable.Repository.QuyenHanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuyenHanServices implements IQuyenHanServices{
    @Autowired
    private QuyenHanRepo quyenHanRepo;
    @Override
    public ObRespon<QuyenHan> themQuyenHan(QuyenHan quyenHan) {
        ObRespon<QuyenHan> themQuyenHan = new ObRespon<QuyenHan>();
        quyenHanRepo.save(quyenHan);
        themQuyenHan.setMess("Them thanh cong");
        themQuyenHan.setData(quyenHan);
        return themQuyenHan;
    }
    @Override
    public ObRespon<QuyenHan> suaQuyenHan(QuyenHan quyenHanSua) {
        ObRespon<QuyenHan> suaQuyenHan = new ObRespon<>();
        Optional<QuyenHan> quyenHan = quyenHanRepo.findById(quyenHanSua.getQuyenHanID());
        if (quyenHan.isEmpty()) {
            suaQuyenHan.setMess("Quyen han khong ton tai");
            return suaQuyenHan;
        }
        QuyenHan qh = quyenHan.get();
        qh.setTenQuyenHan(quyenHanSua.getTenQuyenHan());
        quyenHanRepo.save(qh);
        suaQuyenHan.setMess("Sua thanh cong");
        suaQuyenHan.setData(qh);
        return suaQuyenHan;
    }
    @Override
    public ObRespon<QuyenHan> xoaQuyenHan(int quyenHanId) {
        ObRespon<QuyenHan> xoaQuyenHan = new ObRespon<>();
        Optional<QuyenHan> quyenHan = quyenHanRepo.findById(quyenHanId);
        if (quyenHan.isEmpty()) {
            xoaQuyenHan.setMess("Quyen han khong ton tai");
            return xoaQuyenHan;
        }
        QuyenHan qh = quyenHan.get();
        quyenHanRepo.delete(qh);
        xoaQuyenHan.setMess("Xoa thanh cong");
        xoaQuyenHan.setData(qh);
        return xoaQuyenHan;
    }
    @Override
    public List<QuyenHan> layQuyenHan() {
        return quyenHanRepo.findAll();
    }
}
