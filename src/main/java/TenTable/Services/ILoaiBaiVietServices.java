package TenTable.Services;

import TenTable.Model.LoaiBaiViet;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface ILoaiBaiVietServices {
    public ObRespon<LoaiBaiViet> themLoaiBaiViet(LoaiBaiViet loaiBaiViet);
    public ObRespon<LoaiBaiViet> suaLoaiBaiViet(LoaiBaiViet loaiBaiViet);
    public ObRespon<LoaiBaiViet> xoaLoaiBaiViet(int loaiBaiVietId);
    public List<LoaiBaiViet> hienThi();
}
