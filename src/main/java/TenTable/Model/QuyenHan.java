package TenTable.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "quyenhan")
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quyenhanid")
    private int quyenHanID;
    @Column(name = "tenquyenhan")
    private String tenQuyenHan;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quyenHan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TaiKhoan> taiKhoans;

    public int getQuyenHanID() {
        return quyenHanID;
    }

    public void setQuyenHanID(int quyenHanID) {
        this.quyenHanID = quyenHanID;
    }

    public String getTenQuyenHan() {
        return tenQuyenHan;
    }

    public void setTenQuyenHan(String tenQuyenHan) {
        this.tenQuyenHan = tenQuyenHan;
    }

    public Set<TaiKhoan> getTaiKhoans() {
        return taiKhoans;
    }

    public void setTaiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
    }
}
