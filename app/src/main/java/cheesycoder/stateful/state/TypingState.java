package cheesycoder.stateful.state;

import android.util.Log;

import cheesycoder.stateful.MainState;

/**
 * Created by jinwo on 2017-11-12.
 */

public class TypingState implements State {
    MainState state;

    public TypingState(MainState state) {
        this.state = state;
    }

    @Override
    public void userTyping(int charLength) {
        if (charLength == 0) {
            state.setCurState(state.getInitState());
        } else {
            Log.d("DEBUGGER", "User is STILL typing...");
        }
    }

    @Override
    public void pressSend(String result) {
        state.setCurState(state.getDisplayState(result));
    }
}
