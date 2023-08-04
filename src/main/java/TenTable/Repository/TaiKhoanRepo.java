package TenTable.Repository;

import TenTable.Model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, Integer> {
    List<TaiKhoan> findByTenNguoiDungContaining(String ten);

    List<TaiKhoan> findByTaiKhoanContaining(String ten);
}
