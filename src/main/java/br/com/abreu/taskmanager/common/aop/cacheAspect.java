package br.com.abreu.taskmanager.common.aop;

import br.com.abreu.taskmanager.api.dto.HeaderInfo;
import br.com.abreu.taskmanager.api.dto.IdObjeto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Component
@Aspect
public class cacheAspect {

    @Around("@annotation(br.com.abreu.taskmanager.common.aop.CacheAnnotation)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args1 = {"param1", 42, new int[]{1, 2, 3}, new HeaderInfo("123456", UUID.randomUUID(), 987654321L), new IdObjeto("123456", UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), 987654321L)};
        Object[] args2 = {"param1", 42, new int[]{1, 2, 3}, new HeaderInfo("1", UUID.randomUUID(), 98721L), new IdObjeto("123456", UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), 987654321L)};

        String hash1 = gerarKey(args1);
        String hash2 = gerarKey(args2);

        System.out.println("Hash gerado: " + hash1);
        System.out.println(hash1.equals(hash2));

        Object object = joinPoint.proceed();

        return object;
    }

    private String gerarKey(Object[] args) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Exclui instâncias de HeaderInfo
        Object[] filteredArgs = Arrays.stream(args)
                .filter(arg -> !(arg instanceof HeaderInfo))
                .filter(Objects::nonNull) // Evita nulls
                .toArray();

        return gerarHash(objectMapper.writeValueAsString(filteredArgs));
    }

    private String gerarHash(String  input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}