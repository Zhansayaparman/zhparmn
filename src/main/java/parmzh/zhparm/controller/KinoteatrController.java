package parmzh.zhparm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import parmzh.zhparm.models.Info;
import parmzh.zhparm.models.Kinoteatr;
import parmzh.zhparm.repozitories.InfoRepozitory;
import parmzh.zhparm.repozitories.KinoteatrRepozitory;

@Controller
public class KinoteatrController {
    @Autowired
    private InfoRepozitory infoRepozitory;
    @Autowired
    private KinoteatrRepozitory kinoteatrRepozitory;


    @RequestMapping("/player/{id}")
    public String kinoteatr(@PathVariable("id")int id, Model model){
        model.addAttribute("kinoteatr",kinoteatrRepozitory.findById(id).get());
        model.addAttribute("infos",infoRepozitory.findAll());
        return "kinoteatr";
    }

    @RequestMapping(value = "/kinoteatrs",method = RequestMethod.GET)
    public String kinoteatrsList(Model model){
        model.addAttribute("kinoteatrs",kinoteatrRepozitory.findAll());
        return "kinoteatrs";
    }

    @RequestMapping(value = "/kinoteatrs",method = RequestMethod.POST)
    public String kinoteatrsAdd(@RequestParam String kinoteatr_name Model model){
        Kinoteatr newKinoteatr = new Kinoteatr( );
        newKinoteatr.setKinoteatr_name(kinoteatr_name);

      kinoteatrRepozitory.save(newKinoteatr);

        model.addAttribute("kinoteatr",newKinoteatr);
        model.addAttribute("infos",infoRepozitory.findAll());

        return "redirect:/kinoteatr/"+newKinoteatr.getId();
    }

    @RequestMapping(value = "kinoteatr/{id}/information", method = RequestMethod.POST)
    public String kinoteatrsAddInfo(@PathVariable Integer id,@RequestParam Integer infoId,
                                 @RequestParam Integer kinoteatrId, Model model){
        Info info = infoRepozitory.findById(infoId).get();
        Kinnoteatr= kinoteatrRepository.findById(kinoteatrId).get();


        if(kinoteatr!=null){
            if(!kinoteatr.hasInfo(info) & !kinoteatrhasKinoteatr(kinoteatr)){
               kinoteatr.getInfos().add(info);
                kinoteatr.getKinoteatrs().add(kinoteatr);
            }
            kinoteatrRepozitory.save(kinoteatr);
            model.addAttribute("kinoteatr",kinoteatrRepozitory.findById(id).get());
            model.addAttribute("infos",infoRepozitory.findAll());
            model.addAttribute("kinoteatrs",kinoteatrRepozitory.findAll());
            return "redirect:/kinoteatr/"+kinoteatr.getId();
        }
        model.addAttribute("kinoteatrs",kinoteatrRepozitory.findAll());
        return "redirect:/kinoteatrs";
    }

    @RequestMapping(value = "/infos",method = RequestMethod.GET)
    public String infosAdd(Model model){
        model.addAttribute("info", new Info());
        return "infos";
    }

    @RequestMapping(value = "/infos",method = RequestMethod.POST)
    public String infosAdd(@ModelAttribute Info info){
        infoRepozitory.save(info);
        return "infos";
    }

    @RequestMapping(value = "/teams",method = RequestMethod.GET)
    public String kinoteatrsAdd(Model model){
        model.addAttribute("kinoteatr", new Kinoteatr);
        return "kinoteatrs";
    }

    @RequestMapping(value = "/kinoteatrs",method = RequestMethod.POST)
    public String kinoteatrsAdd(@ModelAttribute Kinoteatr kinoteatr){
        kinoteatrRepozitory.save(kinoteatr);
        return "kinoteatrs";
    }

}