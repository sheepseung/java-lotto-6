package lotto.controller;

import lotto.domain.*;
import lotto.utils.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {
    private final Calculator calculator;
    private final Lottos lottos;

    private WinningNumber winningNumber;
    private BonusNumber bonusNumber;

    private int coin;

    public GameController() {
        this.lottos = new Lottos();
        this.calculator = new Calculator(payment);

        OutputView.printNumberOfLottoPurchase(coin);

    }

    public void run() {
        String inputPayment = InputView.inputPayment();
        Payment payment = Payment.create(inputPayment);

        int coin = Parser.parseAmountToCoin(payment);
        for (int i = 0; i < coin; i++) {
            Lotto lotto = LottoMachine.createLotto();
            lottos.addLotto(lotto);
            System.out.println(lotto.toString());
        }

        this.winningNumber = new WinningNumber(InputView.inputWinningNumber());
        this.bonusNumber = new BonusNumber(InputView.inputBonusNumber());

        Result result = calculator.calculateResult(lottos.getLottos(),
                winningNumber.getWinningNumber(),
                bonusNumber.getBonusNumber());

        OutputView.printResult(result);
        OutputView.printIncomeRate(calculator, result);
    }
}
