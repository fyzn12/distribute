package com.example.kubernetes_demo;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class KubernetesDemoApplicationTests {



    @Test
    void contextLoads() {
        System.out.println("HttpStatus.OK.toString() = " + HttpStatus.OK.value());
        System.out.println("HttpStatus.OK.toString() = " + HttpStatus.METHOD_FAILURE.getReasonPhrase());
//        CoreV1Api apiInstance  = new CoreV1Api();
//        try {
//            V1NamespaceList namespaceList = apiInstance.listNamespace(null, true, null, null,
//                    null, null, null, null, null, null);
//            System.out.println("namespaceList.toString() = " + namespaceList.toString());
//        }catch (Exception e){
//            
//        }
    }

}
