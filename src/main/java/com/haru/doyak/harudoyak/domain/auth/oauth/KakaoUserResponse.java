package com.haru.doyak.harudoyak.domain.auth.oauth;

import lombok.Data;


@Data
public class KakaoUserResponse {
    private Long id;
    private Kakao_account kakao_account;

    @Data
    public static class Kakao_account{
        private String email;
        private String name;
        private Profile profile;

        @Data
        public static class Profile{
            private String nickname;
            private String thumbnail_image_url;
        }
    }
}
