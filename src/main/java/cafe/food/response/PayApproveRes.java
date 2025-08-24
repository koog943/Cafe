package cafe.food.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PayApproveRes {
    private final String aid;
    private final String tid;
    private final String cid;
    private final String sid;
    private final String partner_order_id;
    private final String partner_user_id;
    private final String payment_method_type;
    private final Amount amount;
    private final CardInfo card_info;
    private final String item_name;
    private final String item_code;
    private final String quantity;
    private final String created_at;
    private final String approved_at;
    private final String payload;

    @RequiredArgsConstructor
    @Getter
    public static class Amount {
        private Integer totacl;
        private Integer tax_free;
        private Integer vat;
        private Integer point;
        private Integer discount;
        private Integer green_deposit;
    }

    @RequiredArgsConstructor
    @Getter
    public static class CardInfo {
        private String kakaopay_purchase_corp;
        private String kakaopay_purchase_corp_code;
        private String kakaopay_issuer_corp;
        private String kakaopay_issuer_corp_code;
        private String bin;
        private String card_type;
        private String install_month;
        private String approved_id;
        private String card_mid;
        private String interest_free_install;
        private String installment_type;
        private String card_item_code;
    }
}
