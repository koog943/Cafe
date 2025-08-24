package cafe.food.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class PayApproveReq {
    private final String cid;
    private final String tid;
    private final String partner_order_id;
    private final String partner_user_id;
    private final String pg_token;
}
