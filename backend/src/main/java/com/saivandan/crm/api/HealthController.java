package com.saivandan.crm.api;
import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/health") public class HealthController { @GetMapping public Map<String,String> health(){return Map.of("status","ok","service","sai-vandan-crm-api");} }
