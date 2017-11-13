package cheesycoder.stateful;

import cheesycoder.stateful.state.DisplayState;
import cheesycoder.stateful.state.InitState;
import cheesycoder.stateful.state.State;
import cheesycoder.stateful.state.TypingState;

/**
 * Created by jinwo on 2017-11-12.
 */

public class MainState {
    State initState;
    State displayState;
    State typingState;

    State curState;

    MainContract.Presenter presenter;

    public MainState(MainContract.Presenter presenter, State state) {
        initState = new InitState(this);
        displayState = new DisplayState(this);
        typingState = new TypingState(this);

        if (state == null) {
            state = initState;
        }
        this.curState = state;
        this.presenter = presenter;
        this.presenter.resolveState(this);
    }

    public void setCurState(State curState) {
        this.curState = curState;
        presenter.resolveState(this);
    }

    public State getCurState() {
        return curState;
    }

    public State getDisplayState(String result) {
        if (displayState instanceof DisplayState) {
            ((DisplayState) displayState).setResult(result);
        }
        return displayState;
    }

    public State getTypingState() {
        return typingState;
    }

    public State getInitState() {
        return initState;
    }

    // Actions from Presenter
    public void startTyping(int charLength) {
        curState.userTyping(charLength);
    }

    public void startDisplayingResult(String result) {
        curState.pressSend(result);
    }
}
