package com.zapphireye.zapphireye.helper;

import com.google.gson.Gson;
import com.zapphireye.zapphireye.model.database.Alert;
import com.zapphireye.zapphireye.model.database.Instance;
import com.zapphireye.zapphireye.model.database.Scan;
import com.zapphireye.zapphireye.model.database.Site;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONToObject {

    public static Scan parseListObject(JSONObject list) throws JSONException {
        Scan scan = new Scan();

        String date = (String) list.get("@generated");
        JSONArray siteObject = (JSONArray) list.get("site");
        JSONObject domainIdx = (JSONObject) siteObject.get(0);

        Site targetObject = new Gson().fromJson(domainIdx.toString(), Site.class);
        scan.setSite(targetObject);
        scan.setDate(date);

        return scan;
    }
}
