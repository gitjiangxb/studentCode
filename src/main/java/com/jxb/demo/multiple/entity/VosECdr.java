package com.jxb.demo.multiple.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "e_cdr")
public class VosECdr {
    private int id;
    private String callere164;
    private String calleraccesse164;
    private String calleee164;
    private String calleeaccesse164;
    private String callerip;
    private String callerrtpip;
    private String callercodec;
    private String callergatewayid;
    private String callerproductid;
    private String callertogatewaye164;
    private String callertype;
    private String calleeip;
    private String calleertpip;
    private String calleecodec;
    private String calleegatewayid;
    private String calleeproductid;
    private String calleetogatewaye164;
    private int calleetype;
    private int billingmode;
    private int calllevel;
    private int agentfeetime;
    private long starttime;
    private long stoptime;
    private int callerpdd;
    private int holdtime;
    private String callerareacode;
    private int feetime;
    private double fee;
    private double tax;
    private double suitefee;
    private int suitefeetime;
    private double incomefee;
    private double incometax;
    private String customeraccount;
    private String customername;
    private String calleeareacode;
    private double agentfee;
    private double agenttax;
    private double agentsuitefee;
    private int agentsuitefeetime;
    private String agentaccount;
    private String agentname;
    private long flowno;
    private String softswitchname;
    private String callercallid;
    private String calleroriginalcallid;
    private String calleecallid;
    private String calleroriginalinfo;
    private int rtpforward;
    private int enddirection;
    private int endreason;
    private int billingtype;
    private int cdrlevel;
    private int agentcdrId;
    private String sipreasonheader;
    @TableField(exist = false)
    private int newColumn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallere164() {
        return callere164;
    }

    public void setCallere164(String callere164) {
        this.callere164 = callere164;
    }

    public String getCalleraccesse164() {
        return calleraccesse164;
    }

    public void setCalleraccesse164(String calleraccesse164) {
        this.calleraccesse164 = calleraccesse164;
    }

    public String getCalleee164() {
        return calleee164;
    }

    public void setCalleee164(String calleee164) {
        this.calleee164 = calleee164;
    }

    public String getCalleeaccesse164() {
        return calleeaccesse164;
    }

    public void setCalleeaccesse164(String calleeaccesse164) {
        this.calleeaccesse164 = calleeaccesse164;
    }

    public String getCallerip() {
        return callerip;
    }

    public void setCallerip(String callerip) {
        this.callerip = callerip;
    }

    public String getCallerrtpip() {
        return callerrtpip;
    }

    public void setCallerrtpip(String callerrtpip) {
        this.callerrtpip = callerrtpip;
    }

    public String getCallercodec() {
        return callercodec;
    }

    public void setCallercodec(String callercodec) {
        this.callercodec = callercodec;
    }

    public String getCallergatewayid() {
        return callergatewayid;
    }

    public void setCallergatewayid(String callergatewayid) {
        this.callergatewayid = callergatewayid;
    }

    public String getCallerproductid() {
        return callerproductid;
    }

    public void setCallerproductid(String callerproductid) {
        this.callerproductid = callerproductid;
    }

    public String getCallertogatewaye164() {
        return callertogatewaye164;
    }

    public void setCallertogatewaye164(String callertogatewaye164) {
        this.callertogatewaye164 = callertogatewaye164;
    }

    public String getCallertype() {
        return callertype;
    }

    public void setCallertype(String callertype) {
        this.callertype = callertype;
    }

    public String getCalleeip() {
        return calleeip;
    }

    public void setCalleeip(String calleeip) {
        this.calleeip = calleeip;
    }

    public String getCalleertpip() {
        return calleertpip;
    }

    public void setCalleertpip(String calleertpip) {
        this.calleertpip = calleertpip;
    }

    public String getCalleecodec() {
        return calleecodec;
    }

    public void setCalleecodec(String calleecodec) {
        this.calleecodec = calleecodec;
    }

    public String getCalleegatewayid() {
        return calleegatewayid;
    }

    public void setCalleegatewayid(String calleegatewayid) {
        this.calleegatewayid = calleegatewayid;
    }

    public String getCalleeproductid() {
        return calleeproductid;
    }

    public void setCalleeproductid(String calleeproductid) {
        this.calleeproductid = calleeproductid;
    }

    public String getCalleetogatewaye164() {
        return calleetogatewaye164;
    }

    public void setCalleetogatewaye164(String calleetogatewaye164) {
        this.calleetogatewaye164 = calleetogatewaye164;
    }

    public int getCalleetype() {
        return calleetype;
    }

    public void setCalleetype(int calleetype) {
        this.calleetype = calleetype;
    }

    public int getBillingmode() {
        return billingmode;
    }

    public void setBillingmode(int billingmode) {
        this.billingmode = billingmode;
    }

    public int getCalllevel() {
        return calllevel;
    }

    public void setCalllevel(int calllevel) {
        this.calllevel = calllevel;
    }

