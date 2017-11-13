package cheesycoder.stateful;

/**
 * Created by jinwo on 2017-11-12.
 */

public interface MainContract {
    public interface View {
        void displayTyping();
        void displayResult(String result);
        void displayInit();
    }

    public interface Presenter {
        void resolveState(MainState state);
        void startTyping(int charLength);
        void startDisplayingResult(String result);
    }
}
