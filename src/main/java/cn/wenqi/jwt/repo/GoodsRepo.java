package cn.wenqi.jwt.repo;

import cn.wenqi.jwt.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wenqi
 * @since v
 */
public interface GoodsRepo extends JpaRepository<Goods,Long>,JpaSpecificationExecutor<Goods> {

}
