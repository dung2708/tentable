package TenTable.Services;

import TenTable.Model.*;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DangKyHocServices implements IDangKyHocServices{
    @Autowired
    private DangKyHocRepo dangKyHocRepo;
    @Autowired
    private HocVienRePo hocVienRePo;
    @Autowired
    private KhoaHocRepo khoaHocRepo;
    @Autowired
    private TaiKhoanRepo taiKhoanRepo;
    @Autowired
    private TinhTrangHocRepo tinhTrangHocRepo;
    @Override
    public ObRespon<DangKyHoc> themDangKyHoc(DangKyHoc dangKyHoc) {
        ObRespon<DangKyHoc> themDangKyHoc = new ObRespon<>();
        int kiemTra = kiemTra(dangKyHoc);
        if (kiemTra != 0) {
            themDangKyHoc.setMess(mess(kiemTra));
            return themDangKyHoc;
        }
        if (dangKyHoc.getTinhTrangHocID() != 1) {
            themDangKyHoc.setMess("Dang ky moi phai cho duyet");
            return themDangKyHoc;
        }
        HocVien hv = hocVienRePo.findById(dangKyHoc.getHocVienID()).get();
        KhoaHoc kh = khoaHocRepo.findById(dangKyHoc.getKhoaHocID()).get();
        TaiKhoan tk = taiKhoanRepo.findById(dangKyHoc.getTaiKhoanID()).get();
        TinhTrangHoc tth = tinhTrangHocRepo.findById(dangKyHoc.getTinhTrangHocID()).get();
        kh.setSoHocVien(kh.getSoHocVien() + 1);
        khoaHocRepo.save(kh);
        dangKyHoc.setHocVien(hv);
        dangKyHoc.setKhoaHoc(kh);
        dangKyHoc.setTaiKhoan(tk);
        dangKyHoc.setTinhTrangHoc(tth);
        dangKyHoc.setNgayDangKy(LocalDate.now());
        dangKyHocRepo.save(dangKyHoc);
        themDangKyHoc.setMess("them thanh cong");
        themDangKyHoc.setData(dangKyHoc);
        return themDangKyHoc;
    }
    @Override
    public ObRespon<DangKyHoc> suaDangKyHoc(DangKyHoc dangKyHoc) {
        ObRespon<DangKyHoc> suaDangKyHoc = new ObRespon<>();
        int kiemTra = kiemTra(dangKyHoc);
        if (kiemTra != 0) {
            suaDangKyHoc.setMess(mess(kiemTra));
            return suaDangKyHoc;
        }
        Optional<DangKyHoc> dkh = dangKyHocRepo.findById(dangKyHoc.getDangKyHocID());
        if (dkh.isEmpty()) {
            suaDangKyHoc.setMess("Dang ky hoc khong ton tai");
            return suaDangKyHoc;
        }
        DangKyHoc dangKyHocCu = dkh.get();
        HocVien hv = hocVienRePo.findById(dangKyHoc.getHocVienID()).get();
        KhoaHoc kh = khoaHocRepo.findById(dangKyHoc.getKhoaHocID()).get();
        TaiKhoan tk = taiKhoanRepo.findById(dangKyHoc.getTaiKhoanID()).get();
        TinhTrangHoc tth = tinhTrangHocRepo.findById(dangKyHoc.getTinhTrangHocID()).get();
        if (dangKyHocCu.getTinhTrangHocID() == 4) {
            suaDangKyHoc.setMess("dang ky hoc chua hoan thanh, khong the cap nhat trang thai khac");
            return suaDangKyHoc;
        }
        if (tth.getTinhTrangHocID() == 2) {
            dangKyHocCu.setNgayBatDau(LocalDate.now());
            dangKyHocCu.setNgayKetThuc(LocalDate.now().plusMonths(kh.getThoiGianHoc()));
        }
        if (dangKyHocCu.getKhoaHocID() != dangKyHoc.getKhoaHocID()) {
            KhoaHoc khCu = dangKyHocCu.getKhoaHoc();
            khCu.setSoHocVien(khCu.getSoHocVien() - 1);
            kh.setSoHocVien(kh.getSoHocVien() + 1);
            dangKyHocCu.setKhoaHoc(kh);
        }
        dangKyHocCu.setTinhTrangHoc(tth);
        dangKyHocCu.setHocVien(hv);
        dangKyHocCu.setTaiKhoan(tk);
        dangKyHocRepo.save(dangKyHocCu);
        suaDangKyHoc.setMess("Sua thanh cong");
        suaDangKyHoc.setData(dangKyHocCu);
        return suaDangKyHoc;
    }
    @Override
    public ObRespon<DangKyHoc> xoaDangKyHoc(int dangKyHocId) {
        ObRespon<DangKyHoc> xoaDangKyHoc = new ObRespon<>();
        Optional<DangKyHoc> dkh = dangKyHocRepo.findById(dangKyHocId);
        if (dkh.isEmpty()) {
            xoaDangKyHoc.setMess("Dang ky hoc khong ton tai");
            return xoaDangKyHoc;
        }
        DangKyHoc dangKyHoc = dkh.get();
        KhoaHoc kh = dangKyHoc.getKhoaHoc();
        kh.setSoHocVien(kh.getSoHocVien() - 1);
        khoaHocRepo.save(kh);
        dangKyHocRepo.delete(dangKyHoc);
        xoaDangKyHoc.setData(dangKyHoc);
        xoaDangKyHoc.setMess("Xoa thanh cong");
        return xoaDangKyHoc;
    }
    @Override
    public List<DangKyHoc> layDsDangKyHoc() {
        return dangKyHocRepo.findAll();
    }


    private int kiemTra(DangKyHoc dangKyHoc) {
        int hocVienID = dangKyHoc.getHocVienID();
        int khoaHocID = dangKyHoc.getKhoaHocID();
        int taiKhoanID = dangKyHoc.getTaiKhoanID();
        int tinhTrangHocID = dangKyHoc.getTinhTrangHocID();
        if (hocVienRePo.findById(hocVienID).isEmpty()) {
            return 1;
        }
        if (taiKhoanRepo.findById(taiKhoanID).isEmpty()) {
            return 2;
        }
        if (khoaHocRepo.findById(khoaHocID).isEmpty()) {
            return 3;
        }
        if (tinhTrangHocRepo.findById(tinhTrangHocID).isEmpty()) {
            return 4;
        }
        return 0;
    }

    private String mess(int num) {
        if (num == 1) {
            return "Hoc vien khong ton tai";
        } else if (num == 2) {
            return "Tai khoan khong ton tai";
        } else if (num == 3) {
            return "Khoa hoc khong ton tai";
        }
        return "Tinh trang hoc khong ton tai";
    }
}
