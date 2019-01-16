package com.wipro.mapper;

import com.wipro.data.db.model.FactsRow;
import com.wipro.data.db.model.FactsTitle;
import com.wipro.data.network.model.NetworkFactsModel;
import com.wipro.data.network.model.NetworkRowModel;
import com.wipro.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class ModelMapper {

    public static FactsDBModel convertNetworkToDBModel(NetworkFactsModel networkFactsModel) {
        int titleId = 1;

        List<FactsTitle> titles = new ArrayList<>();
        titles.add(getFactsTitle(titleId, networkFactsModel));

        FactsDBModel model = new FactsDBModel();
        model.setFactsRows(getRows(titleId, networkFactsModel.getRows()));
        model.setFactsTitles(titles);
        return model;

    }

    private static FactsTitle getFactsTitle(int titleId, NetworkFactsModel networkFactsModel) {
        FactsTitle factsTitle = new FactsTitle();
        factsTitle.setTitleId(titleId);
        factsTitle.setTitle(StringUtils.trim(networkFactsModel.getTitle()));
        return factsTitle;
    }

    private static List<FactsRow> getRows(int titleId, List<NetworkRowModel> rows) {
        List<FactsRow> rowList = new ArrayList<>();
        for (NetworkRowModel rowModel : rows) {
            FactsRow row = new FactsRow();
            if(rowModel.isEmpty()){
                continue;
            }
            row.setTitleId(titleId);
            row.setRowDescription(StringUtils.trim(rowModel.getDescription()));
            row.setRowImageUrl(StringUtils.trim(rowModel.getImageHref()));
            row.setRowTitle(StringUtils.trim(rowModel.getTitle()));
            rowList.add(row);
        }
        return rowList;
    }

    public static class FactsDBModel {
        private List<FactsTitle> factsTitles;
        private List<FactsRow> factsRows;

        public List<FactsTitle> getFactsTitles() {
            return factsTitles;
        }

        void setFactsTitles(List<FactsTitle> factsTitles) {
            this.factsTitles = factsTitles;
        }

        public List<FactsRow> getFactsRows() {
            return factsRows;
        }

        void setFactsRows(List<FactsRow> factsRows) {
            this.factsRows = factsRows;
        }
    }

}
