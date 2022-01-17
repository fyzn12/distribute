package com.example.kubernetes_demo.service;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/7 11:06
 * @description
 */
@Service
public class DeploymentServiceImpl implements DeploymentService{
    private static final Logger log = LoggerFactory.getLogger(DeploymentServiceImpl.class);

//    @Autowired
//    @Qualifier("kubernetesClient")
//    private KubernetesClient client;

    @Override
    public void createDeployment() {

    }
//    public V1Service createService(String namespace, String serviceName, Integer port, Map<String, String> selector) {
//        //构建service的yaml对象
//        V1Service svc = new V1ServiceBuilder()
//                .withApiVersion("v1")
//                .withKind("Service")
//                .withNewMetadata()
//                .withName(serviceName)
//                .withNamespace(namespace)
//                .endMetadata()
//                .withNewSpec()
//                .addNewPort()
//                .withProtocol("TCP")
//                .withPort(port)
//                .withTargetPort(new IntOrString(port))
//                .endPort()
//                .withSelector(selector)
//                .withType("clusterIP")
//                .endSpec()
//                .build();
//        System.out.println("svc.toString() = " + svc.toString());
//
//        // Deployment and StatefulSet is defined in apps/v1, so you should use AppsV1Api instead of CoreV1API
//        CoreV1Api api = new CoreV1Api(apiClient);
//        V1Service v1Service = null;
//        try {
//            v1Service = api.createNamespacedService(namespace, svc, null, null, null);
//        } catch (ApiException e) {
//            log.error("创建service异常:" + e.getResponseBody(), e);
//        } catch (Exception e) {
//            log.error("创建service系统异常:", e);
//        }
//        return v1Service;
//    }
}
