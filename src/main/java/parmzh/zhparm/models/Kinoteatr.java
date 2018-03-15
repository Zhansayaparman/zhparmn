package parmzh.zhparm.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Kinoteatr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String kinoteatr_name;

    @OneToMany
    private List<Info> infos;

    public Kinoteatr(int id, String kinoteatr_name, List<Info> infos) {
        this.id = id;
        this.kinoteatr_name = kinoteatr_name;

        this.infos = infos;

    }

    public Kinoteatr(String kinoteatr_name, List<Info> infos) {
        this.kinoteatr_name = kinoteatr_name;

        this.infos = infos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKinoteatr_name() {
        return kinoteatr_name;
    }

    public void setKinoteatr_name(String kinoteatr_name) {
        this.kinoteatr_name = kinoteatr_name;
    }


    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }


    public boolean hasInfo(Info info) {
        for (Info containedSkill : getInfos()) {
            if (containedSkill.getId() == info.getId()) {
                return true;
            }
        }
        return false;
    }

}