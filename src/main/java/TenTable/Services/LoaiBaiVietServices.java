package TenTable.Services;

import TenTable.Model.LoaiBaiViet;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.LoaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiBaiVietServices implements ILoaiBaiVietServices{
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Override
    public ObRespon<LoaiBaiViet> themLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        ObRespon<LoaiBaiViet> themLoaiBaiViet = new ObRespon<>();
        loaiBaiVietRepo.save(loaiBaiViet);
        themLoaiBaiViet.setMess("Them thanh cong");
        themLoaiBaiViet.setData(loaiBaiViet);
        return themLoaiBaiViet;
    }
    @Override
    public ObRespon<LoaiBaiViet> suaLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        ObRespon<LoaiBaiViet> suaLoaiBaiViet = new ObRespon<>();
        Optional<LoaiBaiViet> loaibv = loaiBaiVietRepo.findById(loaiBaiViet.getLoaiBaiVietID());
        if (loaibv.isEmpty()) {
            suaLoaiBaiViet.setMess("Loai bai viet khong ton tai");
            return suaLoaiBaiViet;
        }
        LoaiBaiViet lbv = loaibv.get();
        lbv.setTenLoaiBaiViet(loaiBaiViet.getTenLoaiBaiViet());
        loaiBaiVietRepo.save(lbv);
        suaLoaiBaiViet.setMess("Sua thanh cong");
        suaLoaiBaiViet.setData(lbv);
        return suaLoaiBaiViet;
    }
    @Override
    public ObRespon<LoaiBaiViet> xoaLoaiBaiViet(int loaiBaiVietId) {
        ObRespon<LoaiBaiViet> xoaLoaiBaiViet = new ObRespon<>();
        Optional<LoaiBaiViet> loaibv = loaiBaiVietRepo.findById(loaiBaiVietId);
        if (loaibv.isEmpty()) {
            xoaLoaiBaiViet.setMess("Loai bai viet khong ton tai");
            return xoaLoaiBaiViet;
        }
        LoaiBaiViet lbv = loaibv.get();
        loaiBaiVietRepo.delete(lbv);
        xoaLoaiBaiViet.setMess("Xoa thanh cong");
        xoaLoaiBaiViet.setData(lbv);
        return xoaLoaiBaiViet;
    }

    @Override
    public List<LoaiBaiViet> hienThi() {
        return loaiBaiVietRepo.findAll();
    }

}
