package com.gaana.cardoption;

import android.text.TextUtils;

public class CardSchemeIdentifier {

    public enum CardScheme {
        VISA {
            public String getPattern() {
                return "^4\\d{5}";
            }
        },
        MASTER_CARD {
            public String getPattern() {
                return "^(5[1-5]\\d{4}|222[1-9]\\d{2}|22[3-9]\\d{3}|23\\d{4}|24\\d{4}|25\\d{4}|26\\d{4}|27[0-1]\\d{3}|272000|2720[0-9][0-9])";
            }
        },
        RPAY {
            public String getPattern() {
                return "^(508[5-9][0-9][0-9]|60698[5-9]|60699[0-9]|607[0-8][0-9][0-9]|607[9][0-7][0-9]|60798[0-4]|608[0-4][0-9][0-9]|608100|608500|6521[5-9][0-9]|652[2-7][0-9][0-9]|6528[0-9][0-9]|6529[0-9][0-9]|6530[0-9][0-9]|6531[0-4][0-9])";
            }
        },
        MAESTRO {
            public String getPattern() {
                return "^(5[0678]\\d{4}|6304\\d{2}|6390\\d{2}|6220\\d{2}|6002\\d{2}|603\\d{3}|67\\d{4})";
            }
        },
        DINERS {
            public String getPattern() {
                return "^(36\\d{4}|38\\d{4}|39\\d{4}|30[0-5]\\d{3})";
            }
        },
        JCB {
            public String getPattern() {
                return "^35\\d{4}";
            }
        },
        AMEX {
            public String getPattern() {
                return "^3[47]\\d{4}";
            }
        },
        DISCOVER {
            public String getPattern() {
                return "^(6011\\d{2}|65\\d{4}|64[4-9]\\d{3}|622\\d{3})";
            }
        },
        UNKNOWN {
            public String getPattern() {
                return "";
            }
        };

        public abstract String getPattern();

        public static com.gaana.cardoption.CardOption.CardScheme getCardSchemeUsingNumber(String str) {
            CardScheme cardScheme = UNKNOWN;
            com.gaana.cardoption.CardOption.CardScheme cardScheme2 = com.gaana.cardoption.CardOption.CardScheme.UNKNOWN;
            if (TextUtils.isEmpty(str) || str.length() < 6) {
                return cardScheme2;
            }
            int i = 0;
            str = str.substring(0, 6);
            CardScheme[] values = values();
            int length = values.length;
            while (i < length) {
                CardScheme cardScheme3 = values[i];
                if (str.matches(cardScheme3.getPattern())) {
                    cardScheme = cardScheme3;
                    break;
                }
                i++;
            }
            switch (cardScheme) {
                case VISA:
                    return com.gaana.cardoption.CardOption.CardScheme.VISA;
                case MASTER_CARD:
                    return com.gaana.cardoption.CardOption.CardScheme.MASTER_CARD;
                case MAESTRO:
                    return com.gaana.cardoption.CardOption.CardScheme.MAESTRO;
                case DINERS:
                    return com.gaana.cardoption.CardOption.CardScheme.DINERS;
                case JCB:
                    return com.gaana.cardoption.CardOption.CardScheme.JCB;
                case AMEX:
                    return com.gaana.cardoption.CardOption.CardScheme.AMEX;
                case RPAY:
                    return com.gaana.cardoption.CardOption.CardScheme.RPAY;
                case DISCOVER:
                    return com.gaana.cardoption.CardOption.CardScheme.DISCOVER;
                case UNKNOWN:
                    return com.gaana.cardoption.CardOption.CardScheme.UNKNOWN;
                default:
                    return cardScheme2;
            }
        }
    }
}
