package here.services;

import here.dto.Goods;
import here.dto.GoodsCategory;

import java.util.Optional;

public interface GoodsCategoryInferService {
    Optional<GoodsCategory> inferCategory(Goods goods);
    boolean inferIfImported(Goods goods);
}
