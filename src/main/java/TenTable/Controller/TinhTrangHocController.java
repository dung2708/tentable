package TenTable.Controller;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TinhTrangHoc;
import TenTable.Services.TinhTrangHocServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "tinhtranghoc")
public class TinhTrangHocController {
    @Autowired
    private TinhTrangHocServices tinhTrangHocServices;

    @RequestMapping(value = "themtinhtranghoc", method = RequestMethod.POST)
    public ObRespon<TinhTrangHoc> themTinhTrang(@RequestBody String tinhTrangHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        TinhTrangHoc tinhTrang = gson.fromJson(tinhTrangHoc, TinhTrangHoc.class);
        return tinhTrangHocServices.themTinhTrangHoc(tinhTrang);
    }

    @RequestMapping(value = "suatinhtranghoc", method = RequestMethod.PUT)
    public ObRespon<TinhTrangHoc> suaTinhTrang(@RequestBody String tinhTrangHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        TinhTrangHoc tinhTrang = gson.fromJson(tinhTrangHoc, TinhTrangHoc.class);
        return tinhTrangHocServices.suaTinhTrangHoc(tinhTrang);
    }

    @RequestMapping(value = "xoatinhtranghoc", method = RequestMethod.DELETE)
    public ObRespon<TinhTrangHoc> xoaTinhTrang(@RequestParam int tinhTrangHocId) {
        return tinhTrangHocServices.xoaTinhTrangHoc(tinhTrangHocId);
    }

    @RequestMapping(value = "laytinhtranghoc", method = RequestMethod.GET)
    public List<TinhTrangHoc> layTinhTrangHoc() {
        return tinhTrangHocServices.layTinhTrangHoc();
    }

}
