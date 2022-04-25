package funproject.virus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import funproject.virus.model.VirusInfo;
import funproject.virus.service.VirusService;

@Controller
public class VirusController {

    @Autowired
    private VirusService virusService;

    @GetMapping("/")
    public String home(Model model){
        List<VirusInfo> allStats = virusService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stats -> stats.getTotalCases()).sum();
        model.addAttribute("virusInfo", virusService.getAllStats());
        model.addAttribute("totalCases", totalCases);
        return "home";
    }
}
