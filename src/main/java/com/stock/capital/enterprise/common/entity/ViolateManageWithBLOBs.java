package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;

public class ViolateManageWithBLOBs extends ViolateManage implements Serializable {
    private String content;

    private String violateLaws;

    private String violateLawsClause;

    private String unscramble;

    private String avermentView;

    private String avermentReply;

    private static final long serialVersionUID = 1L;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getViolateLaws() {
        return violateLaws;
    }

    public void setViolateLaws(String violateLaws) {
        this.violateLaws = violateLaws == null ? null : violateLaws.trim();
    }

    public String getViolateLawsClause() {
        return violateLawsClause;
    }

    public void setViolateLawsClause(String violateLawsClause) {
        this.violateLawsClause = violateLawsClause == null ? null : violateLawsClause.trim();
    }

    public String getUnscramble() {
        return unscramble;
    }

    public void setUnscramble(String unscramble) {
        this.unscramble = unscramble == null ? null : unscramble.trim();
    }

    public String getAvermentView() {
        return avermentView;
    }

    public void setAvermentView(String avermentView) {
        this.avermentView = avermentView == null ? null : avermentView.trim();
    }

    public String getAvermentReply() {
        return avermentReply;
    }

    public void setAvermentReply(String avermentReply) {
        this.avermentReply = avermentReply == null ? null : avermentReply.trim();
    }
}