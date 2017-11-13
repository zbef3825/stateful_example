package cheesycoder.stateful.state;

import org.parceler.Parcel;

import cheesycoder.stateful.MainState;

/**
 * Created by jinwo on 2017-11-12.
 */
@Parcel
public class DisplayState implements State {

    MainState state;

    String result;

    public DisplayState() {}

    public DisplayState(MainState state) {
        this.state = state;
    }

    @Override
    public void userTyping(int charLength) {
        state.setState(state.getTypingState());
    }

    @Override
    public void pressSend(String result) {
        state.setState(state.getDisplayState(result));
    }

    public String getResult() {
        return result;
    }

    public DisplayState setResult(String result) {
        this.result = result;
        return this;
    }
}
