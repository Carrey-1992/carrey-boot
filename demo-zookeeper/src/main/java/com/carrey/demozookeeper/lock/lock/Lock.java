package com.carrey.demozookeeper.lock.lock;

public class Lock {
    private String lockId;
    private String lockPath;
    private String path;
    private boolean active;
    public Lock(String lockId,String lockPath, String path) {
        this.lockId = lockId;
        this.path = path;
        this.lockPath = lockPath;
    }

    public Lock() {
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLockPath() {
        return lockPath;
    }

    public void setLockPath(String lockPath) {
        this.lockPath = lockPath;
    }
}