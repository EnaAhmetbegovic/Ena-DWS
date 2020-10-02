package org.igman92.igman92.entity.impl.lkp.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.*;

@Entity
@Table(name = "LKP_recognition")
public class LKPRecognition extends LookUpEntity {

    @ManyToOne
    private LKPCategory category;

    @ManyToOne
    @JoinColumn(name = "LKP_recognition_type")
    @NotNull
    private LKPRecognitionType recognitionType;

    @Column(name = "personal")
    private Integer personal;

    public LKPCategory getCategory() {
        return category;
    }

    public void setCategory(LKPCategory category) {
        this.category = category;
    }

    public LKPRecognitionType getRecognitionType() {
        return recognitionType;
    }

    public void setRecognitionType(LKPRecognitionType recognitionType) {
        this.recognitionType = recognitionType;
    }
}
