package TenTable.Controller;

import TenTable.Model.ChuDe;
import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Services.ChuDeServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "chude")
public class ChuDeController {
    @Autowired
    private ChuDeServices chuDeServices;

    @RequestMapping(value = "themchude", method = RequestMethod.POST)
    public ObRespon<ChuDe> themChuDe(@RequestBody String chuDe) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        ChuDe cd = gson.fromJson(chuDe, ChuDe.class);
        return chuDeServices.themChuDe(cd);
    }

    @RequestMapping(value = "suachude", method = RequestMethod.PUT)
    public ObRespon<ChuDe> suaChuDe(@RequestBody String chuDe) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        ChuDe cd = gson.fromJson(chuDe, ChuDe.class);
        return chuDeServices.suaChuDe(cd);
    }

    @RequestMapping(value = "xoachude", method = RequestMethod.DELETE)
    public ObRespon<ChuDe> xoaChuDe(@RequestParam int chuDeId) {
        return chuDeServices.xoaChuDe(chuDeId);
    }

    @RequestMapping(value = "hienthichude", method = RequestMethod.GET)
    public List<ChuDe> hienThi() {
        return chuDeServices.hienThi();
    }

}
