package com.gaana.cardoption;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.gaana.cardoption.CardOption.CardScheme;
import java.util.regex.Pattern;

public class CardNumberEditText extends EditText {
    public static final Pattern CODE_PATTERN = Pattern.compile("([0-9]{0,4})|([0-9]{4}-)+|([0-9]{4}-[0-9]{0,4})+");
    public static final Pattern EXP_PATTERN = Pattern.compile("^((0[1-9])|(1[0-2]))//((2009)|(20[1-2][0-9]))$");
    private final int DEFAULT_CARD_NUMBER_LENGTH = 19;
    public Context context;
    private TextWatcher customTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String replace;
            if (charSequence.toString().contains(" ")) {
                replace = charSequence.toString().replace(" ", "");
            } else {
                replace = charSequence.toString();
            }
            try {
                CardScheme cardSchemeUsingNumber = CardScheme.getCardSchemeUsingNumber(replace);
                if (CardNumberEditText.this.mCardSchemeCallBack != null) {
                    CardNumberEditText.this.mCardSchemeCallBack.onCardSchemeReceived(cardSchemeUsingNumber);
                }
                String iconName = cardSchemeUsingNumber.getIconName();
                int filterLength = CardScheme.getFilterLength(cardSchemeUsingNumber);
                CardNumberEditText.this.setFilters(new InputFilter[]{new LengthFilter(filterLength)});
                CardNumberEditText.this.context.getResources().getIdentifier(iconName, "drawable", CardNumberEditText.this.context.getPackageName());
                AssetsHelper.getCard(iconName.toUpperCase());
                CardNumberEditText.this.setError(null);
            } catch (Exception unused) {
                CardNumberEditText.this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0 && !CardNumberEditText.CODE_PATTERN.matcher(editable).matches()) {
                String access$200 = CardNumberEditText.this.formatNumbersAsCode(CardNumberEditText.this.keepNumbersOnly(editable.toString()));
                CardNumberEditText.this.removeTextChangedListener(this);
                CardNumberEditText.this.setText(access$200);
                CardNumberEditText.this.setSelection(access$200.length());
                CardNumberEditText.this.addTextChangedListener(this);
            }
        }
    };
    private CardSchemeCallBack mCardSchemeCallBack = null;

    public CardNumberEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        init();
    }

    public CardNumberEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        init();
    }

    public CardNumberEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        addTextChangedListener(this.customTextWatcher);
        setFilters(new InputFilter[]{new LengthFilter(19)});
    }

    private String keepNumbersOnly(CharSequence charSequence) {
        return charSequence.toString().replaceAll("[^0-9]", "");
    }

    private String formatNumbersAsCode(CharSequence charSequence) {
        String str = "";
        int i = 0;
        int i2 = i;
        while (i < charSequence.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(charSequence.charAt(i));
            str = stringBuilder.toString();
            i2++;
            if (i2 == 4) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(" ");
                str = stringBuilder2.toString();
                i2 = 0;
            }
            i++;
        }
        try {
            return str.charAt(str.length() + -1) == ' ' ? str.substring(0, str.length() - 1) : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public void getCardScheme(CardSchemeCallBack cardSchemeCallBack) {
        this.mCardSchemeCallBack = cardSchemeCallBack;
    }
}
