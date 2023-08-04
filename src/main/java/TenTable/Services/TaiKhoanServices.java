package TenTable.Services;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.QuyenHan;
import TenTable.Model.TaiKhoan;
import TenTable.Repository.QuyenHanRepo;
import TenTable.Repository.TaiKhoanRepo;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanServices implements ITaiKhoanServices{
    @Autowired
    private TaiKhoanRepo taiKhoanRepo;
    @Autowired
    private QuyenHanRepo quyenHanRepo;
    @Override
    public ObRespon<TaiKhoan> themTaiKhoan(TaiKhoan taiKhoan) {
        ObRespon<TaiKhoan> themTaiKhoan = new ObRespon<>();
        int quyenHanID = taiKhoan.getQuyenHanID();
        String kiemTra = checkTaiKhoan(taiKhoan.getTaiKhoan());
        if (!kiemTra.equals("Ten tai khoan hop le")) {
            themTaiKhoan.setMess("Tai khoan da ton tai");
            return themTaiKhoan;
        }
        Optional<QuyenHan> quyen = quyenHanRepo.findById(quyenHanID);
        if (quyen.isEmpty()) {
            themTaiKhoan.setMess("Quyen han khong ton tai");
            return themTaiKhoan;
        }
        if (!strongPassword(taiKhoan.getMatKhau())) {
            themTaiKhoan.setMess("Mat khau phai co so va ky tu dac biet");
            return themTaiKhoan;
        }
        QuyenHan quyenHan = quyen.get();
        taiKhoan.setQuyenHan(quyenHan);
        taiKhoanRepo.save(taiKhoan);
        themTaiKhoan.setMess("Them thanh cong");
        themTaiKhoan.setData(taiKhoan);
        return themTaiKhoan;
    }
    @Override
    public ObRespon<TaiKhoan> suaTaiKhoan(TaiKhoan taiKhoanSua) {
        ObRespon<TaiKhoan> suaTaiKhoan = new ObRespon<>();
        Optional<TaiKhoan> tk = taiKhoanRepo.findById(taiKhoanSua.getTaiKhoanID());
        if (tk.isEmpty()) {
            suaTaiKhoan.setMess("Tai khoan khong ton tai");
            return suaTaiKhoan;
        }
        Optional<QuyenHan> quyen = quyenHanRepo.findById(taiKhoanSua.getQuyenHanID());
        if (quyen.isEmpty()) {
            suaTaiKhoan.setMess("Quyen han khong ton tai");
            return suaTaiKhoan;
        }
        if (!strongPassword(taiKhoanSua.getMatKhau())) {
            suaTaiKhoan.setMess("Mat khau phai co so va ky tu dac biet");
            return suaTaiKhoan;
        }
        TaiKhoan tkCu = tk.get();
        if (!taiKhoanSua.getTaiKhoan().equals(tkCu.getTaiKhoan())) {
            String check = checkTaiKhoan(taiKhoanSua.getTaiKhoan());
            if (!check.equals("Ten tai khoan hop le")) {
                suaTaiKhoan.setMess("Tai khoan da ton tai");
                return suaTaiKhoan;
            }
            tkCu.setTaiKhoan(taiKhoanSua.getTaiKhoan());
        }

        QuyenHan quyenHan = quyen.get();
        tkCu.setQuyenHan(quyenHan);
        tkCu.setMatKhau(taiKhoanSua.getMatKhau());
        tkCu.setTenNguoiDung(taiKhoanSua.getTenNguoiDung());
        taiKhoanSua.setQuyenHan(quyenHan);
        taiKhoanRepo.save(tkCu);
        suaTaiKhoan.setMess("Sua thanh cong");
        suaTaiKhoan.setData(tkCu);
        return suaTaiKhoan;
    }
    @Override
    public ObRespon<TaiKhoan> xoaTaiKhoan(int taiKhoanId) {
        ObRespon<TaiKhoan> xoaTaiKhoan = new ObRespon<>();
        Optional<TaiKhoan> tk = taiKhoanRepo.findById(taiKhoanId);
        if (tk.isEmpty()) {
            xoaTaiKhoan.setMess("Tai khoan khong ton tai");
            return xoaTaiKhoan;
        }
        TaiKhoan taiKhoan = tk.get();
        taiKhoanRepo.delete(taiKhoan);
        return xoaTaiKhoan;
    }
    @Override
    public List<TaiKhoan> timKiem(String tenTK) {
        return taiKhoanRepo.findByTaiKhoanContaining(tenTK);
    }

    public boolean strongPassword(String password) {
        PasswordValidator validator = new PasswordValidator(
                new CharacterRule(EnglishCharacterData.Digit, 1),     // Có ít nhất 1 chữ số
                new CharacterRule(EnglishCharacterData.Special, 1)    // Có ít nhất 1 ký tự đặc biệt
        );

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }

    public String checkTaiKhoan(String ten) {
        List<TaiKhoan> taiKhoans = taiKhoanRepo.findAll();
        for (TaiKhoan tk : taiKhoans) {
            if (tk.getTaiKhoan().equals(ten)) {
                return "Ten tai khoan da ton tai";
            }
        }
        return "Ten tai khoan hop le";
    }

}
