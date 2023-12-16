package oncall.view;

import java.util.List;

public class OutputView {

    private static final String FORMAT = "%s %s\n";

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printOnCall(List<String> date, List<String> onCall) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < date.size(); i++) {
            stringBuilder.append(String.format(FORMAT, date.get(i), onCall.get(i)));
        }

        System.out.println(stringBuilder);
    }
}
