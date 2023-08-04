package TenTable.Controller;

import TenTable.Model.LoaiBaiViet;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.LoaiBaiVietServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "loaibaiviet")
public class LoaiBaiVietController {
    @Autowired
    private LoaiBaiVietServices loaiBaiVietServices;

    @RequestMapping(value = "themloaibaiviet", method = RequestMethod.POST)
    private ObRespon<LoaiBaiViet> themLoaiBaiViet(@RequestBody String loaiBaiViet) {
        Gson gson = new Gson();
        LoaiBaiViet loai = gson.fromJson(loaiBaiViet, LoaiBaiViet.class);
        return loaiBaiVietServices.themLoaiBaiViet(loai);
    }

    @RequestMapping(value = "sualoaibaiviet", method = RequestMethod.PUT)
    private ObRespon<LoaiBaiViet> suaLoaiBaiViet(@RequestBody String loaiBaiVietSua) {
        Gson gson = new Gson();
        LoaiBaiViet loaibv = gson.fromJson(loaiBaiVietSua, LoaiBaiViet.class);
        return loaiBaiVietServices.suaLoaiBaiViet(loaibv);
    }

    @RequestMapping(value = "xoaloaibaiviet", method = RequestMethod.DELETE)
    public ObRespon<LoaiBaiViet> xoaLoaiBaiViet(@RequestParam int loaiBaiVietId) {
        return loaiBaiVietServices.xoaLoaiBaiViet(loaiBaiVietId);
    }

    @RequestMapping(value = "hienthiloaibaiviet", method = RequestMethod.GET)
    public List<LoaiBaiViet> hienThi() {
        return loaiBaiVietServices.hienThi();
    }

}
