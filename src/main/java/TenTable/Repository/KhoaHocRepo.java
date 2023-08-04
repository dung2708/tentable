package TenTable.Repository;

import TenTable.Model.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepo extends JpaRepository<KhoaHoc, Integer> {
    Page<KhoaHoc> findByTenKhoaHocContaining(String ten, Pageable pageable);

    List<KhoaHoc> findByTenKhoaHocContaining(String ten);
}
