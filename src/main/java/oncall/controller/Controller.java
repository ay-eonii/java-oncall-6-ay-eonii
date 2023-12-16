package oncall.controller;


import oncall.view.InputView;

public class Controller {
    private final InputView inputView = new InputView();

    public void execute() {
        inputView.readSchedule();
        inputView.readWeekdayOrder();
    }
}
