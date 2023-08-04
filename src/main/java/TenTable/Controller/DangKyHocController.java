package TenTable.Controller;

import TenTable.Model.DangKyHoc;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.DangKyHocServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "dangkyhoc")
public class DangKyHocController {
    @Autowired
    private DangKyHocServices dangKyHocServices;

    @RequestMapping(value = "themdangkyhoc", method = RequestMethod.POST)
    public ObRespon<DangKyHoc> themDangKy(@RequestBody String dangKyHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        DangKyHoc dkh = gson.fromJson(dangKyHoc, DangKyHoc.class);
        return dangKyHocServices.themDangKyHoc(dkh);
    }

    @RequestMapping(value = "suadangkyhoc", method = RequestMethod.PUT)
    public ObRespon<DangKyHoc> suaDangKy(@RequestBody String dangKyHocSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        DangKyHoc dkh = gson.fromJson(dangKyHocSua, DangKyHoc.class);
        return dangKyHocServices.suaDangKyHoc(dkh);
    }

    @RequestMapping(value = "xoadangkyhoc", method = RequestMethod.DELETE)
    public ObRespon<DangKyHoc> xoaDangKyHoc(@RequestParam int dangKyHocId) {
        return dangKyHocServices.xoaDangKyHoc(dangKyHocId);
    }

    @RequestMapping(value = "laydsdangkyhoc", method = RequestMethod.GET)
    public List<DangKyHoc> layDsDangKyHoc() {
        return dangKyHocServices.layDsDangKyHoc();
    }

}
