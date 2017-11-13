package cheesycoder.stateful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    public static final String TAG = MainActivity.class.getName();

    @BindView(R.id.result_view)
    TextView resultView;

    @BindView(R.id.message_field)
    AppCompatEditText messageField;

    @BindView(R.id.send_button)
    AppCompatButton sendButton;

    @BindString(R.string.user_typing)
    String userTyping;

    @BindString(R.string.init_result)
    String initResult;

    MainContract.Presenter presenter;

    Unbinder unbinder;

    private TextWatcher textChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            presenter.startTyping(editable.length());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainState mainState = null;
        if (savedInstanceState != null) {
            mainState = Parcels.unwrap(savedInstanceState.getParcelable(TAG));
        }
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenter(mainState, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(TAG, Parcels.wrap(presenter.getState()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        messageField.addTextChangedListener(textChangeListener);
        super.onResume();
    }

    @Override
    protected void onPause() {
        messageField.removeTextChangedListener(textChangeListener);
        super.onPause();
    }

    @Override
    protected void onStop() {
        presenter.stop();
        unbinder.unbind();
        super.onStop();
    }

    @Override
    public void displayTyping() {
        resultView.setText(userTyping);
    }

    @Override
    public void displayResult(String result) {
        resultView.setText(getResources().getString(R.string.result, result));
    }

    @Override
    public void displayInit() {
        resultView.setText(initResult);
    }

    @OnClick(R.id.send_button)
    public void onClickSendButton() {
        presenter.startDisplayingResult(messageField.getEditableText().toString());
    }
}
