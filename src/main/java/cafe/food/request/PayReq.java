package cafe.food.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class PayReq {
    private final String cid = "TC0ONETIME";
    private final String partner_order_id;
    private final String partner_user_id;
    private final String item_name;
    private final Integer quantity;
    private final Integer total_amount;
    private final Integer tax_free_amount;
    private final String approval_url;
    private final String cancel_url;
    private final String fail_url;
}
