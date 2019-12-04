package com.artur.engineer.payload.user;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class UserBlockPayload {

    protected String lockReason;

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }
}
