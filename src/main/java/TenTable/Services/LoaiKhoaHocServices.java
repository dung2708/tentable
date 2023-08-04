package TenTable.Services;

import TenTable.Model.LoaiKhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiKhoaHocServices implements ILoaiKhoaHocServices{
    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Override
    public ObRespon<LoaiKhoaHoc> themLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        ObRespon<LoaiKhoaHoc> themKhoaHoc = new ObRespon<>();
        loaiKhoaHocRepo.save(loaiKhoaHoc);
        themKhoaHoc.setMess("them thanh cong");
        themKhoaHoc.setData(loaiKhoaHoc);
        themKhoaHoc.setStt(0);
        return themKhoaHoc;
    }
    @Override
    public ObRespon<LoaiKhoaHoc> xoaLoaiKhoaHoc(int loaiKhoaHocID) {
        ObRespon<LoaiKhoaHoc> xoaLoaiKhoaHoc = new ObRespon<>();
        Optional<LoaiKhoaHoc> loai = loaiKhoaHocRepo.findById(loaiKhoaHocID);
        if (loai.isEmpty()) {
            xoaLoaiKhoaHoc.setMess("loai khoa hoc khong ton tai");
            return xoaLoaiKhoaHoc;
        }
        LoaiKhoaHoc loaiKhoaHoc = loai.get();
        loaiKhoaHocRepo.delete(loaiKhoaHoc);
        xoaLoaiKhoaHoc.setMess("xoa thanh cong");
        xoaLoaiKhoaHoc.setData(loaiKhoaHoc);
        xoaLoaiKhoaHoc.setStt(0);
        return xoaLoaiKhoaHoc;
    }
    @Override
    public ObRespon<LoaiKhoaHoc> suaLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        ObRespon<LoaiKhoaHoc> suaLoaiKhoaHoc = new ObRespon<>();
        Optional<LoaiKhoaHoc> loai = loaiKhoaHocRepo.findById(loaiKhoaHoc.getLoaiKhoaHocID());
        if (loai.isEmpty()) {
            suaLoaiKhoaHoc.setMess("loai khoa hoc khong ton tai");
            return suaLoaiKhoaHoc;
        }
        LoaiKhoaHoc lKhoaHoc = loai.get();
        lKhoaHoc.setTenLoaiKhoaHoc(loaiKhoaHoc.getTenLoaiKhoaHoc());
        loaiKhoaHocRepo.save(lKhoaHoc);
        suaLoaiKhoaHoc.setMess("sua thanh cong");
        suaLoaiKhoaHoc.setData(loaiKhoaHoc);
        suaLoaiKhoaHoc.setStt(0);
        return suaLoaiKhoaHoc;
    }
    @Override
    public List<LoaiKhoaHoc> hienThi() {
        return loaiKhoaHocRepo.findAll();
    }
}
