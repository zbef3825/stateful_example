package cheesycoder.stateful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(null, this);
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
