
package com.wipro.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkFactsModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<NetworkRowModel> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NetworkRowModel> getRows() {
        return rows;
    }

    public void setRows(List<NetworkRowModel> rows) {
        this.rows = rows;
    }

}
