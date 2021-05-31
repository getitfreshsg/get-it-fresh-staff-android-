package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class LanguageModel extends BaseModel implements Serializable {
    private String _id, language_name, language_code, flag;
    private List<LanguageModel> result;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<LanguageModel> getResult() {
        return result;
    }

    public void setResult(List<LanguageModel> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return language_name;
    }
}
