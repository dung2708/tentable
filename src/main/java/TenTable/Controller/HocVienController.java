package TenTable.Controller;

import TenTable.Model.HocVien;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.HocVienServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "hocvien")
public class HocVienController {
    @Autowired
    private HocVienServices hocVienServices;

    @RequestMapping(value = "themhocvien", method = RequestMethod.POST)
    public ObRespon<HocVien> themHocVien(@RequestBody String hocVien) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {

            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        HocVien hv = gson.fromJson(hocVien, HocVien.class);
        return hocVienServices.themHocVien(hv);
    }

    @RequestMapping(value = "suahocvien", method = RequestMethod.PUT)
    public ObRespon<HocVien> suaHocVien(@RequestBody String hocVienSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        HocVien hv = gson.fromJson(hocVienSua, HocVien.class);
        return hocVienServices.suaHocVien(hv);
    }

    @RequestMapping(value = "xoahocvien", method = RequestMethod.DELETE)
    public ObRespon<HocVien> xoaHocVien(@RequestParam int hocVienId) {
        return hocVienServices.xoaHocVien(hocVienId);
    }

    @RequestMapping(value = "timkiemtheotenvaemail", method = RequestMethod.GET)
    public List<HocVien> timKiemTheoTenVaEmail(@RequestParam String ten, String email) {
        return hocVienServices.timTheoTenVaEmail(ten, email);
    }

    @RequestMapping(value = "laydanhsach", method = RequestMethod.GET)
    public List<HocVien> layDsHocVien() {
        return hocVienServices.layDsHocVien();
    }

}
