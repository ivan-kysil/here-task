package here.services;


import here.dto.Goods;
import here.dto.GoodsCategory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class GoodsCategoryInferServiceImpl implements GoodsCategoryInferService {

    Set<String> MEDICAL_CATEGORY_WORDS = new HashSet<>(Arrays.asList("drug pills".split("\\s+")));
    Set<String> BOOKS_CATEGORY_WORDS = new HashSet<>(Arrays.asList("book books".split("\\s+")));
    Set<String> FOOD_CATEGORY_WORDS = new HashSet<>(Arrays.asList("food dish nutrition fish vegetable vegetables meal meat chocolate chocolates".split("\\s+")));
    Set<String> IMPORTED_WORDS = new HashSet<>(Arrays.asList("import imported".split("\\s+")));

    @Override
    public Optional<GoodsCategory> inferCategory(Goods goods) {
        final String description = goods.getDescription();
        if (containsSome(description, MEDICAL_CATEGORY_WORDS)) {
            return Optional.of(GoodsCategory.MEDICAL);
        } else if (containsSome(description, BOOKS_CATEGORY_WORDS)) {
            return Optional.of(GoodsCategory.BOOKS);
        } else if (containsSome(description, FOOD_CATEGORY_WORDS)) {
            return Optional.of(GoodsCategory.FOOD);
        }
        return Optional.empty();
    }

    @Override
    public boolean inferIfImported(Goods goods) {
        final String description = goods.getDescription();
        if (containsSome(description, IMPORTED_WORDS)) {
            return true;
        }
        return false;
    }

    private boolean containsSome(String description, Set<String> setOfWords) {
        for (String s: setOfWords) {
            if (description.toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }
}
