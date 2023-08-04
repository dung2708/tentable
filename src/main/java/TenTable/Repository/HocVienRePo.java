package TenTable.Repository;

import TenTable.Model.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocVienRePo extends JpaRepository<HocVien, Integer> {
    List<HocVien> findByHoTenContainingOrEmailContaining(String ten, String email);
}
