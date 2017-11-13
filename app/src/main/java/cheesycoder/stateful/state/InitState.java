package cheesycoder.stateful.state;

import android.util.Log;

import org.parceler.Parcel;

import cheesycoder.stateful.MainState;

/**
 * Created by jinwo on 2017-11-12.
 */
@Parcel
public class InitState implements State {
    MainState state;

    public InitState() {}

    public InitState(MainState state) {
        this.state = state;
    }

    @Override
    public void userTyping(int charLength) {
        state.setState(state.getTypingState());
    }

    @Override
    public void pressSend(String result) {
        Log.d("DEBUGGER", "Cannot Send on Empty String");
    }
}
