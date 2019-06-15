package com.gaana.cardoption;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.gaana.cardoption.AssetsHelper.CARD;

public abstract class CardOption {
    protected String cardCVV = null;
    protected String cardExpiry = null;
    protected String cardExpiryMonth = null;
    protected String cardExpiryYear = null;
    protected String cardHolderName = null;
    protected String cardNumber = null;
    protected CardScheme cardScheme = null;
    protected String nickName = null;

    public enum CardScheme {
        VISA("4") {
            public String getName() {
                return "visa";
            }
        },
        MASTER_CARD("5") {
            public String getName() {
                return "mcrd";
            }
        },
        MAESTRO("502260", "504433", "504434", "504435", "504437", "504645", "504681", "504753", "504775", "504809", "504817", "504834", "504848", "504884", "504973", "504993", "508125", "508126", "508159", "508192", "508227", "56", "600206", "603123", "603741", "603845", "622018", "67") {
            public String getName() {
                return "mtro";
            }
        },
        DINERS("30", "36", "38", "39") {
            public String getName() {
                return "DINERS";
            }
        },
        JCB("35") {
            public String getName() {
                return "jcb";
            }
        },
        AMEX("34", "37") {
            public String getName() {
                return "amex";
            }
        },
        RPAY("5085", "5086", "5087", "5088", "6069", "607", "6081", "6521", "6522", "6524") {
            public String getName() {
                return CARD.RUPAY;
            }
        },
        DISCOVER("60", "62", "64", "65") {
            public String getName() {
                return CARD.DISCOVER;
            }
        },
        UNKNOWN("0") {
            public String getName() {
                return CARD.UNKNOWN;
            }
        };
        
        private final String[] pattern;

        public abstract String getName();

        private CardScheme(String... strArr) {
            this.pattern = strArr;
        }

        public String getIconName() {
            return name();
        }

        public static CardScheme getCardScheme(String str) {
            if ("visa".equalsIgnoreCase(str)) {
                return VISA;
            }
            if ("mcrd".equalsIgnoreCase(str) || "Master Card".equalsIgnoreCase(str)) {
                return MASTER_CARD;
            }
            if ("mtro".equalsIgnoreCase(str) || "Maestro Card".equalsIgnoreCase(str)) {
                return MAESTRO;
            }
            if ("DINERS".equalsIgnoreCase(str)) {
                return DINERS;
            }
            if ("jcb".equalsIgnoreCase(str)) {
                return JCB;
            }
            if ("amex".equalsIgnoreCase(str)) {
                return AMEX;
            }
            if (CARD.DISCOVER.equalsIgnoreCase(str)) {
                return DISCOVER;
            }
            if (CARD.RUPAY.equalsIgnoreCase(str) || "RuPay".equalsIgnoreCase(str)) {
                return RPAY;
            }
            return null;
        }

        public static CardScheme getCardSchemeUsingNumber(String str) {
            return com.gaana.cardoption.CardSchemeIdentifier.CardScheme.getCardSchemeUsingNumber(CardOption.normalizeCardNumber(str));
        }

        public static int getCVVLength(String str) {
            return getCardSchemeUsingNumber(str) == AMEX ? 4 : 3;
        }

        public static int getFilterLength(CardScheme cardScheme) {
            if (cardScheme == AMEX) {
                return 18;
            }
            return cardScheme == MAESTRO ? 23 : 19;
        }
    }

    public enum CardType {
        DEBIT {
            public String getCardType() {
                return "debit";
            }
        },
        CREDIT {
            public String getCardType() {
                return "credit";
            }
        };

        public abstract String getCardType();
    }

    CardOption() {
    }

    public String getCardExpiryYear() {
        return this.cardExpiryYear;
    }

    public String getCardExpiry() {
        return this.cardExpiry;
    }

    public String getCardExpiryMonth() {
        return this.cardExpiryMonth;
    }

    public String getCardCVV() {
        return this.cardCVV;
    }

    public void setCardCVV(String str) {
        this.cardCVV = str;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public CardScheme getCardScheme() {
        return this.cardScheme;
    }

    public void setCardScheme(CardScheme cardScheme) {
        this.cardScheme = cardScheme;
    }

    public static String normalizeCardNumber(String str) {
        return str == null ? null : str.trim().replaceAll("\\s+|-", "");
    }

    public String getLast4Digits() {
        if (!TextUtils.isEmpty(this.cardNumber)) {
            int length = this.cardNumber.length();
            int i = length - 4;
            if (i > 0) {
                return this.cardNumber.substring(i, length);
            }
        }
        return null;
    }

    public Drawable getOptionIcon(Context context) {
        int identifier;
        if (this.cardScheme == CardScheme.VISA) {
            identifier = context.getResources().getIdentifier("visa", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.MASTER_CARD) {
            identifier = context.getResources().getIdentifier("mcrd", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.MAESTRO) {
            identifier = context.getResources().getIdentifier("mtro", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.DINERS) {
            identifier = context.getResources().getIdentifier("diners", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.JCB) {
            identifier = context.getResources().getIdentifier("jcb", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.AMEX) {
            identifier = context.getResources().getIdentifier("amex", "drawable", context.getPackageName());
        } else if (this.cardScheme == CardScheme.RPAY) {
            identifier = context.getResources().getIdentifier("rupay", "drawable", context.getPackageName());
            if (identifier == 0) {
                identifier = context.getResources().getIdentifier("rpay", "drawable", context.getPackageName());
            }
        } else {
            identifier = this.cardScheme == CardScheme.DISCOVER ? context.getResources().getIdentifier("discover", "drawable", context.getPackageName()) : 0;
        }
        if (identifier != 0) {
            return context.getResources().getDrawable(identifier);
        }
        identifier = context.getResources().getIdentifier("default_card", "drawable", context.getPackageName());
        return identifier != 0 ? context.getResources().getDrawable(identifier) : null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("CardOption{");
        stringBuilder.append("cardHolderName='");
        stringBuilder.append(this.cardHolderName);
        stringBuilder.append('\'');
        stringBuilder.append(", cardNumber='");
        stringBuilder.append(this.cardNumber);
        stringBuilder.append('\'');
        stringBuilder.append(", cardCVV='");
        stringBuilder.append(this.cardCVV);
        stringBuilder.append('\'');
        stringBuilder.append(", cardExpiry='");
        stringBuilder.append(this.cardExpiry);
        stringBuilder.append('\'');
        stringBuilder.append(", cardExpiryMonth='");
        stringBuilder.append(this.cardExpiryMonth);
        stringBuilder.append('\'');
        stringBuilder.append(", cardExpiryYear='");
        stringBuilder.append(this.cardExpiryYear);
        stringBuilder.append('\'');
        stringBuilder.append(", cardScheme='");
        stringBuilder.append(this.cardScheme);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int getCVVLength() {
        return this.cardScheme == CardScheme.AMEX ? 4 : 3;
    }
}
