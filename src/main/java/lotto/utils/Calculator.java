package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.Result;

import java.util.List;

public class Calculator {
    private static final int LOTTO_LENGTH = 6;

    private final Result result;

    public Calculator() {
        this.result = new Result();
    }

    public Result calculateResult(List<Lotto> lottos, List<Integer> winningNumber, int bonusNumber) {
        for (int i = 0; i < lottos.size(); i++) {
            List<Integer> lottoNumbers = lottos.get(i).getLottoNumbers();
            int correctCount = countCorrectNumber(lottoNumbers, winningNumber);
            boolean correctBonus = checkCorrectBonusNumber(lottoNumbers, bonusNumber);

            calculateRank(correctCount, correctBonus);
        }

        return result;
    }

    private int countCorrectNumber(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int correctNumber = 0;
        for (int j = 0; j < LOTTO_LENGTH; j++) {
            if (lottoNumbers.contains(winningNumbers.get(j))) {
                correctNumber++;
            }
        }

        return correctNumber;
    }

    private boolean checkCorrectBonusNumber(List<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            return true;
        }
        return false;
    }

        private void calculateRank ( int correctCount, boolean correctBonus){
            if (correctCount == 6) {
                result.countRank(1);
                result.addIncome(2000000000);
                return;
            }
            if (correctCount == 5 && correctBonus) {
                result.countRank(2);
                result.addIncome(30000000);
                return;
            }
            if (correctCount == 5) {
                result.countRank(3);
                result.addIncome(1500000);
                return;
            }
            if (correctCount == 4) {
                result.countRank(4);
                result.addIncome(50000);
                return;
            }
            if (correctCount == 3) {
                result.countRank(5);
                result.addIncome(5000);
            }
        }

        public double calculateIncomeRate (Result result, Payment payment){
            return (result.getIncome() / payment.getPayment()) * 100;
        }
    }