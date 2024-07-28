package org.cb.authn.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.authn.clients.Wso2IamClient;
import org.cb.authn.constants.MessageCodes;
import org.cb.authn.datars.Wso2TokenDataRs;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.rs.Wso2TokenRs;
import org.cb.authn.service.IAuthenticationService;
import org.cb.commons.base.datars.BaseDataRs;
import org.cb.utils.Utils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final Wso2IamClient wso2IamClient;

    private final Messages messages;

    private final RedisTemplate<String, String> redisTemplate;

    private HashOperations<String, String, String> hashOperations = null;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public BaseDataRs token(AuthenticationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing token(AuthenticationRq) -> ");
        }
        try {
            String username = Utils.getValidString(rq.getUsername());
            String password = Utils.getValidString(rq.getPassword());
            Wso2TokenRs tokenRs = wso2IamClient.getAuthToekn(username, password);
            log.info("Access Token : {}", tokenRs.getAccessToken());
            System.out.println("Access Token : " + tokenRs.getAccessToken());
            Optional.ofNullable(tokenRs).filter(token -> Utils.isNotEmpty(token.getAccessToken()))
                            .ifPresent(token -> {
                                /**
                                 * Make a get loggedIn User Info call and take email for the user
                                 */
                                //                                HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
                                hashOperations.put(token.getAccessToken(), token.getAccessToken(),
                                                "dhruv.rbs.java@gmail.com");
                                hashOperations.put("dhruv.rbs.java@gmail.com",
                                                token.getAccessToken(),
                                                String.valueOf(token.getExpiresTime()));
                            });
            String message =
                            messages.getMessageProperties(MessageCodes.MC_AUTHENTICATE_SUCCESSFULL);
            return new Wso2TokenDataRs(message, tokenRs);
        } catch (Exception e) {
            log.error("Exception in token(AuthenticationRq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs logout(String accessToken) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing logout(accessToken) -> "));
        try {
            String accessTokenNonBearer = accessToken.replace("Bearer ", "");
            String user = hashOperations.get(accessTokenNonBearer, accessTokenNonBearer);
            Optional.ofNullable(user).filter(Utils::isNotEmpty).ifPresent(usr -> {
                hashOperations.delete(user, accessTokenNonBearer);
                hashOperations.delete(accessTokenNonBearer, accessTokenNonBearer);
            });
            String message = messages.getMessageProperties(MessageCodes.MC_LOGOUT_SUCCESSFULL);
            return new BaseDataRs(message);
        } catch (Exception e) {
            log.error("Exception in logout(accessToken) -> ");
            throw e;
        }
    }
}
