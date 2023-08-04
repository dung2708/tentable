package TenTable.Services;

import TenTable.Model.HocVien;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.HocVienRePo;
import TenTable.Repository.KhoaHocRepo;
import TenTable.Repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HocVienServices implements IHocVienServices{
    @Autowired
    private HocVienRePo hocVienRePo;
    @Autowired
    private KhoaHocRepo khoaHocRepo;
    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Override
    public ObRespon<HocVien> themHocVien(HocVien hocVien) {
        ObRespon<HocVien> themHocVien = new ObRespon<>();
        int kiemTra = kiemTra(hocVien);
        if (kiemTra == 1) {
            themHocVien.setMess("So dien thoai da ton tai.");
            return themHocVien;
        } else if (kiemTra == 2) {
            themHocVien.setMess("Email da ton tai.");
            return themHocVien;
        }
        hocVienRePo.save(hocVien);
        themHocVien.setMess("Them thanh cong.");
        themHocVien.setData(hocVien);
        return themHocVien;
    }
    @Override
    public ObRespon<HocVien> suaHocVien(HocVien hocVienSua) {
        ObRespon<HocVien> suaHocVien = new ObRespon<>();
        Optional<HocVien> hvop = hocVienRePo.findById(hocVienSua.getHocVienID());
        if (hvop.isEmpty()) {
            suaHocVien.setMess("Khong ton tai hoc vien nay.");
            return suaHocVien;
        }
        HocVien hocVienCu = hvop.get();
        int kiemTra = kiemTra(hocVienSua);
        if (!hocVienSua.getSdt().equals(hocVienCu.getSdt())) {
            if (kiemTra == 1) {
                suaHocVien.setMess("So dien thoai da ton tai.");
                return suaHocVien;
            }
            hocVienCu.setSdt(hocVienSua.getSdt());
        }
        if (!hocVienSua.getEmail().equals(hocVienCu.getEmail())) {
            if (kiemTra == 2) {
                suaHocVien.setMess("Email da ton tai.");
                return suaHocVien;
            }
            hocVienCu.setEmail(hocVienSua.getEmail());
        }
        hocVienCu.setHoTen(hocVienSua.getHoTen());
        hocVienCu.setNgaySinh(hocVienSua.getNgaySinh());
        hocVienCu.setHinhAnh(hocVienSua.getHinhAnh());
        hocVienCu.setSoNha(hocVienSua.getSoNha());
        hocVienCu.setPhuongXa(hocVienSua.getPhuongXa());
        hocVienCu.setQuanHuyen(hocVienSua.getQuanHuyen());
        hocVienCu.setTinhThanh(hocVienSua.getTinhThanh());
        hocVienRePo.save(hocVienCu);
        suaHocVien.setMess("Sua thanh cong");
        suaHocVien.setData(hocVienCu);
        return suaHocVien;
    }
    @Override
    public ObRespon<HocVien> xoaHocVien(int hocVienId) {
        ObRespon<HocVien> xoahocVien = new ObRespon<>();
        Optional<HocVien> hvop = hocVienRePo.findById(hocVienId);
        if (hvop.isEmpty()) {
            xoahocVien.setMess("Hoc vien khong ton tai.");
            return xoahocVien;
        }
        HocVien hv = hvop.get();
        hocVienRePo.delete(hv);
        xoahocVien.setMess("Xoa thanh cong.");
        return xoahocVien;
    }
    @Override
    public List<HocVien> layDsHocVien() {
        return hocVienRePo.findAll();
    }
    @Override
    public List<HocVien> timTheoTenVaEmail(String ten, String email) {
        return hocVienRePo.findByHoTenContainingOrEmailContaining(ten, email);
    }

    public int kiemTra(HocVien hocVien) {
        List<HocVien> hvList = hocVienRePo.findAll();
        String sdt = hocVien.getSdt();
        String email = hocVien.getEmail();
        for (HocVien hv : hvList) {
            if (hv.getEmail().equals(email)) {
                return 2;
            }
            if (hv.getSdt().equals(sdt)) {
                return 1;
            }
        }
        return 0;
    }
}
