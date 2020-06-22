package batch.batches.multistep.bean;

import java.io.Serializable;

/**
 * 语音验证码和通知单条信息
 * @author jiangxb
 */
public class VoiceVernotMessage implements Serializable {
    private long id;
    private long customerId;
    private long voiceSmsAppId;
    private String phone;
    private int voiceType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getVoiceSmsAppId() {
        return voiceSmsAppId;
    }

    public void setVoiceSmsAppId(long voiceSmsAppId) {
        this.voiceSmsAppId = voiceSmsAppId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(int voiceType) {
        this.voiceType = voiceType;
    }
}
