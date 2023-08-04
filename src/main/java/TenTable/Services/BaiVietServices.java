package TenTable.Services;

import TenTable.Model.BaiViet;
import TenTable.Model.ChuDe;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TaiKhoan;
import TenTable.Repository.BaiVietRepo;
import TenTable.Repository.ChuDeRepo;
import TenTable.Repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BaiVietServices implements IBaiVietServices{
    @Autowired
    private BaiVietRepo baiVietRepo;
    @Autowired
    private ChuDeRepo chuDeRepo;
    @Autowired
    private TaiKhoanRepo taiKhoanRepo;

    @Override
    public ObRespon<BaiViet> themBaiViet(BaiViet baiViet) {
        ObRespon<BaiViet> themBaiViet = new ObRespon<>();
        baiViet.setThoiGianTao(LocalDate.now());
        Optional<ChuDe> cd = chuDeRepo.findById(baiViet.getChuDeID());
        if (cd.isEmpty()) {
            themBaiViet.setMess("Chu de khong ton tai");
            return themBaiViet;
        }
        Optional<TaiKhoan> tk = taiKhoanRepo.findById(baiViet.getTaiKhoanID());
        if (tk.isEmpty()) {
            themBaiViet.setMess("Tai khoan khong ton tai");
            return themBaiViet;
        }
        ChuDe chuDe = cd.get();
        TaiKhoan taiKhoan = tk.get();
        baiViet.setChuDe(chuDe);
        baiViet.setTaiKhoan(taiKhoan);
        baiVietRepo.save(baiViet);
        themBaiViet.setMess("Them thanh cong");
        themBaiViet.setData(baiViet);
        return themBaiViet;
    }
    @Override
    public ObRespon<BaiViet> suaBaiViet(BaiViet baiViet) {
        ObRespon<BaiViet> suaBaiViet = new ObRespon<>();
        baiViet.setThoiGianTao(LocalDate.now());
        Optional<BaiViet> bv = baiVietRepo.findById(baiViet.getBaiVietID());
        if (bv.isEmpty()) {
            suaBaiViet.setMess("Bai viet khong ton tai");
            return suaBaiViet;
        }
        Optional<ChuDe> cd = chuDeRepo.findById(baiViet.getChuDeID());
        if (cd.isEmpty()) {
            suaBaiViet.setMess("Chu de khong ton tai");
            return suaBaiViet;
        }
        Optional<TaiKhoan> tk = taiKhoanRepo.findById(baiViet.getTaiKhoanID());
        if (tk.isEmpty()) {
            suaBaiViet.setMess("Tai khoan khong ton tai");
            return suaBaiViet;
        }
        BaiViet bvCu = bv.get();
        ChuDe chuDe = cd.get();
        TaiKhoan taiKhoan = tk.get();
        bvCu.setChuDe(chuDe);
        bvCu.setTaiKhoan(taiKhoan);
        bvCu.setHinhAnh(baiViet.getHinhAnh());
        bvCu.setNoiDung(baiViet.getNoiDung());
        bvCu.setNoiDungNgan(baiViet.getNoiDungNgan());
        bvCu.setTenBaiViet(baiViet.getTenBaiViet());
        bvCu.setTenTacGia(baiViet.getTenTacGia());
        baiVietRepo.save(bvCu);
        suaBaiViet.setMess("sua thanh cong");
        suaBaiViet.setData(bvCu);
        return suaBaiViet;
    }
    @Override
    public ObRespon<BaiViet> xoaBaiViet(int baiVietId) {
        ObRespon<BaiViet> xoaBaiViet = new ObRespon<>();
        Optional<BaiViet> bv = baiVietRepo.findById(baiVietId);
        if (bv.isEmpty()) {
            xoaBaiViet.setMess("Bai viet khong ton tai");
            return xoaBaiViet;
        }
        BaiViet baiViet = bv.get();
        baiVietRepo.delete(baiViet);
        xoaBaiViet.setMess("Xoa thanh cong");
        xoaBaiViet.setData(baiViet);
        return xoaBaiViet;
    }
    @Override
    public List<BaiViet> timKiemTheoTen(String tenBv) {
        return baiVietRepo.findByTenBaiVietContaining(tenBv);
    }
    @Override
    public List<BaiViet> layDsBaiViet() {
        return baiVietRepo.findAll();
    }

}
