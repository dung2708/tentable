package TenTable.Controller;

import TenTable.Model.KhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.KhoaHocServices;
import TenTable.Services.LoaiKhoaHocServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "khoahoc")
public class KhoaHocController {
    @Autowired
    private KhoaHocServices khoaHocServices;
    @Autowired
    private LoaiKhoaHocServices loaiKhoaHocServices;

    @RequestMapping(value = "themkhoahoc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<KhoaHoc> themKhoaHoc(@RequestBody String khoaHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws
                    JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        KhoaHoc kh = gson.fromJson(khoaHoc, KhoaHoc.class);
        return khoaHocServices.themKhoaHoc(kh);
    }

    @RequestMapping(value = "suakhoahoc", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<KhoaHoc> suaKhoaHoc(@RequestBody String khoaHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws
                    JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        KhoaHoc kh = gson.fromJson(khoaHoc, KhoaHoc.class);
        return khoaHocServices.suaKhoaHoc(kh);
    }

    @RequestMapping(value = "xoakhoahoc", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<KhoaHoc> xoaKhoaHoc(@RequestParam int khoaHocID) {
        return khoaHocServices.xoaKhoaHoc(khoaHocID);
    }

    @RequestMapping(value = "hienthi", method = RequestMethod.GET)
    public List<KhoaHoc> hienThi() {
        return khoaHocServices.hienThi();
    }

    @RequestMapping(value = "timkiem", method = RequestMethod.GET)
    public List<KhoaHoc> timKiem(@RequestParam String keyWord) {
        return khoaHocServices.timKiem(keyWord);
    }

    @RequestMapping(value = "timkiemphantrang", method = RequestMethod.GET)
    public Page<KhoaHoc> timKiemPhanTrang(@RequestParam String keyWord) {
        Pageable pageable = PageRequest.of(1, 3);
        return khoaHocServices.timKiemPhanTrang(keyWord, pageable);
    }
}
