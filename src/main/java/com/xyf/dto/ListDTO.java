package com.xyf.dto;

import java.util.List;

public class ListDTO {

    private List<InitCreateCardDTO> initCreateCardList;
    private List<InitPartnerDTO> initPartnerList;
    private List<UseCardDTO> useCardList;

    public List<UseCardDTO> getUseCardList() {
        return useCardList;
    }

    public void setUseCardList(List<UseCardDTO> useCardList) {
        this.useCardList = useCardList;
    }

    public List<InitCreateCardDTO> getInitCreateCardList() {
        return initCreateCardList;
    }

    public void setInitCreateCardList(List<InitCreateCardDTO> initCreateCardList) {
        this.initCreateCardList = initCreateCardList;
    }

    public List<InitPartnerDTO> getInitPartnerList() {
        return initPartnerList;
    }

    public void setInitPartnerList(List<InitPartnerDTO> initPartnerList) {
        this.initPartnerList = initPartnerList;
    }
}
