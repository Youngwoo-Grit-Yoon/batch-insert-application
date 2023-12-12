package com.example.demo.data;

import com.google.gson.JsonObject;
import lombok.Data;

import java.text.MessageFormat;

@Data
public class VdnData {
    private final String vdnNo;
    private final String monitor;
    private final String type;
    private final String split;
    private final String checkLink;
    private final String comment;
    private final String result;

    public VdnData(JsonObject jsonObject) {
        this.vdnNo = jsonObject.get("vdn_no").getAsString();
        this.monitor = jsonObject.get("monitor").getAsString();
        this.type = jsonObject.get("type").getAsString();
        this.split = jsonObject.get("split").getAsString();
        this.checkLink = jsonObject.get("check_link").getAsString();
        this.comment = jsonObject.get("comment").getAsString();
        this.result = jsonObject.get("result").getAsString();
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
      vdn_no     : {0}
      monitor    : {1}
      type       : {2}
      split      : {3}
      check_link : {4}
      comment    : {5}
      result     : {6}""", vdnNo, monitor, type, split, checkLink, comment, result);
    }
}
