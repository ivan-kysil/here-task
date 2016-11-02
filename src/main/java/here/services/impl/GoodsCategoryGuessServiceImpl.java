package here.services.impl;


import here.dto.Goods;
import here.services.GoodsCategoryGuessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GoodsCategoryGuessServiceImpl implements GoodsCategoryGuessService {

    public GoodsCategoryGuessServiceImpl() { }

    public GoodsCategoryGuessServiceImpl(String taxFreeKeyWords, String importedKeyWords) {
        this.taxFreeKeyWords = taxFreeKeyWords;
        this.importedKeyWords = importedKeyWords;
    }

    @Value("${tax.free.key.words}")
    private String taxFreeKeyWords;

    @Value("${imported.key.words}")
    private String importedKeyWords;

    @Override
    public boolean guessIfTaxFree(Goods goods) {
        return containsSome(goods.getDescription(), getTaxFreeKeyWordsList());
    }

    @Override
    public boolean guessIfImported(Goods goods) {
        return containsSome(goods.getDescription(), getImportedKeyWordsList());
    }

    private boolean containsSome(String description, List<String> setOfWords) {
        for (String s: setOfWords) {
            if (description.toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getTaxFreeKeyWordsList() {
        return Arrays.asList(taxFreeKeyWords.trim().split("[,;\\s]+")).stream().filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.toList());
    }

    private List<String> getImportedKeyWordsList() {
        return Arrays.asList(importedKeyWords.trim().split("[,;\\s]+")).stream().filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.toList());
    }
}
