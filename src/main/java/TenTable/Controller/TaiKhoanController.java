package TenTable.Controller;

import TenTable.Model.ObjectRespon.ObRespon;
import TenTable.Model.TaiKhoan;
import TenTable.Services.TaiKhoanServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "taikhoan")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanServices taiKhoanServices;

    @RequestMapping(value = "themtaikhoan", method = RequestMethod.POST)
    public ObRespon<TaiKhoan> themTaiKhoan(@RequestBody String taiKhoan) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {

            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        TaiKhoan tk = gson.fromJson(taiKhoan, TaiKhoan.class);
        return taiKhoanServices.themTaiKhoan(tk);
    }

    @RequestMapping(value = "suataikhoan", method = RequestMethod.PUT)
    public ObRespon<TaiKhoan> suaTaiKhoan(@RequestBody String taiKhoanSua) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {

            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        TaiKhoan tk = gson.fromJson(taiKhoanSua, TaiKhoan.class);
        return taiKhoanServices.suaTaiKhoan(tk);
    }

    @RequestMapping(value = "xoaTaiKhoan", method = RequestMethod.DELETE)
    public ObRespon<TaiKhoan> xoaTaiKhoan(@RequestParam int taiKhoanId) {
        return taiKhoanServices.xoaTaiKhoan(taiKhoanId);
    }

    @RequestMapping(value = "timkiemtaikhoan", method = RequestMethod.GET)
    public List<TaiKhoan> timKiem(@RequestParam String tenTK) {
        return taiKhoanServices.timKiem(tenTK);
    }

}
