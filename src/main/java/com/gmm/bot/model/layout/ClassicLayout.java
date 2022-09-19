package com.gmm.bot.model.layout;

import com.gmm.bot.model.Dice;
import com.gmm.bot.model.block.*;
import lombok.Getter;

import java.util.*;

import static com.gmm.bot.model.block.BlockType.*;

@Getter
public class ClassicLayout extends Layout {
    public static final int START_MONEY = 200;

    public ClassicLayout() {
        super();
        createLayout();
    }

    public ClassicLayout(int size, List<Dice> diceList) {
        super(size, diceList);
        createLayout();
    }

    @Override
    public void createLayout() {
        /*setup four corner*/
        Block start = Start.builder().receiveMoney(200).name(START.name()).position(0).canRepurchase(false).canSell(false).build();
        Block jail = Jail.builder().loseMoney(200).name(JAIL.name()).position(8).canRepurchase(false).canSell(false).build();
        Block woldChampionship = WorldChampionship.builder().loseMoney(50).name(WORLD_CHAMPIONSHIP.name()).position(16).canRepurchase(false).canSell(false).build();
        Block worldTour = WorldTour.builder().loseMoney(50).name(WORLD_TOUR.name()).position(24).canRepurchase(false).canSell(false).build();
        blockList.set(start.getPosition(), start);
        blockList.set(jail.getPosition(), jail);
        blockList.set(woldChampionship.getPosition(), woldChampionship);
        blockList.set(worldTour.getPosition(), worldTour);

        /*setup resort*/
        Block bali = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(4).canRepurchase(false).canSell(true).maxLevel(4).price(0).build();
        Block cyprus = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(14).canRepurchase(false).canSell(true).maxLevel(4).price(0).build();
        Block dubai = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(18).canRepurchase(false).canSell(true).maxLevel(4).price(0).build();
        Block nice = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(25).canRepurchase(false).canSell(true).maxLevel(4).price(0).build();
        blockList.set(bali.getPosition(), bali);
        blockList.set(cyprus.getPosition(), cyprus);
        blockList.set(dubai.getPosition(), dubai);
        blockList.set(nice.getPosition(), nice);

        /*setup house*/
        Block granada = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(1).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block seville = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(2).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block madrid = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(3).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block hongKong = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(5).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block beijing = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(6).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block shanghai = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(7).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block venice = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(9).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block milan = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(10).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block rome = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(11).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block hamburg = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(13).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block berlin = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(15).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block london = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(17).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block sydney = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(19).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block chicago = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(21).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block lasvegas = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(22).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block newyork = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(23).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block lyon = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(26).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block paris = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(27).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block osaka = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(29).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        Block tokyo = Land.builder().coefficient(1).blockType(HOUSE).name(HOUSE.name()).upgradePrice(100).position(31).canRepurchase(true).canSell(true).maxLevel(4).price(0).build();
        blockList.set(granada.getPosition(), granada);
        blockList.set(seville.getPosition(), seville);
        blockList.set(madrid.getPosition(), madrid);
        blockList.set(hongKong.getPosition(), hongKong);
        blockList.set(beijing.getPosition(), beijing);
        blockList.set(shanghai.getPosition(), shanghai);
        blockList.set(venice.getPosition(), venice);
        blockList.set(milan.getPosition(), milan);
        blockList.set(rome.getPosition(), rome);
        blockList.set(hamburg.getPosition(), hamburg);
        blockList.set(berlin.getPosition(), berlin);
        blockList.set(london.getPosition(), london);
        blockList.set(sydney.getPosition(), sydney);
        blockList.set(chicago.getPosition(), chicago);
        blockList.set(lasvegas.getPosition(), lasvegas);
        blockList.set(newyork.getPosition(), newyork);
        blockList.set(lyon.getPosition(), lyon);
        blockList.set(paris.getPosition(), paris);
        blockList.set(osaka.getPosition(), osaka);
        blockList.set(tokyo.getPosition(), tokyo);

        /*setup chance*/
        Block chance1 = Chance.builder().name(CHANCE.name()).position(12).canRepurchase(false).canSell(false).build();
        Block chance2 = Chance.builder().name(CHANCE.name()).position(20).canRepurchase(false).canSell(false).build();
        Block chance3 = Chance.builder().name(CHANCE.name()).position(28).canRepurchase(false).canSell(false).build();
        blockList.set(chance1.getPosition(), chance1);
        blockList.set(chance2.getPosition(), chance2);
        blockList.set(chance3.getPosition(), chance3);

        /*setup tax*/
        Block tax = Tax.builder().name(TAX.name()).position(30).loseMoneyPercent(10).canRepurchase(false).canSell(false).build();
        blockList.set(tax.getPosition(), tax);
    }

}
