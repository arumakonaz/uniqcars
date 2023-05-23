package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "UNIQCARS_PARTNER")
@Entity(name = "uniqcars_Partner")
@NamePattern("%s|name")
public class Partner extends StandardEntity {
    private static final long serialVersionUID = -7293323606570846376L;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    private FileDescriptor image;

    @Column(name = "YEAR_")
    private Integer year;

    @Column(name = "LOCATION")
    private String location;

    @OneToMany(mappedBy = "partner")
    private List<ModelRange> modelRangeList;

    @OneToMany(mappedBy = "partner")
    private List<Media> mediaList;

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public List<ModelRange> getModelRangeList() {
        return modelRangeList;
    }

    public void setModelRangeList(List<ModelRange> modelRangeList) {
        this.modelRangeList = modelRangeList;
    }

    public FileDescriptor getImage() {
        return image;
    }

    public void setImage(FileDescriptor image) {
        this.image = image;
    }

    public Location getLocation() {
        return location == null ? null : Location.fromId(location);
    }

    public void setLocation(Location location) {
        this.location = location == null ? null : location.getId();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}