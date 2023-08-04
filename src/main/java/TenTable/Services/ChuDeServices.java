package TenTable.Services;

import TenTable.Model.ChuDe;
import TenTable.Model.LoaiBaiViet;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.ChuDeRepo;
import TenTable.Repository.LoaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ChuDeServices implements IChuDeServices{
    @Autowired
    private ChuDeRepo chuDeRepo;
    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;
    @Override
    public ObRespon<ChuDe> themChuDe(ChuDe chuDe) {
        ObRespon<ChuDe> themChuDe = new ObRespon<>();
        Optional<LoaiBaiViet> loaibv = loaiBaiVietRepo.findById(chuDe.getLoaiBaiVietID());
        if (loaibv.isEmpty()) {
            themChuDe.setMess("Loai bai viet khong ton tai");
            return themChuDe;
        }
        chuDe.setLoaiBaiViet(loaibv.get());
        chuDeRepo.save(chuDe);
        themChuDe.setMess("Them thanh cong");
        themChuDe.setData(chuDe);
        return themChuDe;
    }
    @Override
    public ObRespon<ChuDe> suaChuDe(ChuDe chuDeSua) {
        ObRespon<ChuDe> suaChuDe = new ObRespon<>();
        Optional<LoaiBaiViet> loaibv = loaiBaiVietRepo.findById(chuDeSua.getLoaiBaiVietID());
        if (loaibv.isEmpty()) {
            suaChuDe.setMess("Loai bai viet khong ton tai");
            return suaChuDe;
        }
        Optional<ChuDe> cd = chuDeRepo.findById(chuDeSua.getChuDeID());
        if (cd.isEmpty()) {
            suaChuDe.setMess("Chu de khong ton tai");
            return suaChuDe;
        }
        LoaiBaiViet loaiBaiViet = loaibv.get();
        ChuDe cdCu = cd.get();
        cdCu.setLoaiBaiViet(loaiBaiViet);
        cdCu.setNoiDung(chuDeSua.getNoiDung());
        cdCu.setTenChuDe(chuDeSua.getTenChuDe());
        chuDeRepo.save(cdCu);
        suaChuDe.setMess("Sua chu de thanh cong");
        suaChuDe.setData(cdCu);
        return suaChuDe;
    }
    @Override
    public ObRespon<ChuDe> xoaChuDe(int chuDeId) {
        ObRespon<ChuDe> xoaChuDe = new ObRespon<>();
        Optional<ChuDe> cd = chuDeRepo.findById(chuDeId);
        if (cd.isEmpty()) {
            xoaChuDe.setMess("Chu de khong ton tai");
            return xoaChuDe;
        }
        ChuDe chuDe = cd.get();
        chuDeRepo.delete(chuDe);
        xoaChuDe.setMess("Xoa thanh cong");
        xoaChuDe.setData(chuDe);
        return xoaChuDe;
    }
    @Override
    public List<ChuDe> hienThi() {
        return chuDeRepo.findAll();
    }

}
