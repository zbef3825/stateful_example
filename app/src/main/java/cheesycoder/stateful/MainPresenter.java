package cheesycoder.stateful;import android.util.Log;import cheesycoder.stateful.state.DisplayState;import cheesycoder.stateful.state.InitState;import cheesycoder.stateful.state.TypingState;/** * Created by jinwo on 2017-11-12. */public class MainPresenter implements MainContract.Presenter {    MainState mainState;    MainContract.View view;    public MainPresenter(MainState mainState, MainContract.View view) {        this.view = view;        if (mainState == null) {            mainState = new MainState(null);        }        this.mainState = mainState;        this.mainState.setPresenter(this);    }    @Override    public void resolveState(MainState state) {        if (state.getCurState() instanceof InitState) {            view.displayInit();        } else if (state.getCurState() instanceof DisplayState) {            view.displayResult(((DisplayState) state.getCurState()).getResult());        } else if (state.getCurState() instanceof TypingState) {            view.displayTyping();        } else {            Log.e("DEBUGGER", "No State is set");        }    }    @Override    public void startTyping(int charLength) {        mainState.startTyping(charLength);    }    @Override    public void startDisplayingResult(String result) {        mainState.startDisplayingResult(result);    }    @Override    public void stop() {        view = null;        mainState.stop();        mainState = null;    }    public MainState getState() {        return mainState;    }}