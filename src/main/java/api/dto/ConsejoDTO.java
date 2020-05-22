package api.dto;

import java.util.Date;

public class ConsejoDTO {


    private String consejo;

    public ConsejoDTO() {
    }

    public ConsejoDTO(String consejo) {
        this.consejo = consejo;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }
}

