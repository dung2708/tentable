package TenTable.Controller;

import TenTable.Model.BaiViet;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.BaiVietServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "baiviet")
public class BaiVietController {
    @Autowired
    private BaiVietServices baiVietServices;

    @RequestMapping(value = "thembaiviet", method = RequestMethod.POST)
    public ObRespon<BaiViet> themBaiViet(@RequestBody String baiViet) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        BaiViet bv = gson.fromJson(baiViet, BaiViet.class);
        return baiVietServices.themBaiViet(bv);
    }

    @RequestMapping(value = "suabaiviet", method = RequestMethod.PUT)
    public ObRespon<BaiViet> suaBaiViet(@RequestBody String baiVietSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        BaiViet bv = gson.fromJson(baiVietSua, BaiViet.class);
        return baiVietServices.suaBaiViet(bv);
    }

    @RequestMapping(value = "xoabaiviet", method = RequestMethod.DELETE)
    public ObRespon<BaiViet> xoaBaiViet(@RequestParam int id) {
        return baiVietServices.xoaBaiViet(id);
    }

    @RequestMapping(value = "timkiemtheotenbaiviet", method = RequestMethod.GET)
    public List<BaiViet> timKiemTheoTen(@RequestParam String ten) {
        return baiVietServices.timKiemTheoTen(ten);
    }

    @RequestMapping(value = "laydsbaiviet", method = RequestMethod.GET)
    public List<BaiViet> layDsBaiViet() {
        return baiVietServices.layDsBaiViet();
    }

}
