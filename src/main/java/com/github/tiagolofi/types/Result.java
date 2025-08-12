package com.github.tiagolofi.types;

import java.util.List;

public class Result {
    private boolean ok;
    private List<Update> result;

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean getOk() {
        return ok;
    }

    public List<Update> getResult() {
        return result;
    }

    public void setResult(List<Update> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
