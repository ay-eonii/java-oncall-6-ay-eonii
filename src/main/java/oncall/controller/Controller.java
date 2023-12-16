package oncall.controller;


import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void execute() {
        inputView.readSchedule();
        readOnCallOrder();

    }

    private void readOnCallOrder() {
        try {
            inputView.readWeekdayOrder();
            inputView.readWeekendOrder();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            readOnCallOrder();
        }
    }
}
