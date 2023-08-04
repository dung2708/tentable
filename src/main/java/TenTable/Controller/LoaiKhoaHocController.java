package TenTable.Controller;

import TenTable.Model.LoaiKhoaHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.LoaiKhoaHocServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "loaikhoahoc")
public class LoaiKhoaHocController {
    @Autowired
    private LoaiKhoaHocServices loaiKhoaHocServices;

    @RequestMapping(value = "themloaikhoahoc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<LoaiKhoaHoc> themLoaiKhoaHoc(@RequestBody String loaiKhoaHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws
                    JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        LoaiKhoaHoc loaiKH = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        return loaiKhoaHocServices.themLoaiKhoaHoc(loaiKH);
    }

    @RequestMapping(value = "xoaloaikhoahoc", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<LoaiKhoaHoc> xoaLoaiKhoaHoc(@RequestParam int loaiKhoaHocID) {
        return loaiKhoaHocServices.xoaLoaiKhoaHoc(loaiKhoaHocID);
    }

    @RequestMapping(value = "sualoaikhoahoc", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObRespon<LoaiKhoaHoc> suaLoaiKhoaHoc(@RequestBody String loaiKhoaHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws
                    JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        LoaiKhoaHoc loaiKH = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        return loaiKhoaHocServices.suaLoaiKhoaHoc(loaiKH);
    }

    @RequestMapping(value = "hienthi", method = RequestMethod.GET)
    public List<LoaiKhoaHoc> hienThi() {
        return loaiKhoaHocServices.hienThi();
    }
}
