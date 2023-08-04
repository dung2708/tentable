package TenTable.Repository;

import TenTable.Model.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyHocRepo extends JpaRepository<DangKyHoc, Integer> {

}
