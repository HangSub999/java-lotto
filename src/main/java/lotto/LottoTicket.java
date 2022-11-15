package lotto;

import java.util.*;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoTicket {

    private List<Integer> winnigLotto;
    private int bonusNum;

    LottoTicket() {
        winnigLotto = null;
        bonusNum = 0;
    }

    public List<Lotto> createManyLotto(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto;
        for (int index = 0; index < lottoCount; index++) {
            lotto = createLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private Lotto createLotto() {
        Lotto lotto;
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        lotto = new Lotto(numbers);
        return lotto;
    }

    public void setWinnigLotto(List<Integer> winnigLotto) {
        this.winnigLotto = winnigLotto;
    }

    public void setBonus(int bonus) {
        this.bonusNum = bonus;
    }

    public Map<LottoRanking, Integer> compareLotto(List<Lotto> lottos) {

        Map<LottoRanking, Integer> winningList = new EnumMap<>(LottoRanking.class);

        for (Lotto lotto : lottos) {
            LottoRanking lottoRanking = lotto.compare(winnigLotto, bonusNum);

            putInMapIfEmpty(winningList, lottoRanking);

            increasePrizeCount(winningList, lottoRanking);
        }
        return winningList;
    }

    private void increasePrizeCount(Map<LottoRanking, Integer> winningList, LottoRanking lottoRanking) {
        int prizeCount = winningList.get(lottoRanking);
        winningList.put(lottoRanking, prizeCount + 1);
    }

    private void putInMapIfEmpty(Map<LottoRanking, Integer> winningList, LottoRanking lottoRanking) {
        if (winningList.get(lottoRanking) == null)
            winningList.put(lottoRanking, 0);
    }

}
