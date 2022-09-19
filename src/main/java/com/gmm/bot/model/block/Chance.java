package com.gmm.bot.model.block;

import com.gmm.bot.model.block.card.BaseCard;
import com.gmm.bot.model.block.card.CardType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Chance extends Block {
    public static final int PAY_CHANGE_HALF_PERCENT = 50;
    public static final int PAY_CHANGE_ZERO_PERCENT = 0;
    public static final int PAY_CHANGE_100_PERCENT = 100;
    public static final long RECEIVE_MONEY_LV1 = 1000;
    public static final long RECEIVE_MONEY_LV2 = 2000;
    public static final long REDUCE_MONEY_LV1 = 1000;
    public static final long REDUCE_MONEY_LV2 = 2000;

    private BaseCard card;
    private CardType cardType;

    public Chance() {
    }

    @Builder
    public Chance(int position, boolean canRepurchase, boolean canSell, String name, BaseCard card, CardType cardType) {
        super(position, canRepurchase, canSell, name);
        this.card = card;
        this.cardType = cardType;
    }

//    public ActionResult onBlock(GameBoard gameBoard) {
//        switch (cardType) {
//            case PAY_HALF:
//                card = new CardPayChangingOnPlayer(PAY_CHANGE_HALF_PERCENT);
//                break;
//            case PAY_ZERO:
//                card = new CardPayChangingOnPlayer(PAY_CHANGE_ZERO_PERCENT);
//                break;
//            case PAY_DOUBLE:
//                card = new CardPayChangingOnPlayer(PAY_CHANGE_100_PERCENT);
//                break;
//            case RECEIVE_MONEY_LV1:
//                card = new CardReceiveMoney(RECEIVE_MONEY_LV1);
//                break;
//            case RECEIVE_MONEY_LV2:
//                card = new CardReceiveMoney(RECEIVE_MONEY_LV2);
//                break;
//            case REDUCE_MONEY_LV1:
//                card = new CardReduceMoney(REDUCE_MONEY_LV1);
//                break;
//            case REDUCE_MONEY_LV2:
//                card = new CardReduceMoney(REDUCE_MONEY_LV2);
//                break;
//            case SWAP_CITY:
//                card = new CardSwapCity();
//                break;
//            case DESTROY_CITY:
//                card = new CardRemoveCityOtherPlayer(false);
//                break;
//            case FORCE_CELL_CITY:
//                card = new CardRemoveCityOtherPlayer(true);
//                break;
//            case DISCOUNT_50_PERCENT:
//                card = new CardPayChangingOnPlayer(50);
//                break;
//        }
//        return card.execute(gameBoard, cardType);
//    }
}
