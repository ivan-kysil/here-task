package here.services;

import here.dto.Goods;
import here.dto.GoodsCategory;

import java.util.Optional;

@Deprecated
public interface GoodsCategoryInferService {
    /**
     * service try to infer/guess category of goods form its content
     * !!! this infer not quite reliable though !!!
     */
    Optional<GoodsCategory> inferCategory(Goods goods);

    /*
    * service try to infer/guess if  of goods is imported
    * !!! this infer not quite reliable though !!!
    */
    boolean inferIfImported(Goods goods);
}
