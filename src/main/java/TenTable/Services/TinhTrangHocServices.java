package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TinhTrangHoc;
import TenTable.Repository.TinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinhTrangHocServices implements ITinhTrangHocServices {
    @Autowired
    private TinhTrangHocRepo tinhTrangHocRepo;

    @Override
    public ObRespon<TinhTrangHoc> themTinhTrangHoc(TinhTrangHoc tinhTrangHoc) {
        ObRespon<TinhTrangHoc> themTinhTrangHoc = new ObRespon<>();
        tinhTrangHocRepo.save(tinhTrangHoc);
        themTinhTrangHoc.setMess("Them thanh cong");
        themTinhTrangHoc.setData(tinhTrangHoc);
        return themTinhTrangHoc;
    }
    @Override
    public ObRespon<TinhTrangHoc> suaTinhTrangHoc(TinhTrangHoc tinhTrangHocSua) {
        ObRespon<TinhTrangHoc> suaTinhTrangHoc = new ObRespon<>();
        Optional<TinhTrangHoc> tinhTrang = tinhTrangHocRepo.findById(tinhTrangHocSua.getTinhTrangHocID());
        if (tinhTrang.isEmpty()) {
            suaTinhTrangHoc.setMess("Tinh trang hoc nay khong ton tai");
            return suaTinhTrangHoc;
        }
        TinhTrangHoc tinhTrangHoc = tinhTrang.get();
        tinhTrangHoc.setTenTinhTrangHoc(tinhTrangHocSua.getTenTinhTrangHoc());
        tinhTrangHocRepo.save(tinhTrangHoc);
        suaTinhTrangHoc.setMess("Sua thanh cong");
        suaTinhTrangHoc.setData(tinhTrangHoc);
        return suaTinhTrangHoc;
    }
    @Override
    public ObRespon<TinhTrangHoc> xoaTinhTrangHoc(int tinhTrangHocId) {
        ObRespon<TinhTrangHoc> xoaTinhTrangHoc = new ObRespon<>();
        Optional<TinhTrangHoc> tinhTrang = tinhTrangHocRepo.findById(tinhTrangHocId);
        if (tinhTrang.isEmpty()) {
            xoaTinhTrangHoc.setMess("Tinh trang hoc khong ton tai");
            return xoaTinhTrangHoc;
        }
        TinhTrangHoc tinhTrangHoc = tinhTrang.get();
        tinhTrangHocRepo.delete(tinhTrangHoc);
        xoaTinhTrangHoc.setData(tinhTrangHoc);
        xoaTinhTrangHoc.setMess("Xoa thanh cong");
        return xoaTinhTrangHoc;
    }
    @Override
    public List<TinhTrangHoc> layTinhTrangHoc() {
        return tinhTrangHocRepo.findAll();
    }


}
