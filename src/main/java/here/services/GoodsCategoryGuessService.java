package here.services;

import here.dto.Goods;

public interface GoodsCategoryGuessService {
    /**
     * service try to guess if goods is tax-free form its content
     */
    boolean guessIfTaxFree(Goods goods);

    /*
    * service try to guess if goods is imported form its content
    */
    boolean guessIfImported(Goods goods);
}
