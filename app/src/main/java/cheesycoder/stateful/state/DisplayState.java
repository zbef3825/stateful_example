package cheesycoder.stateful.state;

import android.util.Log;

import cheesycoder.stateful.MainState;

/**
 * Created by jinwo on 2017-11-12.
 */

public class DisplayState implements State {

    MainState state;

    String result;

    public DisplayState(MainState state) {
        this.state = state;
    }

    @Override
    public void userTyping(int charLength) {
        state.setCurState(state.getTypingState());
    }

    @Override
    public void pressSend(String result) {
        state.setCurState(state.getDisplayState(result));
    }

    public String getResult() {
        return result;
    }

    public DisplayState setResult(String result) {
        this.result = result;
        return this;
    }
}
