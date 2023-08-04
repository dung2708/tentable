package TenTable.Services;

import TenTable.Model.BaiViet;
import TenTable.Model.ObjectRespon.ObRespon;

import java.util.List;

public interface IBaiVietServices {
    public ObRespon<BaiViet> themBaiViet(BaiViet baiViet);
    public ObRespon<BaiViet> suaBaiViet(BaiViet baiViet);
    public ObRespon<BaiViet> xoaBaiViet(int baiVietId);
    public List<BaiViet> timKiemTheoTen(String tenBv);
    public List<BaiViet> layDsBaiViet();
}
