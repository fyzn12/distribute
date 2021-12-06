package com.example.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/3 13:27
 * @description
 */
@Component
public class JwtUtils {

    @Value("${jwt.token.secret}")
    private  String SECRET_KEY;

    @Value("${jwt.token.expireTime}")
    private long TOKEN_EXPIRE_TIME;


    private long EXPIRE_TIME =  TOKEN_EXPIRE_TIME * 1000;


    /**
     * 生成jwt
     */
    public String createJwt(String userId){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 生成 token：头部+载荷+签名
        return JWT.create().withHeader(header)
                .withClaim("Token",userId)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME)).sign(algorithm);
    }

    /**
     * 解析jwt
     */
    public Map<String, Claim> parseJwt(String token) {
        Map<String, Claim> claims = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(token);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            claims = jwt.getClaims();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }


}
