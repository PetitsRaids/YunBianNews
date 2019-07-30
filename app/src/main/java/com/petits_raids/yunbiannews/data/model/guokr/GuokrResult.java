package com.petits_raids.yunbiannews.data.model.guokr;

import java.util.List;

public class GuokrResult {

    private boolean ok;

    private List<GuokrItem> result;

    public List<GuokrItem> getResult() {
        return result;
    }

    public void setResult(List<GuokrItem> result) {
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}
