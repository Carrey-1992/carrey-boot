package com.carrey.consul.domain;

/**
 * @author Conway
 * @className IpModel
 * @description
 * @date 2021/4/7 6:00 下午
 */
public class IpModel {
    private String clientIpAddress;
    private String serverIpAddress;

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }
}
