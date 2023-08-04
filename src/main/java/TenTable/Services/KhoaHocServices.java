package TenTable.Services;

import TenTable.Model.KhoaHoc;
import TenTable.Model.LoaiKhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.KhoaHocRepo;
import TenTable.Repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocServices implements IKhoaHocServices{
    @Autowired
    private KhoaHocRepo khoaHocRepo;
    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Override
    public ObRespon<KhoaHoc> themKhoaHoc(KhoaHoc khoaHoc) {
        ObRespon<KhoaHoc> themKhoaHoc = new ObRespon<>();
        Optional<LoaiKhoaHoc> loaiKH = loaiKhoaHocRepo.findById(khoaHoc.getKhoaHocID());
        if (loaiKH.isEmpty()) {
            themKhoaHoc.setMess("Loai khoa hoc khong ton tai.");
            return themKhoaHoc;
        }
        LoaiKhoaHoc loaiKhoaHoc = loaiKH.get();
        khoaHoc.setLoaiKhoaHoc(loaiKhoaHoc);
        khoaHocRepo.save(khoaHoc);
        themKhoaHoc.setMess("Them khoa hoc thanh cong.");
        themKhoaHoc.setData(khoaHoc);
        return themKhoaHoc;
    }
    @Override
    public ObRespon<KhoaHoc> suaKhoaHoc(KhoaHoc khoaHoc) {
        ObRespon<KhoaHoc> suaKhoaHoc = new ObRespon<>();
        Optional<KhoaHoc> kh = khoaHocRepo.findById(khoaHoc.getKhoaHocID());
        if (kh.isPresent()) {
            Optional<LoaiKhoaHoc> loai = loaiKhoaHocRepo.findById(khoaHoc.getLoaiKhoaHocID());
            if (loai.isPresent()) {
                KhoaHoc kHoc = kh.get();
                LoaiKhoaHoc loaiKhoaHoc = loai.get();
                kHoc.setLoaiKhoaHoc(loaiKhoaHoc);
                kHoc.setGioiThieu(khoaHoc.getGioiThieu());
                kHoc.setHocPhi(khoaHoc.getHocPhi());
                kHoc.setNoiDung(khoaHoc.getNoiDung());
                kHoc.setSoHocVien(khoaHoc.getSoHocVien());
                kHoc.setSoLuongMon(khoaHoc.getSoLuongMon());
                kHoc.setTenKhoaHoc(khoaHoc.getTenKhoaHoc());
                kHoc.setThoiGianHoc(khoaHoc.getThoiGianHoc());
                khoaHocRepo.save(kHoc);
                suaKhoaHoc.setMess("sua thanh cong");
                suaKhoaHoc.setData(kHoc);
                return suaKhoaHoc;
            } else {
                suaKhoaHoc.setMess("loai khoa hoc khong ton tai");
                return suaKhoaHoc;
            }
        }
        suaKhoaHoc.setMess("khoa hoc khong ton tai");
        return suaKhoaHoc;
    }
    @Override
    public ObRespon<KhoaHoc> xoaKhoaHoc(int khoaHocID) {
        ObRespon<KhoaHoc> xoaKhoaHoc = new ObRespon<>();
        Optional<KhoaHoc> kh = khoaHocRepo.findById(khoaHocID);
        if (kh.isEmpty()) {
            xoaKhoaHoc.setMess("khoa hoc khong ton tai");
            return xoaKhoaHoc;
        }
        KhoaHoc khoaHoc = kh.get();
        khoaHocRepo.delete(khoaHoc);
        xoaKhoaHoc.setData(khoaHoc);
        xoaKhoaHoc.setMess("xoa thanh cong");
        return xoaKhoaHoc;
    }

    @Override
    public List<KhoaHoc> hienThi() {
        return khoaHocRepo.findAll();
    }
    @Override
    public List<KhoaHoc> timKiem(String keyWord) {
        return khoaHocRepo.findByTenKhoaHocContaining(keyWord);
    }
    @Override
    public Page<KhoaHoc> timKiemPhanTrang(String keyWord, Pageable pageable) {
        return khoaHocRepo.findByTenKhoaHocContaining(keyWord, pageable);
    }

}
