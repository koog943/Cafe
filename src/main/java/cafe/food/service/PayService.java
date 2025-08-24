package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Member;
import cafe.food.request.PayApproveReq;
import cafe.food.request.PayReq;
import cafe.food.response.PayApproveRes;
import cafe.food.response.PayRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class PayService {
    @Autowired
    private ObjectMapper mapper;

    @Value("${Pay.cid}")
    private String cid;

    @Value("${Pay.key}")
    private String key;

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .build();

    public ResponseEntity<PayRes> callReadyApi(Member member, Food food, int count) throws JsonProcessingException {
        PayReq req = PayReq.builder()
                .partner_order_id("partner_order_id")
                .partner_user_id("partner_user_id")
                .item_name(food.getName())
                .quantity(count)
                .total_amount(food.getPrice() * count)
                .tax_free_amount(0)
                .approval_url("http://localhost:8080/payment/approval")
                .cancel_url("http://localhost:8080/payment/cancel")
                .fail_url("http://localhost:8080/payment/fail")
                .build();

        String json = mapper.writeValueAsString(req);

        ResponseEntity<PayRes> res = restClient.post()
                .uri("/online/v1/payment/ready")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "SECRET_KEY " + key)
                .body(json)
                .retrieve()
                .toEntity(PayRes.class);

        log.info("res={}", res);

        return res;
    }

    public ResponseEntity<PayApproveRes> callApproveApi(String pg_Token, String tid) throws JsonProcessingException {
        PayApproveReq approveReq = PayApproveReq.builder()
                .cid(cid)
                .tid(tid)
                .partner_order_id("partner_order_id")
                .partner_user_id("partner_user_id")
                .pg_token(pg_Token)
                .build();

        String json = mapper.writeValueAsString(approveReq);

        ResponseEntity<PayApproveRes> res = restClient.post()
                .uri("/online/v1/payment/approve")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "SECRET_KEY " + key)
                .body(json)
                .retrieve()
                .toEntity(PayApproveRes.class);

        return res;
    }
}
