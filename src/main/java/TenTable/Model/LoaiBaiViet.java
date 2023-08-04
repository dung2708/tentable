package TenTable.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "loaibaiviet")
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaibaivietid")
    private int loaiBaiVietID;
    @Column(name = "tenloaibaiviet")
    private String tenLoaiBaiViet;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiBaiViet", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ChuDe> chuDes;

    public int getLoaiBaiVietID() {
        return loaiBaiVietID;
    }

    public void setLoaiBaiVietID(int loaiBaiVietID) {
        this.loaiBaiVietID = loaiBaiVietID;
    }

    public String getTenLoaiBaiViet() {
        return tenLoaiBaiViet;
    }

    public void setTenLoaiBaiViet(String tenLoaiBaiViet) {
        this.tenLoaiBaiViet = tenLoaiBaiViet;
    }

    public Set<ChuDe> getChuDes() {
        return chuDes;
    }

    public void setChuDes(Set<ChuDe> chuDes) {
        this.chuDes = chuDes;
    }

}
