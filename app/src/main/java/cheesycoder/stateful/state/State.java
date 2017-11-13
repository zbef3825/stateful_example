package cheesycoder.stateful.state;

/**
 * Created by jinwo on 2017-11-12.
 */

public interface State {
    void userTyping(int charLength);
    void pressSend(String result);
}
