package cafe.food.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PayRes {
    private final String tid;
    private final String next_redirect_app_url;
    private final String next_redirect_mobile_url;
    private final String next_redirect_pc_url;
    private final String android_app_scheme;
    private final String ios_app_scheme;
    private final String created_at;
}
