package parmzh.zhparm.repozitories;

import org.springframework.data.repository.CrudRepository;
import parmzh.zhparm.models.Info;

public interface InfoRepozitory  extends CrudRepository<Info, Integer> {
}