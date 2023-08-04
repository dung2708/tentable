package TenTable.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "loaikhoahoc")
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaikhoahocid")
    private int loaiKhoaHocID;
    @Column(name = "tenloaikhoahoc")
    private String tenLoaiKhoaHoc;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiKhoaHoc", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<KhoaHoc> khoaHocs;

    public int getLoaiKhoaHocID() {
        return loaiKhoaHocID;
    }

    public void setLoaiKhoaHocID(int loaiKhoaHocID) {
        this.loaiKhoaHocID = loaiKhoaHocID;
    }

    public String getTenLoaiKhoaHoc() {
        return tenLoaiKhoaHoc;
    }

    public void setTenLoaiKhoaHoc(String tenLoaiKhoaHoc) {
        this.tenLoaiKhoaHoc = tenLoaiKhoaHoc;
    }

    public Set<KhoaHoc> getKhoaHocs() {
        return khoaHocs;
    }

    public void setKhoaHocs(Set<KhoaHoc> khoaHocs) {
        this.khoaHocs = khoaHocs;
    }

}
