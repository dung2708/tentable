package TenTable.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tinhtranghoc")
public class TinhTrangHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinhtranghocid")
    private int tinhTrangHocID;
    @Column(name = "tentinhtranghoc")
    private String tenTinhTrangHoc;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrangHoc", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DangKyHoc> dangKyHocs;

    public int getTinhTrangHocID() {
        return tinhTrangHocID;
    }

    public void setTinhTrangHocID(int tinhTrangHocID) {
        this.tinhTrangHocID = tinhTrangHocID;
    }

    public String getTenTinhTrangHoc() {
        return tenTinhTrangHoc;
    }

    public void setTenTinhTrangHoc(String tenTinhTrangHoc) {
        this.tenTinhTrangHoc = tenTinhTrangHoc;
    }

    public Set<DangKyHoc> getDangKyHocs() {
        return dangKyHocs;
    }

    public void setDangKyHocs(Set<DangKyHoc> dangKyHocs) {
        this.dangKyHocs = dangKyHocs;
    }

}
