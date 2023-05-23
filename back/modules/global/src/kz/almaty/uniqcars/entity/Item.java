package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "UNIQCARS_ITEM")
@Entity(name = "uniqcars_Item")
@NamePattern("%s | name,nameRu,nameKz,nameEn")
public class Item extends AbstractEntity {
    private static final long serialVersionUID = 5881437559160540835L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ITEM_TYPE_ID")
    @OnDeleteInverse(DeletePolicy.DENY)
    private DicItemType itemType;

    @JoinTable(name = "UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    private List<FileDescriptor> images;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "item")
    private List<AdditionalData> additionalDatas;

    public List<AdditionalData> getAdditionalDatas() {
        return additionalDatas;
    }

    public void setAdditionalDatas(List<AdditionalData> additionalDatas) {
        this.additionalDatas = additionalDatas;
    }

    public List<FileDescriptor> getImages() {
        return images;
    }

    public void setImages(List<FileDescriptor> images) {
        this.images = images;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DicItemType getItemType() {
        return itemType;
    }

    public void setItemType(DicItemType itemType) {
        this.itemType = itemType;
    }
}