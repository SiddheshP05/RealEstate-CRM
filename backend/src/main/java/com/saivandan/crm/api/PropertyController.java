package com.saivandan.crm.api;
import com.saivandan.crm.domain.*; import com.saivandan.crm.repo.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/units") public class PropertyController { private final PropertyUnitRepository units; public PropertyController(PropertyUnitRepository units){this.units=units;} @GetMapping public List<PropertyUnit> list(){return units.findAllByOrderByUnitNumberAsc();} }
