package TenTable.Controller;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.QuyenHan;
import TenTable.Services.QuyenHanServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "quyenhan")
public class QuyenHanController {
    @Autowired
    private QuyenHanServices quyenHanServices;

    @RequestMapping(value = "themquyenhan", method = RequestMethod.POST)
    public ObRespon<QuyenHan> themQuyenHan(@RequestBody String quyenHan) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        QuyenHan qh = gson.fromJson(quyenHan, QuyenHan.class);
        return quyenHanServices.themQuyenHan(qh);
    }

    @RequestMapping(value = "suaquyenhan", method = RequestMethod.PUT)
    public ObRespon<QuyenHan> suaQuyenHan(@RequestBody String quyenHanSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }

        }).create();
        QuyenHan quyenHan = gson.fromJson(quyenHanSua, QuyenHan.class);
        return quyenHanServices.suaQuyenHan(quyenHan);
    }

    @RequestMapping(value = "xoaquyengan", method = RequestMethod.DELETE)
    public ObRespon<QuyenHan> xoaQuyenHan(@RequestParam int quyenHanId) {
        return quyenHanServices.xoaQuyenHan(quyenHanId);
    }

    @RequestMapping(value = "layquyenhan", method = RequestMethod.GET)
    public List<QuyenHan> layQuyenHan() {
        return quyenHanServices.layQuyenHan();
    }

}
