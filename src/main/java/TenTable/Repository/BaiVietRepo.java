package TenTable.Repository;

import TenTable.Model.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiVietRepo extends JpaRepository<BaiViet, Integer> {
    List<BaiViet> findByTenBaiVietContaining(String ten);
}
