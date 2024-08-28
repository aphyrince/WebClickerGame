package controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import model.Log;
import repository.LogRepository;



@Controller
public class MainController {

    private final LogRepository logRepository;

    public MainController(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    @GetMapping("/")
    public String getMain() {
        return "main.html";
    }

    @GetMapping("/log")
    @ResponseBody
    public List<Log> getLastTenLog() {
        return logRepository.getLastTenLogs();
    }
    
    
    @PostMapping("/")
    @ResponseBody
    public void clicked(@RequestParam String id) {
        Log logObject = new Log();
        logObject.setId(id);
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if(ip == null)
            ip = req.getRemoteAddr();
        logObject.setIp(ip);
        logObject.setDate(LocalDate.now());

        logRepository.storeLog(logObject);
    }
    
}