    public int getAgentfeetime() {
        return agentfeetime;
    }

    public void setAgentfeetime(int agentfeetime) {
        this.agentfeetime = agentfeetime;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getStoptime() {
        return stoptime;
    }

    public void setStoptime(long stoptime) {
        this.stoptime = stoptime;
    }

    public int getCallerpdd() {
        return callerpdd;
    }

    public void setCallerpdd(int callerpdd) {
        this.callerpdd = callerpdd;
    }

    public int getHoldtime() {
        return holdtime;
    }

    public void setHoldtime(int holdtime) {
        this.holdtime = holdtime;
    }

    public String getCallerareacode() {
        return callerareacode;
    }

    public void setCallerareacode(String callerareacode) {
        this.callerareacode = callerareacode;
    }

    public int getFeetime() {
        return feetime;
    }

    public void setFeetime(int feetime) {
        this.feetime = feetime;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSuitefee() {
        return suitefee;
    }

    public void setSuitefee(double suitefee) {
        this.suitefee = suitefee;
    }

    public int getSuitefeetime() {
        return suitefeetime;
    }

    public void setSuitefeetime(int suitefeetime) {
        this.suitefeetime = suitefeetime;
    }

    public double getIncomefee() {
        return incomefee;
    }

    public void setIncomefee(double incomefee) {
        this.incomefee = incomefee;
    }

    public double getIncometax() {
        return incometax;
    }

    public void setIncometax(double incometax) {
        this.incometax = incometax;
    }

    public String getCustomeraccount() {
        return customeraccount;
    }

    public void setCustomeraccount(String customeraccount) {
        this.customeraccount = customeraccount;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCalleeareacode() {
        return calleeareacode;
    }

    public void setCalleeareacode(String calleeareacode) {
        this.calleeareacode = calleeareacode;
    }

    public double getAgentfee() {
        return agentfee;
    }

    public void setAgentfee(double agentfee) {
        this.agentfee = agentfee;
    }

    public double getAgenttax() {
        return agenttax;
    }

    public void setAgenttax(double agenttax) {
        this.agenttax = agenttax;
    }

    public double getAgentsuitefee() {
        return agentsuitefee;
    }

    public void setAgentsuitefee(double agentsuitefee) {
        this.agentsuitefee = agentsuitefee;
    }

    public int getAgentsuitefeetime() {
        return agentsuitefeetime;
    }

    public void setAgentsuitefeetime(int agentsuitefeetime) {
        this.agentsuitefeetime = agentsuitefeetime;
    }

    public String getAgentaccount() {
        return agentaccount;
    }

    public void setAgentaccount(String agentaccount) {
        this.agentaccount = agentaccount;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public long getFlowno() {
        return flowno;
    }

    public void setFlowno(long flowno) {
        this.flowno = flowno;
    }

    public String getSoftswitchname() {
        return softswitchname;
    }

    public void setSoftswitchname(String softswitchname) {
        this.softswitchname = softswitchname;
    }

    public String getCallercallid() {
        return callercallid;
    }

    public void setCallercallid(String callercallid) {
        this.callercallid = callercallid;
    }

    public String getCalleroriginalcallid() {
        return calleroriginalcallid;
    }

    public void setCalleroriginalcallid(String calleroriginalcallid) {
        this.calleroriginalcallid = calleroriginalcallid;
    }

    public String getCalleecallid() {
        return calleecallid;
    }

    public void setCalleecallid(String calleecallid) {
        this.calleecallid = calleecallid;
    }

    public String getCalleroriginalinfo() {
        return calleroriginalinfo;
    }

    public void setCalleroriginalinfo(String calleroriginalinfo) {
        this.calleroriginalinfo = calleroriginalinfo;
    }

    public int getRtpforward() {
        return rtpforward;
    }

    public void setRtpforward(int rtpforward) {
        this.rtpforward = rtpforward;
    }

    public int getEnddirection() {
        return enddirection;
    }

    public void setEnddirection(int enddirection) {
        this.enddirection = enddirection;
    }

    public int getEndreason() {
        return endreason;
    }

    public void setEndreason(int endreason) {
        this.endreason = endreason;
    }

    public int getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(int billingtype) {
        this.billingtype = billingtype;
    }

    public int getCdrlevel() {
        return cdrlevel;
    }

    public void setCdrlevel(int cdrlevel) {
        this.cdrlevel = cdrlevel;
    }

    public int getAgentcdrId() {
        return agentcdrId;
    }

    public void setAgentcdrId(int agentcdrId) {
        this.agentcdrId = agentcdrId;
    }

    public String getSipreasonheader() {
        return sipreasonheader;
    }

    public void setSipreasonheader(String sipreasonheader) {
        this.sipreasonheader = sipreasonheader;
    }

    public int getNewColumn() {
        return newColumn;
    }

    public void setNewColumn(int newColumn) {
        this.newColumn = newColumn;
    }
}
